import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;            //Import the File class
import java.io.FileWriter;
import java.io.IOException;     //Import the IOException class to handle errors

public class Main {
    static Scanner input = new Scanner(System.in);

    static ArrayList<Customer> waitingList = new ArrayList<>();


    public static int burger_stock = 50;        //starting burger count
    public static int burger_price = 650;       //burger price

    public static int cashier1_income = 0;
    public static int cashier2_income = 0;
    public static int cashier3_income = 0;

    static FoodQueue queue1 = new FoodQueue(2);
    static FoodQueue queue2 = new FoodQueue(3);
    static FoodQueue queue3 = new FoodQueue(5);

    static Customer[] cashier1 = queue1.getCustomer();
    static Customer[] cashier2 = queue2.getCustomer();
    static Customer[] cashier3 = queue3.getCustomer();


    public static void main(String[] args) throws IOException {

        String option = "";  //declaring option as a string to run the program for both int and string inputs

        while (option != "999" && option != "EXIT") {    //prints the menu until the condition becomes false
            printMenu();
            System.out.print("Enter your option: ");      //asking user for the option
            option = input.next().toUpperCase();        //converting all the inputs to upper_case

            switch (option) {
                case "100":
                case "VFQ":
                    viewAllQueues();
                    break;
                case "101":
                case "VEQ":
                    viewAllEmptyQueues();
                    break;
                case "102":
                case "ACQ":
                    addCustomerToQueue();
                    break;
                case "103":
                case "RCQ":
                    removeCustomerFromQueue();
                    break;
                case "104":
                case "PCQ":
                    removeServedCustomer();
                    break;
                case "105":
                case "VCS":
                    viewCustomerSorted();
                    break;
                case "106":
                case "SPD":
                    storeProgramData();
                    break;
                case "107":
                case "LPD":
                    loadProgramData();
                    break;
                case "108":
                case "STK":
                    viewRemainingBurgerStock();
                    break;
                case "109":
                case "AFS":
                    addBurgersToStock();
                    break;
                case "110":
                case "IFQ":
                    incomeOfEachQueue();
                case "999":
                case "EXIT":
                    System.out.println("\nPROGRAM EXITED");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;

            }
        }

    }

    /**
     * printing the menu
     */
    public static void printMenu() {
        System.out.println("----------MENU----------");
        System.out.println("100 or VFQ: View all Queues.");
        System.out.println("101 or VEQ: View all Empty Queues.");
        System.out.println("102 or ACQ: Add customer to a Queue.");
        System.out.println("103 or RCQ: Remove a customer from a Queue.(From a specific location)");
        System.out.println("104 or PCQ: Remove a served customer.");
        System.out.println("105 or VCS: View Customers Sorted in alphabetical order(Do not use library sort routine)");
        System.out.println("106 or SPD: Store Program Data into file.");
        System.out.println("107 or LPD: Load Program Data from file.");
        System.out.println("108 or STK: View Remaining burgers Stock.");
        System.out.println("109 or AFS: Add Burgers to Stock.");
        System.out.println("110 or IFQ: Print the Income of Each Queue.");
        System.out.println("999 or EXIT: Exit the Program.");

    }

    /**
     * Displays how the cashiers print with the spaces
     * "X" is to show  a queue is not occupied, while "O" is to show it is occupied.
     */
    public static void viewAllQueues() {

        System.out.println("******************");
        System.out.println("*    Cashiers    *");
        System.out.println("******************");

        for (int i = 0; i < cashier3.length; i++) {
            String queue1;
            if (i < cashier1.length) {
                if (cashier1[i] == null) {
                    queue1 = "X";
                } else {
                    queue1 = "O";
                }
            } else {
                queue1 = " ";
            }

            String queue2;
            if (i < cashier2.length) {
                if (cashier2[i] == null) {
                    queue2 = "X";
                } else {
                    queue2 = "O";
                }
            } else {
                queue2 = " ";
            }

            String queue3;
            if (i < cashier3.length) {
                if (cashier3[i] == null) {
                    queue3 = "X";
                } else {
                    queue3 = "O";
                }
            } else {
                queue3 = " ";
            }
            System.out.printf("%-7s %-7s %-7s%n", queue1, queue2, queue3);
        }

        System.out.println("X-Not Occupied  O-Occupied");


        if (!waitingList.isEmpty()) {

            System.out.println("Waiting list : ");
            for (Customer item : waitingList) {
                if (item != null) {
                    System.out.println("Name - " + item.getFull_name());
                }
            }
        }
        else{
            System.out.println("Waiting list is empty");

        }

    }

