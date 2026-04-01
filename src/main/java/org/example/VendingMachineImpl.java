package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.time.LocalDateTime;

public class VendingMachineImpl implements IVendingMachine {
    private static final Set<Integer> VALID_COINS = Set.of(1, 2, 5, 10, 20, 50);

    private final Map<Integer, Product> products;
    private final List<PurchaseRecord> purchaseHistory;
    private int balance;

    public VendingMachineImpl(List<Product> products) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Products list must not be empty.");
        }

        this.products = new LinkedHashMap<>();
        this.purchaseHistory = new ArrayList<>();
        for (Product product : products) {
            if (product == null) {
                throw new IllegalArgumentException("Products must not contain null values.");
            }
            if (this.products.containsKey(product.getId())) {
                throw new IllegalArgumentException("Duplicate product id: " + product.getId());
            }
            this.products.put(product.getId(), product);
        }
    }

    public static VendingMachineImpl createDefault() {
        List<Product> defaultProducts = List.of(
                new Snack(1, "Chips", 20, 5, true),
                new Beverage(2, "Water", 10, 6, false),
                new Beverage(3, "Cola", 25, 4, true),
                new Fruit(4, "Apple", 12, 7, "Sweden"),
                new Snack(5, "Chocolate Bar", 15, 8, false)
        );
        return new VendingMachineImpl(defaultProducts);
    }

    @Override
    public void insertCoin(int coin) {
        if (VALID_COINS.contains(coin)) {
            balance += coin;
        }
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public Product purchaseProduct(int productId) {
        Product product = products.get(productId);
        if (product == null || !product.isInStock() || balance < product.getPrice()) {
            return null;
        }

        product.dispense();
        balance -= product.getPrice();
        purchaseHistory.add(new PurchaseRecord(
                product.getId(),
                product.getName(),
                product.getPrice(),
                LocalDateTime.now()
        ));
        return product;
    }

    @Override
    public int returnChange() {
        int change = balance;
        balance = 0;
        return change;
    }

    @Override
    public List<Product> getProducts() {
        return Collections.unmodifiableList(new ArrayList<>(products.values()));
    }

    public List<PurchaseRecord> getPurchaseHistory() {
        return Collections.unmodifiableList(new ArrayList<>(purchaseHistory));
    }
}
