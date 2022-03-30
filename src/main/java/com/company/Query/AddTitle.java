package com.company.Query;

import com.company.Connection.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddTitle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Title:");
        String title = scanner.nextLine();

        System.out.println("Please enter Author's First Name:");
        String authorFirst = scanner.nextLine();

        System.out.println("Please enter Author's Last Name:");
        String authorLast = scanner.nextLine();

        System.out.println("Please enter Publisher:");
        String publisher = scanner.nextLine();

        System.out.println("Please enter Edition Number:");
        String editionNumber = scanner.nextLine();

        System.out.println("Please enter Year:");
        String year = scanner.nextLine();

        System.out.println("Please enter Price:");
        String price = scanner.nextLine();

        AddTitle.addTitle(title, authorFirst, authorLast, publisher, editionNumber, year, price);
    }

    public static void addTitle(String title, String authorFirst, String authorLast, String publisher, String editionNumber, String year, String price) {
        Statement stmt = null;
        int authorId;
        int publisherId;

        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            authorId = GetAuthorId.getAuthorId(authorFirst, authorLast);
            if(authorId <= 0) {
                authorId = AddAuthor.addAuthor(authorFirst, authorLast);
            }

            publisherId = GetPublisherId.getPublisherId(publisher);
            if(publisherId <= 0) {
                publisherId = AddPublisher.addPublisher(publisher);
            }

            String mainQuery = "INSERT INTO titles VALUES ((SELECT isbn FROM authorisbn " +
                    "WHERE authorID = '" + authorId + "'), '" + title + "', '" + editionNumber + "', '" + year + "', '" + publisherId + "', '" + price + "')";

            int queryResult = stmt.executeUpdate(mainQuery);
            String answer;
            if (queryResult == 1) {
                answer = "New title " + title + " added!";
            }else {
                answer = "New title " + title + " NOT added!";
            }
            System.out.println(answer);

        }catch(SQLException se) {
            se.printStackTrace();
        } finally {
            JDBC.close();
        }
    }
}
