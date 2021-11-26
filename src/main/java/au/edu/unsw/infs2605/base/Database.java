/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.infs2605.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Win10
 */
public class Database {
    public String db = "jdbc:sqlite:foodDatabase.db";
    
    public void setupDatabase() throws ClassNotFoundException, SQLException {
        //Connect to Database
        Connection conn = DriverManager.getConnection(db);
        Statement st = conn.createStatement();
        
        // Create Groceries table
        String createGroceries = "CREATE TABLE IF NOT EXISTS Groceries"
                + "(ID INTEGER PRIMARY KEY autoincrement"
                + ", NAME TEXT NOT NULL"
                + ", PRICE INTEGER NOT NULL"
                + ");";
        st.execute(createGroceries);
        
        
        //Insert Groceries
        PreparedStatement pSt = conn.prepareStatement(
            "INSERT OR IGNORE INTO Groceries (id, name, price) VALUES (?,?,?)"
        );

        // Data to insert
        String[] names = {"Apple", "Bannana", "Milk", "Yoghurt Tub"};
        Double[] prices = {1.15, 1.50, 2.95, 3.95};
        

        // Loop to insert via sanitised prepared statements
        for (int i = 0; i < 4; i++) {
            pSt.setInt(1, i);
            pSt.setString(2, names[i]);
            pSt.setDouble(3, prices[i]);
            pSt.executeUpdate();
        }
        
        st.close();
        conn.close();
    }
    
    
    
    
}
