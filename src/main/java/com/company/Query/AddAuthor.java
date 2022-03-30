package com.company.Query;

import com.company.Connection.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddAuthor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Author's First Name:");
        String firstName = scanner.nextLine();
        System.out.println("Please enter Author's Last Name:");
        String lastName = scanner.nextLine();

        int authorId = AddAuthor.addAuthor(firstName, lastName);
    }

    public static int addAuthor(String firstName, String lastName) {
        int authorId = -1;
        Statement stmt = null;
        try{

            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            String query = "INSERT INTO authors (firstName, lastName) VALUES ('" + firstName + "', '" + lastName + "')";
            int result = stmt.executeUpdate(query);
            if(result > 0) {
                authorId = GetAuthorId.getAuthorId(firstName, lastName);
                if(authorId >= 0) {
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
        return authorId;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
