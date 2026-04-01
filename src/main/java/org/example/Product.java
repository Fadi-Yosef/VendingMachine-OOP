package org.example;

public abstract class Product {
    private final int id;
    private final String name;
    private final int price;
    private int quantity;

    protected Product(int id, String name, int price, int quantity) {
        if (id <= 0) {
            throw new IllegalArgumentException("Product id must be positive.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name must not be blank.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Product price must be positive.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must not be negative.");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isInStock() {
        return quantity > 0;
    }

    public void dispense() {
        if (!isInStock()) {
            throw new IllegalStateException("Product is out of stock.");
        }
        quantity--;
    }

    public abstract String getDescription();

    @Override
    public String toString() {
        return String.format(
                "#%d %s - %d SEK | Stock: %d | %s",
                id,
                name,
                price,
                quantity,
                getDescription()
        );
    }
}
