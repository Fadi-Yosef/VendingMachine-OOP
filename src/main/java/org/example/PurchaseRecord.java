package org.example;

import java.time.LocalDateTime;

public final class PurchaseRecord {
    private final int productId;
    private final String productName;
    private final int pricePaid;
    private final LocalDateTime purchasedAt;

    public PurchaseRecord(int productId, String productName, int pricePaid, LocalDateTime purchasedAt) {
        this.productId = productId;
        this.productName = productName;
        this.pricePaid = pricePaid;
        this.purchasedAt = purchasedAt;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getPricePaid() {
        return pricePaid;
    }

    public LocalDateTime getPurchasedAt() {
        return purchasedAt;
    }

    @Override
    public String toString() {
        return String.format(
                "%s | Product #%d %s | Paid: %d SEK",
                purchasedAt,
                productId,
                productName,
                pricePaid
        );
    }
}
