package com.book.project.shop.car.service.impl;

import java.util.List;
import com.book.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.project.shop.car.mapper.BookCarMapper;
import com.book.project.shop.car.domain.BookCar;
import com.book.project.shop.car.service.IBookCarService;
import com.book.common.utils.text.Convert;

/**
 * 购物车Service业务层处理
 * 
 * @author book
 * @date 2022-05-08
 */
@Service
public class BookCarServiceImpl implements IBookCarService 
{
    @Autowired
    private BookCarMapper bookCarMapper;

    /**
     * 查询购物车
     * 
     * @param id 购物车主键
     * @return 购物车
     */
    @Override
    public BookCar selectBookCarById(Long id)
    {
        return bookCarMapper.selectBookCarById(id);
    }

    /**
     * 查询购物车列表
     * 
     * @param bookCar 购物车
     * @return 购物车
     */
    @Override
    public List<BookCar> selectBookCarList(BookCar bookCar)
    {
        return bookCarMapper.selectBookCarList(bookCar);
    }

    /**
     * 新增购物车
     * 
     * @param bookCar 购物车
     * @return 结果
     */
    @Override
    public int insertBookCar(BookCar bookCar)
    {
        bookCar.setCreateTime(DateUtils.getNowDate());
        return bookCarMapper.insertBookCar(bookCar);
    }

    /**
     * 修改购物车
     * 
     * @param bookCar 购物车
     * @return 结果
     */
    @Override
    public int updateBookCar(BookCar bookCar)
    {
        return bookCarMapper.updateBookCar(bookCar);
    }

    /**
     * 批量删除购物车
     * 
     * @param ids 需要删除的购物车主键
     * @return 结果
     */
    @Override
    public int deleteBookCarByIds(String ids)
    {
        return bookCarMapper.deleteBookCarByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除购物车信息
     * 
     * @param id 购物车主键
     * @return 结果
     */
    @Override
    public int deleteBookCarById(Long id)
    {
        return bookCarMapper.deleteBookCarById(id);
    }
}
