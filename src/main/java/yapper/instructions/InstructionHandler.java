package yapper.instructions;

import yapper.exceptions.ExceptionHandler;
import yapper.exceptions.YapperException;
import yapper.io.InputStringHandler;
import yapper.io.OutputStringHandler;
import yapper.io.StringStorage;
import yapper.io.SaveFileHandler;
import yapper.tasks.TaskHandler;
import yapper.tasks.Task;
import yapper.tasks.Deadline;
import yapper.tasks.Event;
import yapper.tasks.Todo;

// Human-Yapper Interface
public class InstructionHandler {
    // UI Operations: Error_Check -> Do -> Print -> Update_File
    public static void handleListInstruction(TaskHandler taskHandler) {
        try {
            // Error_Check
            ExceptionHandler.checkIfTaskOrdinalIsOutOfRange(taskHandler.getCurrTaskTotal());
            // Do & Print
            OutputStringHandler.printTasks(taskHandler.getAllTasks(), taskHandler.getCurrTaskTotal());
            // No Update_File needed
        } catch (YapperException e) {
            StringStorage.printWithDividers(
                    "cannot handle list instruction because: \n" + e.getMessage());
        }
    }
    public static void handleAddInstruction(TaskHandler taskHandler, Task task) {
        try {
            // No Error_Check yet ?
            // Do
            taskHandler.addTask(task);
            // Print
            OutputStringHandler.printAddedTask(task, taskHandler.getCurrTaskTotal());
            // Update_File
            SaveFileHandler.storeAddedTask(task);
        } catch (YapperException e) {
            StringStorage.printWithDividers(
                    "cannot handle add instruction because: \n" + e.getMessage());
        }
    }
    public static void handleDeleteInstruction(TaskHandler taskHandler, Integer taskOrdinal) {
        // OOB method should indirectly check if list is empty?
        try {
            // No Error_Check yet
            ExceptionHandler.checkIfTaskOrdinalIsOutOfRange(taskHandler.getCurrTaskTotal(), taskOrdinal);
            // Do
            Task task = taskHandler.getTask(taskOrdinal);
            taskHandler.deleteTask(taskOrdinal);
            // Print
            OutputStringHandler.printDeletedTask(task, taskHandler.getCurrTaskTotal());
            // Update_File
            SaveFileHandler.unstoreDeletedTask(taskOrdinal);
        } catch (YapperException e) {
            StringStorage.printWithDividers(
                    "cannot handle delete instruction because: \n" + e.getMessage());
        }
    }
    public static void handleMarkingInstruction(TaskHandler taskHandler, Integer taskOrdinal, boolean isDone) {
        try {
            // Error Check
            ExceptionHandler.checkIfTaskOrdinalIsOutOfRange(taskHandler.getCurrTaskTotal(), taskOrdinal);
            Task task = taskHandler.getTask(taskOrdinal - StringStorage.INDEX_OFFSET); // need for methods later
            ExceptionHandler.checkIfDoneStatusNeedsChanging(task.isDone(), isDone);
            // Do
            taskHandler.updateTaskStatus(task, isDone);
            // Print
            OutputStringHandler.printTaskStatus(task, isDone);
            // Update_File
            SaveFileHandler.amendTaskStatus(task, taskOrdinal); // uses taskToString after doneStatus is changed
        } catch (YapperException e) {
            StringStorage.printWithDividers(
                    "cannot handle mark instruction because: \n" + e.getMessage());
        }
    }

    //
    public static void handleInstruction(TaskHandler taskHandler, String userInputString) {
        Instruction instruction = null;
        try {
            instruction = InputStringHandler.parseUserInput(userInputString);
        } catch (YapperException e) {
            StringStorage.printWithDividers(
                    "cannot handle instruction because: \n" + e.getMessage());
            return;
        }
        // TO DECIDE: try-catch to extend to the whole method?
        Instruction.InstructionType instructionType = instruction.getInstructionType();
        switch (instructionType) {
        case LIST:
            handleListInstruction(taskHandler);
            break;
        case TODO:
            String todoDesc = instruction.getInstructionDesc();
            handleAddInstruction(taskHandler,
                    new Todo(todoDesc) );
            break;
        case DEADLINE:
            String deadlineDesc = instruction.getInstructionDesc();
            String deadline = instruction.getTaskDates()[0];
            handleAddInstruction(taskHandler,
                    new Deadline(deadlineDesc, deadline) );
            break;
        case EVENT:
            String eventDesc = instruction.getInstructionDesc();
            String startDate = instruction.getTaskDates()[0];
            String endDate = instruction.getTaskDates()[1];
            handleAddInstruction(taskHandler,
                    new Event(eventDesc, startDate, endDate) );
            break;
        case DELETE:
            handleDeleteInstruction(taskHandler,
                    instruction.getTaskOrdinal() );
            break;
        case MARK:
            handleMarkingInstruction(taskHandler,
                    instruction.getTaskOrdinal(), true);
            break;
        case UNMARK:
            handleMarkingInstruction(taskHandler,
                    instruction.getTaskOrdinal(), false);
            break;
//        case HELP:
//            StringStorage.printWithDividers(StringStorage.HELP_MESSAGE);
//            break;
        }
        // FYI: BYE instruction is not handled here, but in Yapper.startYappin()
    }
}