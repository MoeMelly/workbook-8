import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Data {
    public static void main(String[] args) throws SQLException {


        String url = "jdbc:mysql://localhost:3306/mydatabase?user=root&password=";
        try (BasicDataSource dataSource = new BasicDataSource()) {


            dataSource.setUrl(url);
            Connection connection = dataSource.getConnection();

        }
    }
}
