package com.mitrais.model;

public class UserInfo {
    private String name;
    private String balance;
    private String pin;
    private String accountNumber;

    public UserInfo() {

    }

    public UserInfo(String name, String balance, String pin, String accountNumber) {
        this.name = name;
        this.balance = balance;
        this.pin = pin;
        this.accountNumber = accountNumber;
    }
}
