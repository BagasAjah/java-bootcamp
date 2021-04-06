package com.mitrais.model;

public class AccountInfo extends Account {
    private String name;
    private int balance;
    private String errorMessage;

    public AccountInfo() {

    }

    public AccountInfo(String name, int balance, String pin, String accountNumber) {
        this.name = name;
        this.balance = balance;
        this.pin = pin;
        this.accountNumber = accountNumber;
    }

    public void withdrawProcess(int withdrawBalance) {
        int estimatedBalance = this.balance - withdrawBalance;
        if (estimatedBalance < 0) {
            this.errorMessage = "Insufficient balance $" + withdrawBalance;
            System.out.println(errorMessage);
            return;
        }
        this.balance = estimatedBalance;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
