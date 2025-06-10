import java.sql.*;
import java.util.*;


public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean keepRunning = true;
        Connection connection = null;
        while (keepRunning) {
            System.out.println("Enter a command: ");
            System.out.println("1.Connect to database");
            System.out.println("2)Display all products");
            System.out.println("3) Display all customers");
            System.out.println("4) Display all categories");
            System.out.println("5) Exit");
            int command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {
                case 1 -> {
                    try {
                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "yearup");
                        System.out.println("Connection Successful!");
                    } catch (SQLException e) {
                        System.out.println("Connection failed: " + e.getMessage());
                    }
                }
                case 2 -> {
                    if (connection == null) {
                        System.out.println("Please Connect to database first.");
                        break;
                    }
                    try {
                        List<String[]> products = getQuery(connection, "products", "*");
                        System.out.println("--- Products ---");
                        for (String[] row : products) {
                            System.out.println(Arrays.toString(row));
                        }
                    } catch (SQLException e) {
                        System.out.println("Failed to retrieve products: " + e.getMessage());
                    }
                }
                case 3 -> {
                    if (connection == null) {
                        System.out.println("Please connect to database first.");
                        break;
                    }
                    try {
                        List<String[]> customers = getQuery(connection, "customers", "*");
                        System.out.println("--- Customers ---");
                        for (String[] row : customers) {
                            System.out.println(Arrays.toString(row));
                        }
                    } catch (SQLException e) {
                        System.out.println("Failed to retrieve customer information: " + e.getMessage());
                    }

                }
                case 4 -> {
                    if (connection == null) {
                        System.out.println("Please Connect to a database first.");
                        break;
                    }
                    try {
                        List<String[]> categories = getQuery(connection, "categories", "CategoryID,CategoryName");
                        System.out.println("--- Categories ---");
                        for (String[] row : categories) {
                            System.out.println(Arrays.toString(row));
                        }
                    } catch (SQLException e) {
                        System.out.println("Failure HAHA!");
                    }
                }
            }


        }
    }


    public static List<String[]> getQuery(Connection con, String tableName, String columns) throws SQLException {

        if (con == null || tableName == null || columns == null || tableName.isBlank() || columns.isBlank()) {
            throw new IllegalArgumentException("Cannot be null.");
        }

        String query = "SELECT " + columns + " FROM " + tableName;
        List<String[]> result = new ArrayList<>();

        try (PreparedStatement statement = con.prepareStatement(query);
             ResultSet set = statement.executeQuery()) {


            int count = set.getMetaData().getColumnCount();
            while (set.next()) {
                String[] row = new String[count];
                for (int i = 1; i <= count; i++) row[i - 1] = set.getString(i);
                result.add(row);

            }


        }
        return result;

    }

}






























