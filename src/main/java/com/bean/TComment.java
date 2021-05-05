package com.bean;


public class TComment {

  private long commentId;
  private String userName;
  private long blogId;
  private java.sql.Timestamp commentTime;
  private String content;


  public long getCommentId() {
    return commentId;
  }

  public void setCommentId(long commentId) {
    this.commentId = commentId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public long getBlogId() {
    return blogId;
  }

  public void setBlogId(long blogId) {
    this.blogId = blogId;
  }


  public java.sql.Timestamp getCommentTime() {
    return commentTime;
  }

  public void setCommentTime(java.sql.Timestamp commentTime) {
    this.commentTime = commentTime;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
