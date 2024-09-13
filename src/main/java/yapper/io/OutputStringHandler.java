package yapper.io;

import yapper.exceptions.ErrorHandler;
import yapper.exceptions.YapperException;
import yapper.tasks.Task;

// Output Text Formatter for Yapper
public class OutputStringHandler {
    // For Instruction: List
    public static void printTasks(Task[] tasks, int taskCount) {
        try {
            ErrorHandler.checkIfListEmpty(taskCount);

            System.out.println(StringStorage.LINE_DIVIDER);
            System.out.println(StringStorage.LIST_ALL_TASKS_STRING);
            for (int i = 0; i < taskCount; i++) {
                System.out.print( (i + 1) + "." ); // task list is displayed 1-indexed
                System.out.println( tasks[i].taskToString() );
            }
            System.out.println(StringStorage.LINE_DIVIDER);
        } catch (YapperException e) {
            System.out.println(e.getMessage());
        }
    }
    // For Instruction: Mark, Unmark
    public static void printTaskStatus(Task task, boolean isDone) {
        String status = (isDone ? "done" : "undone");
        System.out.println(StringStorage.LINE_DIVIDER);
        System.out.println("This task is now " + status + ": \n" + "  " + task);
        System.out.println(StringStorage.LINE_DIVIDER);
    }
}
