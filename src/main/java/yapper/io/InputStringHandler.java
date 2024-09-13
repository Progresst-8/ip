package yapper.io;

import yapper.exceptions.ErrorHandler;
import yapper.exceptions.YapperException;
import yapper.instructions.Instruction;

// Input Text Parser for Yapper
public class InputStringHandler {
    private static String DEADLINE_END_DATE_DELIMITER = "/by";
    private static String EVENT_START_DATE_DELIMITER = "/from";
    private static String EVENT_END_DATE_DELIMITER = "/to";

    public static Instruction parseUserInput(String userInputString) {
        // User Input Validation
        try {
            ErrorHandler.checkIfUserInputEmpty(userInputString);
        } catch (YapperException e) {
            System.out.println(e.getMessage()); //
        }
        int splitAtIndex = userInputString.indexOf(' ');
        String instructionType = userInputString.substring(0, splitAtIndex);

        // Handle One-Argument Instructions
        if (splitAtIndex >= 0) {
            switch (instructionType) {
            case "list":
                return new Instruction(Instruction.InstructionType.LIST);
            case "bye": // TODO remove
                return new Instruction(Instruction.InstructionType.BYE); // TODO remove
            }
        }

        // Input Validation
        String instructionArgs = userInputString.substring(splitAtIndex);
        try {
            ErrorHandler.checkIfUserInputArgsEmpty(instructionArgs);
        } catch (YapperException e) {
            System.out.println(e.getMessage()); //
        }

        // Handle Two-Argument Instructions
        switch (instructionType) {
        case "todo":
            return new Instruction(Instruction.InstructionType.TODO, instructionArgs);
        case "mark":
        case "unmark":
            try {
                int taskOrdinal = Integer.parseInt(instructionArgs);
                Instruction.InstructionType type = instructionType.equals("mark")
                                                 ? Instruction.InstructionType.MARK
                                                 : Instruction.InstructionType.UNMARK;
                return new Instruction(type, taskOrdinal);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage()); // "invalid task number format"
            }
        }

        // Handle Three-Argument Instructions
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
            String startDate = dates[1].trim();
            String endDate = dates[1].trim();
            return new Instruction(Instruction.InstructionType.EVENT, eventDesc, startDate, endDate);
        }

        //
        System.out.println(StringStorage.UNRECOGNISED_INSTRUCTION_MESSAGE);
        return null; // TODO instruction cannot be parsed
    }
}
