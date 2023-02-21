package Homework01;

import java.sql.*;

public class Question04 {
    public static void answer() throws SQLException {
        String dbURL ="jdbc:postgresql://localhost:5432/dvdrental";
        String username = "postgres";
        String password = "12345";
        Connection connection = DriverManager.getConnection(dbURL,username,password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        String query = "SELECT country,SUM (amount) FROM  country co\n" +
                "INNER JOIN city ci\n" +
                "ON co.country_id = ci.country_id\n" +
                "INNER JOIN address a\n" +
                "ON a.city_id = ci.city_id\n" +
                "INNER JOIN customer cu\n" +
                "ON cu.address_id = a.address_id\n" +
                "INNER JOIN payment p\n" +
                "ON p.customer_id = cu.customer_id\n" +
                "GROUP BY co.country\n" +
                "HAVING SUM (amount) > 800\n" +
                "ORDER BY sum DESC";
        ResultSet resultSet = statement.executeQuery(query);
        System.out.printf("%-15s | %-15s%n","country","sum");
        while(resultSet.next()){
            String country = resultSet.getString("country");
            String sum = resultSet.getString("sum");
            System.out.printf("%-15s | %-15s%n",country,sum);
        }
    }
}
