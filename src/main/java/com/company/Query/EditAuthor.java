package com.company.Query;

import com.company.Connection.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EditAuthor {
    public static void main(String[] args) {

        System.out.println("Please enter Author's id:");
        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.nextLine();
    }

    public static void editPublisher(String publisherToEdit, String newPublisherName) {
        Statement stmt = null;
        try{

            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            String search = "SELECT * FROM publishers WHERE publisherName = '"+ publisherToEdit +"'";
            ResultSet searchResult = stmt.executeQuery(search);

            if(searchResult.next()) {
                String query = "UPDATE publishers SET publisherName = '"+newPublisherName+"' WHERE publisherName = '"+ publisherToEdit +"'";
                int result = stmt.executeUpdate(query);
                if(result == 1) {
                    System.out.println("Publisher was successfully updated");
                }else {
                    System.out.println("Update failed");
                }
            }else {
                System.out.println("Publisher not found");
            }
            System.out.println("------------");

        } catch(SQLException se) {
            se.printStackTrace();
        } finally {
            JDBC.close();
        }
    }
}
