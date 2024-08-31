// Input Text Parser for Yapper
public class InputStringHandler {
     public static Instruction parseUserInput(String userInputString) {
        String[] instruction = userInputString.split(" ", 2);
        String instructionType = instruction[0].toLowerCase();
        String instructionArgument = instruction.length > 1 ? instruction[1] : "";
        // account for OOB in case command is 1 word long

        switch (instructionType) {
        case "add":
            return new Instruction(Instruction.InstructionType.ADD, instructionArgument);
        case "list":
            return new Instruction(Instruction.InstructionType.LIST);
        case "mark":
            return new Instruction(Instruction.InstructionType.MARK, Integer.parseInt(instructionArgument));
        case "unmark":
            return new Instruction(Instruction.InstructionType.UNMARK, Integer.parseInt(instructionArgument));
        case "bye":
            return new Instruction(Instruction.InstructionType.BYE);
        }
        return new Instruction(null); // shouldn't happen
    }
}
