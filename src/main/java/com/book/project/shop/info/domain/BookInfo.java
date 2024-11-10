package com.book.project.shop.info.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.book.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.book.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 书籍信息对象 book_info
 * 
 * @author book
 * @date 2022-05-08
 */
public class BookInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 书名 */
    @Excel(name = "书名")
    private String bookName;

    /** 作者名 */
    @Excel(name = "作者名")
    private String author;

    /** 书籍类型 */
    @Excel(name = "书籍类型")
    private Long bookType;

    /** 价格 */
    @Excel(name = "价格")
    private String price;

    /** 出版号 */
    @Excel(name = "出版号")
    private String publishingCode;

    /** 封面链接 */
    @Excel(name = "封面链接")
    private String pic;

    /** 库存数 */
    @Excel(name = "库存数")
    private Long stockNum;

    /** 已售数 */
    @Excel(name = "已售数")
    private Long saleNum;

    @Excel(name = "状态")
    private Long status;

    /** 上架时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上架时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date putTime;

    /** 上架人 */
    @Excel(name = "上架人")
    private String putUser;

    private String percent;

    private String introduce;

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getBookName()
    {
        return bookName;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getAuthor()
    {
        return author;
    }
    public void setBookType(Long bookType)
    {
        this.bookType = bookType;
    }

    public Long getBookType()
    {
        return bookType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPublishingCode(String publishingCode)
    {
        this.publishingCode = publishingCode;
    }

    public String getPublishingCode()
    {
        return publishingCode;
    }
    public void setPic(String pic)
    {
        this.pic = pic;
    }

    public String getPic()
    {
        return pic;
    }
    public void setStockNum(Long stockNum)
    {
        this.stockNum = stockNum;
    }

    public Long getStockNum()
    {
        return stockNum;
    }
    public void setSaleNum(Long saleNum)
    {
        this.saleNum = saleNum;
    }

    public Long getSaleNum()
    {
        return saleNum;
    }
    public void setPutTime(Date putTime)
    {
        this.putTime = putTime;
    }

    public Date getPutTime()
    {
        return putTime;
    }
    public void setPutUser(String putUser)
    {
        this.putUser = putUser;
    }

    public String getPutUser()
    {
        return putUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bookName", getBookName())
            .append("author", getAuthor())
            .append("bookType", getBookType())
            .append("price", getPrice())
            .append("publishingCode", getPublishingCode())
            .append("pic", getPic())
            .append("stockNum", getStockNum())
            .append("saleNum", getSaleNum())
            .append("putTime", getPutTime())
            .append("putUser", getPutUser())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
