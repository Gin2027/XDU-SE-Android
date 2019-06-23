package com.ljx.xdreminder.json;

public class RemindJson {
    private String androidID;
    private boolean CampusNetworkLogin = false; //校园网登录提醒开关
    private int way1 = 1;   //校园网登录提醒方式
    private boolean CampusNetworkBalance = false;   //校园网流量余量提醒开关
    private int way2 = 1;   //校园网流量余量提醒方式
    private int NetworkLimit = 500; //校园网流量余量提醒门槛
    private boolean CardBalance = false;    //一卡通余额提醒开关
    private int way3 = 1;   //一卡通余额提醒方式
    private int CardLimit = -1; //一卡通余额提醒门槛
    private boolean Book = false;   //借阅图书归还提醒开关
    private int way4 = 1;   //借阅图书归还提醒方式
    private String account; //统一身份认证账号
    private String cardpassword; //统一身份认证密码
    private String netaccount;  //校园网账号
    private String netpassword; //校园网密码
    private String email;   //提醒邮箱

    public String getAndroidID() {
        return androidID;
    }

    public void setAndroidID(String androidID) {
        this.androidID = androidID;
    }

    public boolean isCampusNetworkLogin() {
        return CampusNetworkLogin;
    }

    public void setCampusNetworkLogin(boolean campusNetworkLogin) {
        CampusNetworkLogin = campusNetworkLogin;
    }

    public int getWay1() {
        return way1;
    }

    public void setWay1(int way1) {
        this.way1 = way1;
    }

    public boolean isCampusNetworkBalance() {
        return CampusNetworkBalance;
    }

    public void setCampusNetworkBalance(boolean campusNetworkBalance) {
        CampusNetworkBalance = campusNetworkBalance;
    }

    public int getWay2() {
        return way2;
    }

    public void setWay2(int way2) {
        this.way2 = way2;
    }

    public int getNetworkLimit() {
        return NetworkLimit;
    }

    public void setNetworkLimit(int networkLimit) {
        NetworkLimit = networkLimit;
    }

    public boolean isCardBalance() {
        return CardBalance;
    }

    public void setCardBalance(boolean cardBalance) {
        CardBalance = cardBalance;
    }

    public int getWay3() {
        return way3;
    }

    public void setWay3(int way3) {
        this.way3 = way3;
    }

    public int getCardLimit() {
        return CardLimit;
    }

    public void setCardLimit(int cardLimit) {
        CardLimit = cardLimit;
    }

    public boolean isBook() {
        return Book;
    }

    public void setBook(boolean book) {
        Book = book;
    }

    public int getWay4() {
        return way4;
    }

    public void setWay4(int way4) {
        this.way4 = way4;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCardpassword() {
        return cardpassword;
    }

    public void setCardpassword(String cardpassword) {
        this.cardpassword = cardpassword;
    }

    public String getNetaccount() {
        return netaccount;
    }

    public void setNetaccount(String netaccount) {
        this.netaccount = netaccount;
    }

    public String getNetpassword() {
        return netpassword;
    }

    public void setNetpassword(String netpassword) {
        this.netpassword = netpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RemindJson{" +
                "androidID='" + androidID + '\'' +
                ", CampusNetworkLogin=" + CampusNetworkLogin +
                ", way1=" + way1 +
                ", CampusNetworkBalance=" + CampusNetworkBalance +
                ", way2=" + way2 +
                ", NetworkLimit=" + NetworkLimit +
                ", CardBalance=" + CardBalance +
                ", way3=" + way3 +
                ", CardLimit=" + CardLimit +
                ", Book=" + Book +
                ", way4=" + way4 +
                ", account='" + account + '\'' +
                ", cardpassword='" + cardpassword + '\'' +
                ", netaccount='" + netaccount + '\'' +
                ", netpassword='" + netpassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
