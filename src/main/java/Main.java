import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseQueryService queryService = new DatabaseQueryService();

        printMaxProjectCountClients(queryService);
        printLongestProject(queryService);
        printWorkerWithMaxSalary(queryService);
        printOldestAndYoungestWorkers(queryService);
        printProjectPrices(queryService);
    }

    /**
     * Виводить інформацію про клієнтів з максимальною кількістю проєктів.
     *
     * @param queryService об'єкт DatabaseQueryService для виконання запитів до бази даних
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
     * @param queryService об'єкт DatabaseQueryService для виконання запитів до бази даних
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
     * @param queryService об'єкт DatabaseQueryService для виконання запитів до бази даних
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
     * @param queryService об'єкт DatabaseQueryService для виконання запитів до бази даних
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
     * @param queryService об'єкт DatabaseQueryService для виконання запитів до бази даних
     */
    private static void printProjectPrices(DatabaseQueryService queryService) {
        List<ProjectPrice> projectPrices = queryService.findProjectPrices();
        System.out.println("Cost of each project:");
        projectPrices.forEach(System.out::println);
        System.out.println("----------------------------------------------------");
    }
}