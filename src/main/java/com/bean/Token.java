package com.bean;

public class Token {
    private String userName;
    private String role;
    private String expirationTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Override
    public String toString() {
        return "Token{" +
                "userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                ", expirationTime='" + expirationTime + '\'' +
                '}';
    }
}
