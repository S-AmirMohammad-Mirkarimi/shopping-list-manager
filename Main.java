import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;

// ============================================================
//  Simple helper class for one shopping item.
//  It only holds a name and a quantity.
// ============================================================
class ShoppingItem {
    String name;
    int quantity;

    ShoppingItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}

public class Main {
    static final String FILE_NAME = "shopping_list.txt";
    public static void main(String[] args) {

        ArrayList<ShoppingItem> shoppingList = new ArrayList<ShoppingItem>();

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println("=====================================");
            System.out.println("   SHOPPING LIST MANAGER");
            System.out.println("=====================================");
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. View list");
            System.out.println("4. Save list to file");
            System.out.println("5. Load list from file");
            System.out.println("6. Exit");
            System.out.println("=====================================");
            System.out.print("Please choose an option (1-6): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addItem(shoppingList, scanner);
                    break;
                case "2":
                    removeItem(shoppingList, scanner);
                    break;
                case "3":
                    viewList(shoppingList);
                    break;
                case "4":
                    saveList(shoppingList);
                    break;
                case "5":
                    loadList(shoppingList);
                    break;
                case "6":
                    System.out.println("Goodbye! Thank you for using Shopping List Manager.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    // =========================================================
    //  Option 1: Add a new item to the list
    // =========================================================
    public static void addItem(ArrayList<ShoppingItem> list, Scanner scanner) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        System.out.print("Enter quantity: ");

        int quantity = 0;

        try {
            quantity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
            return;
        }

        if (quantity < 0) {
            System.out.println("Please enter a valid number!");
            return;
        }

        ShoppingItem item = new ShoppingItem(name, quantity);
        list.add(item);

        System.out.println("Item added successfully!");
    }

    // =========================================================
    //  Option 2: Remove an item by its number
    // =========================================================
    public static void removeItem(ArrayList<ShoppingItem> list, Scanner scanner) {

        if (list.isEmpty()) {
            System.out.println("The list is empty!");
            return;
        }

        // Show the current list with numbers.
        for (int i = 0; i < list.size(); i++) {
            ShoppingItem item = list.get(i);
            System.out.println((i + 1) + ". " + item.name + " (" + item.quantity + ")");
        }

        System.out.print("Enter the number of the item to remove: ");

        int number = 0;

        try {
            number = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
            return;
        }

        if (number < 1 || number > list.size()) {
            System.out.println("Invalid number! Please try again.");
            return;
        }

        list.remove(number - 1);
        System.out.println("Item removed successfully!");
    }

    // =========================================================
    //  Option 3: View the whole list
    // =========================================================
    public static void viewList(ArrayList<ShoppingItem> list) {

        if (list.isEmpty()) {
            System.out.println("Your shopping list is empty.");
            return;
        }

        System.out.println("Your Shopping List:");
        System.out.println("--------------------");
        for (int i = 0; i < list.size(); i++) {
            ShoppingItem item = list.get(i);
            System.out.println((i + 1) + ". " + item.name + " - Quantity: " + item.quantity);
        }
        System.out.println("--------------------");
    }

    // =========================================================
    //  Option 4: Save the list to a text file (CSV format)
    // =========================================================
    public static void saveList(ArrayList<ShoppingItem> list) {

        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i = 0; i < list.size(); i++) {
                ShoppingItem item = list.get(i);
                printWriter.println(item.name + "," + item.quantity);
            }

            printWriter.close();
            System.out.println("List saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving file!");
        }
    }

    // =========================================================
    //  Option 5: Load the list from a text file
    // =========================================================
    public static void loadList(ArrayList<ShoppingItem> list) {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No saved file found!");
            return;
        }

        try {
            FileReader fileReader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            list.clear();

            while ((line = bufferedReader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length == 2) {
                    String name = parts[0];

                    int quantity = 0;
                    try {
                        quantity = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        continue;
                    }

                    ShoppingItem item = new ShoppingItem(name, quantity);
                    list.add(item);
                }
            }

            bufferedReader.close();
            System.out.println("List loaded successfully!");
        } catch (IOException e) {
            System.out.println("Error reading file!");
        }
    }
}
