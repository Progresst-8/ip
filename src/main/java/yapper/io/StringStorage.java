package yapper.io;

// TODO: make more conversational

/**
 * Contains characters/strings for the Yapper program
 *
 * <p>
 * The {@code StringStorage} class holds various constants and utility methods
 * related to string management for user input and output in the application.
 * <p/>
 *
 * <p>
 * This class includes constants for file paths, instruction prefixes,
 * symbols for different task types, and various messages for user interaction.
 * It also provides methods to assist in string manipulation and formatted output.
 * </p>
 *
 * <p>
 * Constants in this class are organized into several categories: <ul>
 *   <li><b>File Management:</b> Constants related to file paths and delimiters.</li>
 *   <li><b>User Instructions:</b> Constants representing prefixes for user commands.</li>
 *   <li><b>Task Symbols:</b> Symbols used to represent task types and statuses.</li>
 *   <li><b>Messages:</b> Predefined messages for user interaction.</li>
 *   <li><b>Error Messages:</b> Messages used to inform users of issues or missing input.</li>
 * </ul> </p>
 */
public class StringStorage {
    /**
     * An offset value used to adjust indexing for user input and display.
     * <p>
     * The list array is 0-indexed (i.e. 0, 1, ...), but
     * the list display is 1-indexed (i.e. 1, 2, ...).
     * Therefore, the user input ordinal is equal to the
     * internal use ordinal plus this offset value.
     * </p>
     */
    public static final int INDEX_OFFSET = 1;

    /**
     * Constants representing prefixes for user commands, for User I/O.
     */
    public static final String     LIST_INSTRUCTION_PREFIX = "list";
    public static final String     TODO_INSTRUCTION_PREFIX = "todo";
    public static final String DEADLINE_INSTRUCTION_PREFIX = "deadline";
    public static final String    EVENT_INSTRUCTION_PREFIX = "event";
    public static final String   DELETE_INSTRUCTION_PREFIX = "delete";
    public static final String     MARK_INSTRUCTION_PREFIX = "mark";
    public static final String   UNMARK_INSTRUCTION_PREFIX = "unmark";
    public static final String      BYE_INSTRUCTION_PREFIX = "bye";
    public static final String DEADLINE_END_DATE_DELIMITER = "/by";
    public static final String  EVENT_START_DATE_DELIMITER = "/from";
    public static final String    EVENT_END_DATE_DELIMITER = "/to";

    /**
     * Symbols used to represent task types and statuses, for File I/O.
     */
    public static final String NOT_DONE_SYMBOL = "X";
    public static final String  IS_DONE_SYMBOL = "O";
    public static final String     TODO_SYMBOL = "T";
    public static final String DEADLINE_SYMBOL = "D";
    public static final String    EVENT_SYMBOL = "E";

    /**
     * Constants related to file management.
     */
    public static final String SAVE_FILE_PATH = "./data/savedata.txt";
    public static final String COMBINE_USING_DELIMITER = "|";
    public static final String   SPLIT_USING_DELIMITER = "\\|";
    /**
     * Splits a task string by a specified delimiter.
     *
     * @param taskAsString The string representation of the task to be split.
     * @return An array of strings obtained by splitting the input string.
     */
    public static String[] splitByDelimiter(String taskAsString) {
        return taskAsString.split(SPLIT_USING_DELIMITER, 3);
    }


    // Text Delimiter
    public static final String LINE_DIVIDER =
            "____________________________________________________________";
    public static final String LINE_DIVIDER_INPUT =
            "_____________________ USER INPUT BELOW _____________________";
    public static final String LINE_DIVIDER_OUTPUT =
            "___________________ PROGRAM OUTPUT BELOW ___________________";
    public static final String LINE_DIVIDER_YAPPER =
            "___________________ YAPPING OUTPUT BELOW ___________________";
    /**
     * Prints a message with dividers for better visibility.
     *
     * @param message The message to be printed.
     */
    public static void printWithDividers(String message) {
        System.out.println(LINE_DIVIDER_OUTPUT);
        System.out.println(message);
        System.out.println(LINE_DIVIDER_INPUT);
    }

    /**
     * Predefined messages for user interaction, if nothing goes wrong.
     */
    public static final String HELP_MESSAGE =
            "To jog your memory, here's what we can discuss: \n"
            // Nullary Argument Commands
            + "list" + ", if you forgot what you said. \n"
            + "bye"  + ", if you want me to stop yappin. \n"
            // Unary Argument Commands
            + "delete [index]" + ", if you don't want something. \n"
            +   "mark [index]" + ", if you're done with something. \n"
            + "unmark [index]" + ", if you're not done with something. \n"
            + "todo [todoDesc]" + ", to for a task with no dates. \n"
            // Binary Argument Commands
            + "deadline [deadlineDesc] /by [end]" + ", for a task with an end date. \n"
            // Trinary Argument Commands
            + "event [eventDesc] /from [start] /to [end]" + ", for a task with a start date and an end date. ";
    public static final String START_UP_MESSAGE =
            "Wassup! \n"
            + "Ya ready for me to yap yer ear off? \n"
            + "Whatchu wanna talk about? ";
    public static final String SHUT_DOWN_MESSAGE =
            "Thanks for listenin' to my yappin'. \n"
            + "Call for me whenever ya feel like listening again. \n"
            + "Cya! ";

