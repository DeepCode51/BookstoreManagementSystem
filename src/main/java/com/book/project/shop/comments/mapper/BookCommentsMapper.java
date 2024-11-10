package com.book.project.shop.comments.mapper;

import java.util.List;
import com.book.project.shop.comments.domain.BookComments;

/**
 * 评论Mapper接口
 * 
 * @author book
 * @date 2022-05-08
 */
public interface BookCommentsMapper 
{
    /**
     * 查询评论
     * 
     * @param id 评论主键
     * @return 评论
     */
    public BookComments selectBookCommentsById(Long id);

    /**
     * 查询评论列表
     * 
     * @param bookComments 评论
     * @return 评论集合
     */
    public List<BookComments> selectBookCommentsList(BookComments bookComments);

    /**
     * 新增评论
     * 
     * @param bookComments 评论
     * @return 结果
     */
    public int insertBookComments(BookComments bookComments);

    /**
     * 修改评论
     * 
     * @param bookComments 评论
     * @return 结果
     */
    public int updateBookComments(BookComments bookComments);

    /**
     * 删除评论
     * 
     * @param id 评论主键
     * @return 结果
     */
    public int deleteBookCommentsById(Long id);

    /**
     * 批量删除评论
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookCommentsByIds(String[] ids);
}
