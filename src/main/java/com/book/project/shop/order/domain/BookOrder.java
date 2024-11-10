package com.book.project.shop.order.domain;

import java.util.Date;

import com.book.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.book.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 订单对象 book_order
 *
 * @author book
 * @date 2022-05-08
 */
public class BookOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNo;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Integer state;

    /** 书id */
    @Excel(name = "书id")
    private Long bookId;

    /** 书名 */
    @Excel(name = "书名")
    private String bookName;

    /** 书籍类型 */
    @Excel(name = "书籍类型")
    private Long bookType;

    /** 购买人id */
    @Excel(name = "购买人id")
    private Long buyerId;

    /** 购买人 */
    @Excel(name = "购买人")
    private String buyer;

    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date saleTime;

    /** 交易数量 */
    @Excel(name = "交易数量")
    private Long saleNum;

    /** 价格 */
    @Excel(name = "价格")
    private String salePrice;

    private String remark;

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setBookId(Long bookId)
    {
        this.bookId = bookId;
    }

    public Long getBookId()
    {
        return bookId;
    }
    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getBookName()
    {
        return bookName;
    }
    public void setBookType(Long bookType)
    {
        this.bookType = bookType;
    }

    public Long getBookType()
    {
        return bookType;
    }
    public void setBuyerId(Long buyerId)
    {
        this.buyerId = buyerId;
    }

    public Long getBuyerId()
    {
        return buyerId;
    }
    public void setBuyer(String buyer)
    {
        this.buyer = buyer;
    }

    public String getBuyer()
    {
        return buyer;
    }
    public void setSaleTime(Date saleTime)
    {
        this.saleTime = saleTime;
    }

    public Date getSaleTime()
    {
        return saleTime;
    }
    public void setSaleNum(Long saleNum)
    {
        this.saleNum = saleNum;
    }

    public Long getSaleNum()
    {
        return saleNum;
    }
    public void setSalePrice(String salePrice)
    {
        this.salePrice = salePrice;
    }

    public String getSalePrice()
    {
        return salePrice;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bookId", getBookId())
            .append("bookName", getBookName())
            .append("bookType", getBookType())
            .append("buyerId", getBuyerId())
            .append("buyer", getBuyer())
            .append("saleTime", getSaleTime())
            .append("saleNum", getSaleNum())
            .append("salePrice", getSalePrice())
            .toString();
    }
}
