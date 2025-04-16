package com.example.banking.model;

public class CheckingAccount extends Account {

    public CheckingAccount(int accountNumber, String clientName) {
        super(accountNumber, clientName);
    }
    
    @Override
    public String getAccountType() {
        return "Conta Corrente";
    }
}
