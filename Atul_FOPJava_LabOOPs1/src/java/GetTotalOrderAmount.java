package java;

import java.sql.*;

public class GetTotalOrderAmount {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ecommerce";
        String username = "your_username";
        String password = "your_password";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {
            
            String query = "SELECT c.CUS_NAME, SUM(o.ORD_AMOUNT) AS total_amount " +
                           "FROM customer c INNER JOIN order_table o ON c.CUS_ID = o.CUS_ID " +
                           "GROUP BY c.CUS_NAME";
            ResultSet rs = stmt.executeQuery(query);
            
            System.out.println("Customer Order Amounts:");
            while (rs.next()) {
                String customerName = rs.getString("CUS_NAME");
                int totalAmount = rs.getInt("total_amount");
                System.out.println("Customer: " + customerName + ", Total Amount: " + totalAmount);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
