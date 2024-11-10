package com.book.project.shop.pic.controller;

import java.util.List;
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
import com.book.project.shop.pic.domain.BookPic;
import com.book.project.shop.pic.service.IBookPicService;
import com.book.framework.web.controller.BaseController;
import com.book.framework.web.domain.AjaxResult;
import com.book.common.utils.poi.ExcelUtil;
import com.book.framework.web.page.TableDataInfo;

/**
 * 轮播图Controller
 * 
 * @author book
 * @date 2022-05-08
 */
@Controller
@RequestMapping("/shop/pic")
public class BookPicController extends BaseController
{
    private String prefix = "shop/pic";

    @Autowired
    private IBookPicService bookPicService;

    @RequiresPermissions("shop:pic:view")
    @GetMapping()
    public String pic()
    {
        return prefix + "/pic";
    }

    /**
     * 查询轮播图列表
     */
    @RequiresPermissions("shop:pic:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookPic bookPic)
    {
        startPage();
        List<BookPic> list = bookPicService.selectBookPicList(bookPic);
        return getDataTable(list);
    }

    /**
     * 导出轮播图列表
     */
    @RequiresPermissions("shop:pic:export")
    @Log(title = "轮播图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookPic bookPic)
    {
        List<BookPic> list = bookPicService.selectBookPicList(bookPic);
        ExcelUtil<BookPic> util = new ExcelUtil<BookPic>(BookPic.class);
        return util.exportExcel(list, "轮播图数据");
    }

    /**
     * 新增轮播图
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存轮播图
     */
    @RequiresPermissions("shop:pic:add")
    @Log(title = "轮播图", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookPic bookPic)
    {
        return toAjax(bookPicService.insertBookPic(bookPic));
    }

    /**
     * 修改轮播图
     */
    @RequiresPermissions("shop:pic:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BookPic bookPic = bookPicService.selectBookPicById(id);
        mmap.put("bookPic", bookPic);
        return prefix + "/edit";
    }

    /**
     * 修改保存轮播图
     */
    @RequiresPermissions("shop:pic:edit")
    @Log(title = "轮播图", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookPic bookPic)
    {
        return toAjax(bookPicService.updateBookPic(bookPic));
    }

    /**
     * 删除轮播图
     */
    @RequiresPermissions("shop:pic:remove")
    @Log(title = "轮播图", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookPicService.deleteBookPicByIds(ids));
    }
}
