package com.goatstickers.DTO.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateProductDTO {

    @NotBlank(message = "O título é obrigatório")
    public String title;

    @NotBlank(message = "A marca é obrigatória")
    public String brand;

    @NotBlank(message = "A categoria é obrigatória")
    public String category;

    @NotBlank(message = "A cor é obrigatória")
    public String color;

    @NotBlank(message = "O material é obrigatório")
    public String material;

    @NotBlank(message = "O tamanho é obrigatório")
    public String size;

    @NotNull(message = "A quantidade de unidades é obrigatória")
    public Integer unitQuantity;

    @NotBlank(message = "A descrição é obrigatória")
    public String description;

    @NotBlank(message = "A URL da imagem é obrigatória")
    public String imageUrl;

    @NotNull(message = "A disponibilidade é obrigatória")
    public Boolean available = true;

    public CreateProductDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getUnitQuantity() {
        return unitQuantity;
    }

    public void setUnitQuantity(int unitQuantity) {
        this.unitQuantity = unitQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}