package com.company.Query;
import com.company.Connection.JDBC;

import java.sql.*;
import java.util.*;

public class GetPublishers {
    public static void main(String[] argv) {
        Statement stmt = null;
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            String publishers = "SELECT * FROM publishers";
            System.out.println("Show all publishers");
            ResultSet resultSet = stmt.executeQuery(publishers);

            while (resultSet.next()) {
                int id = resultSet.getInt("publisherID");
                String pubName = resultSet.getString("publisherName");
                System.out.println(id + "\t" + pubName);
            }

        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            JDBC.close();
        }
    }
}
