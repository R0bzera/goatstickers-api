package com.goatstickers.DTO.Product;

import java.util.Optional;

public class UpdateProductDTO {
    private Optional<String> title = Optional.empty();
    private Optional<String> brand = Optional.empty();
    private Optional<String> category = Optional.empty();
    private Optional<String> color = Optional.empty();
    private Optional<String> material = Optional.empty();
    private Optional<String> size = Optional.empty();
    private Optional<Integer> unitQuantity = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<String> imageUrl = Optional.empty();
    private Optional<Boolean> available = Optional.empty();

    public Optional<String> getTitle() {
        return title;
    }

    public void setTitle(Optional<String> title) {
        this.title = title;
    }

    public Optional<String> getBrand() {
        return brand;
    }

    public void setBrand(Optional<String> brand) {
        this.brand = brand;
    }

    public Optional<String> getCategory() {
        return category;
    }

    public void setCategory(Optional<String> category) {
        this.category = category;
    }

    public Optional<String> getColor() {
        return color;
    }

    public void setColor(Optional<String> color) {
        this.color = color;
    }

    public Optional<String> getMaterial() {
        return material;
    }

    public void setMaterial(Optional<String> material) {
        this.material = material;
    }

    public Optional<String> getSize() {
        return size;
    }

    public void setSize(Optional<String> size) {
        this.size = size;
    }

    public Optional<Integer> getUnitQuantity() {
        return unitQuantity;
    }

    public void setUnitQuantity(Optional<Integer> unitQuantity) {
        this.unitQuantity = unitQuantity;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(Optional<String> description) {
        this.description = description;
    }

    public Optional<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Optional<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Optional<Boolean> getAvailable() {
        return available;
    }

    public void setAvailable(Optional<Boolean> available) {
        this.available = available;
    }
}