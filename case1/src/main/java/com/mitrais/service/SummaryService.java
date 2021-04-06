package com.mitrais.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SummaryService implements IService {

    private int withdraw;
    private int balance;

    public SummaryService(int withdraw, int balance) {
        this.withdraw = withdraw;
        this.balance = balance;
    }

    @Override
    public void display() {
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a");
        LocalDateTime todayDatetime = LocalDateTime.now();
        String formatedDate = todayDatetime.format(datePattern);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Summary");
        System.out.println("Date: " + formatedDate);
        System.out.println("Withdraw: $" + withdraw);
        System.out.println("Balance: $" + balance);
        System.out.println("");
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Choose option[2]: ");
    }

    @Override
    public boolean process(Scanner scanner) {
        final String EXIT_OPTION = "2";
        this.display();
        String transactionType = scanner.nextLine();
        if (EXIT_OPTION.equals(transactionType)) {
            return false;
        }
        return true;
    }
}
