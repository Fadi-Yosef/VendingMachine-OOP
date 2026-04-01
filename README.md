# Vending Machine OOP

A console-based vending machine application built with Java and Maven. The project follows the workshop UML at a conceptual level and demonstrates core OOP principles through a simple product catalog, purchase flow, and stock management.

## Objectives covered

- Inheritance through the abstract `Product` base class and concrete `Snack`, `Beverage`, and `Fruit` types
- Polymorphism through shared handling of different product types in `VendingMachineImpl`
- Abstraction through `IVendingMachine` and the abstract product model
- Encapsulation through private fields and controlled behavior such as `dispense()`
- Console UI interaction through `ConsoleUI`
- JUnit testing for the core vending machine business logic
- Runnable JAR packaging with Maven

## Project structure

- `src/main/java/org/example/IVendingMachine.java` defines the vending machine contract
- `src/main/java/org/example/Product.java` defines shared product state and behavior
- `src/main/java/org/example/Snack.java`, `Beverage.java`, `Fruit.java` are concrete product types
- `src/main/java/org/example/VendingMachineImpl.java` contains the main business logic
- `src/main/java/org/example/ConsoleUI.java` provides the menu-driven console interface
- `src/test/java/org/example/VendingMachineImplTest.java` covers the required business rules

## Payment rules

The vending machine accepts only these coin values:

- `1`
- `2`
- `5`
- `10`
- `20`
- `50`

All other coin values are rejected and do not change the current balance.

## Build and run

Requirements:

- Java 17+
- Maven 3.9+

Run the test suite:

```bash
mvn test
```

Build the runnable JAR:

```bash
mvn clean package
```

Run the application:

```bash
java -jar target/VendingMachine-OOP-1.0-SNAPSHOT.jar
```

## Example menu actions

The console UI allows the user to:

- View available products
- Insert valid coins
- Buy a product by ID
- Return remaining balance
- Exit the program

## Optional extension

I extended the design with a new class: `PurchaseRecord`.

What I added:

- `PurchaseRecord` stores the product id, product name, paid price, and purchase timestamp
- `VendingMachineImpl` now keeps a purchase history list
- `ConsoleUI` includes a menu option to display completed purchases
- Additional tests verify that successful purchases are recorded and failed purchases are not

Why I added it:

- A real vending machine often needs a basic transaction log
- It introduces an extra class and relationship in a clean OOP way without changing the core UML contract
- It makes the system easier to extend later for reporting, auditing, or daily sales summaries

How it improves the system:

- The machine now preserves useful business information instead of only returning a product
- The user can review what has been bought during the session
- The design becomes more realistic and demonstrates stronger object-oriented modeling

I also kept the product-specific descriptive fields:

- `Snack` includes whether it is crunchy
- `Beverage` includes whether it is carbonated
- `Fruit` includes its origin

## UML mapping summary

The implementation follows the workshop diagram:

- `IVendingMachine` is implemented by `VendingMachineImpl`
- `Product` is abstract
- `Snack`, `Beverage`, and `Fruit` inherit from `Product`
- `ConsoleUI` depends on the `IVendingMachine` abstraction rather than the concrete implementation

## Author

Fadi Yosef
