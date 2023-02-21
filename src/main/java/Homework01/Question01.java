package Homework01;

import java.sql.*;

public class Question01 {
    public static void answer() throws SQLException {
        String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
        String username = "postgres";
        String password = "12345";
        Connection connection = DriverManager.getConnection(dbURL, username, password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT first_name||' '||last_name AS name,rental_date FROM actor a\n" +
                "INNER JOIN film_actor fa\n" +
                "ON a.actor_id = fa.actor_id\n" +
                "INNER JOIN inventory i\n" +
                "ON i.film_id = fa.film_id\n" +
                "INNER JOIN rental r\n" +
                "ON r.inventory_id = i.inventory_id\n" +
                "ORDER BY rental_date DESC\n" +
                "LIMIT 3";
        ResultSet resultSet = statement.executeQuery(query);
        System.out.printf("%-20s | %-20s%n","Name","Rental Date");
        System.out.println("-----------------------------------------------------");
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            Timestamp rentalDate = resultSet.getTimestamp("rental_date");
            System.out.printf("%-20s | %-20s%n",name,rentalDate);
        }
    }
}
