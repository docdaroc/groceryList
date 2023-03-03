package listings;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static ArrayList<String> groceryList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean quit = false;
        while (!quit) {
            System.out.print("\tAvailable actions:\n\t" +
                    "0 - to shut down\n\t" +
                    "1 - to add item(s) to list (comma delimited list)\n\t" +
                    "2 - to remove any items (comma delimited list)\n\t" +
                    "3 - print grocery list\n\t" +
                    "Enter a number for which action you want to do [0-3]: ");

            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 0 -> quit = true;
                case 1 -> addItem();
                case 2 -> removeItem();
                case 3 -> printList();
            }
        }
    }

    public static void printList() {
        System.out.print("\t******************************");
        if (!groceryList.isEmpty()) {
            System.out.println("\n\tGrocery list contains:");
            for (String string : groceryList) {
                System.out.println("\t\t- " + string);
            }
            System.out.println("\t******************************");
        }else {
            System.out.print("\tGrocery list is empty.\n\t******************************");
        }
    }


    public static boolean duplicationCheck(String item) {
        if (groceryList.contains(item)) {
            System.out.print("\tItem " + item + " already in the list.\n\tWould you lieke to add it anyway [Y/N]? ");//1
            switch (scanner.nextLine().toLowerCase()) {
                case "y" -> {
                    return false;
                }
                case "n" -> {
                    return true;
                }
                default -> {
                    System.out.println("\tPlease enter proper input [Y/N]\n\t" +
                            "********************************");
                    duplicationCheck(item);
                }
            }
        }
        return false;
    }

    public static void addItem() {
        System.out.print("\t**************************\n\tAdd item(s): ");
        ArrayList<String> currentList = new ArrayList<>();
        boolean singleItem = false;
        String[] array = scanner.nextLine().split(",");

        for (int i = 0; i < array.length; i++) {
            if (!duplicationCheck(array[i])) {
                currentList.add(array[i]);
            }
        }
        if (currentList.size() > 1) {
            System.out.println("\tItems:");
            for (String item : currentList) {
                System.out.println("\t\t- " + item);
                groceryList.add(item);
            }
            System.out.print("\thave ");

        } else {
            singleItem = true;
            groceryList.add(currentList.get(0));
            System.out.print("\tItem - " + currentList.get(0) + " has ");
        }
        if (!currentList.isEmpty() || !singleItem) {
            System.out.println("been added to the list.");
            System.out.println("\t**************************");
        }
        currentList.clear();
    }

    public static void removeItem() {
        System.out.print("\t*****************************\n\tEnter item to be removed: ");
        String item = scanner.nextLine();
        if (groceryList.contains(item)) {
            System.out.print("\tWould you like to remove the occurrence of "
                    + item + "\n\tat all from the list? [Y/N] ");
            String input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "y" -> {
                    groceryList.removeAll(List.of(item));
                    System.out.println("\t" + item.toUpperCase().charAt(0) +
                            item.substring(1, item.length()) + " has been removed at all from the list"
                    +"\n\t********************************");
                }
                case "n" -> {
                    groceryList.remove(item);
                    System.out.println("\tItem " + item.toUpperCase().charAt(0) +
                            item.substring(1, item.length()) + " has been removed");
                    System.out.println("********************************\"");
                }
                default -> {
                    System.out.println("\tPlease enter proper input [Y/N]\n\t" +
                            "********************************");
                    duplicationCheck(item);
                }
            }
        } else {
            System.out.println("\tItem " + item + " not on the list.");
        }
    }
}

