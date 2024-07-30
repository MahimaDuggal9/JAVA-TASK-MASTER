import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static TaskManager taskManager = new TaskManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Create Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. View Tasks by Category");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> createTask();
                case 2 -> viewTasks();
                case 3 -> updateTask();
                case 4 -> deleteTask();
                case 5 -> viewTasksByCategory();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void createTask() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter due date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());

        Task task = new Task(title, description, category, dueDate);
        taskManager.addTask(task);
    }

    private static void viewTasks() {
        List<Task> tasks = taskManager.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void updateTask() {
        System.out.print("Enter task number to update: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        System.out.print("Enter new title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new description: ");
        String description = scanner.nextLine();
        System.out.print("Enter new category: ");
        String category = scanner.nextLine();
        System.out.print("Enter new due date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());

        Task task = new Task(title, description, category, dueDate);
        taskManager.updateTask(index, task);
    }

    private static void deleteTask() {
        System.out.print("Enter task number to delete: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        taskManager.deleteTask(index);
    }

    private static void viewTasksByCategory() {
        System.out.print("Enter category to view tasks: ");
        String category = scanner.nextLine();
        List<Task> tasksByCategory = taskManager.getTasksByCategory(category);
        for (int i = 0; i < tasksByCategory.size(); i++) {
            System.out.println((i + 1) + ". " + tasksByCategory.get(i));
        }
    }
}
