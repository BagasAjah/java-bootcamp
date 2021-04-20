package com.mitrais;

import com.mitrais.model.AccountInfo;
import com.mitrais.model.AccountLogin;
import com.mitrais.service.IService;
import com.mitrais.service.LoginService;
import com.mitrais.service.TransactionService;
import com.mitrais.service.AccountService;
import com.mitrais.util.DelayUtils;
import com.mitrais.util.Utils;

import java.util.Scanner;
import java.util.function.Function;

public class AtmMainController {
    public static void main(String args[]) {
        try (Scanner inputScanner = new Scanner(System.in)) {
            AccountService accountService = new AccountService();
            while (true) {
                LoginService loginService = new LoginService(accountService);
                if (!loginService.login(getLoginRequest.apply(inputScanner))) {
                    DelayUtils.delay();
                    continue;
                }
                AccountInfo userInfo = loginService.getValidUser();
                IService transactionService = new TransactionService(userInfo, accountService);
                transactionService.process(inputScanner);
            }
        }
    }

    private static Function<Scanner, AccountLogin> getLoginRequest = scanner -> {
        final String accountNumber;
        final String pin;
        Utils.clearConsole();
        System.out.print("Enter your Account Number: ");
        accountNumber = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        pin = scanner.nextLine();
        return new AccountLogin(accountNumber, pin);
    };
}