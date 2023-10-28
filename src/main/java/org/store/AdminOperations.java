package org.store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminOperations extends Operations {
    private final List<Category> categoryList = new ArrayList<>();
    private final List<PurchaseOrder> purchaseOrderList = new ArrayList<>();

    public AdminOperations(User user) {
        super(user);
    }

    public User searchStoreManagerByStoreName(String storeName) {
        Store store = searchStoreByName(storeName);
        return store.getStoreManager();
    }

    public void viewPurchaseOrder(String purchaseOrderNumber) {
        checkRole(this, Role.ADMIN);
        PurchaseOrder purchaseOrder = purchaseOrderList.stream()
                .filter(pO -> pO.getNumber().equals(Integer.valueOf(purchaseOrderNumber)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Purchase order not found..."));
        System.out.println("Purchase order found: "+purchaseOrder.toString());
    }

    public List<Store> createStore(User storeManager, String name, Location location, ContactInformation contactInformation,
                                   String storeType) {
        checkRole(this, Role.ADMIN);
        if(storeManager.getRole().equals(Role.STORE_MANAGER)) {
            Store store = new Store(name, location, contactInformation, storeManager, storeType, LocalDate.now());
            storeList.add(store);
            return storeList;
        } else {
            throw new RuntimeException("User not store manager...");
        }
    }

    public Store searchStoreByName(String storeName) {
        checkRole(this, Role.ADMIN);
        return storeList.stream().filter(s -> s.getName().equals(storeName)).findFirst().orElseThrow(() -> new RuntimeException("Store not found..."));
    }

    public List<Store> updateStore(Store store) {
        checkRole(this, Role.ADMIN);
        if(store.getStoreManager().getRole().equals(Role.STORE_MANAGER)) {
            Store storeFromTheList = storeList.stream()
                    .filter(s -> s.getName().equals(store.getName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Store not found..."));
            storeList.remove(storeFromTheList);
            storeList.add(store);
            return storeList;
        } else {
            throw new RuntimeException("User not store manager...");
        }
    }

    public void deleteStore(Store store) {
        checkRole(this, Role.ADMIN);
        Store storeFromTheList = storeList.stream()
                .filter(s -> s.getName().equals(store.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Store not found..."));
        storeList.remove(storeFromTheList);
    }

    public void createUser(String storeName, String userName, Role role) {
        checkRole(this, Role.ADMIN);
        User user = new User(userName, role);
        Store store = storeList.stream()
                .filter(s -> s.getName().equals(storeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Store not found..."));
        store.addStaff(user);
    }

    public void updateRole(String storeName, String userName, Role role) {
        checkRole(this, Role.ADMIN);
        Store store = storeList.stream()
                .filter(s -> s.getName().equals(storeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Store not found..."));
        User user = store.getStaff().stream()
                .filter(s -> s.getName().equals(userName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found..."));
        user.setName(userName);
        user.setRole(role);
    }

    public Category createCategory(String name) {
        checkRole(this, Role.ADMIN);
        Category category = new Category(name);
        categoryList.add(category);
        return category;
    }

    public void generatePurchaseOrder(Store store, Item item, Long quantity) {
        checkRole(this, Role.ADMIN);
        PurchaseOrder purchaseOrder = new PurchaseOrder(store, item, quantity);
        purchaseOrderList.add(purchaseOrder);
    }

    public Item searchItemByName(String storeName, String itemName) {
        Store store = storeList.stream()
                .filter(s -> s.getName().equals(storeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Store not found..."));
        return store.getInventory().stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found..."));
    }

    public Supplier createSupplier(String supplierName) {
        Supplier supplier = new Supplier(supplierName);
        supplierList.add(supplier);
        return supplier;
    }

    public void creatItem(String storeName, String itemName, String description, Category category, Double price,
                          Long initialQuality, Long threshold, Supplier supplier) {
        Store store = searchStoreByName(storeName);
        Item item = new Item(itemName, description, category, price, initialQuality, threshold, supplier);
        store.addInventory(item);
    }
}
