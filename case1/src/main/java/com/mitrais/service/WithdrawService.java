package com.mitrais.service;

import com.mitrais.model.AccountInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WithdrawService implements IService {

    private boolean isExit;
    private AccountInfo accountInfo;
    private Map<String, Integer> defaultOption = new HashMap<>();

    public WithdrawService(AccountInfo accountInfo) {
        this.isExit = false;
        this.accountInfo = accountInfo;
        defaultOption.put("1", 10);
        defaultOption.put("2", 50);
        defaultOption.put("3", 100);
    }

    @Override
    public void display() {
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Back");
        System.out.print("Please choose option[5]: ");
    }

    @Override
    public boolean process(Scanner scanner) {
        final String OPTION_ONE = "1";
        final String OPTION_TWO = "2";
        final String OPTION_THREE = "3";
        final String OPTION_FOUR = "4";
        while (!isExit) {
            display();
            String withdrawType = scanner.nextLine();
            switch (withdrawType) {
                case OPTION_ONE:
                case OPTION_TWO:
                case OPTION_THREE:
                    int withdrawBalance = defaultOption.get(withdrawType);
                    withdrawBalance(withdrawBalance, scanner);
                    break;
                case OPTION_FOUR:
                    OtherWithdrawService withdrawService = new OtherWithdrawService(accountInfo);
                    if (!withdrawService.process(scanner)) {
                        this.isExit = true;
                    }
                    break;
                default:
                    return true;
            }
        }
        return false;
    }

    private void withdrawBalance(int withdrawBalance, Scanner scanner) {
        accountInfo.withdrawProcess(withdrawBalance);
        if (accountInfo.getErrorMessage().length() > 0) {
            return;
        }
        SummaryService summaryService = new SummaryService(withdrawBalance, accountInfo.getBalance());
        if (!summaryService.process(scanner)) {
            this.isExit = true;
        }
    }
}
