package com.example.banking.model;

public class InvestmentAccount extends Account {

    public InvestmentAccount(int accountNumber, String clientName) {
        super(accountNumber, clientName);
    }
    
    @Override
    public String getAccountType() {
        return "Conta de Investimentos";
    }
    
    // Método para simulação de investimento:
    // Retira o valor investido, simula um retorno de 10% e deposita o retorno.
    public void invest(double amount) {
        if(amount > 0 && withdraw(amount)) {
            double returnValue = amount * 1.1; // Simula um retorno de 10%
            deposit(returnValue);
            System.out.printf("Investimento realizado. Valor investido: R$ %.2f, Retorno: R$ %.2f\n", amount, returnValue);
        } else {
            System.out.println("Saldo insuficiente para investimento.");
        }
    }
}
