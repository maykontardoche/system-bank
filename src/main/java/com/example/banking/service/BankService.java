package com.example.banking.service;

import com.example.banking.model.*;
import com.example.banking.repository.AccountRepository;

public class BankService {
    private AccountRepository accountRepository;
    private int accountSequence = 1000; // Numero base para as contas

    public BankService() {
        accountRepository = new AccountRepository();
    }
    
    // Cria uma conta com base no tipo (1: Corrente, 2: Poupança, 3: Investimentos)
    public Account createAccount(String clientName, int accountTypeOption) {
        accountSequence++;
        Account account = null;
        
        switch(accountTypeOption) {
            case 1:
                account = new CheckingAccount(accountSequence, clientName);
                break;
            case 2:
                account = new SavingsAccount(accountSequence, clientName);
                break;
            case 3:
                account = new InvestmentAccount(accountSequence, clientName);
                break;
            default:
                System.out.println("Tipo de conta invalido.");
                break;
        }
        
        if(account != null) {
            accountRepository.addAccount(account);
            System.out.println("Conta criada com sucesso. Numero: " + account.getAccountNumber());
        }
        return account;
    }
    
    public boolean updateAccount(int accountNumber, String newClientName) {
        Account account = accountRepository.getAccount(accountNumber);
        if(account != null) {
            account.setClientName(newClientName);
            System.out.println("Conta atualizada com sucesso.");
            return true;
        }
        System.out.println("Conta nao encontrada.");
        return false;
    }
    
    public boolean deposit(int accountNumber, double amount) {
        Account account = accountRepository.getAccount(accountNumber);
        if(account != null) {
            account.deposit(amount);
            System.out.println("Depósito realizado com sucesso.");
            return true;
        }
        System.out.println("Conta nao encontrada.");
        return false;
    }
    
    public boolean withdraw(int accountNumber, double amount) {
        Account account = accountRepository.getAccount(accountNumber);
        if(account != null) {
            if(account.withdraw(amount)) {
                System.out.println("Saque realizado com sucesso.");
                return true;
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            System.out.println("Conta nao encontrada.");
        }
        return false;
    }
    
    // Transferência ou PIX entre contas
    public boolean transfer(int fromAccountNumber, int toAccountNumber, double amount) {
        Account fromAccount = accountRepository.getAccount(fromAccountNumber);
        Account toAccount = accountRepository.getAccount(toAccountNumber);
        if(fromAccount == null || toAccount == null) {
            System.out.println("Conta de origem ou destino nao encontrada.");
            return false;
        }
        if(fromAccount.withdraw(amount)) {
            toAccount.deposit(amount);
            System.out.println("Transferência realizada com sucesso.");
            return true;
        } else {
            System.out.println("Saldo insuficiente para transferência.");
        }
        return false;
    }
    
    public void displayAccountDetails(int accountNumber) {
        Account account = accountRepository.getAccount(accountNumber);
        if(account != null) {
            account.displayDetails();
        } else {
            System.out.println("Conta nao encontrada.");
        }
    }
    
    // Aplica juros na conta poupança
    public boolean applyInterest(int accountNumber) {
        Account account = accountRepository.getAccount(accountNumber);
        if(account != null && account instanceof SavingsAccount) {
            ((SavingsAccount) account).applyInterest();
            return true;
        } else {
            System.out.println("Conta nao encontrada ou nao é do tipo poupança.");
        }
        return false;
    }
    
    // Realiza um investimento na conta de investimentos
    public boolean invest(int accountNumber, double amount) {
        Account account = accountRepository.getAccount(accountNumber);
        if(account != null && account instanceof InvestmentAccount) {
            ((InvestmentAccount) account).invest(amount);
            return true;
        } else {
            System.out.println("Conta nao encontrada ou nao é do tipo investimentos.");
        }
        return false;
    }
}
