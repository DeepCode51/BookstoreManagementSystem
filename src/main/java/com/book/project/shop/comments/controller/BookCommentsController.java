package com.book.project.shop.comments.controller;

import java.util.List;

import com.book.common.utils.security.ShiroUtils;
import com.book.project.shop.order.domain.BookOrder;
import com.book.project.shop.order.service.IBookOrderService;
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
import com.book.project.shop.comments.domain.BookComments;
import com.book.project.shop.comments.service.IBookCommentsService;
import com.book.framework.web.controller.BaseController;
import com.book.framework.web.domain.AjaxResult;
import com.book.common.utils.poi.ExcelUtil;
import com.book.framework.web.page.TableDataInfo;

/**
 * 评论Controller
 * 
 * @author book
 * @date 2022-05-08
 */
@Controller
@RequestMapping("/shop/comments")
public class BookCommentsController extends BaseController
{
    private String prefix = "shop/comments";

    @Autowired
    private IBookCommentsService bookCommentsService;

    @Autowired
    private IBookOrderService bookOrderService;

    @RequiresPermissions("shop:comments:view")
    @GetMapping()
    public String comments()
    {
        return prefix + "/comments";
    }

    /**
     * 查询评论列表
     */
//    @RequiresPermissions("shop:comments:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BookComments bookComments)
    {
        startPage();
        List<BookComments> list = bookCommentsService.selectBookCommentsList(bookComments);
        return getDataTable(list);
    }

    /**
     * 导出评论列表
     */
    @RequiresPermissions("shop:comments:export")
    @Log(title = "评论", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookComments bookComments)
    {
        List<BookComments> list = bookCommentsService.selectBookCommentsList(bookComments);
        ExcelUtil<BookComments> util = new ExcelUtil<BookComments>(BookComments.class);
        return util.exportExcel(list, "评论数据");
    }

    /**
     * 新增评论
     */
    @GetMapping("/add")
    public String add(ModelMap mmap,String id)
    {
        mmap.put("id", id);
        return prefix + "/add";
    }

    /**
     * 新增保存评论
     */
//    @RequiresPermissions("shop:comments:add")
    @Log(title = "评论", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BookComments bookComments)
    {
        User sysUser = ShiroUtils.getSysUser();

        Long id = bookComments.getId();
        BookOrder bookOrder = bookOrderService.selectBookOrderById(id);

        BookComments comments = new BookComments();
        comments.setBookId(bookOrder.getBookId());
        comments.setName(bookOrder.getBookName());
        comments.setContent(bookComments.getContent());
        comments.setStar(bookComments.getStar());
        comments.setUserId(sysUser.getUserId());
        comments.setUserName(sysUser.getUserName());

        return toAjax(bookCommentsService.insertBookComments(comments));
    }

    /**
     * 修改评论
     */
    @RequiresPermissions("shop:comments:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BookComments bookComments = bookCommentsService.selectBookCommentsById(id);
        mmap.put("bookComments", bookComments);
        return prefix + "/edit";
    }

    /**
     * 修改保存评论
     */
    @RequiresPermissions("shop:comments:edit")
    @Log(title = "评论", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BookComments bookComments)
    {
        return toAjax(bookCommentsService.updateBookComments(bookComments));
    }

    /**
     * 删除评论
     */
    @RequiresPermissions("shop:comments:remove")
    @Log(title = "评论", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookCommentsService.deleteBookCommentsByIds(ids));
    }
}
