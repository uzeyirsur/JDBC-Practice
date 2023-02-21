package org.example;

import java.sql.*;

public class Question01 {
    public static void answer() throws SQLException {
        String dbURl ="jdbc:postgresql://localhost:5432/exercises";
        String username = "postgres";
        String password = "12345";

        Connection connection = DriverManager.getConnection(dbURl,username,password);

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        String query = "SELECT TO_CHAR(joindate,'Month') AS month,\n" +
                "COUNT (*) FROM cd.members\n" +
                "GROUP BY month\n" +
                "ORDER BY count DESC\n" +
                "LIMIT 1";

        ResultSet resultSet = statement.executeQuery(query);

        int count = 0;
        while(resultSet.next()){
            System.out.println(count++ + ": " + resultSet.getString("month"));
        }

    }
}
