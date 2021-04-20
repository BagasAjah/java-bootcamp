package com.mitrais.service;

import com.mitrais.model.AccountInfo;
import com.mitrais.util.DelayUtils;
import com.mitrais.util.Utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WithdrawService implements IService {

    private boolean isExit;
    private AccountInfo accountInfo;
    private AccountService accountService;
    private static final Map<String, Integer> defaultOption;
    public static final String OPTION_ONE = "1";
    public static final String OPTION_TWO = "2";
    public static final String OPTION_THREE = "3";
    public static final String OPTION_FOUR = "4";

    static {
        Map<String, Integer> option = new HashMap<>();
        option.put("1", 10);
        option.put("2", 50);
        option.put("3", 100);
        defaultOption = Collections.unmodifiableMap(option);
    }

    public WithdrawService(AccountInfo accountInfo, AccountService accountService) {
        this.isExit = false;
        this.accountInfo = accountInfo;
        this.accountService = accountService;
    }

    @Override
    public void display() {
        Utils.clearConsole();
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Back");
        System.out.print("Please choose option[5]: ");
    }

    @Override
    public boolean process(Scanner scanner) {
        while (!this.isExit) {
            display();
            String withdrawType = scanner.nextLine();
            switch (withdrawType) {
                case OPTION_ONE:
                case OPTION_TWO:
                case OPTION_THREE:
                    int withdrawBalance = this.defaultOption.get(withdrawType);
                    this.withdrawBalance(withdrawBalance, scanner);
                    break;
                case OPTION_FOUR:
                    IService withdrawService = new OtherWithdrawService(this.accountInfo);
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
        this.accountInfo.withdrawProcess(withdrawBalance);
        if (this.accountInfo.getErrorMessage() != null && this.accountInfo.getErrorMessage().length() > 0) {
            DelayUtils.delay();
            return;
        }
        this.accountService.updateAccountValue(accountInfo);
        IService summaryService = new SummaryService(withdrawBalance, this.accountInfo.getBalance());
        if (!summaryService.process(scanner)) {
            this.isExit = true;
        }
    }
}
