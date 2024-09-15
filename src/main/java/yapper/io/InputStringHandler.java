package yapper.io;

import yapper.exceptions.ErrorHandler;
import yapper.exceptions.YapperException;
import yapper.instructions.Instruction;

// Input Text Parser for Yapper
public class InputStringHandler {
    private static String DEADLINE_END_DATE_DELIMITER = "/by";
    private static String EVENT_START_DATE_DELIMITER = "/from";
    private static String EVENT_END_DATE_DELIMITER = "/to";

    public static Instruction parseUserInput(String userInputString) throws YapperException {
        // User Input Validation
        try {
            ErrorHandler.checkIfUserInputEmpty(userInputString);
        } catch (YapperException e) {
            System.out.println(e.getMessage()); //
        }
        userInputString = userInputString.trim();
        int splitAtIndex = userInputString.indexOf(' ');
        String instructionType = (splitAtIndex == -1) ?
                userInputString : userInputString.substring(0, splitAtIndex);

        // Handle 1-Argument Instructions: Instruction
        if (splitAtIndex == -1) {
            switch (instructionType) {
            case "list":
                return new Instruction(Instruction.InstructionType.LIST);
            }
        }

        // User Input Validation
        String instructionArgs = userInputString.substring(splitAtIndex).trim();
        try {
            ErrorHandler.checkIfUserInputArgsEmpty(instructionArgs);
        } catch (YapperException e) {
            StringStorage.printWithDividers(e.getMessage()); //
        }

        // Handle 2-Argument Instructions: Instruction-Desc, Instruction-Ordinal
        int taskOrdinal;
        switch (instructionType) {
        case "todo":
            return new Instruction(Instruction.InstructionType.TODO, instructionArgs);
        case "delete":
            try {
                taskOrdinal = Integer.parseInt(instructionArgs);
                return new Instruction(Instruction.InstructionType.DELETE, taskOrdinal);
            } catch (NumberFormatException e) {
                StringStorage.printWithDividers(e.getMessage()); // "invalid task number format"
            }
        case "mark":
        case "unmark":
            try {
                taskOrdinal = Integer.parseInt(instructionArgs);
                Instruction.InstructionType type = instructionType.equals("mark")
                                                 ? Instruction.InstructionType.MARK
                                                 : Instruction.InstructionType.UNMARK;
                return new Instruction(type, taskOrdinal);
            } catch (NumberFormatException e) {
                StringStorage.printWithDividers(e.getMessage()); // "invalid task number format"
            }
        }

        // Handle 3-Argument Instruction: Instruction-Desc-endDate
        // Handle 4-Argument Instruction: Instruction-Desc-startDate-endDate
        String args[];
        switch (instructionType) {
        case "deadline":
            args = instructionArgs.split(DEADLINE_END_DATE_DELIMITER);
            String taskDesc = args[0].trim();
            String deadline = args[1].trim();
            return new Instruction(Instruction.InstructionType.DEADLINE, taskDesc, deadline);
        case "event":
            args = instructionArgs.split(EVENT_START_DATE_DELIMITER);
            String eventDesc = args[0].trim();
            String[] dates = args[1].split(EVENT_END_DATE_DELIMITER);
            String startDate = dates[0].trim();
            String endDate = dates[1].trim();
            return new Instruction(Instruction.InstructionType.EVENT, eventDesc, startDate, endDate);
        }
        // TODO exception for incorrect arguments

        // If none of the above code works, user input cannot be recognized
        throw new YapperException(StringStorage.UNRECOGNISED_INSTRUCTION_MESSAGE);
    }
}
