package com.mitrais.model;

public class AccountLogin {
    private String accountNumber;
    private String pin;
    public AccountLogin(String accountNumber, String pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }
}
