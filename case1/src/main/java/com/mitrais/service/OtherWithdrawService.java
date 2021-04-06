package com.mitrais.service;

import com.mitrais.model.AccountInfo;
import com.mitrais.util.FormatUtils;

import java.util.Scanner;
import java.util.function.Function;

public class OtherWithdrawService implements IService {

    private AccountInfo accountInfo;

    public OtherWithdrawService(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    @Override
    public void display() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Other Withdraw");
        System.out.print("Enter amount withdraw :");
    }

    @Override
    public boolean process(Scanner scanner) {
        this.display();
        String withdrawAmount = scanner.nextLine();
        if (!this.validateAmount.apply(withdrawAmount)) {
            return true;
        }
        SummaryService summaryService = new SummaryService(Integer.valueOf(withdrawAmount), accountInfo.getBalance());
        return summaryService.process(scanner);
    }

    public Function<String, Boolean> validateAmount = amountString -> {
        if (!FormatUtils.isNumeric(amountString)) {
            System.out.println("Invalid amount");
            return false;
        }
        int amount = Integer.valueOf(amountString);
        final int MAX_AMOUNT_VALUE = 1000;
        final int MIN_AMOUNT_VALUE = 10;
        final int MULTIPLE_AMOUNT_VALUE = 10;
        if (amount > MAX_AMOUNT_VALUE) {
            System.out.println("Maximum amount to withdraw is $1000");
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
