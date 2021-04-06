package com.mitrais.service;

import com.mitrais.model.AccountInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserService {

    private List<AccountInfo> userInfoList = new ArrayList<>();

    public UserService() {
        AccountInfo userInfoOne = new AccountInfo("John Doe", 100, "012108", "112233");
        AccountInfo userInfoTwo = new AccountInfo("Jane Doe", 30, "932012", "112244");
        AccountInfo userInfoThree = new AccountInfo("Test", 50, "111222", "123123");
        userInfoList = Arrays.asList(userInfoOne, userInfoTwo, userInfoThree);
    }

    public Map<String, AccountInfo> getUserMap() {
        return userInfoList.stream()
                .collect(Collectors.toMap(user -> user.getAccountNumber(), user -> user));
    }
}
