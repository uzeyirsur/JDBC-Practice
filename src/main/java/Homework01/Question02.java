package Homework01;

import java.sql.*;


public class Question02 {
   public static void answer() throws SQLException {
       String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
       String username = "postgres";
       String password = "12345";

       Connection connection = DriverManager.getConnection(dbURL,username,password);
       Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
       String query= "SELECT DISTINCT first_name ||' '||last_name AS name,username,password FROM staff s\n" +
               "INNER JOIN inventory i\n" +
               "ON s.store_id =i.store_id\n" +
               "INNER JOIN film f\n" +
               "ON i.film_id = f.film_id\n" +
               "WHERE f.title = 'Glass Dying'";

      ResultSet resultSet = statement.executeQuery(query);

       System.out.printf("%-15s | %-15s%n","Name","Password");

       while(resultSet.next()){
          String name = resultSet.getString("name");
          String userpassword = resultSet.getString("password");

          System.out.printf("%-15s | %-15s%n",name,userpassword);
      }
   }


}
