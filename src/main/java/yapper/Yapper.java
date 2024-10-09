package yapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import yapper.exceptions.YapperException;
import yapper.instructions.Instruction;
import yapper.instructions.InstructionHandler;
import yapper.io.*;
import yapper.tasks.TaskHandler;

/**
 * Main class for Yapper.
 *
 * <p>
 * The Yapper class implements a simple command-line chatbot.
 * It interacts with the user through a loop that
 * processes user input, handles tasks, and manages program flow.
 * <p/>
 *
 */
public class Yapper {

    /**
     * Validates the existence of the save folder and file.
     * If either the folder or the file does not exist, they are created.
     * After creating or validating their existence, the tasks managed by the
     * {@code TaskHandler} are stored using {@code OutputFileHandler.storeAllTasks()}.
     * <p>
     * If an {@code IOException} or {@code YapperException} occurs during the process, an appropriate message is
     * printed to the console.
     *
     * @param taskHandler the {@code TaskHandler} instance that manages the tasks to be stored.
     * @throws IOException if an I/O error occurs during file or folder initialization.
     * @throws YapperException if a Yapper-specific error occurs during task storage.
     */
    private static void validateSaveFolderAndFile(TaskHandler taskHandler) {
        try {
            if (!FileHandler.saveFolderExists() || !FileHandler.saveFileExists()) {
                File file = new File(StringStorage.SAVE_FILE_PATH);
                FileHandler.initSaveFolder(file, false);
                FileHandler.initSaveFile(file, false);
                OutputFileHandler.storeAllTasks(taskHandler);
            }
        } catch (IOException e) {
            System.out.println("IOException encountered");
        } catch (YapperException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Contains the main Chatbot Loop
     *
     * <p>
     * Starts the main chatbot loop, allowing the user to interact
     * with the chatbot until they decide to exit.
     * <p/>
     *
     * @param taskHandler the TaskHandler instance that manages tasks
     */
    private static void runMainLoop(TaskHandler taskHandler) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(StringStorage.LINE_DIVIDER_INPUT);
            validateSaveFolderAndFile(taskHandler);
            String userInputString = scanner.nextLine().trim();

            System.out.println(StringStorage.LINE_DIVIDER_OUTPUT);
            if (userInputString.equals(StringStorage.PREFIX_BYE_INSTRUCTION)) {
                break;
            } else if (userInputString.startsWith(StringStorage.PREFIX_BYE_INSTRUCTION)) {
                System.out.println(StringStorage.PREFIX_BYE_INSTRUCTION
                    + " does not need other parameters. ");
                continue;
            }

            try {
                Instruction instruction = InputStringHandler.parseUserInput(userInputString);
                InstructionHandler.handleInstruction(taskHandler, instruction);
            } catch (YapperException e) {
                System.out.println("YapperException has occurred " + e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * The main method that serves as the entry point of the Yapper program.
     *
     * <p>
     * It initializes the TaskHandler, starts the chatbot loop,
     * and displays messages when the program starts and ends.
     * <p/>
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println(StringStorage.LINE_DIVIDER_OUTPUT);
        TaskHandler taskHandler = InputFileHandler.loadTasksFromFile();
        if (taskHandler.isEmpty()) {
            System.out.println(StringStorage.START_UP_MESSAGE);
            System.out.println(StringStorage.LINE_DIVIDER);
            System.out.println(StringStorage.HELP_MESSAGE);
        } else {
            System.out.println("Let us resume where we left off, shall we?");
        }

        runMainLoop(taskHandler);

        System.out.println(StringStorage.SHUT_DOWN_MESSAGE);
        System.out.println(StringStorage.LINE_DIVIDER);
    }
}
