package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Використовується для з'єднання з базою даних та виконання SQL-запитів
 */
public class Database {
    private static final Logger logger = LogManager.getLogger(Database.class);

    private static Database instance;
    private Connection connection;

    /**
     * Приватний конструктор є частиною шаблону Singleton для класу
     */
    private Database() {
        try {
            String url = "jdbc:mysql://localhost:3306/init_db";
            String username = "root";
            String password = "nenosa55";
            this.connection = DriverManager.getConnection(url, username, password);
            logger.info("Connected to database with URL: {} and user: {}", url, username);
        } catch (SQLException e) {
            logger.error("Failed to connect to database. Error: {}", e.getMessage());
        }
    }

    /**
     * Цей метод створює єдиний екземпляр класу util.Database
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Цей метод повертає з'єднання з базою даних
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Цей метод Призначений для закриття з'єднання з базою даних після завершення роботи з нею.
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Connection to database closed successfully.");
            } catch (SQLException e) {
                logger.error("Failed to close database connection. Error: {}", e.getMessage());
            }
        }
    }

    /**
     * Цей метод перевіряє, чи встановлене з'єднання з базою даних.
     */
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            logger.error("Failed to check connection status. Error: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Зчитує SQL-запит з файлу та виконує його
     */
    public ResultSet executeQueryFromFile(Connection connection, String filename) throws SQLException, IOException {
        StringBuilder query = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                query.append(line).append("\n");
            }
        } catch (IOException e) {
            logger.error("Failed to read query file {}. Error: {}", filename, e.getMessage());
            throw e;
        }
        PreparedStatement statement = connection.prepareStatement(query.toString());
        return statement.executeQuery();
    }
}