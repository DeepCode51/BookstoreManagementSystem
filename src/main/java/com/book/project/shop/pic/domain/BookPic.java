package com.book.project.shop.pic.domain;

import com.book.framework.aspectj.lang.annotation.Excel;
import com.book.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 轮播图对象 book_pic
 * 
 * @author book
 * @date 2022-05-08
 */
public class BookPic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String picAdress;

    private  String title;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPicAdress(String picAdress)
    {
        this.picAdress = picAdress;
    }

    public String getPicAdress()
    {
        return picAdress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("picAdress", getPicAdress())
            .append("createTime", getCreateTime())
            .toString();
    }
}
