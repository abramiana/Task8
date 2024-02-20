import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Цей клас служить для ініціалізації бази даних, використовуючи SQL-скрипт за шляхом який вказаний у змінній sqlFilePath
 */
public class DatabaseInitService {
    private static final Logger logger = LogManager.getLogger(DatabaseInitService.class);

    public static void main(String[] args) {
        String sqlFilePath = "src/sql/init_db.sql";

        try (Connection connection = Database.getInstance().getConnection()) {
            DatabaseUtils.executeSqlFile(connection, sqlFilePath);
            logger.info("Database initialization completed successfully.");
        } catch (SQLException | IOException e) {
            logger.error("Error occurred during database initialization: {}", e.getMessage());
        }
    }
}