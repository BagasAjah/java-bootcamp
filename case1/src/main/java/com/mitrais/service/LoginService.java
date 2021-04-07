package com.mitrais.service;

import com.mitrais.model.AccountInfo;
import com.mitrais.model.AccountLogin;
import com.mitrais.util.FormatUtils;

import java.util.Map;
import java.util.function.Function;

public class LoginService {

    private AccountInfo validUser;
    private AccountService accountService;

    public LoginService(AccountService accountService) {
        this.accountService = accountService;
    }

    public boolean validate(AccountLogin userLogin) {
        if (!this.validateAccountNumber.apply(userLogin.getAccountNumber())) {
            return false;
        }
        if (!this.validatePin.apply(userLogin.getPin())) {
            return false;
        }
        if (!this.validateUserInfo.apply(userLogin)) {
            return false;
        }
        return true;
    }

    private Function<String, Boolean> validatePin = pin -> {
        final int MAX_PIN_LENGTH = 6;
        if (MAX_PIN_LENGTH != pin.length()) {
            System.out.println("PIN should have " + MAX_PIN_LENGTH + " digits length");
            return false;
        }
        if (!FormatUtils.isNumeric(pin)) {
            System.out.println("PIN should only contains numbers");
            return false;
        }
        return true;
    };

    private Function<String, Boolean> validateAccountNumber = accountNumber -> {
        final int MAX_ACCOUNT_NUMBER_LENGTH = 6;
        if (MAX_ACCOUNT_NUMBER_LENGTH != accountNumber.length()) {
            System.out.println("Account Number should have " + MAX_ACCOUNT_NUMBER_LENGTH + " digits length.");
            return false;
        }
        if (!FormatUtils.isNumeric(accountNumber)) {
            System.out.println("Account Number should only contains numbers");
            return false;
        }
        return true;
    };

    private Function<AccountLogin, Boolean> validateUserInfo = userLogin -> {
        Map<String, AccountInfo> userInfoMap = accountService.getUserMap();
        if (!userInfoMap.containsKey(userLogin.getAccountNumber())) {
            System.out.println("Invalid Account Number/PIN");
            return false;
        }
        AccountInfo userInfo = userInfoMap.get(userLogin.getAccountNumber());
        if (!userLogin.getPin().equals(userInfo.getPin())) {
            System.out.println("Invalid Account Number/PIN");
            return false;
        }
        this.validUser = userInfo;
        return true;
    };

    public AccountInfo getValidUser() {
        return this.validUser;
    }
}
