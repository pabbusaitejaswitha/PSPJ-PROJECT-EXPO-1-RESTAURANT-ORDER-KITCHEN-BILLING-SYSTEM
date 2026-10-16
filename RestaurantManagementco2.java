import java.util.Scanner;

public class RestaurantManagementco2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String restaurantName = "X Restaurant";
        double burgerPrice = 3.59;
        double friesPrice = 1.79;
        double sodaPrice = 1.29;
        double chipsPrice = 2.19;

        int burgerQuantity = 0;
        int friesQuantity = 0;
        int sodaQuantity = 0;
        int chipsQuantity = 0;

        boolean ordering = true;
        double subtotal = 0.0;

        System.out.println("Welcome to " + restaurantName + "!");

        while (ordering) {
            System.out.println("\n------ MENU ------");
            System.out.println("1) Burger       $" + burgerPrice);
            System.out.println("2) Fries        $" + friesPrice);
            System.out.println("3) Soda         $" + sodaPrice);
            System.out.println("4) Potato chips $" + chipsPrice);
            System.out.println("0) Finish order");
            System.out.print("Choose an item: ");

            int choice = input.nextInt();

            if (choice == 0) {
                ordering = false;
            } else if (choice >= 1 && choice <= 4) {
                System.out.print("Enter quantity (1 to 10): ");
                int quantity = input.nextInt();

                if (quantity >= 1 && quantity <= 10) {
                    switch (choice) {
                        case 1:
                            burgerQuantity = burgerQuantity + quantity;
                            subtotal = subtotal + burgerPrice * quantity;
                            System.out.println("Added " + quantity + " burger(s).");
                            break;
                        case 2:
                            friesQuantity = friesQuantity + quantity;
                            subtotal = subtotal + friesPrice * quantity;
                            System.out.println("Added " + quantity + " fries.");
                            break;
                        case 3:
                            sodaQuantity = sodaQuantity + quantity;
                            subtotal = subtotal + sodaPrice * quantity;
                            System.out.println("Added " + quantity + " soda(s).");
                            break;
                        case 4:
                            chipsQuantity = chipsQuantity + quantity;
                            subtotal = subtotal + chipsPrice * quantity;
                            System.out.println("Added " + quantity + " bag(s) of chips.");
                            break;
                    }
                } else {
                    System.out.println("Invalid quantity. Enter a number from 1 to 10.");
                }
            } else {
                System.out.println("Invalid choice. Select an item from 1 to 4, or 0 to finish.");
            }
        }

        double cgst = subtotal * 0.05;
        double sgst = subtotal * 0.05;
        double total = subtotal + cgst + sgst;
        boolean hasItems = subtotal > 0;

        System.out.println("\n" + restaurantName);
        System.out.println("-------- RECEIPT --------");

        if (hasItems) {
            if (burgerQuantity > 0) {
                System.out.println("Burger x " + burgerQuantity + " = $" + (burgerPrice * burgerQuantity));
            }
            if (friesQuantity > 0) {
                System.out.println("Fries x " + friesQuantity + " = $" + (friesPrice * friesQuantity));
            }
            if (sodaQuantity > 0) {
                System.out.println("Soda x " + sodaQuantity + " = $" + (sodaPrice * sodaQuantity));
            }
            if (chipsQuantity > 0) {
                System.out.println("Potato chips x " + chipsQuantity + " = $" + (chipsPrice * chipsQuantity));
            }

            System.out.println("-------------------------");
            System.out.printf("Subtotal: $%.2f%n", subtotal);
            System.out.printf("CGST (5%%): $%.2f%n", cgst);
            System.out.printf("SGST (5%%): $%.2f%n", sgst);
            System.out.printf("Total: $%.2f%n", total);
        } else {
            System.out.println("No items were ordered.");
        }

        System.out.println("Thank you for visiting!");
        input.close();
    }
}
