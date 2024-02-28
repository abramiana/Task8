package clientprocessing;

import util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private Database database;

    public ClientService() {
        this.database = Database.getInstance();
    }

    /**
     * Метод для створення нового клієнта з заданим ім'ям
     */
    public long create(String name) throws IllegalArgumentException {
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO client (name) VALUES (?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating client failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating client failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error creating client: " + e.getMessage());
        }
    }

    /**
     * Метод для отримання імені клієнта за його ідентифікатором
     */
    public String getById(long id) throws IllegalArgumentException {
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT name FROM client WHERE id = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                } else {
                    throw new IllegalArgumentException("Client with ID " + id + " does not exist.");
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error getting client name: " + e.getMessage());
        }
    }

    /**
     * Метод для встановлення імені клієнта за його ідентифікатором
     */
    public void setName(long id, String name) throws IllegalArgumentException {
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE client SET name = ? WHERE id = ?")) {
            statement.setString(1, name);
            statement.setLong(2, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Client with ID " + id + " does not exist.");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error updating client name: " + e.getMessage());
        }
    }

    /**
     * Метод для видалення клієнта за його ідентифікатором
     */
    public void deleteById(long id) throws IllegalArgumentException {
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM client WHERE id = ?")) {
            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Client with ID " + id + " does not exist.");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error deleting client: " + e.getMessage());
        }
    }

    /**
     * Метод для отримання списку всіх клієнтів
     */
    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM client");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                clients.add(new Client(id, name));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error retrieving clients: " + e.getMessage());
        }
        return clients;
    }
}