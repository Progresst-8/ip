// manages Tasks for Yapper
public class TaskManager {
    private Task[] tasks;
    private int taskCount;

    // Constructor and Getters
    public TaskManager(int maxCapacity) {
        tasks = new Task[maxCapacity];
        taskCount = 0;
    }
    public Task[] getAllTasks() {
        return tasks;
    }
    public int getTaskCount() {
        return taskCount;
    }
    public Task getTask(int taskOrdinal) {
        return tasks[taskOrdinal];
    }
    // TaskManager Operations
    public void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
    }
}
