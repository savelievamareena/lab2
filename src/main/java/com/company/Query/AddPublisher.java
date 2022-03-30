package com.company.Query;

import com.company.Connection.JDBC;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddPublisher {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Publisher Name:");
        String publisher = scanner.nextLine();
        AddPublisher.addPublisher(publisher);
    }

    public static int addPublisher(String publisher) {
        int result = -1;
        Statement stmt = null;
        try{

            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            String query = "INSERT INTO publishers (publisherName) VALUES ('" + publisher + "')";
            int rs1 = stmt.executeUpdate(query);
            String answer = "";
            if (rs1 == 1) {
                answer = "New publisher " + publisher + " added!";
                result = GetPublisherId.getPublisherId(publisher);
            }else {
                answer = "New publisher " + publisher + " NOT added!";
            }
            System.out.println(answer);

        } catch(SQLException se) {
            se.printStackTrace();
        } finally {
            JDBC.close();
        }

        return result;
    }
}
