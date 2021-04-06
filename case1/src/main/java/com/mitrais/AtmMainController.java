package com.mitrais;

import com.mitrais.model.AccountInfo;
import com.mitrais.model.AccountLogin;
import com.mitrais.service.LoginService;
import com.mitrais.service.TransactionService;

import java.util.Scanner;
import java.util.function.Function;

public class AtmMainController {
    public static void main(String args[]) {
        try (Scanner inputScanner = new Scanner(System.in)) {
            while (true) {
                LoginService loginService = new LoginService();
                if (!loginService.validate(getLoginInfo.apply(inputScanner))) {
                    continue;
                }
                AccountInfo userInfo = loginService.getValidUser();
                TransactionService transactionService = new TransactionService(userInfo);
                transactionService.process(inputScanner);
            }
        }
    }

    private static Function<Scanner, AccountLogin> getLoginInfo = scanner -> {
        final String accountNumber;
        final String pin;
        System.out.print("Enter your Account Number: ");
        accountNumber = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        pin = scanner.nextLine();
        return new AccountLogin(accountNumber, pin);
    };
}