package com.book.project.shop.order.service;

import java.util.List;
import com.book.project.shop.order.domain.BookOrder;

/**
 * 订单Service接口
 * 
 * @author book
 * @date 2022-05-08
 */
public interface IBookOrderService 
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
     * 批量删除订单
     * 
     * @param ids 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteBookOrderByIds(String ids);

    /**
     * 删除订单信息
     * 
     * @param id 订单主键
     * @return 结果
     */
    public int deleteBookOrderById(Long id);
}
