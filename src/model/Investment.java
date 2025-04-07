package model;

public class Investment {
    private int id;
    private int accountId;
    private String investmentType; // Ex.: stocks, bonds, etc.
    private double amount;

    public Investment() {}

    public Investment(int accountId, String investmentType, double amount) {
        this.accountId = accountId;
        this.investmentType = investmentType;
        this.amount = amount;
    }

    public Investment(int id, int accountId, String investmentType, double amount) {
        this.id = id;
        this.accountId = accountId;
        this.investmentType = investmentType;
        this.amount = amount;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public String getInvestmentType() { return investmentType; }
    public void setInvestmentType(String investmentType) { this.investmentType = investmentType; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    @Override
    public String toString() {
        return "Investment [id=" + id + ", accountId=" + accountId + ", investmentType=" + investmentType + ", amount=" + amount + "]";
    }
}
