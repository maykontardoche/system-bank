package model;

public class SavingsAccount {
    private int id;
    private int accountId;
    private double interestRate;

    public SavingsAccount() {}

    public SavingsAccount(int accountId, double interestRate) {
        this.accountId = accountId;
        this.interestRate = interestRate;
    }

    public SavingsAccount(int id, int accountId, double interestRate) {
        this.id = id;
        this.accountId = accountId;
        this.interestRate = interestRate;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }

    @Override
    public String toString() {
        return "SavingsAccount [id=" + id + ", accountId=" + accountId + ", interestRate=" + interestRate + "]";
    }
}
