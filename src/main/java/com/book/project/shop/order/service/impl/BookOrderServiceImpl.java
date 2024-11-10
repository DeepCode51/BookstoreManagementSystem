package com.book.project.shop.order.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.project.shop.order.mapper.BookOrderMapper;
import com.book.project.shop.order.domain.BookOrder;
import com.book.project.shop.order.service.IBookOrderService;
import com.book.common.utils.text.Convert;

/**
 * 订单Service业务层处理
 * 
 * @author book
 * @date 2022-05-08
 */
@Service
public class BookOrderServiceImpl implements IBookOrderService 
{
    @Autowired
    private BookOrderMapper bookOrderMapper;

    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public BookOrder selectBookOrderById(Long id)
    {
        return bookOrderMapper.selectBookOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param bookOrder 订单
     * @return 订单
     */
    @Override
    public List<BookOrder> selectBookOrderList(BookOrder bookOrder)
    {
        return bookOrderMapper.selectBookOrderList(bookOrder);
    }

    /**
     * 新增订单
     * 
     * @param bookOrder 订单
     * @return 结果
     */
    @Override
    public int insertBookOrder(BookOrder bookOrder)
    {
        return bookOrderMapper.insertBookOrder(bookOrder);
    }

    /**
     * 修改订单
     * 
     * @param bookOrder 订单
     * @return 结果
     */
    @Override
    public int updateBookOrder(BookOrder bookOrder)
    {
        return bookOrderMapper.updateBookOrder(bookOrder);
    }

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteBookOrderByIds(String ids)
    {
        return bookOrderMapper.deleteBookOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteBookOrderById(Long id)
    {
        return bookOrderMapper.deleteBookOrderById(id);
    }
}
