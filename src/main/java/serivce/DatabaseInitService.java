package serivce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

/**
 * Цей клас служить для ініціалізації бази даних, використовуючи SQL-скрипт за шляхом який вказаний у змінній sqlFilePath
 */
public class DatabaseInitService {
    private static final Logger logger = LogManager.getLogger(DatabaseInitService.class);

    public static void main(String[] args) {
        Flyway flyway = Flyway
                .configure()
                .dataSource("jdbc:mysql://localhost:3306/init_db", "root", "nenosa55")
                .baselineOnMigrate(true)
                .load();

        try {
            flyway.migrate();
            logger.info("util.Database initialization completed successfully.");
        } catch (Exception e) {
            logger.error("Error occurred during database initialization: {}", e.getMessage());
        }
    }
}