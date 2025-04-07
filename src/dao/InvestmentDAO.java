package dao;

import model.Investment;
import util.DBConnection;
import java.sql.*;

public class InvestmentDAO {

    public boolean createInvestment(Investment investment) {
        String sql = "INSERT INTO investments (account_id, investment_type, amount) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            stmt.setInt(1, investment.getAccountId());
            stmt.setString(2, investment.getInvestmentType());
            stmt.setDouble(3, investment.getAmount());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    investment.setId(rs.getInt(1));
                }
                return true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Investment getInvestmentById(int id) {
        String sql = "SELECT * FROM investments WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Investment(
                    rs.getInt("id"),
                    rs.getInt("account_id"),
                    rs.getString("investment_type"),
                    rs.getDouble("amount")
                );
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateInvestment(Investment investment) {
        String sql = "UPDATE investments SET amount = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setDouble(1, investment.getAmount());
            stmt.setInt(2, investment.getId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
