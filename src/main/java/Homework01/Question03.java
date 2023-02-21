package Homework01;

import java.sql.*;

public class Question03 {
    public static void answer() throws SQLException {
        String dbURl = "jdbc:postgresql://localhost:5432/dvdrental";
        String username = "postgres";
        String password = "12345";

        Connection connection = DriverManager.getConnection(dbURl,username,password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT c.name FROM category c\n" +
                "INNER JOIN film_category fc\n" +
                "ON c.category_id = fc.category_id\n" +
                "INNER JOIN inventory i\n" +
                "ON i.film_id =fc.film_id\n" +
                "WHERE i.store_id = 1\n" +
                "GROUP BY name\n" +
                "HAVING COUNT (i.film_id) >150\n" +
                "ORDER BY COUNT (i.film_id) DESC\n" +
                "LIMIT 5 ";
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("Top Five Categories");
        while(resultSet.next()){
            System.out.println(resultSet.getString("name"));
        }
    }
}
