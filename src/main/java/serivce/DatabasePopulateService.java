package serivce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

/**
 * Цей клас служить для наповнення бази даних, використовуючи SQL-скрипт за шляхом який вказаний у змінній sqlFilePath
 */
public class DatabasePopulateService {
    private static final Logger logger = LogManager.getLogger(DatabasePopulateService.class);

    public static void main(String[] args) {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/init_db", "root", "nenosa55")
                .load();

        try {
            flyway.migrate(); // Виконання міграції для наповнення БД
            logger.info("util.Database population completed successfully.");
        } catch (Exception e) {
            logger.error("Error occurred during database population: {}", e.getMessage());
            logger.error("Stack trace: ", e);
        }
    }
}