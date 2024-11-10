package com.book.project.shop.info.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.book.common.utils.security.ShiroUtils;
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
import com.book.project.shop.info.domain.BookInfo;
import com.book.project.shop.info.service.IBookInfoService;
import com.book.framework.web.controller.BaseController;
import com.book.framework.web.domain.AjaxResult;
import com.book.common.utils.poi.ExcelUtil;
import com.book.framework.web.page.TableDataInfo;

/**
 * 书籍信息Controller
 *
 * @author book
 * @date 2022-05-08
 */
@Controller
@RequestMapping("/shop/info")
public class BookInfoController extends BaseController
{
    private String prefix = "shop/info";

    @Autowired
    private IBookInfoService bookInfoService;

    @RequiresPermissions("shop:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    @RequiresPermissions("shop:info:view")
    @GetMapping("/charts")
    public String charts(ModelMap mmap)
    {
        mmap.put("bookInfo", bookInfoService.charts());
        return prefix + "/charts";
    }

    /**
     * 查询书籍信息列表
     */
//    @RequiresPermissions("shop:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookInfo bookInfo)
    {
        startPage();
        List<BookInfo> list = bookInfoService.selectBookInfoList(bookInfo);
        for(BookInfo book : list){
            Long stockNum = book.getStockNum();
            Long saleNum = book.getSaleNum();
            String percent = getPercent(stockNum-saleNum, stockNum);
            book.setPercent(percent);
        }
        return getDataTable(list);
    }

    @GetMapping("/details")
    public String details(ModelMap mmap,String id){

        BookInfo bookInfo = bookInfoService.selectBookInfoById(Long.parseLong(id));
        mmap.put("bookInfo",bookInfo);
        return prefix + "/details";
    }

    public static String getPercent(Long x, Long y) {
        double d1 = x * 1.0;
        double d2 = y * 1.0;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        // 设置保留几位小数，这里设置的是保留两位小数
        percentInstance.setMinimumFractionDigits(2);
        return percentInstance.format(d1 / d2);
    }

    /**
     * 导出书籍信息列表
     */
    @RequiresPermissions("shop:info:export")
    @Log(title = "书籍信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookInfo bookInfo)
    {
        List<BookInfo> list = bookInfoService.selectBookInfoList(bookInfo);
        ExcelUtil<BookInfo> util = new ExcelUtil<BookInfo>(BookInfo.class);
        return util.exportExcel(list, "书籍信息数据");
    }

    /**
     * 新增书籍信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存书籍信息
     */
    @RequiresPermissions("shop:info:add")
    @Log(title = "书籍信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookInfo bookInfo)
    {
        String userName = ShiroUtils.getSysUser().getUserName();
        bookInfo.setPutUser(userName);
        return toAjax(bookInfoService.insertBookInfo(bookInfo));
    }

    /**
     * 修改书籍信息
     */
    @RequiresPermissions("shop:info:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BookInfo bookInfo = bookInfoService.selectBookInfoById(id);
        mmap.put("bookInfo", bookInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存书籍信息
     */
    @RequiresPermissions("shop:info:edit")
    @Log(title = "书籍信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookInfo bookInfo)
    {
        String userName = ShiroUtils.getSysUser().getUserName();
        bookInfo.setPutUser(userName);
        return toAjax(bookInfoService.updateBookInfo(bookInfo));
    }

    /**
     * 删除书籍信息
     */
    @RequiresPermissions("shop:info:remove")
    @Log(title = "书籍信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookInfoService.deleteBookInfoByIds(ids));
    }
}
