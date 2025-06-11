import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SakiloaMovies {
    private static final Logger logger = Logger.getLogger(SakiloaMovies.class.getName());

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/sakila?user=root&password=yearup";


        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);


        System.out.println("Choose Search Option:");
        System.out.println("1).Search Actors By Name");
        System.out.println("2).Search by Actor ID");
        SakilaDataManager manager = new SakilaDataManager(dataSource);
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.println("Enter Actors name: ");
            String name = scanner.nextLine();
            try {
                List<Actor> actors = manager.getActorByFirstNameLastName(name);

                if (actors == null || actors.isEmpty()) {
                    System.out.println("No Actors found with: " + name);
                } else {
                    actors.forEach(System.out::println);
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error Fetching Actor Names." + e);
            }


        } else if (choice == 2) {
            System.out.println("Enter Actors Id: ");
            int actorId = scanner.nextInt();

            try {
                List<Film> films = manager.getFilmsByActorId(actorId);

                if (films == null || films.isEmpty()) {
                    System.out.println("No films associated that name: " + actorId);
                } else {
                    films.forEach(System.out::println);
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error fetching matches to input.");
            }


        }

    }

}





