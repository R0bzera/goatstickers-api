package com.goatstickers.DTO.Product;

import java.util.UUID;

public class ProductDTO {
    public UUID id;
    public String title;
    public String brand;
    public String category;
    public String color;
    public String material;
    public String size;
    public Integer unitQuantity;
    public String description;
    public Double rating;
    public String imageUrl;
    public Boolean available;
    public Double averageRating;

    public ProductDTO() {}

    public ProductDTO(UUID id, String title, String brand, String category, String color, String material, String size,
                      Integer unitQuantity, String description, Double rating, String imageUrl, Boolean available) {
        this.id = id;
        this.title = title;
        this.brand = brand;
        this.category = category;
        this.color = color;
        this.material = material;
        this.size = size;
        this.unitQuantity = unitQuantity;
        this.description = description;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.available = available;
    }
}