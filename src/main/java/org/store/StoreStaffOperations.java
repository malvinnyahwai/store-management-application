package org.store;

import java.util.List;

public class StoreStaffOperations extends Operations {

    public StoreStaffOperations(User user) {
        super(user);
    }

    public Item updateItemQuantity(String storeName, String itemName) {
        checkRole(this, Role.STORE_STAFF);
        Store store = storeList.stream()
                .filter(s -> s.getName().equals(storeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Store not found..."));

        Item item = store.getInventory().stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found..."));

        Long quantity = item.getQuantity();
        item.setQuantity(quantity - 1);
        return item;
    }

    public Integer checkInventoryLevel(String storeName) {
        checkRole(this, Role.STORE_STAFF);
        Store store = storeList.stream()
                .filter(s -> s.getName().equals(storeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Store not found..."));
        return store.getInventory().size();
    }

    public List<Item> requestItemAddition(String storeName, String itemName, String description, Category category,
                                          Double price, Long initialQuality, String supplierName) {
        checkRole(this, Role.STORE_STAFF);
        Store store = storeList.stream().filter(s -> s.getName().equals(storeName)).findFirst().orElseThrow(() -> new RuntimeException("Store not found..."));
        StoreManagerOperations storeManagerOperations = new StoreManagerOperations(store.getStoreManager());
        return storeManagerOperations.requestItemAddition(storeName, itemName, description, category, price, initialQuality, supplierName);
    }
}
