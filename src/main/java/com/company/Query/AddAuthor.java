package com.company.Query;

import com.company.Connection.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddAuthor {
    public static void main(String[] args) {
        System.out.println("Please enter Author's First Name:");
        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.nextLine();
        System.out.println("Please enter Author's Last Name:");
        String lastName = scanner.nextLine();

        AddAuthor.addAuthor(firstName, lastName);
    }

    public static void addAuthor(String firstName, String lastName) {
        Statement stmt = null;
        try{

            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            String query = "INSERT INTO authors (firstName, lastName) VALUES ('" + firstName + "', '" + lastName + "')";
            int result = stmt.executeUpdate(query);
            if(result > 0) {
                System.out.println();

                String findAuthorIdQuery = "SELECT authorId FROM authors " +
                        "WHERE firstName = '" + firstName + "' AND lastName = '" + lastName + "' " +
                        "ORDER BY authorId LIMIT 1";

                ResultSet newAuthorId = stmt.executeQuery(findAuthorIdQuery);
                if(newAuthorId.next()) {
                    int authorId = newAuthorId.getInt("authorID");

                    StringBuilder isbn = new StringBuilder();
                    for(int i = 0; i < 10; i++) {
                        int number = getRandomNumber(0, 9);
                        isbn.append(number);
                    }

                    String isbnSting = isbn.toString();
                    String isbnQuery = "INSERT INTO authorisbn VALUES ('" + authorId + "', '" + isbnSting + "')";

                    int result2 = stmt.executeUpdate(isbnQuery);
                    String answer = "";
                    if (result2 == 1) {
                        answer = "The author was added SUCCESSFULLY";
                    }
                    System.out.println(answer);
                }

            }
        } catch(SQLException se) {
            se.printStackTrace();
        } finally {
            JDBC.close();
        }
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
