import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private static final String FILE_NAME = "tasks.ser";

    public TaskManager() {
        tasks = new ArrayList<>();
        loadTasks();
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void updateTask(int index, Task task) {
        tasks.set(index, task);
        saveTasks();
    }

    public void deleteTask(int index) {
        tasks.remove(index);
        saveTasks();
    }

    public List<Task> getTasksByCategory(String category) {
        List<Task> categorizedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getCategory().equalsIgnoreCase(category)) {
                categorizedTasks.add(task);
            }
        }
        return categorizedTasks;
    }

    private void saveTasks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            tasks = (List<Task>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, no tasks to load
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
