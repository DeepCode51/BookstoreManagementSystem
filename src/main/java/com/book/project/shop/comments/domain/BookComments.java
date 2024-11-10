package com.book.project.shop.comments.domain;

import com.book.framework.aspectj.lang.annotation.Excel;
import com.book.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 评论对象 book_comments
 * 
 * @author book
 * @date 2022-05-08
 */
public class BookComments extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    /** 小说id */
    @Excel(name = "小说id")
    private Long bookId;

    /** 评论人id */
    @Excel(name = "评论人id")
    private Long userId;

    /** 评论人 */
    @Excel(name = "评论人")
    private String userName;

    /** 评级 */
    @Excel(name = "评级")
    private Long star;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setBookId(Long bookId)
    {
        this.bookId = bookId;
    }

    public Long getBookId()
    {
        return bookId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setStar(Long star)
    {
        this.star = star;
    }

    public Long getStar()
    {
        return star;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("content", getContent())
            .append("bookId", getBookId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("star", getStar())
            .append("createTime", getCreateTime())
            .toString();
    }
}
