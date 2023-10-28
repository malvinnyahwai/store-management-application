package org.store;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(" Please select operation to perform: ");
        System.out.println(" 1. search");
        System.out.println(" 2. other operations");
        System.out.print(" (1/2)?: ");
        int input = scanner.nextInt();
        if (input == 1) {
            performSearchOperation();
        } else {
            performOtherOperations();
        }
    }

    public static void performOtherOperations() {
        System.out.println(" Please select role: ");
        System.out.println(" 1. ADMIN");
        System.out.println(" 2. STORE MANAGER");
        System.out.println(" 3. STORE STAFF");
        System.out.print(" (1/2/3)?: ");

        int input = scanner.nextInt();

        if (input == 1) {
            performAdminOperations();
        } else if (input == 2) {
            performStoreManagerOperations();
        } else if (input == 3) {
            performStoreStaffOperations();
        } else
            System.out.println("Invalid input...");

    }

    /*

        Store Manager


        StoreStaff


        EveryOne
        14.storeSearch
        15.itemSearch

         */


    public static void performAdminOperations() {
        System.out.print(" Enter name (firstname lastname):");
        String input = scanner.nextLine();
        User admin = new User(input, Role.ADMIN);
        AdminOperations adminOperations = new AdminOperations(admin);

        System.out.println(" Please select operation to perform: ");
        System.out.println(" 1. Create store ");
        System.out.println(" 2. Update store ");
        System.out.println(" 3. Delete store ");
        System.out.println(" 4. Create user ");
        System.out.println(" 5. Update user roles ");
        System.out.println(" 6. Create category ");
        System.out.println(" 7. Generate purchase order ");
        System.out.println(" 8. Create item ");
        System.out.println(" 9. View purchase order ");
        System.out.print(" Input: ");

        int operationInput = scanner.nextInt();

        switch (operationInput) {
            case 1 -> {
                System.out.println(" Please enter comma separated values Store manager name, store name, store location, store phone number (xxx xxxx xxx), store type");
                String storeCreateInput = scanner.nextLine();
                String[] storeCreateInputArray = storeCreateInput.split(",");
                if (storeCreateInputArray.length == 5) {
                    User storeManager = new User(storeCreateInputArray[0], Role.STORE_MANAGER);
                    adminOperations.createStore(storeManager, storeCreateInputArray[1], new Location(storeCreateInputArray[2]),
                            new ContactInformation(storeCreateInputArray[3]), storeCreateInputArray[4]);
                }
            }
            case 2 -> {
                System.out.println(" Please enter store name to be updated");
                String storeNameInput = scanner.nextLine();
                Store store = adminOperations.searchStoreByName(storeNameInput);
                System.out.println(" Please enter comma separated values new store manager name, new store name, new store location, new store phone number (xxx xxxx xxx)");
                storeNameInput = scanner.nextLine();
                String[] storeUpdateInputArray = storeNameInput.split(",");
                if (storeUpdateInputArray.length == 4) {
                    User storeManager = new User(storeUpdateInputArray[0], Role.STORE_MANAGER);
                    store.setStoreManager(storeManager);
                    store.setName(storeUpdateInputArray[1]);
                    Location location = new Location(storeUpdateInputArray[2]);
                    store.setLocation(location);
                    ContactInformation contactInformation = new ContactInformation(storeUpdateInputArray[3]);
                    store.setContactInformation(contactInformation);
                    adminOperations.updateStore(store);
                } else
                    throw new RuntimeException("Invalid input...");
            }
            case 3 -> {
                System.out.println(" Please enter store name to be deleted");
                String storeNameToBeDeleted = scanner.nextLine();
                Store storeToBeDeleted = adminOperations.searchStoreByName(storeNameToBeDeleted);
                adminOperations.deleteStore(storeToBeDeleted);
            }
            case 4 -> {
                System.out.println(" Please enter comma separated values Store name, User name, role (available roles: ADMIN, STORE_MANAGER OR STORE_STAFF)");
                String userCreationInput = scanner.nextLine();
                String[] userCreationInputArray = userCreationInput.split(",");
                if (userCreationInputArray.length == 3) {
                    Role role;
                    if (userCreationInputArray[2].equalsIgnoreCase("ADMIN")) {
                        role = Role.ADMIN;
                    } else if (userCreationInputArray[2].equalsIgnoreCase("STORE_MANAGER")) {
                        role = Role.STORE_MANAGER;
                    } else
                        role = Role.STORE_STAFF;
                    adminOperations.createUser(userCreationInputArray[0], userCreationInputArray[1], role);
                } else
                    throw new RuntimeException("Invalid input...");
            }
            case 5 -> {
                System.out.println(" Please enter comma separated values Store name, user name, new role (available roles: ADMIN, STORE_MANAGER OR STORE_STAFF)");
                String userUpdateInput = scanner.nextLine();
                String[] userUpdateInputArray = userUpdateInput.split(",");
                if (userUpdateInputArray.length == 3) {
                    Role role;
                    if (userUpdateInputArray[2].equalsIgnoreCase("ADMIN")) {
                        role = Role.ADMIN;
                    } else if (userUpdateInputArray[2].equalsIgnoreCase("STORE_MANAGER")) {
                        role = Role.STORE_MANAGER;
                    } else
                        role = Role.STORE_STAFF;
                    adminOperations.updateRole(userUpdateInputArray[0], userUpdateInputArray[1], role);
                } else
                    throw new RuntimeException("Invalid input...");
            }
            case 6 -> {
                System.out.println(" Please enter category name");
                String categoryInput = scanner.nextLine();
                adminOperations.createCategory(categoryInput);
            }
            case 7 -> {
                System.out.println(" Please enter comma separated values Store name, item name, quantity");
                String purchaseOrderGenerationInput = scanner.nextLine();
                String[] purchaseOrderGenerationInputArray = purchaseOrderGenerationInput.split(",");
                if (purchaseOrderGenerationInputArray.length == 3) {
                    Store storeSearch = adminOperations.searchStoreByName(purchaseOrderGenerationInputArray[0]);
                    Item itemSearch = adminOperations.searchItemByName(purchaseOrderGenerationInputArray[0], purchaseOrderGenerationInputArray[1]);
                    adminOperations.generatePurchaseOrder(storeSearch, itemSearch, Long.getLong(purchaseOrderGenerationInputArray[2]));
                } else
                    throw new RuntimeException("Invalid input...");
            }
            case 8 -> {
                System.out.println(" Please enter comma separated values Store name, item name, item description, " +
                        "item category, item price, item initial quantity, item threshold, item supplier name");
                String createItemInput = scanner.nextLine();
                String[] createItemInputArray = createItemInput.split(",");
                if (createItemInputArray.length == 8) {
                    Category category = adminOperations.createCategory(createItemInputArray[3]);
                    Supplier supplier = adminOperations.createSupplier(createItemInputArray[7]);
                    adminOperations.creatItem(createItemInputArray[0], createItemInputArray[1], createItemInputArray[2],
                            category, Double.valueOf(createItemInputArray[4]), Long.valueOf(createItemInputArray[5]),
                            Long.valueOf(createItemInputArray[6]), supplier);
                } else
                    throw new RuntimeException("Invalid input...");
            }
            case 9 -> {
                System.out.println(" Please enter purchase order number");
                String purchaseOrderNumberInput = scanner.nextLine();
                adminOperations.viewPurchaseOrder(purchaseOrderNumberInput);
            }
        }
    }

    public static void performStoreManagerOperations() {
        System.out.print(" Enter store name):");
        String storeName = scanner.nextLine();
        User systemUser = new User("SYSTEM_GENERATED_USER", Role.ADMIN);
        AdminOperations adminOperations = new AdminOperations(systemUser);

        User storeManager = adminOperations.searchStoreManagerByStoreName(storeName);
        Store store = adminOperations.searchStoreByName(storeName);
        StoreManagerOperations storeManagerOperations = new StoreManagerOperations(storeManager);

        System.out.println(" Please select operation to perform: ");
        System.out.println(" 1. Add items to inventory ");
        System.out.println(" 2. Get inventory level ");
        System.out.println(" 3. Generate purchase order ");
        System.out.println(" 4. View purchase order ");
        System.out.print(" Input: ");

        int operationInput = scanner.nextInt();

        switch (operationInput) {
            case 1 -> {
                System.out.println("Please enter comma separated values item name, description, category name, " +
                        "item price, initial quality, item threshold, supplier name");
                String itemCreation = scanner.nextLine();
                String[] itemCreationArray = itemCreation.split(",");
                if(itemCreationArray.length == 7) {
                    storeManagerOperations.addItemToStoreInventory(storeName, itemCreationArray[0], itemCreationArray[1],
                            new Category(itemCreationArray[2]), Double.valueOf(itemCreationArray[3]),
                            Long.valueOf(itemCreationArray[4]), Long.valueOf(itemCreationArray[5]),
                            itemCreationArray[6]);
                } else
                    throw new RuntimeException("Invalid input");
            }
            case 2 -> {
                storeManagerOperations.checkInventoryLevel(store);
            }
            case 3 -> {
                System.out.println("Please enter comma separated values item name, quantity");
                String purchaseOrderCreation = scanner.nextLine();
                String[] purchaseOrderCreationArray = purchaseOrderCreation.split(",");
                if(purchaseOrderCreationArray.length == 2) {
                    Item item = adminOperations.searchItemByName(store.getName(), purchaseOrderCreationArray[0]);
                    storeManagerOperations.generatePurchaseOrder(store, item, Long.valueOf(purchaseOrderCreationArray[1]));
                } else
                    throw new RuntimeException("Invalid input...");
            }
            case 4 -> {
                System.out.println(" Please enter purchase order number");
                String purchaseOrderNumberInput = scanner.nextLine();
                adminOperations.viewPurchaseOrder(purchaseOrderNumberInput);
            }
        }
    }

    public static void performStoreStaffOperations() {
        System.out.print(" Enter comma separated store name, user name):");
        User systemUser = new User("SYSTEM_GENERATED_USER", Role.ADMIN);
        AdminOperations adminOperations = new AdminOperations(systemUser);

        String inputString = scanner.nextLine();
        String[] inputStringArray = inputString.split(",");

        if(inputStringArray.length == 2) {
            User storeStaff = adminOperations.searchStoreManagerByStoreName(inputStringArray[0]);
            Store store = adminOperations.searchStoreByName(inputStringArray[1]);
            StoreStaffOperations storeStaffOperations = new StoreStaffOperations(storeStaff);

            System.out.println(" Please select operation to perform: ");
            System.out.println(" 1. Get inventory level ");
            System.out.println(" 2. Request item addition ");
            System.out.println(" 3. Update item quantities ");
            System.out.print(" Input: ");


            int operationInput = scanner.nextInt();

            switch (operationInput) {
                case 1 -> {
                    storeStaffOperations.checkInventoryLevel(store.getName());
                }
                case 2 -> {
                    System.out.println(" Please enter comma separated values item name, item description, " +
                            "item category, item price, item initial quantity, item threshold, item supplier name");
                    String createItemInput = scanner.nextLine();
                    String[] createItemInputArray = createItemInput.split(",");
                    if(createItemInputArray.length == 7) {
                        Category category = new Category(createItemInputArray[2]);
                        storeStaffOperations.requestItemAddition(store.getName(), createItemInputArray[0],
                                createItemInputArray[1], category, Double.valueOf(createItemInputArray[3]),
                                Long.valueOf(createItemInputArray[4]), createItemInputArray[5]);
                    } else
                        throw new RuntimeException("Invalid input...");
                }
                case 3 -> {
                    System.out.println(" Please enter comma separated values item name, item description, " +
                            "item category, item price, item initial quantity, item threshold, item supplier name");
                    String createItemInput = scanner.nextLine();
                    String[] createItemInputArray = createItemInput.split(",");
                    if(createItemInputArray.length == 7) {
                        storeStaffOperations.updateItemQuantity(store.getName(), createItemInputArray[0]);
                    } else
                        throw new RuntimeException("Invalid input...");
                }
            }
        }
    }

    public static void performSearchOperation() {
        System.out.println("1. search for store");
        System.out.println("2. search for item");
        System.out.print(" (1/2)?: ");
        int input = scanner.nextInt();
        if(input == 1) {
            System.out.println("Please enter comma seperated values: Location, Store type, Opening date (dd/MM/yy)");
            String storeSearchValues = scanner.nextLine();
            String[] storeSearchValuesArray = storeSearchValues.split(",");
            if(storeSearchValuesArray.length == 3) {
                performStoreSearch(storeSearchValuesArray[0], storeSearchValuesArray[1], storeSearchValuesArray[2]);
            } else {
                throw new RuntimeException("Invalid input!");
            }
        } else {
            System.out.println("Please enter comma seperated values: Store name, Item name, Category name, Price range (x - y)");
            String itemSearchValues = scanner.nextLine();
            String[] itemSearchValuesArray = itemSearchValues.split(",");
            if(itemSearchValuesArray.length == 4) {
                String[] priceRange = itemSearchValuesArray[3].split("-");
                List<Double> priceRangeList = Arrays.stream(priceRange)
                        .map(pR -> pR.replaceAll("\\s", ""))
                        .map(Double::valueOf)
                        .toList();
                performItemSearch(itemSearchValuesArray[0], itemSearchValuesArray[1], itemSearchValuesArray[2], priceRangeList.get(0), priceRangeList.get(1));
            } else {
                throw new RuntimeException("Invalid input!");
            }
        }
    }

    public static void performStoreSearch(String location, String storeType, String openingDate) {
        UserOperations.performStoreSearch(location, storeType, openingDate);
    }

    public static void performItemSearch(String storeName, String itemName, String categoryName, double from, double to) {
        UserOperations.performItemSearch(storeName, itemName, categoryName, from, to);
    }

}