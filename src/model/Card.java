package model;

public class Card {
    private int id;
    private int accountId;
    private String cardType; // Valores: credit ou debit
    private String cardNumber;
    private Double limitAmount; // Apenas para cartão de crédito

    public Card() {}

    public Card(int accountId, String cardType, String cardNumber, Double limitAmount) {
        this.accountId = accountId;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.limitAmount = limitAmount;
    }

    public Card(int id, int accountId, String cardType, String cardNumber, Double limitAmount) {
        this.id = id;
        this.accountId = accountId;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.limitAmount = limitAmount;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public String getCardType() { return cardType; }
    public void setCardType(String cardType) { this.cardType = cardType; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public Double getLimitAmount() { return limitAmount; }
    public void setLimitAmount(Double limitAmount) { this.limitAmount = limitAmount; }

    @Override
    public String toString() {
        return "Card [id=" + id + ", accountId=" + accountId + ", cardType=" + cardType 
                + ", cardNumber=" + cardNumber + ", limitAmount=" + limitAmount + "]";
    }
}
