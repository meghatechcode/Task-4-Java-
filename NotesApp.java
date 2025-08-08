import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("=================================");
        System.out.println("       # Notes Manager App #     ");
        System.out.println("=================================");

        while (true) {
            System.out.println("\n1. Write a new note");
            System.out.println("2. View all notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    writeNote(sc);
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    System.out.println("Thank you for using Notes Manager! Goodbye 👋");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    
    private static void writeNote(Scanner sc) {
        System.out.println("\nEnter your note (type 'END' in a new line to finish):");
        StringBuilder noteBuilder = new StringBuilder();

        while (true) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("END")) break;
            noteBuilder.append(line).append(System.lineSeparator());
        }

        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write("----- New Note -----\n");
            fw.write(noteBuilder.toString());
            fw.write("\n");
            System.out.println(" Note saved successfully!");
        } catch (IOException e) {
            System.out.println(" Error writing note: " + e.getMessage());
        }
    }

    
    private static void readNotes() {
        System.out.println("\n Your Notes:");
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No notes found. Start writing some!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(" Error reading notes: " + e.getMessage());
        }
    }
}
