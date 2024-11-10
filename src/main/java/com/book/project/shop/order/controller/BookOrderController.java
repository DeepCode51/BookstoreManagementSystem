package com.book.project.shop.order.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.book.common.utils.security.ShiroUtils;
import com.book.project.shop.car.domain.BookCar;
import com.book.project.shop.car.service.IBookCarService;
import com.book.project.shop.info.domain.BookInfo;
import com.book.project.shop.info.service.IBookInfoService;
import com.book.project.system.role.domain.Role;
import com.book.project.system.user.domain.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.book.framework.aspectj.lang.annotation.Log;
import com.book.framework.aspectj.lang.enums.BusinessType;
import com.book.project.shop.order.domain.BookOrder;
import com.book.project.shop.order.service.IBookOrderService;
import com.book.framework.web.controller.BaseController;
import com.book.framework.web.domain.AjaxResult;
import com.book.common.utils.poi.ExcelUtil;
import com.book.framework.web.page.TableDataInfo;

/**
 * 订单Controller
 *
 * @author book
 * @date 2022-05-08
 */
@Controller
@RequestMapping("/shop/order")
public class BookOrderController extends BaseController
{
    private String prefix = "shop/order";

    @Autowired
    private IBookOrderService bookOrderService;

    @Autowired
    private IBookCarService bookCarService;

    @Autowired
    private IBookInfoService bookInfoService;

    @RequiresPermissions("shop:order:view")
    @GetMapping()
    public String order(ModelMap mmap)
    {
        // 取身份信息
        User user = getSysUser();
        mmap.put("user", user);
        return prefix + "/order";
    }

