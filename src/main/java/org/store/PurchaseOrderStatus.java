package org.store;

public enum PurchaseOrderStatus {
    PENDING("pending"),
    APPROVED("approved"),
    DELIVERED("delivered");

    private String name;

    PurchaseOrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
