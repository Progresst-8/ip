// Output Text Formatter for Yapper
public class OutputStringHandler {
    // TODO use printTask
    public String printTask(Task task) {
        return "[" + (task.isDone ? "X" : " ") + "] " + task.taskDesc;
    }

    // For Command: Add
    public static void printAddedTask(String taskDescription) {
        System.out.println(CommonStrings.LINE_DIVIDER);
        System.out.println("you now wanna: " + taskDescription);
        System.out.println(CommonStrings.LINE_DIVIDER);
    }
    // For Command: List
    public static void printTasks(Task[] tasks, int taskCount) {
        System.out.println(CommonStrings.LINE_DIVIDER);
        System.out.println("Da tasks in yer list are:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println(CommonStrings.LINE_DIVIDER);
    }
    // For Command: Mark, Unmark
    public static void printTaskStatus(Task task, boolean isDone) {
        String status = (isDone ? "done" : "undone");
        System.out.println(CommonStrings.LINE_DIVIDER);
        System.out.println("This task is now " + status + ":\n" + "  " + task);
        System.out.println(CommonStrings.LINE_DIVIDER);
    }
}
