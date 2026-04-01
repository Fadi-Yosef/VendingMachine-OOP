package org.example;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final IVendingMachine vendingMachine;
    private final VendingMachineImpl vendingMachineImpl;
    private final Scanner scanner;

    public ConsoleUI(IVendingMachine vendingMachine) {
        this(vendingMachine, new Scanner(System.in));
    }

    ConsoleUI(IVendingMachine vendingMachine, Scanner scanner) {
        this.vendingMachine = vendingMachine;
        this.vendingMachineImpl = vendingMachine instanceof VendingMachineImpl impl ? impl : null;
        this.scanner = scanner;
    }

    public void run() {
        boolean running = true;
        System.out.println("Welcome to the vending machine.");

        while (running) {
            printMenu();
            int choice = readInt();

            switch (choice) {
                case 1 -> showProducts();
                case 2 -> insertCoin();
                case 3 -> purchaseProduct();
                case 4 -> returnChange();
                case 5 -> showPurchaseHistory();
                case 6 -> {
                    returnChange();
                    running = false;
                    System.out.println("Goodbye.");
                }
                default -> System.out.println("Invalid menu option.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Current balance: " + vendingMachine.getBalance() + " SEK");
        System.out.println("1. View products");
        System.out.println("2. Insert coin");
        System.out.println("3. Purchase product");
        System.out.println("4. Return change");
        System.out.println("5. View purchase history");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private void showProducts() {
        List<Product> products = vendingMachine.getProducts();
        System.out.println();
        System.out.println("Available products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private void insertCoin() {
        System.out.print("Insert coin (1, 2, 5, 10, 20, 50): ");
        int coin = readInt();
        int previousBalance = vendingMachine.getBalance();
        vendingMachine.insertCoin(coin);

        if (vendingMachine.getBalance() == previousBalance) {
            System.out.println("Rejected coin: " + coin);
        } else {
            System.out.println("Accepted. New balance: " + vendingMachine.getBalance() + " SEK");
        }
    }

    private void purchaseProduct() {
        System.out.print("Enter product id: ");
        int productId = readInt();
        Product purchasedProduct = vendingMachine.purchaseProduct(productId);

        if (purchasedProduct == null) {
            System.out.println("Purchase failed. Check product id, stock, or balance.");
        } else {
            System.out.println("Dispensed: " + purchasedProduct.getName());
            System.out.println("Remaining balance: " + vendingMachine.getBalance() + " SEK");
        }
    }

    private void returnChange() {
        int change = vendingMachine.returnChange();
        System.out.println("Returned change: " + change + " SEK");
    }

    private void showPurchaseHistory() {
        if (vendingMachineImpl == null) {
            System.out.println("Purchase history is unavailable for this vending machine implementation.");
            return;
        }

        List<PurchaseRecord> history = vendingMachineImpl.getPurchaseHistory();
        if (history.isEmpty()) {
            System.out.println("No purchases have been made yet.");
            return;
        }

        System.out.println();
        System.out.println("Purchase history:");
        for (PurchaseRecord record : history) {
            System.out.println(record);
        }
    }

    private int readInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException ex) {
                scanner.nextLine();
                System.out.print("Enter a valid integer: ");
            }
        }
    }
}
