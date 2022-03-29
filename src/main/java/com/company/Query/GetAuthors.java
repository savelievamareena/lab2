package com.company.Query;

import com.company.Connection.JDBC;

import java.sql.*;
import java.util.*;

public class GetAuthors {

    public static void main(String[] args) {
        Statement stmt = null;
        try{

            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            String exampleQuery1 = "SELECT * FROM Authors ORDER BY lastName, firstName";
            System.out.println("Authors:");
            ResultSet rs1 = stmt.executeQuery(exampleQuery1);
            while (rs1.next()) {
                int id = rs1.getInt("authorID");
                String lastName = rs1.getString("lastName");
                String firstName = rs1.getString("firstName");
                System.out.println(id + "\t" + lastName + "\t" + firstName);
            }
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block, used to close resources
            JDBC.close();
        }
    }
}