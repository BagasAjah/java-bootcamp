package com.mitrais;

import com.mitrais.service.LoginService;

import java.util.Scanner;

public class AtmMainController {
    public static void main(String args[]){
        try (Scanner inputAccountNumber = new Scanner(System.in);
             Scanner inputPin = new Scanner(System.in)) {
            System.out.print("Enter your Account Number: ");
            String accountNumber = inputAccountNumber.nextLine();
            System.out.print("Enter your PIN: ");
            String pin = inputPin.nextLine();
            LoginService loginService = new LoginService();
            loginService.validate(accountNumber, pin);
        }
    }
}