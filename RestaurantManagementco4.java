import java.util.Scanner;

public class RestaurantManagementco4 {
    // CHANGED: A simple boolean array tracks whether each table is booked.
    static boolean[] tables = new boolean[32];

    // ADDED: Each array slot stores one Reservation object instead of
    // keeping customer, table, food, quantity, and total in separate arrays.
    static Reservation[] reservations = new Reservation[100];
    static int reservationCount = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- X Restaurant ---");
            System.out.println("1) Make reservation");
            System.out.println("2) View reservations");
            System.out.println("3) Exit");
            System.out.print("Choose: ");
            int choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                // ADDED: This method handles the reservation workflow.
                makeReservation(input);
            } else if (choice == 2) {
                // ADDED: This method displays saved Reservation objects.
                viewReservations();
            } else if (choice == 3) {
                running = false;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        input.close();
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
        if (reservationCount == reservations.length) {
            System.out.println("Reservation list is full.");
            return;
        }

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
                tables[table - 1] = true;
                // ADDED: Create and save a Reservation object for this customer.
                reservations[reservationCount] = new Reservation(name, table, food, quantity, price);
                reservations[reservationCount].showDetails();
                reservationCount++;
            }
        }
    }

    static void viewReservations() {
        if (reservationCount == 0) {
            System.out.println("No reservations saved.");
        } else {
            for (int i = 0; i < reservationCount; i++) {
                reservations[i].showDetails();
            }
        }
    }
}

// ADDED: A class groups the details that belong to one reservation.
class Reservation {
    // ADDED: Private attributes keep reservation details inside this class.
    private String customerName;
    private int tableNumber;
    private String food;
    private int quantity;
    private double total;

    // ADDED: The constructor fills the attributes when a Reservation is created.
    Reservation(String customerName, int tableNumber, String food, int quantity, double price) {
        this.customerName = customerName;
        this.tableNumber = tableNumber;
        this.food = food;
        this.quantity = quantity;
        this.total = price * quantity;
    }

    // ADDED: This method displays the details stored in this object.
    void showDetails() {
        System.out.println("Customer: " + customerName
                + " | Table: " + tableNumber
                + " | Order: " + food + " x " + quantity
                + " | Total: $" + total);
    }
}
