// Main function for Yapper
public class Yapper {
    public static void main(String[] args) {
        // Initialize
        TaskManager taskManager = new TaskManager(100);
        // Startup ChatBot Program
        UserInterface.startYappin(taskManager);
    }
}
