package com.mitrais.service;

import com.mitrais.model.AccountInfo;
import com.mitrais.util.Utils;

import java.util.Scanner;

public class TransactionService implements IService {

    private boolean isExit;
    private AccountInfo accountInfo;
    private AccountService accountService;
    public static final String WITHDRAW_TYPE = "1";
    public static final String FUND_TRANSFER_TYPE = "2";

    public TransactionService(AccountInfo accountInfo, AccountService accountService) {
        this.isExit = false;
        this.accountInfo = accountInfo;
        this.accountService = accountService;
    }

    @Override
    public void display() {
        Utils.clearConsole();
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");
        System.out.print("Please choose option[3]: ");
    }

    @Override
    public boolean process(Scanner scanner) {
        while (!this.isExit) {
            this.display();
            String transactionType = scanner.nextLine();
            switch (transactionType) {
                case WITHDRAW_TYPE:
                    IService withdrawService = new WithdrawService(this.accountInfo, this.accountService);
                    if (!withdrawService.process(scanner)) {
                        this.isExit = true;
                    }
                    break;
                case FUND_TRANSFER_TYPE:
                    IService fundTransferService = new FundTransferService(this.accountInfo, this.accountService);
                    fundTransferService.process(scanner);
                    break;
                default:
                    break;
            }
        }
        return true;
    }
}
