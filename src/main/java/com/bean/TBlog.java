package com.bean;


public class TBlog {

  private long blogId;
  private String userName;
  private long classId;
  private String title;
  private java.sql.Timestamp postTime;
  private String content;
  private String imageList;
  private Integer likeNum;

  private String isLiked;
  private String avatarUrl;
  private String userTimId;

  public long getBlogId() {
    return blogId;
  }

  public void setBlogId(long blogId) {
    this.blogId = blogId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public long getClassId() {
    return classId;
  }

  public void setClassId(long classId) {
    this.classId = classId;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public java.sql.Timestamp getPostTime() {
    return postTime;
  }

  public void setPostTime(java.sql.Timestamp postTime) {
    this.postTime = postTime;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getImageList() {
    return imageList;
  }

  public void setImageList(String imageList) {
    this.imageList = imageList;
  }

  public String getIsLiked() {
    return isLiked;
  }

  public void setIsLiked(String isLiked) {
    this.isLiked = isLiked;
  }

  public Integer getLikeNum() {
    return likeNum;
  }

  public void setLikeNum(Integer likeNum) {
    this.likeNum = likeNum;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getUserTimId() {
    return userTimId;
  }

  public void setUserTimId(String userTimId) {
    this.userTimId = userTimId;
  }

  @Override
  public String toString() {
    return "TBlog{" +
            "blogId=" + blogId +
            ", userName='" + userName + '\'' +
            ", classId=" + classId +
            ", title='" + title + '\'' +
            ", postTime=" + postTime +
            ", content='" + content + '\'' +
            ", imageList='" + imageList + '\'' +
            ", likeNum='" + likeNum + '\'' +
            ", isLiked='" + isLiked + '\'' +
            ", avatarUrl='" + avatarUrl + '\'' +
            ", userTimId='" + userTimId + '\'' +
            '}';
  }
}
