import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseQueryService queryService = new DatabaseQueryService();

        // Викликаємо метод findMaxProjectsClient() для отримання списку клієнтів з максимальною кількістю проектів
        List<MaxProjectCountClient> maxProjectCountClients = queryService.findMaxProjectsClient();

        // Виводимо інформацію про кожного клієнта з максимальною кількістю проектів
        System.out.println("Clients with maximum project count:");
        for (MaxProjectCountClient client : maxProjectCountClients) {
            System.out.println(client);
        }


        //  Викликаємо метод findLongestProject() для отримання найдовший тривалий проект
        List<LongestProject> longestProject = queryService.findLongestProject();

        // Виводимо інформацію про кожного клієнта з максимальною кількістю проектів
        System.out.println("Project with the longest duration:");
        for (LongestProject client : longestProject) {
            System.out.println(client);
        }
        List<Worker> worker = queryService.findWorkerWithMaxSalary();

        // Виводимо інформацію про працівника з найбільшою заробітною платою
        System.out.println("Employee with the highest salary: ");
        for (Worker employee : worker) {
            System.out.println(employee);
        }
        List<Worker> workerOld = queryService.findOldestAndYoungestWorkers();

        // Виводимо інформацію про молодшого та найстаршого працівника
        System.out.println("Oldest and youngest employee: ");
        for (Worker employee : workerOld) {
            System.out.println(employee);
        }


        List<ProjectPrice> projectPrices = queryService.findProjectPrices();
//         Виводимо інформацію про вартість кожного проєкту
        System.out.println("Cost of each project: ");
        for (
                ProjectPrice project : projectPrices) {
            System.out.println(project);
        }
    }
}
