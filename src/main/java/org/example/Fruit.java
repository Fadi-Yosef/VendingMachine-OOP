package org.example;

public class Fruit extends Product {
    private final String origin;

    public Fruit(int id, String name, int price, int quantity, String origin) {
        super(id, name, price, quantity);
        if (origin == null || origin.isBlank()) {
            throw new IllegalArgumentException("Origin must not be blank.");
        }
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public String getDescription() {
        return "Fresh fruit from " + origin;
    }
}