    /**
     * 查询订单列表
     */
    @RequiresPermissions("shop:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookOrder bookOrder)
    {
        startPage();

        User sysUser = ShiroUtils.getSysUser();
        Role role = sysUser.getRoles().get(0);
        Long roleId = role.getRoleId();
        if(roleId == 100){
            bookOrder.setBuyerId(sysUser.getUserId());
        }

        List<BookOrder> list = bookOrderService.selectBookOrderList(bookOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @RequiresPermissions("shop:order:export")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookOrder bookOrder)
    {
        List<BookOrder> list = bookOrderService.selectBookOrderList(bookOrder);
        ExcelUtil<BookOrder> util = new ExcelUtil<BookOrder>(BookOrder.class);
        return util.exportExcel(list, "订单数据");
    }

    /**
     * 新增订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单
     */
    @RequiresPermissions("shop:order:add")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookOrder bookOrder)
    {
        //时间（精确到毫秒）
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String localDate = LocalDateTime.now().format(ofPattern);
        //随机数
        String randomNumeric = RandomStringUtils.randomNumeric(8);
        bookOrder.setOrderNo(localDate+randomNumeric);
        bookOrder.setState(1);
        return toAjax(bookOrderService.insertBookOrder(bookOrder));
    }

    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/buy")
    @ResponseBody
    public AjaxResult buy(String ids)
    {
        User sysUser = ShiroUtils.getSysUser();
        Long userId = sysUser.getUserId();
        String userName = sysUser.getUserName();
        Role role = sysUser.getRoles().get(0);
        Long roleId = role.getRoleId();
        if(roleId != 100){
            return AjaxResult.warn("非买家角色无法购买！");
        }

        String[] split = ids.split(",");
        for(String cid : split){
            // 根据id查询出购物车里的商品，并且更新商品状态
            BookCar bookCar = bookCarService.selectBookCarById(Long.parseLong(cid));
            bookCar.setState(2l);
            bookCarService.updateBookCar(bookCar);
            Long bookId = bookCar.getBookId();


            // 将商品插入订单表
            BookOrder bookOrder = new BookOrder();
            bookOrder.setBookId(bookCar.getBookId());
            bookOrder.setBookName(bookCar.getBookName());
            bookOrder.setBuyer(userName);
            bookOrder.setBuyerId(userId);
            bookOrder.setSaleTime(new Date());
            bookOrder.setSaleNum((long)bookCar.getNum());
            bookOrder.setSalePrice(bookCar.getPrice());
            //时间（精确到毫秒）
            DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
            String localDate = LocalDateTime.now().format(ofPattern);
            //随机数
            String randomNumeric = RandomStringUtils.randomNumeric(8);
            bookOrder.setOrderNo(localDate+randomNumeric);
            bookOrder.setState(1);
            bookOrderService.insertBookOrder(bookOrder);

            // 更新书籍表里已售数量
            BookInfo info = bookInfoService.selectBookInfoById(bookId);
            Long saleNum = info.getSaleNum();
            BookInfo bookInfo = new BookInfo();
            bookInfo.setId(bookId);
            long l = saleNum + (long) bookCar.getNum();
            if(l > info.getStockNum()){
                return AjaxResult.error("请勿超出库存数量！");
            }
            bookInfo.setSaleNum(l);
            bookInfoService.updateBookInfo(bookInfo);
        }

        return AjaxResult.success("购买成功！");
    }

    @Log(title = "订单", businessType = BusinessType.INSERT)
    @GetMapping("/buyOne")
    @ResponseBody
    public AjaxResult buyOne(Long bookId,String bookName,String price)
    {

        User sysUser = ShiroUtils.getSysUser();
        Long userId = sysUser.getUserId();
        String userName = sysUser.getUserName();
        Role role = sysUser.getRoles().get(0);
        Long roleId = role.getRoleId();
        if(roleId != 100){
            return AjaxResult.warn("非买家角色无法购买！");
        }

        // 将商品插入订单表
        BookOrder bookOrder = new BookOrder();
        bookOrder.setBookId(bookId);
        bookOrder.setBookName(bookName);
        bookOrder.setBuyer(userName);
        bookOrder.setBuyerId(userId);
        bookOrder.setSaleTime(new Date());
        bookOrder.setSaleNum(1l);
        bookOrder.setSalePrice(price);
        //时间（精确到毫秒）
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String localDate = LocalDateTime.now().format(ofPattern);
        //随机数
        String randomNumeric = RandomStringUtils.randomNumeric(8);
        bookOrder.setOrderNo(localDate+randomNumeric);
        bookOrder.setState(1);
        bookOrderService.insertBookOrder(bookOrder);

        // 更新书籍表里已售数量
        BookInfo info = bookInfoService.selectBookInfoById(bookId);
        Long saleNum = info.getSaleNum();
        BookInfo bookInfo = new BookInfo();
        bookInfo.setId(bookId);
        long l = saleNum + 1;
        if(l > info.getStockNum()){
            return AjaxResult.error("请勿超出库存数量！");
        }
        bookInfo.setSaleNum(l);
        bookInfoService.updateBookInfo(bookInfo);

        return AjaxResult.success("购买成功！");
    }

    /**
     * 修改订单
     */
    @RequiresPermissions("shop:order:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BookOrder bookOrder = bookOrderService.selectBookOrderById(id);
        mmap.put("bookOrder", bookOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单
     */
    @RequiresPermissions("shop:order:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookOrder bookOrder)
    {
        return toAjax(bookOrderService.updateBookOrder(bookOrder));
    }

    /**
     * 修改保存订单
     */
    @RequiresPermissions("shop:order:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PostMapping("/sign")
    @ResponseBody
    public AjaxResult sign(BookOrder bookOrder)
    {
        Integer state = bookOrder.getState();
        bookOrder = bookOrderService.selectBookOrderById(bookOrder.getId());
        bookOrder.setState(state);
        return toAjax(bookOrderService.updateBookOrder(bookOrder));
    }

    /**
     * 删除订单
     */
    @RequiresPermissions("shop:order:remove")
    @Log(title = "订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookOrderService.deleteBookOrderByIds(ids));
    }
}
