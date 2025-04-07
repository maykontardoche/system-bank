package dao;

import model.SavingsAccount;
import util.DBConnection;
import java.sql.*;

public class SavingsAccountDAO {

    public boolean createSavingsAccount(SavingsAccount savingsAccount) {
        String sql = "INSERT INTO savings_accounts (account_id, interest_rate) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            stmt.setInt(1, savingsAccount.getAccountId());
            stmt.setDouble(2, savingsAccount.getInterestRate());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    savingsAccount.setId(rs.getInt(1));
                }
                return true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public SavingsAccount getSavingsAccountByAccountId(int accountId) {
        String sql = "SELECT * FROM savings_accounts WHERE account_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new SavingsAccount(
                    rs.getInt("id"),
                    rs.getInt("account_id"),
                    rs.getDouble("interest_rate")
                );
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateSavingsAccount(SavingsAccount savingsAccount) {
        String sql = "UPDATE savings_accounts SET interest_rate = ? WHERE account_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setDouble(1, savingsAccount.getInterestRate());
            stmt.setInt(2, savingsAccount.getAccountId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
