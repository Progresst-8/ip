// Input Text Parser for Yapper
public class InputStringHandler {
    private static String DEADLINE_END_DATE_DELIMITER = "/by";
    private static String EVENT_START_DATE_DELIMITER = "/from";
    private static String EVENT_END_DATE_DELIMITER = "/to";

    public static Instruction parseUserInput(String userInputString) {
        int splitAtIndex = userInputString.indexOf(' ');
        String instructionType = userInputString.substring(0, splitAtIndex);

        // Handle One-Argument Instructions
        if (splitAtIndex >= 0) {
            switch (instructionType) {
            case "list":
                // TODO error handling
                return new Instruction(Instruction.InstructionType.LIST);
            case "bye":
                return new Instruction(Instruction.InstructionType.BYE);
            }
        }
        String instructionArgs = userInputString.substring(splitAtIndex);
        // TODO error handling

        // Handle Two-Argument Instructions
        switch (instructionType) {
        case "add": // TODO remove
            return new Instruction(Instruction.InstructionType.ADD, instructionArgs);
        case "todo":
            return new Instruction(Instruction.InstructionType.TODO, instructionArgs);
        case "mark":
            return new Instruction(Instruction.InstructionType.MARK, Integer.parseInt(instructionArgs));
        case "unmark":
            return new Instruction(Instruction.InstructionType.UNMARK, Integer.parseInt(instructionArgs));
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
        return new Instruction(null); // shouldn't happen
        // replace with proper error handling
    }
}
