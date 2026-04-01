package org.example;

public class Snack extends Product {
    private final boolean crunchy;

    public Snack(int id, String name, int price, int quantity, boolean crunchy) {
        super(id, name, price, quantity);
        this.crunchy = crunchy;
    }

    public boolean isCrunchy() {
        return crunchy;
    }

    @Override
    public String getDescription() {
        return crunchy ? "Crunchy snack" : "Soft snack";
    }
}
