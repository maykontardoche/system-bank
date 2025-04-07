-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS InfinityBank;
USE InfinityBank;

-- Tabela de Contas
CREATE TABLE IF NOT EXISTS accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(50) UNIQUE NOT NULL,
    holder_name VARCHAR(100) NOT NULL,
    balance DOUBLE NOT NULL,
    account_type VARCHAR(20) NOT NULL  -- Valores: checking, savings, investment
);

-- Tabela de Contas Poupança (Savings)
CREATE TABLE IF NOT EXISTS savings_accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT NOT NULL,
    interest_rate DOUBLE NOT NULL,
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);

-- Tabela de Investimentos
CREATE TABLE IF NOT EXISTS investments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT NOT NULL,
    investment_type VARCHAR(50) NOT NULL, -- Ex.: stocks, bonds, etc.
    amount DOUBLE NOT NULL,
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);

-- Tabela de Cartões
CREATE TABLE IF NOT EXISTS cards (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT NOT NULL,
    card_type VARCHAR(20) NOT NULL,  -- Valores: credit ou debit
    card_number VARCHAR(16) UNIQUE NOT NULL,
    limit_amount DOUBLE,  -- Apenas para cartão de crédito
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);
