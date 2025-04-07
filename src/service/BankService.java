package service;

import dao.AccountDAO;
import dao.SavingsAccountDAO;
import dao.InvestmentDAO;
import dao.CardDAO;
import model.Account;
import model.SavingsAccount;
import model.Investment;
import model.Card;

public class BankService {

    private AccountDAO accountDAO = new AccountDAO();
    private SavingsAccountDAO savingsDAO = new SavingsAccountDAO();
    private InvestmentDAO investmentDAO = new InvestmentDAO();
    private CardDAO cardDAO = new CardDAO();

    // Criação de conta – se for poupança, cria registro na tabela de savings
    public boolean createAccount(String accountNumber, String holderName, double initialBalance, String accountType) {
        Account account = new Account(accountNumber, holderName, initialBalance, accountType);
        boolean created = accountDAO.createAccount(account);
        if(created && accountType.equalsIgnoreCase("savings")){
            // Exemplo: taxa de juros padrão de 0.5%
            SavingsAccount savings = new SavingsAccount(account.getId(), 0.005);
            savingsDAO.createSavingsAccount(savings);
        }
        return created;
    }

    // Depósito
    public boolean deposit(String accountNumber, double amount) {
        Account account = accountDAO.getAccountByNumber(accountNumber);
        if(account != null && amount > 0) {
            account.setBalance(account.getBalance() + amount);
            return accountDAO.updateAccount(account);
        }
        return false;
    }

    // Saque
    public boolean withdraw(String accountNumber, double amount) {
        Account account = accountDAO.getAccountByNumber(accountNumber);
        if(account != null && amount > 0 && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            return accountDAO.updateAccount(account);
        }
        return false;
    }

    // Transferência entre contas
    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = accountDAO.getAccountByNumber(fromAccountNumber);
        Account toAccount = accountDAO.getAccountByNumber(toAccountNumber);
        if(fromAccount != null && toAccount != null && amount > 0 && fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            boolean updatedFrom = accountDAO.updateAccount(fromAccount);
            boolean updatedTo = accountDAO.updateAccount(toAccount);
            return updatedFrom && updatedTo;
        }
        return false;
    }

    // Aplica juros na conta poupança
    public boolean applyInterest(String accountNumber) {
        Account account = accountDAO.getAccountByNumber(accountNumber);
        if(account != null && account.getAccountType().equalsIgnoreCase("savings")){
            SavingsAccount savings = savingsDAO.getSavingsAccountByAccountId(account.getId());
            if(savings != null) {
                double interest = account.getBalance() * savings.getInterestRate();
                account.setBalance(account.getBalance() + interest);
                return accountDAO.updateAccount(account);
            }
        }
        return false;
    }

    // Realiza investimento (deduz o valor da conta e cria registro de investimento)
    public boolean invest(String accountNumber, String investmentType, double amount) {
        Account account = accountDAO.getAccountByNumber(accountNumber);
        if(account != null && amount > 0 && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            boolean updated = accountDAO.updateAccount(account);
            if(updated) {
                Investment investment = new Investment(account.getId(), investmentType, amount);
                return investmentDAO.createInvestment(investment);
            }
        }
        return false;
    }

    // Emite cartão vinculado à conta (para crédito pode definir limite)
    public boolean issueCard(String accountNumber, String cardType, String cardNumber, Double limitAmount) {
        Account account = accountDAO.getAccountByNumber(accountNumber);
        if(account != null) {
            Card card = new Card(account.getId(), cardType, cardNumber, limitAmount);
            return cardDAO.createCard(card);
        }
        return false;
    }
}
