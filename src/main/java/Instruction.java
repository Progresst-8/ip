// Handles the various input instructions for Yapper
public class Instruction {
    public enum InstructionType {
        ADD, LIST, MARK, UNMARK, BYE
    }
    private InstructionType instructionType;
    private String instructionStringArgument;
    private Integer instructionIntegerArgument;

    // Constructors
    public Instruction(InstructionType type) {
        this.instructionType = type;
    }
    public Instruction(InstructionType type, String argument) {
        this.instructionType = type;
        this.instructionStringArgument = argument;
    }
    public Instruction(InstructionType type, Integer argument) {
        this.instructionType = type;
        this.instructionIntegerArgument = argument;
    }
    // Getters
    public InstructionType getInstructionType() {
        return instructionType;
    }
    public String getInstructionStringArgument() {
        return instructionStringArgument;
    }
    public Integer getInstructionIntegerArgument() {
        return instructionIntegerArgument;
    }
}
