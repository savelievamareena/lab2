package com.company.Query;

import com.company.Connection.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetPublisherId {

    public static int getPublisherId(String publisher) {
        int result = 0;
        Statement stmt = null;

        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            String getAuthorIdQuery = "SELECT publisherID FROM publishers WHERE publisherName = '" + publisher + "' LIMIT 1";
            ResultSet authorExisted = stmt.executeQuery(getAuthorIdQuery);
            if(authorExisted.next()) {
                result = authorExisted.getInt("publisherID");
            }

        }catch(SQLException se) {
            se.printStackTrace();
        } finally {
            JDBC.close();
        }

        return result;
    }
}
