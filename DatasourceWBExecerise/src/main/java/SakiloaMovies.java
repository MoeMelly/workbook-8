import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SakiloaMovies {
    public static void main(String[] args) throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/sakila?user=root&password=yearup";
        BasicDataSource sakila = new BasicDataSource();
        sakila.setUrl(url);
        try (Connection connection = sakila.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            String input = Design.getNounPrompt(scanner,true,"Enter Last Name",true);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM actor WHERE actor.last_name = '"  + input + "';");
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean found = false;
            while (resultSet.next()) {
                if (!found) {
                    System.out.println("Your matches: \n");
                    found = true;
                }
                System.out.printf("first_name = %s, last_name = %s; \n", resultSet.getString(1), resultSet.getString(2));
            }
            if (!found) {
                System.out.println("No Matches Found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();


        }

    }
}




