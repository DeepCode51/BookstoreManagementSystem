package com.book.project.shop.order.mapper;

import java.util.List;
import com.book.project.shop.order.domain.BookOrder;

/**
 * 订单Mapper接口
 * 
 * @author book
 * @date 2022-05-08
 */
public interface BookOrderMapper 
{
    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    public BookOrder selectBookOrderById(Long id);

    /**
     * 查询订单列表
     * 
     * @param bookOrder 订单
     * @return 订单集合
     */
    public List<BookOrder> selectBookOrderList(BookOrder bookOrder);

    /**
     * 新增订单
     * 
     * @param bookOrder 订单
     * @return 结果
     */
    public int insertBookOrder(BookOrder bookOrder);

    /**
     * 修改订单
     * 
     * @param bookOrder 订单
     * @return 结果
     */
    public int updateBookOrder(BookOrder bookOrder);

    /**
     * 删除订单
     * 
     * @param id 订单主键
     * @return 结果
     */
    public int deleteBookOrderById(Long id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookOrderByIds(String[] ids);
}
