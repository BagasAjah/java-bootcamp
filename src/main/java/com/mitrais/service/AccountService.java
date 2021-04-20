package com.mitrais.service;

import com.mitrais.model.AccountInfo;
import com.mitrais.model.AccountLogin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountService {

    private AccountInfo userInfoOne = new AccountInfo("John Doe", 100, "222111", "112233");
    private AccountInfo userInfoTwo = new AccountInfo("Jane Doe", 30, "932012", "112244");
    private AccountInfo userInfoThree = new AccountInfo("Test", 50, "111222", "123123");
    private List<AccountInfo> userInfoList = Arrays.asList(userInfoOne, userInfoTwo, userInfoThree);
    private Map<String, AccountInfo> accountInfoMap = this.userInfoList.stream()
            .collect(Collectors.toMap(user -> user.getAccountNumber(), user -> user));

    public AccountService() {
    }

    public Map<String, AccountInfo> getUserMap() {
        return this.accountInfoMap;
    }

    public void updateAccountValue(AccountInfo accountInfo) {
        this.accountInfoMap.put(accountInfo.getAccountNumber(), accountInfo);
    }

    public AccountInfo getUserByAccountNumber(String accountNumber) {
        AccountService accountService = new AccountService();
        Map<String, AccountInfo> userInfoMap = accountService.getUserMap();
        return userInfoMap.get(accountNumber);
    }

    public AccountInfo validateCredentials(AccountLogin accountLogin) {
        Map<String, AccountInfo> userInfoMap = getUserMap();
        if (!userInfoMap.containsKey(accountLogin.getAccountNumber())) {
            return null;
        }
        AccountInfo userInfo = userInfoMap.get(accountLogin.getAccountNumber());
        if (!accountLogin.getPin().equals(userInfo.getPin())) {
            return null;
        }
        return userInfo;
    }
}
