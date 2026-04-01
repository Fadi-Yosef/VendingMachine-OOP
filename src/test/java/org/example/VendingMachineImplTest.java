package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class VendingMachineImplTest {
    private VendingMachineImpl vendingMachine;
    private Product chips;
    private Product water;
    private Product apple;

    @BeforeEach
    void setUp() {
        chips = new Snack(1, "Chips", 20, 3, true);
        water = new Beverage(2, "Water", 10, 2, false);
        apple = new Fruit(3, "Apple", 12, 1, "Sweden");
        vendingMachine = new VendingMachineImpl(List.of(chips, water, apple));
    }

    @Test
    void insertValidCoinIncreasesBalance() {
        vendingMachine.insertCoin(10);

        assertEquals(10, vendingMachine.getBalance());
    }

    @Test
    void invalidCoinIsRejected() {
        vendingMachine.insertCoin(7);

        assertEquals(0, vendingMachine.getBalance());
    }

    @Test
    void purchaseProductSuccessfullyReducesBalanceAndStock() {
        vendingMachine.insertCoin(20);

        Product purchased = vendingMachine.purchaseProduct(1);

        assertNotNull(purchased);
        assertEquals("Chips", purchased.getName());
        assertEquals(0, vendingMachine.getBalance());
        assertEquals(2, chips.getQuantity());
    }

    @Test
    void purchaseFailsWhenBalanceIsInsufficient() {
        vendingMachine.insertCoin(10);

        Product purchased = vendingMachine.purchaseProduct(1);

        assertNull(purchased);
        assertEquals(10, vendingMachine.getBalance());
        assertEquals(3, chips.getQuantity());
    }

    @Test
    void purchaseFailsWhenProductIsOutOfStock() {
        Product soldOutDrink = new Beverage(4, "Juice", 20, 0, false);
        vendingMachine = new VendingMachineImpl(List.of(chips, water, soldOutDrink));
        vendingMachine.insertCoin(20);

        Product purchased = vendingMachine.purchaseProduct(4);

        assertNull(purchased);
        assertEquals(20, vendingMachine.getBalance());
        assertEquals(0, soldOutDrink.getQuantity());
    }

    @Test
    void returnChangeResetsBalance() {
        vendingMachine.insertCoin(50);

        int returned = vendingMachine.returnChange();

        assertEquals(50, returned);
        assertEquals(0, vendingMachine.getBalance());
    }

    @Test
    void getProductsReturnsAllItems() {
        List<Product> products = vendingMachine.getProducts();

        assertEquals(3, products.size());
    }

    @Test
    void successfulPurchaseCanLeaveRemainingBalance() {
        vendingMachine.insertCoin(20);

        Product purchased = vendingMachine.purchaseProduct(3);

        assertNotNull(purchased);
        assertEquals(8, vendingMachine.getBalance());
        assertEquals(0, apple.getQuantity());
    }

    @Test
    void purchaseFailsForUnknownProductId() {
        vendingMachine.insertCoin(20);

        Product purchased = vendingMachine.purchaseProduct(99);

        assertNull(purchased);
        assertEquals(20, vendingMachine.getBalance());
    }

    @Test
    void successfulPurchaseIsStoredInPurchaseHistory() {
        vendingMachine.insertCoin(20);

        vendingMachine.purchaseProduct(1);

        List<PurchaseRecord> history = vendingMachine.getPurchaseHistory();
        assertEquals(1, history.size());
        assertEquals(1, history.get(0).getProductId());
        assertEquals("Chips", history.get(0).getProductName());
        assertEquals(20, history.get(0).getPricePaid());
    }

    @Test
    void failedPurchaseDoesNotCreatePurchaseHistoryRecord() {
        vendingMachine.insertCoin(10);

        vendingMachine.purchaseProduct(1);

        assertFalse(vendingMachine.getPurchaseHistory().iterator().hasNext());
    }
}
