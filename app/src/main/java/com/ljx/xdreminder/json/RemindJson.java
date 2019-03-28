package com.ljx.xdreminder.json;

public class RemindJson {
    private boolean CampusNetworkLogin = false;
    private int way1 = 0;
    private boolean CampusNetworkBalance = false;
    private int way2 = 0;
    private int NetworkLimit = 500;
    private boolean CardBalance = false;
    private int way3 = 0;
    private int CardLimit = -1;
    private boolean Book = false;
    private int way4 = 0;

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

    @Override
    public String toString() {
        return "RemindJson{" +
                "CampusNetworkLogin=" + CampusNetworkLogin +
                ", way1=" + way1 +
                ", CampusNetworkBalance=" + CampusNetworkBalance +
                ", way2=" + way2 +
                ", NetworkLimit=" + NetworkLimit +
                ", CardBalance=" + CardBalance +
                ", way3=" + way3 +
                ", CardLimit=" + CardLimit +
                ", Book=" + Book +
                ", way4=" + way4 +
                '}';
    }
}
