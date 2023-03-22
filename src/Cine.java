/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author alumnogreibd
 */

import java.sql.*;

public class Cine {

    public static void main(String[] args) {
        
        //Tras crear una base de datos en DBeaver llamada "Cine"
        
        Connection c = null;
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager
              .getConnection("jdbc:postgresql://localhost:5432/Cine",
              "postgres", "greibd2021");
           
        System.out.println("Opened database successfully");

         Statement stmt = c.createStatement();
         String sql = "CREATE TABLE COMPANY " +
            "(ID INT PRIMARY KEY     NOT NULL," +
            " NAME           TEXT    NOT NULL, " +
            " AGE            INT     NOT NULL, " +
            " ADDRESS        CHAR(50), " +
            " SALARY         REAL)";
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
         
        } catch (Exception e) {
           e.printStackTrace();
           System.err.println(e.getClass().getName()+": "+e.getMessage());
           System.exit(0);
        }       
        
    }
}
