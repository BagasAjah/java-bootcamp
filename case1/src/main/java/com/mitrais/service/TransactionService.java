package com.mitrais.service;

import com.mitrais.model.AccountInfo;

import java.io.IOException;
import java.util.Scanner;

public class TransactionService implements IService {

    private boolean isExit;
    private AccountInfo userInfo;

    public TransactionService(AccountInfo userInfo) {
        this.isExit = false;
        this.userInfo = userInfo;
    }

    @Override
    public void display() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");
        System.out.print("Please choose option[3]: ");
    }

    @Override
    public boolean process(Scanner scanner) {
        final String WITHDRAW_TYPE = "1";
        final String FUND_TRANSFER_TYPE = "2";
        final String EXIT_TYPE = "3";
        while (!isExit) {
            display();
            String transactionType = scanner.nextLine();
            if (WITHDRAW_TYPE.equals(transactionType)) {
                WithdrawService withdrawService = new WithdrawService(userInfo);
                if (!withdrawService.process(scanner)) {
                    isExit = true;
                }
            }
            if (FUND_TRANSFER_TYPE.equals(transactionType)) {
                System.out.println("TRF");
            }
            if (transactionType.length() == 0 || EXIT_TYPE.equals(transactionType)) {
                break;
            }
        }
        return true;
    }
}
