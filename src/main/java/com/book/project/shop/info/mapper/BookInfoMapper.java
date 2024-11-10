package com.book.project.shop.info.mapper;

import java.util.List;
import java.util.Map;

import com.book.project.shop.info.domain.BookInfo;

/**
 * 书籍信息Mapper接口
 *
 * @author book
 * @date 2022-05-08
 */
public interface BookInfoMapper
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
     * 删除书籍信息
     *
     * @param id 书籍信息主键
     * @return 结果
     */
    public int deleteBookInfoById(Long id);

    /**
     * 批量删除书籍信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookInfoByIds(String[] ids);
}
