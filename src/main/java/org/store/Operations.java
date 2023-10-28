package org.store;

import java.util.ArrayList;
import java.util.List;

public class Operations {
    private final User user;
    protected final List<Store> storeList = new ArrayList<>();
    protected final List<Supplier> supplierList = new ArrayList<>();
    public Operations(User user) {
        this.user = user;
    }

    protected void checkRole(Operations operations, Role role) {
        if(operations.user.getRole() != role) {
            throw new RuntimeException("403 Operation not allowed");
        }
    }
}
