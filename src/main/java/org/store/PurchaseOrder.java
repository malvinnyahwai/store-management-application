package org.store;

import java.util.Random;

public class PurchaseOrder {
    private Integer number;
    private Store store;
    private Item item;
    private Long quantity;
    private PurchaseOrderStatus purchaseOrderStatus;

    public PurchaseOrder(Store store, Item item, Long quantity) {
        number = generateOrderNumber();
        this.store = store;
        this.item = item;
        this.quantity = quantity;
        this.purchaseOrderStatus = PurchaseOrderStatus.APPROVED;
    }

    private Integer generateOrderNumber() {
        Random random = new Random();
        int min = 10000000;
        int max = 99999999;
        return random.nextInt(max - min + 1) + min;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public PurchaseOrderStatus getPurchaseOrderStatus() {
        return purchaseOrderStatus;
    }

    public void setPurchaseOrderStatus(PurchaseOrderStatus purchaseOrderStatus) {
        this.purchaseOrderStatus = purchaseOrderStatus;
    }
}
