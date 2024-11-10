package com.book.project.shop.car.controller;

import java.util.List;

import com.book.common.utils.security.ShiroUtils;
import com.book.project.system.role.domain.Role;
import com.book.project.system.user.domain.User;
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
import com.book.project.shop.car.domain.BookCar;
import com.book.project.shop.car.service.IBookCarService;
import com.book.framework.web.controller.BaseController;
import com.book.framework.web.domain.AjaxResult;
import com.book.common.utils.poi.ExcelUtil;
import com.book.framework.web.page.TableDataInfo;

/**
 * 购物车Controller
 * 
 * @author book
 * @date 2022-05-08
 */
@Controller
@RequestMapping("/shop/car")
public class BookCarController extends BaseController
{
    private String prefix = "shop/car";

    @Autowired
    private IBookCarService bookCarService;

    @RequiresPermissions("shop:car:view")
    @GetMapping()
    public String car()
    {
        return prefix + "/car";
    }

    /**
     * 查询购物车列表
     */
    @RequiresPermissions("shop:car:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookCar bookCar)
    {
        startPage();

        Long userId = ShiroUtils.getUserId();
        bookCar.setUserId(userId);
        bookCar.setState(1l);
        List<BookCar> list = bookCarService.selectBookCarList(bookCar);
        return getDataTable(list);
    }

    /**
     * 导出购物车列表
     */
    @RequiresPermissions("shop:car:export")
    @Log(title = "购物车", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookCar bookCar)
    {
        List<BookCar> list = bookCarService.selectBookCarList(bookCar);
        ExcelUtil<BookCar> util = new ExcelUtil<BookCar>(BookCar.class);
        return util.exportExcel(list, "购物车数据");
    }

    /**
     * 新增购物车
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存购物车
     */
    @RequiresPermissions("shop:car:add")
    @Log(title = "购物车", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookCar bookCar)
    {
        return toAjax(bookCarService.insertBookCar(bookCar));
    }

    @Log(title = "购物车", businessType = BusinessType.INSERT)
    @GetMapping("/addBook")
    @ResponseBody
    public AjaxResult addBook(Long bookId,String price,String bookName)
    {
        User sysUser = ShiroUtils.getSysUser();
        Role role = sysUser.getRoles().get(0);
        Long roleId = role.getRoleId();
        if(roleId != 100){
            return AjaxResult.warn("非买家角色无法购买！");
        }
        Long userId = sysUser.getUserId();

        BookCar car = new BookCar();
        car.setBookId(bookId);
        car.setUserId(userId);
        car.setState(1l);
        List<BookCar> bookCars = bookCarService.selectBookCarList(car);

        if(bookCars.size() != 0){
            BookCar bookCar = bookCars.get(0);
            Integer num = bookCar.getNum();
            bookCar.setNum(num+1);
            bookCarService.updateBookCar(bookCar);
        }else {
            BookCar bookCar = new BookCar();
            bookCar.setUserId(userId);
            bookCar.setBookId(bookId);
            bookCar.setState(1l);
            bookCar.setPrice(price);
            bookCar.setBookName(bookName);
            bookCar.setNum(1);
            int i = bookCarService.insertBookCar(bookCar);
        }



        return AjaxResult.success("加入成功！");
    }

    /**
     * 修改购物车
     */
    @RequiresPermissions("shop:car:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BookCar bookCar = bookCarService.selectBookCarById(id);
        mmap.put("bookCar", bookCar);
        return prefix + "/edit";
    }

    /**
     * 修改保存购物车
     */
    @RequiresPermissions("shop:car:edit")
    @Log(title = "购物车", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookCar bookCar)
    {
        return toAjax(bookCarService.updateBookCar(bookCar));
    }

    /**
     * 删除购物车
     */
    @RequiresPermissions("shop:car:remove")
    @Log(title = "购物车", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookCarService.deleteBookCarByIds(ids));
    }
}
