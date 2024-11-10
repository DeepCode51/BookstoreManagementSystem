package com.book.project.shop.comments.service.impl;

import java.util.List;
import com.book.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.project.shop.comments.mapper.BookCommentsMapper;
import com.book.project.shop.comments.domain.BookComments;
import com.book.project.shop.comments.service.IBookCommentsService;
import com.book.common.utils.text.Convert;

/**
 * 评论Service业务层处理
 * 
 * @author book
 * @date 2022-05-08
 */
@Service
public class BookCommentsServiceImpl implements IBookCommentsService 
{
    @Autowired
    private BookCommentsMapper bookCommentsMapper;

    /**
     * 查询评论
     * 
     * @param id 评论主键
     * @return 评论
     */
    @Override
    public BookComments selectBookCommentsById(Long id)
    {
        return bookCommentsMapper.selectBookCommentsById(id);
    }

    /**
     * 查询评论列表
     * 
     * @param bookComments 评论
     * @return 评论
     */
    @Override
    public List<BookComments> selectBookCommentsList(BookComments bookComments)
    {
        return bookCommentsMapper.selectBookCommentsList(bookComments);
    }

    /**
     * 新增评论
     * 
     * @param bookComments 评论
     * @return 结果
     */
    @Override
    public int insertBookComments(BookComments bookComments)
    {
        bookComments.setCreateTime(DateUtils.getNowDate());
        return bookCommentsMapper.insertBookComments(bookComments);
    }

    /**
     * 修改评论
     * 
     * @param bookComments 评论
     * @return 结果
     */
    @Override
    public int updateBookComments(BookComments bookComments)
    {
        return bookCommentsMapper.updateBookComments(bookComments);
    }

    /**
     * 批量删除评论
     * 
     * @param ids 需要删除的评论主键
     * @return 结果
     */
    @Override
    public int deleteBookCommentsByIds(String ids)
    {
        return bookCommentsMapper.deleteBookCommentsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除评论信息
     * 
     * @param id 评论主键
     * @return 结果
     */
    @Override
    public int deleteBookCommentsById(Long id)
    {
        return bookCommentsMapper.deleteBookCommentsById(id);
    }
}
