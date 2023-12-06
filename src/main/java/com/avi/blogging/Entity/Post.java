package com.avi.blogging.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import javax.lang.model.element.Name;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long postId;
    @Column(name = "title",length = 100,nullable = false)
    private String postTitle;
    @Column(length = 10000)
    private String content;
    private String imageName;
    private Date addDate;
    @ManyToOne
    @JoinColumn(name = "cId")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "uId")
    private User user;

    public Post() {
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
