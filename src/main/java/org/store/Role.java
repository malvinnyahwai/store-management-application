package org.store;

public enum Role {
    ADMIN("admin"),
    STORE_MANAGER("store_manager"),
    STORE_STAFF("store_staff"),
    DEFAULT_USER("default_user");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
