package org.store;

import java.util.List;

public class UserOperations extends Operations{

    public UserOperations(User user) {
        super(user);
    }

    public static void performStoreSearch(String location, String storeType, String openingDate) {
        UserOperations userOperations = new UserOperations(new User("Default User", Role.DEFAULT_USER));
        List<Store> storeList = userOperations.storeList.stream()
                .filter(s -> s.getLocation().getName().equals(location)
                            && s.getStoreType().equals(storeType)
                            && s.getOpeningDate().toString().equals(openingDate))
                .toList();

        if(storeList.isEmpty()) {
            System.out.println("No store found...");
        }

        storeList.forEach(
                System.out::println
        );
    }

    public static void performItemSearch(String storeName, String itemName, String categoryName, double from, double to) {
        UserOperations userOperations = new UserOperations(new User("Default User", Role.DEFAULT_USER));
        Store storeList = userOperations.storeList.stream()
                .filter(store -> store.getName().equals(storeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Store not found..."));
        Item item = storeList.getInventory().stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found..."));
        System.out.println("Item found :"+item.toString());
    }
}
