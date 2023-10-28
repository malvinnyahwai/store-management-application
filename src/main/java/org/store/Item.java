package org.store;

public class Item {
    private String name;
    private String description;
    private Category category;
    private Double price;
    private Long initialQuality;
    private Long quantity;
    private Long itemThreshold;
    private Supplier supplier;

    public Item(String name, String description, Category category, Double price, Long initialQuality, Long itemThreshold,
                Supplier supplier) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.initialQuality = initialQuality;
        this.quantity = initialQuality;
        this.itemThreshold = itemThreshold;
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getInitialQuality() {
        return initialQuality;
    }

    public void setInitialQuality(Long initialQuality) {
        this.initialQuality = initialQuality;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getItemThreshold() {
        return itemThreshold;
    }

    public void setItemThreshold(Long itemThreshold) {
        this.itemThreshold = itemThreshold;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
