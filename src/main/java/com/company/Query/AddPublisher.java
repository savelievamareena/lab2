package com.company.Query;

import com.company.Connection.JDBC;

import java.sql.SQLException;
import java.sql.Statement;

public class AddPublisher {
    public static void main(String[] args) {

        String[] publishers = {"New Publisher", "Best Pub"};
        AddPublisher.addPublisher(publishers);
    }

    public static void addPublisher(String[] publishers) {
        Statement stmt = null;
        try{

            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            for (String publisher : publishers) {
                String query = "INSERT INTO publishers (publisherName) VALUES ('" + publisher + "')";
                int rs1 = stmt.executeUpdate(query);
                String answer = "";
                if (rs1 == 1) {
                    answer = "New publisher " + publisher + " added!";
                }else {
                    answer = "New publisher " + publisher + " NOT added!";
                }
                System.out.println(answer);
            }

        } catch(SQLException se) {
            se.printStackTrace();
        } finally {
            JDBC.close();
        }
    }
}
