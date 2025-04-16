package com.example.banking.model;

public abstract class Account {
    protected int accountNumber;
    protected String clientName;
    protected double balance;
    
    public Account(int accountNumber, String clientName) {
        this.accountNumber = accountNumber;
        this.clientName = clientName;
        this.balance = 0.0;
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getClientName() {
        return clientName;
    }
    
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        if(amount > 0){
            balance += amount;
        }
    }
    
    public boolean withdraw(double amount) {
        if(amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
    public abstract String getAccountType();
    
    public void displayDetails() {
        System.out.println("\n============ Detalhes da Conta ============");
        System.out.println("Tipo: " + getAccountType());
        System.out.println("Número da Conta: " + accountNumber);
        System.out.println("Cliente: " + clientName);
        System.out.printf("Saldo: R$ %.2f\n", balance);
    }
}
