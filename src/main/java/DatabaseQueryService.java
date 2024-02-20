import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * У цьому класі реалізовані методи для кожного SELECT запиту.
 */
public class DatabaseQueryService {

    private static final Logger logger = LogManager.getLogger(DatabaseQueryService.class);
    Connection connection = Database.getInstance().getConnection();

    /**
     * Цей метод знаходить клієнтів з найбільшою кількістю проєктів
     */
    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> result = new ArrayList<>();
        String sqlQuery = "src/sql/find_max_projects_client.sql";
        try (ResultSet resultSet = Database.getInstance().executeQueryFromFile(connection, sqlQuery);
        ) {
            if (resultSet != null) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int projectCount = resultSet.getInt("ProjectCount");
                    result.add(new MaxProjectCountClient(name, projectCount));
                }
            }
        } catch (SQLException | IOException e) {
            logger.error("Error occurred while fetching max project count clients: {}", e.getMessage());
        }
        logger.info("Database connection  {}", Database.getInstance().isConnected());
        return result;
    }

    /**
     * Цей метод знаходить проєкти з найбільшою тривалістю
     */
    public List<LongestProject> findLongestProject() {
        List<LongestProject> result = new ArrayList<>();
        String sqlQuery = "src/sql/find_longest_project.sql";
        try (ResultSet resultSet = Database.getInstance().executeQueryFromFile(connection, sqlQuery)) {
            if (resultSet != null && resultSet.next()) {
                int projectId = resultSet.getInt("ID");
                Date startDate = resultSet.getDate("START_DATE");
                Date finishDate = resultSet.getDate("FINISH_DATE");
                int durationInMonths = resultSet.getInt("DurationInMonths");
                int clientId = resultSet.getInt("CLIENT_ID");

                result.add(new LongestProject(projectId, startDate, finishDate, durationInMonths, clientId));
                logger.info("Longest project found - ID: {}, Start Date: {}, Finish Date: {}, Duration in Months: {}, Client ID: {}",
                        projectId, startDate, finishDate, durationInMonths, clientId);
            } else {
                logger.info("No records found for the longest project.");
            }
        } catch (SQLException | IOException e) {
            logger.error("Error occurred while fetching the longest project: {}", e.getMessage());
        }
        logger.info("Database connection  {}", Database.getInstance().isConnected());
        return result;
    }

    /**
     * Цей метод знаходить працівника з найбільшою заробітною платою.
     */
    public List<Worker> findWorkerWithMaxSalary() {
        List<Worker> result = new ArrayList<>();
        String sqlQuery = "src/sql/find_max_salary_worker.sql";
        try (ResultSet resultSet = Database.getInstance().executeQueryFromFile(connection, sqlQuery)) {
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                double salary = resultSet.getDouble("SALARY");
                result.add(new Worker(name, salary));
            }
        } catch (SQLException | IOException e) {
            logger.error("Error occurred while fetching workers with max salary: {}", e.getMessage());
        }
        logger.info("Database connection  {}", Database.getInstance().isConnected());
        return result;
    }

    /**
     * Цей метод знаходить найстаршого та наймолодшого працівника
     */
    public List<Worker> findOldestAndYoungestWorkers() {
        List<Worker> result = new ArrayList<>();
        String sqlQuery = "src/sql/find_youngest_eldest_workers.sql";
        try (ResultSet resultSet = Database.getInstance().executeQueryFromFile(connection, sqlQuery)) {
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                Date birthday = resultSet.getDate("BIRTHDAY");
                result.add(new Worker(name, birthday));
            }

        } catch (SQLException | IOException e) {
            logger.error("Error occurred while fetching oldest and youngest workers: {}", e.getMessage());
        }
        logger.info("Database connection  {}", Database.getInstance().isConnected());
        return result;
    }

    /**
     * Цей метод виводить вартість кожного проєкту.
     */
    public List<ProjectPrice> findProjectPrices() {
        List<ProjectPrice> result = new ArrayList<>();

        String sqlQuery = "src/sql/print_project_prices.sql"; // Шлях до файлу з SQL-запитом

        try (ResultSet resultSet = Database.getInstance().executeQueryFromFile(connection, sqlQuery)) {
            while (resultSet.next()) {
                String projectName = resultSet.getString("Name");
                double projectPrice = resultSet.getDouble("Price");
                result.add(new ProjectPrice(projectName, projectPrice));
            }
        } catch (SQLException | IOException e) {
            logger.error("Error occurred while fetching project prices: {}", e.getMessage());
        }
        logger.info("Database connection  {}", Database.getInstance().isConnected());
        Database.getInstance().closeConnection(); // Закриття контакту з БД.
        logger.info("Database connection  {}", Database.getInstance().isConnected());
        return result;

    }

}