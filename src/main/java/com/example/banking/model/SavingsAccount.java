package com.example.banking.model;

public class SavingsAccount extends Account {
    private double interestRate;
    
    public SavingsAccount(int accountNumber, String clientName) {
        super(accountNumber, clientName);
        this.interestRate = 0.01; // 1% de juros padrão
    }
    
    @Override
    public String getAccountType() {
        return "Conta Poupança";
    }
    
    public void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.printf("Juros aplicados: R$ %.2f\n", interest);
    }
    
    public double getInterestRate() {
        return interestRate;
    }
    
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
