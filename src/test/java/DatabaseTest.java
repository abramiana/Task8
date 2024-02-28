import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Database;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseTest {
    private static final Logger logger = LogManager.getLogger(DatabaseTest.class);
    private static Database database;
    private static final String TEST_FILE_PATH = "src/sql/init_db.sql";
    private static final String TEST_QUERY = "SELECT * FROM worker;";

    /**
     * Ініціалізація бази даних та створення тестового SQL-файлу.
     *
     * @throws IOException якщо сталася помилка при створенні тестового файлу
     */
    @BeforeAll
    public static void setUp() throws IOException {
        database = Database.getInstance();
        createTestFile(TEST_QUERY);
        logger.info("Test setup completed.");
    }

    /**
     * Закриття з'єднання з базою даних та видалення тестового SQL-файлу.
     */
    @AfterAll
    public static void tearDown() {
        database.closeConnection();
        deleteTestFile();
        logger.info("Test teardown completed.");
    }

    /**
     * Тест для методу executeQueryFromFile.
     *
     * @throws SQLException якщо виникла помилка SQL
     * @throws IOException  якщо виникла помилка вводу-виводу
     */
    @Test
    public void testExecuteQueryFromFile() throws SQLException, IOException {
        logger.info("Executing testExecuteQueryFromFile...");

        ResultSet resultSet = database.executeQueryFromFile(TEST_FILE_PATH);
        assertNotNull(resultSet, "ResultSet should not be null");
        assertTrue(resultSet.next(), "ResultSet should have at least one row");

        resultSet.close();
        logger.info("test ExecuteQueryFromFile completed successfully.");
    }

    /**
     * Створення тестового SQL-файлу з запитом.
     *
     * @param query запит для запису у файл
     * @throws IOException якщо сталася помилка при записі у файл
     */
    private static void createTestFile(String query) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE_PATH))) {
            writer.write(query);
        }
    }

    /**
     * Видалення тестового SQL-файлу.
     */
    private static void deleteTestFile() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }
}