    /**
     * Displays the  all cashiers that are empty.
     * "X" shows an empty queue, while a space shows an occupied queue.
     */
    public static void viewAllEmptyQueues() {

        System.out.println("******************");
        System.out.println("*    Cashiers    *");
        System.out.println("******************");

        for (int i = 0; i < cashier3.length; i++) {
            String queue1;
            if (i < cashier1.length) {
                if (cashier1[i] == null) {
                    queue1 = "X";
                } else {
                    queue1 = " ";
                }
            } else {
                queue1 = " ";
            }

            String queue2;
            if (i < cashier2.length) {
                if (cashier2[i] == null) {
                    queue2 = "X";
                } else {
                    queue2 = " ";
                }
            } else {
                queue2 = " ";
            }

            String queue3;
            if (i < cashier3.length) {
                if (cashier3[i] == null) {
                    queue3 = "X";
                } else {
                    queue3 = " ";
                }
            } else {
                queue3 = " ";
            }
            System.out.printf("%-7s %-7s %-7s%n", queue1, queue2, queue3);
        }


    }

    /**
     * Add customers to the queue with the minimum length.
     * Asking for the first name, second name and no. of burgers and creating a new customer object and assigning the inputs to it.
     * Checking if the number of burgers are less than 50 if not print an error message
     * Checking if the input for burger is numeric if not printing an error message and asking user again for the input.
     * Checking if the input for first name and second name is not numeric if not printing an error message and asking user again for the input.
     */
    public static void addCustomerToQueue() {

        String fName;
        while (true) {
            System.out.println("Enter your First Name :");
            fName = input.next();
            input.nextLine();
            if (!isNumeric(fName)) {
                break;
            } else {
                System.out.println("Invalid input. First Name cannot be a number. Please try again.");
            }
        }
        String sName;
        while (true) {
            System.out.println("Enter your Second Name :");
            sName = input.next();
            input.nextLine();
            if (!isNumeric(sName)) {
                break;
            } else {
                System.out.println("Invalid input. Second Name cannot be a number. Please try again.");
            }
        }

        int burger;
        while (true) {
            System.out.println("How many burgers do you want:");
            String burgerInput = input.next();
            input.nextLine();

            if (isNumeric(burgerInput)) {
                burger = Integer.parseInt(burgerInput);
                break;
            } else {
                System.out.println("Invalid input. Number of burgers must be numeric. Please try again.");
            }
        }

        Customer newCustomer = new Customer(fName, sName, burger);

        if (burger > 50) {
            System.out.println("You can only buy 50 burgers");
            return;
        }

        if (cashier1[0] == null) {
            cashier1[0] = newCustomer;
            System.out.println("Customer successfully added to queue 1.");
        } else {
            if (cashier2[0] == null) {
                cashier2[0] = newCustomer;
                System.out.println("Customer successfully added to queue 2.");
            } else {
                if (cashier3[0] == null) {
                    cashier3[0] = newCustomer;
                    System.out.println("Customer successfully added to queue 3.");
                } else {
                    if (cashier1[1] == null) {
                        cashier1[1] = newCustomer;
                        System.out.println("Customer successfully added to queue 1.");
                    } else {
                        if (cashier2[1] == null) {
                            cashier2[1] = newCustomer;
                            System.out.println("Customer successfully added to queue 2.");
                        } else {
                            if (cashier3[1] == null) {
                                cashier3[1] = newCustomer;
                                System.out.println("Customer successfully added to queue 3.");
                            } else {
                                if (cashier2[2] == null) {
                                    cashier2[2] = newCustomer;
                                    System.out.println("Customer successfully added to queue 2.");
                                } else {
                                    if (cashier3[2] == null) {
                                        cashier3[2] = newCustomer;
                                        System.out.println("Customer successfully added to queue 3.");
                                    } else {
                                        if (cashier3[3] == null) {
                                            cashier3[3] = newCustomer;
                                            System.out.println("Customer successfully added to queue 3.");
                                        } else {
                                            if (cashier3[4] == null) {
                                                cashier3[4] = newCustomer;
                                                System.out.println("Customer successfully added to queue 3.");
                                            }
                                            else {
                                                System.out.println("All queues are full. Cannot add more customers.\n");
                                                if (waitingList.size()!=5) {
                                                    waitingList.add(newCustomer);
                                                    System.out.println(newCustomer.getFull_name() + " added to waiting list");
                                                }
                                                else {
                                                    System.out.println("Waiting list is full");
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }


    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Asks the user for the queue number and the position to remove customer without serving.
     * Burger stock won't change in this method.
     * Removes the customer from the queue and shifts the other customers to the front.
     */
    public static void removeCustomerFromQueue() {

        System.out.print("Enter the queue number (1, 2, or 3): ");
        int cashierNumber = input.nextInt();

        System.out.print("Enter the position to remove (starting from 1): ");
        int position = input.nextInt();

        if (cashierNumber == 1 && position > 0 && position < 3) {
            if (position == 1) {
                if (cashier1[position - 1] == null) {
                    System.out.println("No customer found at the position you entered.");
                } else {
                    if (waitingList.isEmpty()){
                        cashier1[position - 1] = null;
                        System.out.println("Customer removed from the queue.");
                    }
                    else {
                        System.out.println(waitingList.get(0).getFull_name() + " added to cashier 1 from waiting list");
                        cashier1[position-1]=waitingList.get(0);
                        waitingList.remove(0);
                    }

                    for (int i = 0; i < (cashier1.length - 1); i++) {
                        if (cashier1[i] == null) {
                            Customer tmp = cashier1[i + 1];
                            cashier1[i + 1] = cashier1[i];
                            cashier1[i] = tmp;
                        }
                    }
                }
            } else if (position == 2) {
                if (cashier1[position - 1] == null) {
                    System.out.println("No customer found at the position you entered.");
                } else {
                    if (waitingList.isEmpty()){
                        cashier2[position - 1] = null;
                        System.out.println("Customer removed from the queue.");
                    }
                    else {
                        System.out.println(waitingList.get(0).getFull_name() + " added to cashier 2 from waiting list");
                        cashier2[position-1]=waitingList.get(0);
                        waitingList.remove(0);
                    }

                    for (int i = 0; i < (cashier1.length - 1); i++) {
                        if (cashier1[i] == null) {
                            Customer tmp = cashier1[i + 1];
                            cashier1[i + 1] = cashier1[i];
                            cashier1[i] = tmp;
                        }
                    }
                }

            } else {
                System.out.println("Wrong Input. Try Again!!!");
            }

        } else if (cashierNumber == 2 && position > 0 && position < 4) {
            if (position == 1) {
                if (cashier2[position - 1] == null) {
                    System.out.println("No customer found at the position you entered.");
                } else {
                    if (waitingList.isEmpty()){
                        cashier3[position - 1] = null;
                        System.out.println("Customer removed from the queue.");
                    }
                    else {
                        System.out.println(waitingList.get(0).getFull_name() + " added to cashier 3 from waiting list");
                        cashier3[position-1]=waitingList.get(0);
                        waitingList.remove(0);
                    }

                    for (int i = 0; i < (cashier2.length - 1); i++) {
                        if (cashier2[i] == null) {
                            Customer tmp = cashier2[i + 1];
                            cashier2[i + 1] = cashier2[i];
                            cashier2[i] = tmp;
                        }
                    }
                }
            } else if (position == 2) {
                if (cashier2[position - 1] == null) {
                    System.out.println("No customer found at the position you entered.");
                } else {
                    cashier2[position - 1] = null;
                    System.out.println("Customer removed from the queue.");

                    for (int i = 0; i < (cashier2.length - 1); i++) {
                        if (cashier2[i] == null) {
                            Customer tmp = cashier2[i + 1];
                            cashier2[i + 1] = cashier2[i];
                            cashier2[i] = tmp;
                        }
                    }
                }

            } else if (position == 3) {
                if (cashier2[position - 1] == null) {
                    System.out.println("No customer found at the position you entered.");
                } else {
                    cashier2[position - 1] = null;
                    System.out.println("Customer removed from the queue.");

                    for (int i = 0; i < (cashier2.length - 1); i++) {
                        if (cashier2[i] == null) {
                            Customer tmp = cashier2[i + 1];
                            cashier2[i + 1] = cashier2[i];
                            cashier2[i] = tmp;
                        }
                    }
                }

            } else {
                System.out.println("Wrong Input. Try Again!!!");
            }

        } else if (cashierNumber == 3 && position > 0 && position < 6) {
            if (position == 1) {
                if (cashier3[position - 1] == null) {
                    System.out.println("No customer found at the position you entered.");
                } else {
                    cashier3[position - 1] = null;
                    System.out.println("Customer removed from the queue.");

                    for (int i = 0; i < (cashier3.length - 1); i++) {
                        if (cashier3[i] == null) {
                            Customer tmp = cashier3[i + 1];
                            cashier3[i + 1] = cashier3[i];
                            cashier3[i] = tmp;
                        }
                    }
                }

            }
            else if (position == 2) {
                if (cashier3[position - 1] == null) {
                    System.out.println("No customer found at the position you entered.");
                } else {
                    cashier3[position - 1] = null;
                    System.out.println("Customer removed from the queue.");

                    for (int i = 0; i < (cashier3.length - 1); i++) {
                        if (cashier3[i] == null) {
                            Customer tmp = cashier3[i + 1];
                            cashier3[i + 1] = cashier3[i];
                            cashier3[i] = tmp;
                        }
                    }
                }

            }
            else if (position == 3) {
                if (cashier3[position - 1] != null) {
                    cashier3[position - 1] = null;
                    System.out.println("Customer removed from the queue.");
                } else {
                    System.out.println("No customer found at the position you entered.");

                    for (int i = 0; i < (cashier3.length - 1); i++) {
                        if (cashier3[i] == null) {
                            Customer tmp = cashier3[i + 1];
                            cashier3[i + 1] = cashier3[i];
                            cashier3[i] = tmp;
                        }
                    }
                }

            }
            else if (position == 4) {
                if (cashier3[position - 1] == null) {
                    System.out.println("No customer found at the position you entered.");
                } else {
                    cashier3[position - 1] = null;
                    System.out.println("Customer removed from the queue.");

                    for (int i = 0; i < (cashier3.length - 1); i++) {
                        if (cashier3[i] == null) {
                            Customer tmp = cashier3[i + 1];
                            cashier3[i + 1] = cashier3[i];
                            cashier3[i] = tmp;
                        }
                    }
                }

            }
            else if (position == 5) {
                if (cashier3[position - 1] == null) {
                    System.out.println("No customer found at the position you entered.");
                } else {
                    cashier3[position - 1] = null;
                    System.out.println("Customer removed from the queue.");

                    for (int i = 0; i < (cashier3.length - 1); i++) {
                        if (cashier3[i] == null) {
                            Customer tmp = cashier3[i + 1];
                            cashier3[i + 1] = cashier3[i];
                            cashier3[i] = tmp;
                        }
                    }
                }
            }

            else{
                System.out.println("Wrong Input. Try Again!!!");
            }
        }
        else {
            System.out.println("Invalid cashier number or position. Please try again.");
        }
    }

    /**
     * Asks user for the cashier number to remove a customer after serving.
     * Removes the first customer in entered queue and shifts the other customers to the front.
     * Not allowing to serve customers if the burger_stock is 0 and printing an error message telling user to add burgers to the stock before serving.
     */

    public static void removeServedCustomer() {

        System.out.println("Enter the cashier number(1-3): ");
        int cashierNumber = input.nextInt();

        if (burger_stock == 0) {
            System.out.println("----Burger Stock is Empty-----");
            System.out.println("Add burgers to the stock before serving customers");
            return;
        }

        if(cashierNumber==1){
            if (cashier1[0]!=null){
                System.out.println("\nCustomer : " + cashier1[0].getFull_name() + " was served with " + cashier1[0].getBurgerQuantity() + " burgers");
                burger_stock -= cashier1[0].getBurgerQuantity();
                cashier1_income += cashier1[0].getBurgerQuantity();
                if (waitingList.isEmpty()){
                    cashier1[0] = null;
                }
                else {
                    System.out.println("\nCustomer : " + cashier1[0].getFull_name() + " was served with " + cashier1[0].getBurgerQuantity() + " burgers");
                    System.out.println(waitingList.get(0).getFull_name() + " added to cashier 1 from waiting list");
                    cashier1[0]=waitingList.get(0);
                    waitingList.remove(0);
                }

                for (int i = 0; i < (cashier1.length - 1); i++) {
                    if (cashier1[i] == null) {
                        Customer tmp = cashier1[i + 1];
                        cashier1[i + 1] = cashier1[i];
                        cashier1[i] = tmp;
                    }
                }
            }
            else {
                System.out.println("\nQueue 1 is empty");
            }
        }else if(cashierNumber==2){
            if (cashier2[0]!=null){
                System.out.println("\nCustomer : " + cashier2[0].getFull_name()+ " was served with " + cashier2[0].getBurgerQuantity() + " burgers");
                burger_stock -= cashier2[0].getBurgerQuantity();
                cashier2_income += cashier2[0].getBurgerQuantity();
                if (waitingList.isEmpty()){
                    cashier2[0] = null;
                }
                else {
                    System.out.println("\nCustomer : " + cashier2[0].getFull_name() + " was served with " + cashier2[0].getBurgerQuantity() + " burgers");
                    System.out.println(waitingList.get(0).getFull_name() + " added to cashier 1 from waiting list");
                    cashier2[0]=waitingList.get(0);
                    waitingList.remove(0);
                }

                for (int i = 0; i < (cashier2.length - 1); i++) {
                    if (cashier2[i] == null) {
                        Customer tmp = cashier2[i + 1];
                        cashier2[i + 1] = cashier2[i];
                        cashier2[i] = tmp;
                    }
                }
            }
        }else if(cashierNumber==3){
            if (cashier3[0]!=null){
                System.out.println("\nCustomer : " + cashier3[0].getFull_name() + " was served with " + cashier3[0].getBurgerQuantity() + " burgers");
                burger_stock -= cashier3[0].getBurgerQuantity();
                cashier3_income += cashier3[0].getBurgerQuantity();
                if (waitingList.isEmpty()){
                    cashier3[0] = null;
                }
                else {
                    System.out.println("\nCustomer : " + cashier3[0].getFull_name() + " was served with " + cashier3[0].getBurgerQuantity() + " burgers");
                    System.out.println(waitingList.get(0).getFull_name() + " added to cashier 1 from waiting list");
                    cashier3[0]=waitingList.get(0);
                    waitingList.remove(0);
                }

                for (int i = 0; i < (cashier3.length - 1); i++) {
                    if (cashier3[i] == null) {
                        Customer tmp = cashier3[i + 1];
                        cashier3[i + 1] = cashier3[i];
                        cashier3[i] = tmp;
                    }
                }
            }
            else {
                System.out.println("\nQueue 3 is empty");
            }
        }
        else{
            System.out.println("Invalid cashier number. Please try again.");
        }

    }

    public static void viewRemainingBurgerStock() {

        System.out.println("Remaining burger stock is : " + burger_stock);
    }

    /**
     * Adds burgers to the stock if the stock is less than 50.
     * Checks if the stock is already full (equal to 50) and displays a message if so.
     * Updates the burger_stock with the new stock value.
     * Breaks the loop if the user enters 999 to go back to the menu.
     * if the user tries to add burgers more than 50 there prints an error message.
     * Prints an error message if the user enters an invalid value.
     */
    public static void addBurgersToStock() {
        System.out.println("--------Adding Burgers To The Stock--------");
        System.out.println("burger stock: " + burger_stock + "\n");

        if (burger_stock == 50) {
            System.out.println("----Burger Stock is Already Full-----");
            return;
        }

        if (burger_stock < 50) {
            while (true) {
                System.out.print("How many burgers do you want to add or 999 to Go Back to Menu: ");
                int new_burgers = input.nextInt();

                if (new_burgers == 999) {
                    break;
                }

                if (new_burgers > 0 && new_burgers <= 50 -burger_stock) {
                    burger_stock += new_burgers;
                    System.out.println("New burger stock: " + burger_stock + "\n");
                    break;
                }
                else {
                    System.out.println("\nYou can only add 1 - " + (50 - burger_stock) + " burgers to the stock.\n");
                }
            }
        }
        else {
            System.out.println("\nWrong Input.");

        }

    }

    /**
     * Calculating the total income of each queue by multiplying the burgers in the respected queues with the burger price.
     * Calculating the total income
     */
    public static void incomeOfEachQueue() {

        int total_income = (cashier1_income)*burger_price + (cashier2_income)*burger_price + (cashier3_income)*burger_price;

        System.out.println("Cashier 1 income: " + (cashier1_income)*burger_price);
        System.out.println("Cashier 2 income: " + (cashier2_income)*burger_price);
        System.out.println("Cashier 3 income: " + (cashier3_income)*burger_price);
        System.out.println("Total income: " + total_income);
    }

    /**
     * Displays the customer names from all cashier queues before sorting.
     * Displays the customer names from all cashier queues in sorted order.
     */
    public static void viewCustomerSorted() {
        int queueLength = cashier1.length + cashier2.length + cashier3.length;
        String[] names = new String[queueLength];

        int index = 0;

        for (Customer customer : cashier1) {
            if (customer != null) {
                names[index] = customer.getFull_name();
                index++;
            }
        }

        for (Customer customer : cashier2) {
            if (customer != null) {
                names[index] = customer.getFull_name();
                index++;
            }
        }

        for (Customer customer : cashier3) {
            if (customer != null) {
                names[index] = customer.getFull_name();
                index++;
            }
        }

        System.out.print("Before sorting the names: "+"\n");
        for (int i = 0; i < index; i++) {
            System.out.print(  names[i] +"\n");
        }
        System.out.println();

        for (int i = 0; i < index - 1; i++) {
            for (int j = 0; j < index - i - 1; j++) {
                if (names[j].compareTo(names[j + 1]) > 0) {
                    String temp = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = temp;
                }
            }
        }

        System.out.print("Sorted names: "+"\n");
        for (int i = 0; i < index; i++) {
            System.out.print(names[i] +"\n");
        }
        System.out.println();
    }

    /**
     * Loads program data from the "Customer_Details.txt" file.
     * Reads the file line by line each line represents the queue1,queue2,queue3 and stock respectively.
     * The customer names are separated by commas within same queue.
     * Split the customer names and burger quantity into an array for each queue
     */
    public static void loadProgramData() {
        try {
            File file = new File("Customer_Details.txt");
            Scanner file_reader = new Scanner(file);

            String line1 = file_reader.nextLine();
            System.out.println(line1);
            String line2 = file_reader.nextLine();
            System.out.println(line2);
            String line3 = file_reader.nextLine();
            System.out.println(line3);
            String burgers = file_reader.nextLine();
            System.out.println(burgers);
            burger_stock = Integer.parseInt(burgers);

            String[] test1 = line1.split(":");
            String[] test2 = line2.split(":");
            String[] test3 = line3.split(":");

            cashier1 = new Customer[2];
            cashier2 = new Customer[3];
            cashier3 = new Customer[5];

            if (test1.length != 1) {
                String[] array1 = test1[1].split(",");
                if (array1.length != 0) {
                    for (int i = 0; i < array1.length; i++) {
                        String[] customer1 = array1[i].split(" ");
                        if (customer1.length != 0) {
                            for (int j = 0; j < cashier1.length; j++) {
                                Customer customer = new Customer(customer1[0], customer1[1], Integer.parseInt(customer1[2]));
                                if (cashier1[j] == null) {
                                    cashier1[j] = customer;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (test2.length != 1) {
                String[] array2 = test2[1].split(",");
                if (array2.length != 0) {
                    for (int i = 0; i < array2.length; i++) {
                        String[] customer1 = array2[i].split(" ");
                        if (customer1.length != 0) {
                            for (int j = 0; j < cashier2.length; j++) {
                                Customer customer = new Customer(customer1[0], customer1[1], Integer.parseInt(customer1[2]));
                                if (cashier2[j] == null) {
                                    cashier2[j] = customer;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (test3.length != 1) {
                String[] array3 = test3[1].split(",");
                if (array3.length != 0) {
                    for (int i = 0; i < array3.length; i++) {
                        String[] customer1 = array3[i].split(" ");
                        if (customer1.length != 0) {
                            for (int j = 0; j < cashier3.length; j++) {
                                Customer customer = new Customer(customer1[0], customer1[1], Integer.parseInt(customer1[2]));
                                if (cashier3[j] == null) {
                                    cashier3[j] = customer;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            file_reader.close();
            System.out.println("Successfully loaded the data");
        } catch (Exception e) {
            System.out.println("Error in loading data!!!");
        }
    }

    /**
     * Create a new FileWriter to write data to "Customer_Details.txt" file.
     * Write queue1,queue2.queue3  and burger quantity  to the file separately.
     * Write queue1,queue2.queue3 data and burger quantity  to the file separately.
     * Close the file and prints a message telling that storing data was successful.
     * @throws IOException if there is an error while writing to the file.
     */
    public static void  storeProgramData() throws IOException {
        FileWriter storeFile = new FileWriter("Customer_Details.txt");
        storeFile.write("cashier 1:");
        for (int i=0;i<cashier1.length;i++){
            if(cashier1[i]!=null){
                storeFile.write(cashier1[i].getFull_name() + " " + cashier1[i].getBurgerQuantity()+" ," ) ;
            }
        }
        storeFile.write("\n");
        storeFile.write("cashier 2:");
        for (int i=0;i<cashier2.length;i++){
            if(cashier2[i]!=null){
                storeFile.write(cashier2[i].getFull_name() + " " + cashier2[i].getBurgerQuantity()+" ," ) ;
            }
        }
        storeFile.write("\n");
        storeFile.write("cashier 3:");
        for (int i=0;i<cashier3.length;i++){
            if(cashier3 [i]!=null){
                storeFile.write(cashier3[i].getFull_name() + " " + cashier3[i].getBurgerQuantity()+" ,") ;
            }
        }
        storeFile.write("\n");
        storeFile.write(Integer.toString(burger_stock));
        storeFile.close();
        System.out.println("Successfully stored the data into a file");
    }
}


