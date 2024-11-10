package com.book.project.shop.pic.service.impl;

import java.util.List;
import com.book.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.project.shop.pic.mapper.BookPicMapper;
import com.book.project.shop.pic.domain.BookPic;
import com.book.project.shop.pic.service.IBookPicService;
import com.book.common.utils.text.Convert;

/**
 * 轮播图Service业务层处理
 * 
 * @author book
 * @date 2022-05-08
 */
@Service
public class BookPicServiceImpl implements IBookPicService 
{
    @Autowired
    private BookPicMapper bookPicMapper;

    /**
     * 查询轮播图
     * 
     * @param id 轮播图主键
     * @return 轮播图
     */
    @Override
    public BookPic selectBookPicById(Long id)
    {
        return bookPicMapper.selectBookPicById(id);
    }

    /**
     * 查询轮播图列表
     * 
     * @param bookPic 轮播图
     * @return 轮播图
     */
    @Override
    public List<BookPic> selectBookPicList(BookPic bookPic)
    {
        return bookPicMapper.selectBookPicList(bookPic);
    }

    /**
     * 新增轮播图
     * 
     * @param bookPic 轮播图
     * @return 结果
     */
    @Override
    public int insertBookPic(BookPic bookPic)
    {
        bookPic.setCreateTime(DateUtils.getNowDate());
        return bookPicMapper.insertBookPic(bookPic);
    }

    /**
     * 修改轮播图
     * 
     * @param bookPic 轮播图
     * @return 结果
     */
    @Override
    public int updateBookPic(BookPic bookPic)
    {
        return bookPicMapper.updateBookPic(bookPic);
    }

    /**
     * 批量删除轮播图
     * 
     * @param ids 需要删除的轮播图主键
     * @return 结果
     */
    @Override
    public int deleteBookPicByIds(String ids)
    {
        return bookPicMapper.deleteBookPicByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除轮播图信息
     * 
     * @param id 轮播图主键
     * @return 结果
     */
    @Override
    public int deleteBookPicById(Long id)
    {
        return bookPicMapper.deleteBookPicById(id);
    }
}
