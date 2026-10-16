public class RestaurantManagementco3 {
int Total_Tables = 32;

// One status value for each table
char[] status = new char[Total_Tables];

// Arrays that store order information at matching positions
static final int MAX_ORDERS = 100;
static int[] orderNumbers = new int[MAX_ORDERS];
static int[] orderTablesList = new int[MAX_ORDERS];
static String[] orderFoodList = new String[MAX_ORDERS];
static double[] orderTotals = new double[MAX_ORDERS];
static String[] orderStatus = new String[MAX_ORDERS];

static int orderRecordCount = 0;

public void initStatus() {
    for (int i = 0; i < Total_Tables; i++) {
        status[i] = 'A';
    }
}

public void DisplayTable(int[] table, int row) {
    System.out.print("Row" + row + ": ");

    for (int x : table) {
        System.out.print(x + ":" + status[x - 1] + " | ");
    }

    System.out.println();
}

public boolean BookTable(int tableNo) {
    int index = tableNo - 1;

    if (index < 0 || index >= Total_Tables || status[index] == 'X') {
        return false;
    }

    status[index] = 'X';
    return true;
}

static void saveOrderRecord(int orderNo, int tables, String food, double total) {
    orderNumbers[orderRecordCount] = orderNo;
    orderTablesList[orderRecordCount] = tables;
    orderFoodList[orderRecordCount] = food;
    orderTotals[orderRecordCount] = total;
    orderStatus[orderRecordCount] = "Pending";
    orderRecordCount++;
}

static void printOrderDetails(int index) {
    System.out.println("Order #" + orderNumbers[index]);
    System.out.println("Tables: " + orderTablesList[index]);
    System.out.println("Food:\n" + orderFoodList[index]);
}

        int[] tablesr1 = { 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 };
        int[] tablesr2 = { 9 , 10, 11, 12, 13, 14, 15, 16};
        int[] tablesr3 = {17, 18, 19, 20, 21, 22, 23, 24};
        int[] tablesr4 = {25, 26, 27, 28, 29, 30, 31, 32};
        int[] finalchoice = new int[32];
        int bookedcount = 0;
}