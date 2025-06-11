import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SakilaDataManager {
    private static final Logger logger = Logger.getLogger(SakilaDataManager.class.getName());
    private final DataSource dataSource;
    List<Actor> actors;
    List<Film> films;

    public SakilaDataManager(DataSource dataSource) {

        if (dataSource == null) {
            throw new IllegalArgumentException("DataSource must not be null");
        }

        this.dataSource = dataSource;
    }

    public List<Actor> getAllActors() {
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT actor_id, first_name, last_name FROM actor";

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("actor_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                logger.info("Actor: " + firstName + " " + lastName);
                Actor actor = new Actor(id, firstName, lastName);
                actors.add(actor);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while fetching actors", e);

        }
        return actors;
    }

    public List<Film> getFilms() throws SQLException {
        List<Film> films = new ArrayList<>();
        String query = "SELECT film_id, title, description, release_year, length FROM film";

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("film_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                int releaseYear = resultSet.getInt("release_year");
                int length = resultSet.getInt("length");

                logger.info("Films: " + id + " " + title + " " + description + " " + releaseYear + " " + length);
                Film film = new Film(id, title, description, releaseYear, length);
                films.add(film);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, " Database error while fetching Film objects... :(");
        }

        return films;
    }

    public List<Film> getFilmsByActorId(int actorId) throws SQLException {
        List<Film> films1 = new ArrayList<>();
        String query = """
                "SELECT f.film_id, f.title, f.description, f.release_year, f.length
                FROM film fa
                JOIN film_actor fa ON f.film_id = fa.film_id
                WHERE fa.actor_id = ?
                """;

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, actorId);
            try (ResultSet r = statement.executeQuery()) {
                while (r.next()) {
                    int filmId = r.getInt("film_id");
                    String title = r.getString("title");
                    String description = r.getString("description");
                    int releaseYear = r.getInt("release_year");
                    int length = r.getInt("length");
                    logger.info("Film: " + films);

                    Film film = new Film(filmId, title, description, releaseYear, length);
                    films.add(film);
                }

            } catch (SQLException e) {
                logger.log(Level.SEVERE, "No Actors Found.");
            }

        }
        return films1;
    }

    public List<Actor> getActorByFirstNameLastName(String name) throws SQLException {
        List<Actor> actors = new ArrayList<>();
        String SQL = "SELECT actor_id, first_name, last_name FROM actor WHERE first_name = ? OR last_name = ? ";

        try (Connection connection = dataSource.getConnection(); PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setString(1, name.trim());
            stmt.setString(2,name.trim());

            try (ResultSet r = stmt.executeQuery()) {
                while (r.next()) {
                    int id = r.getInt("actor_id");
                    String first = r.getString("first_name");
                    String last = r.getString("last_name");
                    Actor actor = new Actor(id, first, last);
                    actors.add(actor);
                }
                return actors;
            }


        }


    }


}

