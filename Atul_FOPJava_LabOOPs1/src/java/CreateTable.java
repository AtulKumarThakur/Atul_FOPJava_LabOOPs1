package java;

import java.sql.*;

public class CreateTable {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ecommerce";
        String username = "your_username";
        String password = "your_password";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {
            
            // Create supplier table
            String createSupplierTable = "CREATE TABLE supplier (" +
                    "SUPP_ID INT PRIMARY KEY," +
                    "SUPP_NAME VARCHAR(50) NOT NULL," +
                    "SUPP_CITY VARCHAR(50) NOT NULL," +
                    "SUPP_PHONE VARCHAR(50) NOT NULL)";
            stmt.executeUpdate(createSupplierTable);
            
            // Create customer table
            String createCustomerTable = "CREATE TABLE customer (" +
                    "CUS_ID INT PRIMARY KEY," +
                    "CUS_NAME VARCHAR(20) NOT NULL," +
                    "CUS_PHONE VARCHAR(10) NOT NULL," +
                    "CUS_CITY VARCHAR(30) NOT NULL," +
                    "CUS_GENDER CHAR)";
            stmt.executeUpdate(createCustomerTable);
            
            // Create category table
            String createCategoryTable = "CREATE TABLE category (" +
                    "CAT_ID INT PRIMARY KEY," +
                    "CAT_NAME VARCHAR(20) NOT NULL)";
            stmt.executeUpdate(createCategoryTable);
            
            // Create product table
            String createProductTable = "CREATE TABLE product (" +
                    "PRO_ID INT PRIMARY KEY," +
                    "PRO_NAME VARCHAR(20) NOT NULL DEFAULT 'Dummy'," +
                    "PRO_DESC VARCHAR(60)," +
                    "CAT_ID INT," +
                    "FOREIGN KEY (CAT_ID) REFERENCES category(CAT_ID))";
            stmt.executeUpdate(createProductTable);
            
            // Create supplier_pricing table
            String createSupplierPricingTable = "CREATE TABLE supplier_pricing (" +
                    "PRICING_ID INT PRIMARY KEY," +
                    "PRO_ID INT," +
                    "SUPP_ID INT," +
                    "SUPP_PRICE INT DEFAULT 0," +
                    "FOREIGN KEY (PRO_ID) REFERENCES product(PRO_ID)," +
                    "FOREIGN KEY (SUPP_ID) REFERENCES supplier(SUPP_ID))";
            stmt.executeUpdate(createSupplierPricingTable);
            
            // Create order table
            String createOrderTable = "CREATE TABLE order_table (" +
                    "ORD_ID INT PRIMARY KEY," +
                    "ORD_AMOUNT INT NOT NULL," +
                    "ORD_DATE DATE NOT NULL," +
                    "CUS_ID INT," +
                    "PRICING_ID INT," +
                    "FOREIGN KEY (CUS_ID) REFERENCES customer(CUS_ID)," +
                    "FOREIGN KEY (PRICING_ID) REFERENCES supplier_pricing(PRICING_ID))";
            stmt.executeUpdate(createOrderTable);
            
            // Create rating table
            String createRatingTable = "CREATE TABLE rating (" +
                    "RAT_ID INT PRIMARY KEY," +
                    "ORD_ID INT," +
                    "RAT_RATSTARS INT NOT NULL," +
                    "FOREIGN KEY (ORD_ID) REFERENCES order_table(ORD_ID))";
            stmt.executeUpdate(createRatingTable);
            
            System.out.println("Tables created successfully.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
