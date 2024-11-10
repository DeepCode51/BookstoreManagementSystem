package com.book.project.shop.info.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.book.project.shop.info.domain.BookInfo;

/**
 * 书籍信息Service接口
 *
 * @author book
 * @date 2022-05-08
 */
public interface IBookInfoService
{

    List<Map<String, Object>> charts();
    /**
     * 查询书籍信息
     *
     * @param id 书籍信息主键
     * @return 书籍信息
     */
    public BookInfo selectBookInfoById(Long id);

    /**
     * 查询书籍信息列表
     *
     * @param bookInfo 书籍信息
     * @return 书籍信息集合
     */
    public List<BookInfo> selectBookInfoList(BookInfo bookInfo);

    /**
     * 新增书籍信息
     *
     * @param bookInfo 书籍信息
     * @return 结果
     */
    public int insertBookInfo(BookInfo bookInfo);

    /**
     * 修改书籍信息
     *
     * @param bookInfo 书籍信息
     * @return 结果
     */
    public int updateBookInfo(BookInfo bookInfo);

    /**
     * 批量删除书籍信息
     *
     * @param ids 需要删除的书籍信息主键集合
     * @return 结果
     */
    public int deleteBookInfoByIds(String ids);

    /**
     * 删除书籍信息信息
     *
     * @param id 书籍信息主键
     * @return 结果
     */
    public int deleteBookInfoById(Long id);
}
