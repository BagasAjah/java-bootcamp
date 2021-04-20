package com.mitrais.service;

import com.mitrais.model.AccountInfo;
import com.mitrais.util.Utils;

import java.util.Scanner;

public class FundTransferSummaryService implements IService {
    private AccountInfo accountInfo;
    private int referenceNumber;
    private int transferAmount;
    public static final String OPTION_ONE = "1";

    public FundTransferSummaryService(AccountInfo accountInfo, int transferAmount, int referenceNumber) {
        this.accountInfo = accountInfo;
        this.transferAmount = transferAmount;
        this.referenceNumber = referenceNumber;
    }

    @Override
    public void display() {
        Utils.clearConsole();
        System.out.println("Fund Transfer Summary");
        System.out.println("Destination Account : " + accountInfo.getAccountNumber());
        System.out.println("Transfer Amount : $" + transferAmount);
        System.out.println("Reference Number : " + referenceNumber);
        System.out.println("Balance : $" + accountInfo.getBalance());
        System.out.println("");
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Choose option[2]:");
    }

    @Override
    public boolean process(Scanner scanner) {
        this.display();
        String transactionType = scanner.nextLine();
        if (OPTION_ONE.equals(transactionType)) {
            return true;
        } else {
            return false;
        }
    }
}
