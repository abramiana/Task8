import clientprocessing.*;
import model.*;
import serivce.DatabaseQueryService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseQueryService queryService = new DatabaseQueryService();
        ClientService clientService = new ClientService();

        printMaxProjectCountClients(queryService);
        printLongestProject(queryService);
        printWorkerWithMaxSalary(queryService);
        printOldestAndYoungestWorkers(queryService);
        printProjectPrices(queryService);

        createClient(clientService, "Artur");
        getClientName(clientService, 1);
        updateClientName(clientService, 1, "Artem");
        deleteClient(clientService, 1);
        printAllClients(clientService);
    }

    /**
     * Виводить інформацію про клієнтів з максимальною кількістю проєктів.
     *
     * @param queryService об'єкт serivce.DatabaseQueryService для виконання запитів до бази даних
     */
    private static void printMaxProjectCountClients(DatabaseQueryService queryService) {
        List<MaxProjectCountClient> maxProjectCountClients = queryService.findMaxProjectsClient();
        System.out.println("Clients with maximum project count:");
        maxProjectCountClients.forEach(System.out::println);
        System.out.println("----------------------------------------------------");
    }

    /**
     * Виводить інформацію про проекти з найбільшою тривалістю.
     *
     * @param queryService об'єкт serivce.DatabaseQueryService для виконання запитів до бази даних
     */
    private static void printLongestProject(DatabaseQueryService queryService) {
        List<LongestProject> longestProjects = queryService.findLongestProject();
        System.out.println("Project with the longest duration:");
        longestProjects.forEach(System.out::println);
        System.out.println("----------------------------------------------------");
    }

    /**
     * Виводить інформацію про працівника з найвищою зарплатою.
     *
     * @param queryService об'єкт serivce.DatabaseQueryService для виконання запитів до бази даних
     */
    private static void printWorkerWithMaxSalary(DatabaseQueryService queryService) {
        List<Worker> workers = queryService.findWorkerWithMaxSalary();
        System.out.println("Employee with the highest salary:");
        workers.forEach(System.out::println);
        System.out.println("----------------------------------------------------");
    }

    /**
     * Виводить інформацію про найстаршого та наймолодшого працівників.
     *
     * @param queryService об'єкт serivce.DatabaseQueryService для виконання запитів до бази даних
     */
    private static void printOldestAndYoungestWorkers(DatabaseQueryService queryService) {
        List<Worker> workers = queryService.findOldestAndYoungestWorkers();
        System.out.println("Oldest and youngest employee:");
        workers.forEach(System.out::println);
        System.out.println("----------------------------------------------------");
    }

    /**
     * Виводить інформацію про вартість кожного проєкту.
     *
     * @param queryService об'єкт serivce.DatabaseQueryService для виконання запитів до бази даних
     */
    private static void printProjectPrices(DatabaseQueryService queryService) {
        List<ProjectPrice> projectPrices = queryService.findProjectPrices();
        System.out.println("Cost of each project:");
        projectPrices.forEach(System.out::println);
        System.out.println("----------------------------------------------------");
    }

    /**
     * Метод для створення клієнта
     */
    private static void createClient(ClientService clientService, String name) {
        try {
            long clientId = clientService.create(name);
            System.out.println("Created client with ID: " + clientId);
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating client: " + e.getMessage());
        }
    }

    /**
     * Метод для отримання імені клієнта за його ID
     */
    private static void getClientName(ClientService clientService, long id) {
        try {
            System.out.println(clientService.getById(id));
        } catch (IllegalArgumentException e) {
            System.out.println("Error getting client name: " + e.getMessage());
            System.out.println("null"); // Повертаємо null в разі помилки
        }
    }

    /**
     * Метод для оновлення імені клієнта за його ID
     */
    private static void updateClientName(ClientService clientService, long id, String name) {
        try {
            clientService.setName(id, name);
            System.out.println("Client name updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating client name: " + e.getMessage());
        }
    }


    /**
     * Метод для видалення клієнта за його ID
     */
    private static void deleteClient(ClientService clientService, long id) {
        try {
            clientService.deleteById(id);
            System.out.println("Client name delete successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error deleting client: " + e.getMessage());
        }
    }

    /**
     * Метод для отримання списку всіх клієнтів
     */
    private static void printAllClients(ClientService clientService) {
        List<Client> allClients = clientService.listAll();
        System.out.println("All clients:");
        for (Client client : allClients) {
            System.out.println(client);
        }
    }
}