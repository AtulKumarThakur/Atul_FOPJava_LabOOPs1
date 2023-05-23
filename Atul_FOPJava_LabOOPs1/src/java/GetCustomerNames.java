package java;

import java.sql.*;

public class GetCustomerNames {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ecommerce";
        String username = "your_username";
        String password = "your_password";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {
            
            String query = "SELECT DISTINCT CUS_NAME FROM customer c INNER JOIN order_table o ON c.CUS_ID = o.CUS_ID";
            ResultSet rs = stmt.executeQuery(query);
            
            System.out.println("Customer Names:");
            while (rs.next()) {
                String customerName = rs.getString("CUS_NAME");
                System.out.println(customerName);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
