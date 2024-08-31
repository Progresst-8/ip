import java.util.Scanner;

// Human-Yapper Interface
public class UserInterface {
    // UI Operations
    public static void handleAddCommand(TaskManager taskManager, String taskDesc) {
        Task task = new Task(taskDesc);
        taskManager.addTask(task);
        OutputStringHandler.printAddedTask(taskDesc);
    }
    public static void handleMarkCommand(TaskManager taskManager, Integer taskOrdinal) {
        Task task = taskManager.getTask(taskOrdinal - 1); // 0-Indexed
        task.markAsDone();
        OutputStringHandler.printTaskStatus(task, true);
    }
    public static void handleUnmarkCommand(TaskManager taskManager, Integer taskOrdinal) {
        Task task = taskManager.getTask(taskOrdinal - 1); // 0-Indexed
        task.markAsNotDone();
        OutputStringHandler.printTaskStatus(task, false);
    }

    // Main ChatBot Loop
    public static void startYappin(TaskManager taskManager) {
        System.out.println(CommonStrings.START_UP_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInputString = scanner.nextLine();
            Instruction instruction = InputStringHandler.parseUserInput(userInputString);
            switch (instruction.getInstructionType()) {
            case ADD:
                handleAddCommand(taskManager, instruction.getInstructionStringArgument());
                break;
            case LIST:
                OutputStringHandler.printTasks(taskManager.getAllTasks(), taskManager.getTaskCount());
                break;
            case MARK:
                handleMarkCommand(taskManager, instruction.getInstructionIntegerArgument());
                break;
            case UNMARK:
                handleUnmarkCommand(taskManager, instruction.getInstructionIntegerArgument());
                break;
            case BYE:
                System.out.println(CommonStrings.SHUT_DOWN_MESSAGE);
                return;
            }
            // TBA ?
        }
    }
}
// there's at most 1 UI at any point in time.
// I shouldn't need static?
