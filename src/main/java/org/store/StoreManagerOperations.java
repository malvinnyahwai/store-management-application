package org.store;

import java.util.ArrayList;
import java.util.List;

public class StoreManagerOperations extends Operations {

    private final List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
    private final List<Store> storeList = new ArrayList<>();

    public StoreManagerOperations(User user) {
        super(user);
    }

    public List<Item> addItemToStoreInventory(String storeName, String itemName, String description, Category category,
                                              Double price, Long initialQuality, Long itemThreshold, String supplierName) {
        checkRole(this, Role.STORE_MANAGER);
        Supplier supplier = new Supplier(supplierName);
        Item item = new Item(itemName, description, category, price, initialQuality, itemThreshold, supplier);
        Store store = storeList.stream()
                .filter(s -> s.getName().equals(storeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Store not found..."));
        store.getInventory().add(item);
        return store.getInventory();
    }

    public Integer checkInventoryLevel(Store store) {
        checkRole(this, Role.STORE_MANAGER);
        return store.getInventory().size();
    }

    public List<PurchaseOrder> generatePurchaseOrder(Store store, Item item, Long quantity) {
        checkRole(this, Role.STORE_MANAGER);
        PurchaseOrder purchaseOrder = new PurchaseOrder(store, item, quantity);
        purchaseOrderList.add(purchaseOrder);
        return purchaseOrderList;
    }

    public List<Item> requestItemAddition(String storeName, String itemName, String description, Category category,
                                          Double price, Long initialQuality, String supplierName) {
        checkRole(this, Role.STORE_MANAGER);
        Long DEFAULT_THRESHOLD = 500L;
        return addItemToStoreInventory(storeName, itemName, description, category, price, initialQuality, DEFAULT_THRESHOLD,
                supplierName);
    }

}
