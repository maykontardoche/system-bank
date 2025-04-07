package dao;

import model.Card;
import util.DBConnection;
import java.sql.*;

public class CardDAO {

    public boolean createCard(Card card) {
        String sql = "INSERT INTO cards (account_id, card_type, card_number, limit_amount) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            stmt.setInt(1, card.getAccountId());
            stmt.setString(2, card.getCardType());
            stmt.setString(3, card.getCardNumber());
            if(card.getLimitAmount() != null) {
                stmt.setDouble(4, card.getLimitAmount());
            } else {
                stmt.setNull(4, Types.DOUBLE);
            }
            int affectedRows = stmt.executeUpdate();
            if(affectedRows > 0){
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    card.setId(rs.getInt(1));
                }
                return true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Card getCardByNumber(String cardNumber) {
        String sql = "SELECT * FROM cards WHERE card_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, cardNumber);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Card(
                    rs.getInt("id"),
                    rs.getInt("account_id"),
                    rs.getString("card_type"),
                    rs.getString("card_number"),
                    rs.getObject("limit_amount") != null ? rs.getDouble("limit_amount") : null
                );
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateCard(Card card) {
        String sql = "UPDATE cards SET limit_amount = ? WHERE card_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            if(card.getLimitAmount() != null) {
                stmt.setDouble(1, card.getLimitAmount());
            } else {
                stmt.setNull(1, Types.DOUBLE);
            }
            stmt.setString(2, card.getCardNumber());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
