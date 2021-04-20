package com.mitrais.service;

import com.mitrais.model.AccountInfo;
import com.mitrais.util.DelayUtils;
import com.mitrais.util.FormatUtils;
import com.mitrais.util.Utils;

import java.util.Scanner;
import java.util.function.Function;

public class OtherWithdrawService implements IService {

    private AccountInfo accountInfo;
    public static final int MAX_AMOUNT_VALUE = 1000;
    public static final int MIN_AMOUNT_VALUE = 10;
    public static final int MULTIPLE_AMOUNT_VALUE = 10;

    public OtherWithdrawService(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    @Override
    public void display() {
        Utils.clearConsole();
        System.out.println("Other Withdraw");
        System.out.print("Enter amount withdraw :");
    }

    @Override
    public boolean process(Scanner scanner) {
        this.display();
        String withdrawAmount = scanner.nextLine();
        if (!this.validateAmount.apply(withdrawAmount)) {
            DelayUtils.delay();
            return true;
        }
        IService summaryService = new SummaryService(Integer.valueOf(withdrawAmount), accountInfo.getBalance());
        return summaryService.process(scanner);
    }

    public Function<String, Boolean> validateAmount = amountString -> {
        if (!FormatUtils.isNumeric(amountString)) {
            System.out.println("Invalid amount");
            return false;
        }
        int amount = Integer.valueOf(amountString);
        if (amount > MAX_AMOUNT_VALUE) {
            System.out.println("Maximum amount to withdraw is $" + MAX_AMOUNT_VALUE);
            return false;
        }
        if (amount < MIN_AMOUNT_VALUE) {
            System.out.println("Insufficient amount $" + amount);
            return false;
        }
        if (amount % MULTIPLE_AMOUNT_VALUE != 0) {
            System.out.println("Invalid amount");
            return false;
        }
        return true;
    };
}
