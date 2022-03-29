package com.company.Query;

import com.company.Connection.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EditAuthor {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter Author's id:");
        String authorId = scanner.nextLine();
        System.out.println("Please enter Author's new FIRST name:");
        String firstName = scanner.nextLine();
        System.out.println("Please enter Author's new LAST name:");
        String lastName = scanner.nextLine();

        EditAuthor.editAuthor(authorId, firstName, lastName);
    }
    public static void editAuthor(String authorId, String firstName, String lastName) {
        Statement stmt = null;
        try{
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            String search = "SELECT * FROM authors WHERE authorId = '"+ authorId +"'";
            ResultSet searchResult = stmt.executeQuery(search);

            if(searchResult.next()) {
                String query = "UPDATE authors SET firstName = '" + firstName + "', lastName = '" + lastName + "' " +
                        "WHERE authorID = '" + authorId + "'";
                int result = stmt.executeUpdate(query);
                if(result == 1) {
                    System.out.println("Author was successfully updated");
                }else {
                    System.out.println("Update failed");
                }
            }else {
                System.out.println("Author not found");
            }
            System.out.println("------------");

        } catch(SQLException se) {
            se.printStackTrace();
        } finally {
            JDBC.close();
        }
    }
}
