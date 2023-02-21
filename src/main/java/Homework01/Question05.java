package Homework01;

import java.sql.*;

public class Question05 {
    public static void answer() throws SQLException {
        String dbURl = "jdbc:postgresql://localhost:5432/dvdrental";
        String username = "postgres";
        String password = "12345";

        Connection connection = DriverManager.getConnection(dbURl, username, password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT i.store_id,SUM(amount) FROM payment p\n" +
                "INNER JOIN rental r\n" +
                "ON r.rental_id = p.rental_id\n" +
                "INNER JOIN inventory i\n" +
                "ON i.inventory_id = r.inventory_id\n" +
                "WHERE store_id = 2\n" +
                "GROUP BY store_id";
        ResultSet resultSet = statement.executeQuery(query);


        while (resultSet.next()) {
            String store_id = resultSet.getString("store_id");
            String sum = resultSet.getString("sum");
            System.out.println(store_id + "\t" + sum);
        }
    }
}
