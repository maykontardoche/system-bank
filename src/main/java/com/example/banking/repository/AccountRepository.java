package com.example.banking.repository;

import com.example.banking.model.Account;
import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    private Map<Integer, Account> accountMap;
    
    public AccountRepository() {
        accountMap = new HashMap<>();
    }
    
    public void addAccount(Account account) {
        accountMap.put(account.getAccountNumber(), account);
    }
    
    public Account getAccount(int accountNumber) {
        return accountMap.get(accountNumber);
    }
    
    // Métodos adicionais para remoção ou listagem podem ser adicionados conforme necessário.
}
