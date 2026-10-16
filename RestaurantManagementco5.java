// FILE HANDLING: Classes used to create, write, read, and delete the records file.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class RestaurantManagementco5 {
    static boolean[] tables = new boolean[32];
    static final String FILE_NAME = "reservations.txt";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        createReservationFile();
        boolean running = true;

        while (running) {
            System.out.println("\n--- X Restaurant ---");
            System.out.println("1) Make reservation");
            System.out.println("2) View saved reservations");
            System.out.println("3) Delete saved reservations");
            System.out.println("4) Exit");
            System.out.print("Choose: ");
            int choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                makeReservation(input);
            } else if (choice == 2) {
                readReservations();
            } else if (choice == 3) {
                deleteReservations();
            } else if (choice == 4) {
                running = false;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        input.close();
    }

    static void createReservationFile() {
        // FILE HANDLING: Create reservations.txt if it does not already exist.
        File file = new File(FILE_NAME);
        try {
            if (file.createNewFile()) {
                System.out.println("Reservation file created.");
            }
        } catch (IOException e) {
            System.out.println("Could not create the reservation file.");
        }
    }

    static void showTables() {
        System.out.println("\nTables (A = available, X = booked):");
        for (int i = 0; i < tables.length; i++) {
            if (tables[i]) {
                System.out.print((i + 1) + ":X  ");
            } else {
                System.out.print((i + 1) + ":A  ");
            }
            if ((i + 1) % 8 == 0) {
                System.out.println();
            }
        }
    }

    static void makeReservation(Scanner input) {
        showTables();
        System.out.print("Customer name: ");
        String name = input.nextLine();

        System.out.print("Table number: ");
        int table = input.nextInt();
        input.nextLine();

        if (table < 1 || table > tables.length) {
            System.out.println("Invalid table number.");
        } else if (tables[table - 1]) {
            System.out.println("That table is already booked.");
        } else {
            System.out.println("Menu: 1) Burger $3.59  2) Fries $1.79  3) Soda $1.29");
            System.out.print("Food choice: ");
            int foodChoice = input.nextInt();
            System.out.print("Quantity: ");
            int quantity = input.nextInt();
            input.nextLine();

            String food = "";
            double price = 0;

            if (foodChoice == 1) {
                food = "Burger";
                price = 3.59;
            } else if (foodChoice == 2) {
                food = "Fries";
                price = 1.79;
            } else if (foodChoice == 3) {
                food = "Soda";
                price = 1.29;
            }

            if (food.equals("") || quantity < 1) {
                System.out.println("Invalid food choice or quantity.");
            } else {
                Reservation reservation = new Reservation(name, table, food, quantity, price);
                if (saveReservation(reservation)) {
                    tables[table - 1] = true;
                    reservation.showDetails();
                }
            }
        }
    }

    static boolean saveReservation(Reservation reservation) {
        try {
            // FILE HANDLING: Open the file in append mode so old reservations remain.
            FileOutputStream fileOutput = new FileOutputStream(FILE_NAME, true);
            OutputStreamWriter outputStream = new OutputStreamWriter(fileOutput);
            // FILE HANDLING: Buffer and write this reservation as a line of text.
            BufferedWriter writer = new BufferedWriter(outputStream);

            writer.write(reservation.toFileLine());
            writer.newLine();
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Could not save the reservation.");
            return false;
        }
    }

    static void readReservations() {
        try {
            // FILE HANDLING: Open the saved file and read its text one line at a time.
            FileInputStream fileInput = new FileInputStream(FILE_NAME);
            InputStreamReader inputStream = new InputStreamReader(fileInput);
            BufferedReader reader = new BufferedReader(inputStream);

            String line = reader.readLine();
            if (line == null) {
                System.out.println("No reservations saved.");
            }
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Could not read the reservation file.");
        }
    }

    static void deleteReservations() {
        // FILE HANDLING: Delete the file, then create an empty one for future records.
        File file = new File(FILE_NAME);
        if (file.delete()) {
            System.out.println("Saved reservations deleted.");
            for (int i = 0; i < tables.length; i++) {
                tables[i] = false;
            }
            createReservationFile();
        } else {
            System.out.println("There were no saved reservations to delete.");
        }
    }
}

class Reservation {
    private String customerName;
    private int tableNumber;
    private String food;
    private int quantity;
    private double total;

    Reservation(String customerName, int tableNumber, String food, int quantity, double price) {
        this.customerName = customerName;
        this.tableNumber = tableNumber;
        this.food = food;
        this.quantity = quantity;
        this.total = price * quantity;
    }

    void showDetails() {
        System.out.println("Saved: " + customerName
                + " | Table: " + tableNumber
                + " | Order: " + food + " x " + quantity
                + " | Total: $" + total);
    }

    String toFileLine() {
        return "Customer: " + customerName
                + " | Table: " + tableNumber
                + " | Order: " + food + " x " + quantity
                + " | Total: $" + total;
    }
}