    // Program Strings that are part of a Message
    public static final String LIST_ALL_TASKS_STRING =
            "You don't remember your tasks? Lemme refresh your memory: ";
    public static final String TASK_ADDED_STRING =
            "Now, ya gotta do this too: ";
    public static final String TASK_DELETED_STRING =
            "Now, ya dun hafta do dis anymore: ";
    public static final String LIST_SIZE_STRING =
            "Your list is now THIS BIG: ";
//    public static final String BEFORE_SAVING_STRING =
//            "saving data to: "; // ok, just give me a while to recall
//    public static final String AFTER_SAVING_STRING =
//            "saved successfully!"; // Ok, I think I remember it all
//    public static final String BEFORE_LOADING_STRING =
//            "retrieving data from: "; // hold on, let me remember
//    public static final String AFTER_LOADING_STRING =
//            "retrieved successfully!"; // I've got it


    /**
     * Messages used to inform users of issues or missing input.
     */
    public static final String EMPTY_INPUT_MESSAGE =
            "Input is missing. \n" +
            LINE_DIVIDER_YAPPER + "\n"+
            "I can say nothing in reply. I just stare at you. \n" +
            "There is an awkward silence.";
    public static final String MISSING_ARGUMENTS_MESSAGE =
            "Argument(s) for instruction are missing. \n" +
            LINE_DIVIDER_YAPPER + "\n"+
            "I need more details to understand what you wanna do. ";
    public static final String MISSING_PREFIX_MESSAGE =
            "Input does not start with any of the known prefixes: " +
            BYE_INSTRUCTION_PREFIX + ", " +
            LIST_INSTRUCTION_PREFIX + ", " +
            TODO_INSTRUCTION_PREFIX + ", " +
            DEADLINE_INSTRUCTION_PREFIX + ", " +
            EVENT_INSTRUCTION_PREFIX + ", " +
            DELETE_INSTRUCTION_PREFIX + ", " +
            MARK_INSTRUCTION_PREFIX + ", " +
            UNMARK_INSTRUCTION_PREFIX + ", \n" +
            LINE_DIVIDER_YAPPER + "\n"+
            "Explain in a way I can understand. ";
    // tba TODO ARGS MISSING
    // tba DEADLINE ARGS MISSING
    // tba EVENT ARGS MISSING
    // tba DEADLINE KEYWORDS MISSING
    // tba EVENT KEYWORDS MISSING
    public static final String MISSING_DESCRIPTION_MESSAGE =
            "Task description not given. \n" +
            LINE_DIVIDER_YAPPER + "\n"+
            "You haven't told me what this is for. ";
    public static final String MISSING_START_DATE_MESSAGE =
            "Start date is not given. \n" +
            LINE_DIVIDER_YAPPER + "\n"+
            "You haven't told me when this starts. ";
    public static final String MISSING_END_DATE_MESSAGE =
            "End date is not given. \n" +
            LINE_DIVIDER_YAPPER + "\n"+
            "You haven't told me when this ends. ";

    //
    public static final String INVALID_TASK_TYPE_MESSAGE =
            "Task type abbreviation is not any of these: "
            + TODO_SYMBOL + ", "
            + DEADLINE_SYMBOL + ", "
            + EVENT_SYMBOL; // + "\n" +
//            LINE_DIVIDER_YAPPER + "\n"+
//            "I don't remember if what kind of task this is.";
    public static final String INVALID_TASK_STATUS_MESSAGE =
            "Task completion status abbreviation is not any of these: "
            + IS_DONE_SYMBOL + ", "
            + NOT_DONE_SYMBOL; // + "\n" +
//            LINE_DIVIDER_YAPPER + "\n"+
//            "I don't remember if this task was done or not.";
    public static final String LIST_EMPTY_MESSAGE =
            "List is empty. \n" +
            LINE_DIVIDER_YAPPER + "\n"+
            "You have nothing to remember at the moment. ";
    public static final String LIST_OOB_MESSAGE =
            "Given list ordinal is invalid. \n" +
            LINE_DIVIDER_YAPPER + "\n"+
            "I don't what you're referring to. Come again? ";
    public static final String TASK_ALREADY_DONE_MESSAGE =
            "Task is already marked as done. \n" +
            LINE_DIVIDER_YAPPER + "\n"+
            "I know you've already done this. No need to tell me again. ";
    public static final String TASK_STILL_NOT_DONE_MESSAGE =
            "Task is already marked as not done. \n" +
            LINE_DIVIDER_YAPPER + "\n"+
            "I know you still haven't done this. I won't forget. ";

    public static final String INCOMPLETE_INSTRUCTION_MESSAGE =
            "Sorry, you cut off there. Continue what you were saying? ";
    public static final String UNRECOGNISED_INSTRUCTION_MESSAGE =
            "Instruction is not recognised. \n" +
            LINE_DIVIDER_YAPPER + "\n"+
            "I dunno whatcha just said. Repeat it for me will ya? ";
    public static final String SAVING_ERROR_MESSAGE = "error in saving data";
    public static final String LOADING_ERROR_MESSAGE = "error in retrieving data";

//    public static final String MESSAGE =
//            "";
}
