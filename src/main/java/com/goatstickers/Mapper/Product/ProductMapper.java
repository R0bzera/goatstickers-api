package com.goatstickers.Mapper.Product;

import com.goatstickers.DTO.Product.CreateProductDTO;
import com.goatstickers.DTO.Product.ProductDTO;
import com.goatstickers.Entity.ProductEntity;

public class ProductMapper {

    public static ProductEntity toEntity(CreateProductDTO dto) {
        ProductEntity product = new ProductEntity();
        product.setTitle(dto.title);
        product.setBrand(dto.brand);
        product.setCategory(dto.category);
        product.setColor(dto.color);
        product.setMaterial(dto.material);
        product.setSize(dto.size);
        product.setUnitQuantity(dto.unitQuantity);
        product.setDescription(dto.description);
        product.setImageUrl(dto.imageUrl);
        product.setAvailable(dto.available);
        product.setAverageRating(0.0);
        return product;
    }

    public static ProductDTO toDTO(ProductEntity entity) {
        ProductDTO dto = new ProductDTO();
        dto.id = entity.getId();
        dto.title = entity.getTitle();
        dto.brand = entity.getBrand();
        dto.category = entity.getCategory();
        dto.color = entity.getColor();
        dto.material = entity.getMaterial();
        dto.size = entity.getSize();
        dto.unitQuantity = entity.getUnitQuantity();
        dto.description = entity.getDescription();
        dto.imageUrl = entity.getImageUrl();
        dto.available = entity.getAvailable();
        dto.averageRating = entity.getAverageRating();
        return dto;
    }
}