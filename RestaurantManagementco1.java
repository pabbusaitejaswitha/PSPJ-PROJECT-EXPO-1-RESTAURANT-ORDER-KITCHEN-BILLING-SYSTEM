public class RestaurantManagementco1 {
    public static void main(String[] args) {
      
        String restaurantName = "X Restaurant";
        String currency = "$";

        double burgerPrice = 3.59;
        double friesPrice = 1.79;
        double sodaPrice = 1.29;

   
        int burgerQuantity = 2;
        int friesQuantity = 1;
        int sodaQuantity = 2;

      
        double burgerCost = burgerPrice * burgerQuantity;
        double friesCost = friesPrice * friesQuantity;
        double sodaCost = sodaPrice * sodaQuantity;
        double subtotal = burgerCost + friesCost + sodaCost;

      
        double cgstRate = 0.05;
        double sgstRate = 0.05;
        double cgst = subtotal * cgstRate;
        double sgst = subtotal * sgstRate;
        double total = subtotal + cgst + sgst;

       
        int roundedSubtotal = (int) subtotal;

        System.out.println(restaurantName);
        System.out.println("-------------------------");
        System.out.println("Burger x " + burgerQuantity + ": " + currency + burgerCost);
        System.out.println("Fries x " + friesQuantity + ": " + currency + friesCost);
        System.out.println("Soda x " + sodaQuantity + ": " + currency + sodaCost);
        System.out.println("-------------------------");
        System.out.println("Subtotal: " + currency + subtotal);
        System.out.println("CGST: " + currency + cgst);
        System.out.println("SGST: " + currency + sgst);
        System.out.println("Total: " + currency + total);
        System.out.println("Subtotal as an integer: " + roundedSubtotal);
    }
}