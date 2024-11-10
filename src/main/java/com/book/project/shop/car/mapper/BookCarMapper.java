package com.book.project.shop.car.mapper;

import java.util.List;
import com.book.project.shop.car.domain.BookCar;

/**
 * 购物车Mapper接口
 * 
 * @author book
 * @date 2022-05-08
 */
public interface BookCarMapper 
{
    /**
     * 查询购物车
     * 
     * @param id 购物车主键
     * @return 购物车
     */
    public BookCar selectBookCarById(Long id);

    /**
     * 查询购物车列表
     * 
     * @param bookCar 购物车
     * @return 购物车集合
     */
    public List<BookCar> selectBookCarList(BookCar bookCar);

    /**
     * 新增购物车
     * 
     * @param bookCar 购物车
     * @return 结果
     */
    public int insertBookCar(BookCar bookCar);

    /**
     * 修改购物车
     * 
     * @param bookCar 购物车
     * @return 结果
     */
    public int updateBookCar(BookCar bookCar);

    /**
     * 删除购物车
     * 
     * @param id 购物车主键
     * @return 结果
     */
    public int deleteBookCarById(Long id);

    /**
     * 批量删除购物车
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookCarByIds(String[] ids);
}
