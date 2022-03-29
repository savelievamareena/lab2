package com.company.Query;

import com.company.Connection.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GetBooksByPublisher {
    public static void main(String[] args) {

        System.out.println("Please enter Book's TITLE or Publisher's ID:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try{
            int inputInt = Integer.parseInt(input);
            GetBooksByPublisher.getBooksById(inputInt);
        }catch (NumberFormatException ex) {
            GetBooksByPublisher.getBooksByPubName(input);
        }
    }

    public static void getBooksById(int publisher) {
        String query = "SELECT * FROM Titles WHERE publisherID = '" + publisher + "'";
        GetBooksByPublisher.getBooks(query);
    }

    public static void getBooksByPubName(String title) {
        String query = "SELECT * FROM Titles WHERE title = '" + title + "'";
        GetBooksByPublisher.getBooks(query);
    }

    public static void getBooks(String query) {
        Statement stmt = null;
        try{

            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            ResultSet rs1 = stmt.executeQuery(query);
            while (rs1.next()) {
                int isbn = rs1.getInt("isbn");
                String title = rs1.getString("title");
                String year = rs1.getString("year");
                String price = rs1.getString("price");
                System.out.println(isbn + "\t" + title + "\t" + year + "\t" + price);
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
