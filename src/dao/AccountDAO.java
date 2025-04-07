package dao;

import model.Account;
import util.DBConnection;
import java.sql.*;

public class AccountDAO {

    public boolean createAccount(Account account) {
        String sql = "INSERT INTO accounts (account_number, holder_name, balance, account_type) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            stmt.setString(1, account.getAccountNumber());
            stmt.setString(2, account.getHolderName());
            stmt.setDouble(3, account.getBalance());
            stmt.setString(4, account.getAccountType());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    account.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Account getAccountByNumber(String accountNumber) {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Account(
                    rs.getInt("id"),
                    rs.getString("account_number"),
                    rs.getString("holder_name"),
                    rs.getDouble("balance"),
                    rs.getString("account_type")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Método auxiliar para buscar conta pelo id (se necessário)
    public Account getAccountById(int id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Account(
                    rs.getInt("id"),
                    rs.getString("account_number"),
                    rs.getString("holder_name"),
                    rs.getDouble("balance"),
                    rs.getString("account_type")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAccount(Account account) {
        String sql = "UPDATE accounts SET holder_name = ?, balance = ?, account_type = ? WHERE account_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, account.getHolderName());
            stmt.setDouble(2, account.getBalance());
            stmt.setString(3, account.getAccountType());
            stmt.setString(4, account.getAccountNumber());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
