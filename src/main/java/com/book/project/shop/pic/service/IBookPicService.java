package com.book.project.shop.pic.service;

import java.util.List;
import com.book.project.shop.pic.domain.BookPic;

/**
 * 轮播图Service接口
 * 
 * @author book
 * @date 2022-05-08
 */
public interface IBookPicService 
{
    /**
     * 查询轮播图
     * 
     * @param id 轮播图主键
     * @return 轮播图
     */
    public BookPic selectBookPicById(Long id);

    /**
     * 查询轮播图列表
     * 
     * @param bookPic 轮播图
     * @return 轮播图集合
     */
    public List<BookPic> selectBookPicList(BookPic bookPic);

    /**
     * 新增轮播图
     * 
     * @param bookPic 轮播图
     * @return 结果
     */
    public int insertBookPic(BookPic bookPic);

    /**
     * 修改轮播图
     * 
     * @param bookPic 轮播图
     * @return 结果
     */
    public int updateBookPic(BookPic bookPic);

    /**
     * 批量删除轮播图
     * 
     * @param ids 需要删除的轮播图主键集合
     * @return 结果
     */
    public int deleteBookPicByIds(String ids);

    /**
     * 删除轮播图信息
     * 
     * @param id 轮播图主键
     * @return 结果
     */
    public int deleteBookPicById(Long id);
}
