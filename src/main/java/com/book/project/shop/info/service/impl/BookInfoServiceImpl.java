package com.book.project.shop.info.service.impl;

import java.util.List;
import java.util.Map;

import com.book.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.project.shop.info.mapper.BookInfoMapper;
import com.book.project.shop.info.domain.BookInfo;
import com.book.project.shop.info.service.IBookInfoService;
import com.book.common.utils.text.Convert;

/**
 * 书籍信息Service业务层处理
 *
 * @author book
 * @date 2022-05-08
 */
@Service
public class BookInfoServiceImpl implements IBookInfoService
{
    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Override
    public List<Map<String, Object>> charts() {
        return bookInfoMapper.charts();
    }

    /**
     * 查询书籍信息
     *
     * @param id 书籍信息主键
     * @return 书籍信息
     */
    @Override
    public BookInfo selectBookInfoById(Long id)
    {
        return bookInfoMapper.selectBookInfoById(id);
    }

    /**
     * 查询书籍信息列表
     *
     * @param bookInfo 书籍信息
     * @return 书籍信息
     */
    @Override
    public List<BookInfo> selectBookInfoList(BookInfo bookInfo)
    {
        return bookInfoMapper.selectBookInfoList(bookInfo);
    }

    /**
     * 新增书籍信息
     *
     * @param bookInfo 书籍信息
     * @return 结果
     */
    @Override
    public int insertBookInfo(BookInfo bookInfo)
    {
        return bookInfoMapper.insertBookInfo(bookInfo);
    }

    /**
     * 修改书籍信息
     *
     * @param bookInfo 书籍信息
     * @return 结果
     */
    @Override
    public int updateBookInfo(BookInfo bookInfo)
    {
        bookInfo.setUpdateTime(DateUtils.getNowDate());
        return bookInfoMapper.updateBookInfo(bookInfo);
    }

    /**
     * 批量删除书籍信息
     *
     * @param ids 需要删除的书籍信息主键
     * @return 结果
     */
    @Override
    public int deleteBookInfoByIds(String ids)
    {
        return bookInfoMapper.deleteBookInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除书籍信息信息
     *
     * @param id 书籍信息主键
     * @return 结果
     */
    @Override
    public int deleteBookInfoById(Long id)
    {
        return bookInfoMapper.deleteBookInfoById(id);
    }
}
