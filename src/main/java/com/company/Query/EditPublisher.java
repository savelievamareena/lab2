package com.company.Query;

import com.company.Connection.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditPublisher {
    public static void main(String[] args) {

        String publisherToEdit = "Money";
        String newPublisherName = "CodeacAdemy";
        EditPublisher.editPublisher(publisherToEdit, newPublisherName);
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
