package java;

import java.sql.*;

public class GetAverageProductRating {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ecommerce";
        String username = "your_username";
        String password = "your_password";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {
            
            String query = "SELECT p.PRO_NAME, AVG(r.RAT_RATSTARS) AS average_rating " +
                           "FROM product p LEFT JOIN supplier_pricing sp ON p.PRO_ID = sp.PRO_ID " +
                           "LEFT JOIN rating r ON sp.PRICING_ID = r.ORD_ID " +
                           "GROUP BY p.PRO_NAME";
            ResultSet rs = stmt.executeQuery(query);
            
            System.out.println("Product Average Ratings:");
            while (rs.next()) {
                String productName = rs.getString("PRO_NAME");
                double averageRating = rs.getDouble("average_rating");
                System.out.println("Product: " + productName + ", Average Rating: " + averageRating);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
