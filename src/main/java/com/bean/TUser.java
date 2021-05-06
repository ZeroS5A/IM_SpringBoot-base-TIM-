package com.bean;

public class TUser {

  private long id;
  private String userName;
  private String userPassword;
  private String userNickName;
  private String avatarUrl;
  private String userTimId;
  private String email;
  private String role;
  private Integer isban;

  private String userSig;
  private String token;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public String getUserNickName() {
    return userNickName;
  }

  public void setUserNickName(String userNickName) {
    this.userNickName = userNickName;
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


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Integer getIsban() {
    return isban;
  }

  public void setIsban(Integer isban) {
    this.isban = isban;
  }

  public String getUserSig() {
    return userSig;
  }

  public void setUserSig(String userSig) {
    this.userSig = userSig;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public String toString() {
    return "TUser{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", userPassword='" + userPassword + '\'' +
            ", userNickName='" + userNickName + '\'' +
            ", avatarUrl='" + avatarUrl + '\'' +
            ", userTimId='" + userTimId + '\'' +
            ", email='" + email + '\'' +
            ", role='" + role + '\'' +
            ", isban='" + isban + '\'' +
            ", userSig='" + userSig + '\'' +
            ", token='" + token + '\'' +
            '}';
  }
}
