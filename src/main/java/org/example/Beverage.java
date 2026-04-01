package org.example;

public class Beverage extends Product {
    private final boolean carbonated;

    public Beverage(int id, String name, int price, int quantity, boolean carbonated) {
        super(id, name, price, quantity);
        this.carbonated = carbonated;
    }

    public boolean isCarbonated() {
        return carbonated;
    }

    @Override
    public String getDescription() {
        return carbonated ? "Cold carbonated drink" : "Still beverage";
    }
}
