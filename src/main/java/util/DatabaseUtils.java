package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Цей клас надає зручний спосіб виконання SQL-запитів з файлів
 */
public class DatabaseUtils {

    private static final Logger logger = LogManager.getLogger(DatabaseUtils.class);

    /**
     * Це статичний метод, який приймає з'єднання з базою даних та шлях до файлу SQL-запитів.
     */
    public static void executeSqlFile(Connection connection, String filePath) throws IOException, SQLException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder query = new StringBuilder();
            Statement statement = connection.createStatement();

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty() && !line.trim().startsWith("--")) {
                    query.append(line.trim());

                    if (line.trim().endsWith(";")) {
                        String queryToExecute = query.toString();
                        logger.debug("Executing SQL query: {}", queryToExecute);
                        statement.executeUpdate(queryToExecute);
                        logger.debug("SQL query executed successfully.");
                        query.setLength(0);
                    }
                }
            }
        } catch (IOException | SQLException e) {
            logger.error("Error executing SQL query: {}", e.getMessage());
            throw e;
        }
    }
}