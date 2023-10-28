package org.store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private String name;
    private Location location;
    private ContactInformation contactInformation;
    private final List<Item> inventory = new ArrayList<>();
    private User storeManager;
    private String storeType;
    private LocalDate openingDate;
    private final List<User> staff = new ArrayList<>();

    public Store(String name, Location location, ContactInformation contactInformation, User storeManager,
                 String storeType, LocalDate openingDate) {
        this.name = name;
        this.location = location;
        this.contactInformation = contactInformation;
        this.storeManager = storeManager;
        staff.add(storeManager);
        this.storeType = storeType;
        this.openingDate = openingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void addInventory(Item item) {
        this.inventory.add(item);
    }

    public User getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(User storeManager) {
        this.storeManager = storeManager;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public List<User> getStaff() {
        return staff;
    }

    public void addStaff(User staff) {
        this.staff.add(staff);
    }
}
