package com.company.Query;

import com.company.Connection.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetAuthorId {

    public static int getAuthorId(String first, String last) {
        int result = 0;
        Statement stmt = null;

        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            String getAuthorIdQuery = "SELECT authorId FROM authors " +
                    "WHERE firstName = '" + first + "' AND lastName = '" + last + "' LIMIT 1";
            ResultSet authorExisted = stmt.executeQuery(getAuthorIdQuery);
            if(authorExisted.next()) {
                result = authorExisted.getInt("authorID");
            }

        }catch(SQLException se) {
            se.printStackTrace();
        } finally {
            JDBC.close();
        }

        return result;
    }

}
