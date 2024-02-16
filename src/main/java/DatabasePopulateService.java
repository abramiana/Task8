import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Цей клас служить для наповнення бази даних, використовуючи SQL-скрипт за шляхом який вказаний у змінній sqlFilePath
 */
public class DatabasePopulateService {
    private static final Logger logger = LogManager.getLogger(DatabasePopulateService.class);

    public static void main(String[] args) {
        String sqlFilePath = "src/sql/populate_db.sql"; // Шлях до файлу для заповнення БД
        try {
            Connection connection = Database.getInstance().getConnection();
            DatabaseUtils.executeSqlFile(connection, sqlFilePath);
            connection.close();
            logger.info("Database population completed successfully.");
        } catch (SQLException | IOException e) {
            logger.error("Error occurred during database population: {}", e.getMessage());
            logger.error("Stack trace: ", e);
        } finally {
            if (Database.getInstance().isConnected()) {
                Database.getInstance().closeConnection();
            }
        }
    }
}