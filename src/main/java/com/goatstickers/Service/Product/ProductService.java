package com.goatstickers.Service.Product;

import com.goatstickers.DTO.Product.CreateProductDTO;
import com.goatstickers.DTO.Product.ProductDTO;
import com.goatstickers.DTO.Product.UpdateProductDTO;
import com.goatstickers.Entity.ProductEntity;
import com.goatstickers.Exception.ApiException;
import com.goatstickers.Mapper.Product.ProductMapper;
import com.goatstickers.Repository.Product.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    @Transactional
    public ProductDTO createProduct(CreateProductDTO dto) {
        ProductEntity product = ProductMapper.toEntity(dto);
        productRepository.persist(product);
        return ProductMapper.toDTO(product);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.listAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(UUID id) {
        ProductEntity product = productRepository.findById(id);
        if (product == null) {
            throw new ApiException(Response.Status.NOT_FOUND, "Produto não encontrado");
        }
        return ProductMapper.toDTO(product);
    }

    @Transactional
    public ProductDTO updateProduct(UUID id, UpdateProductDTO dto) {
        ProductEntity product = productRepository.findById(id);

        if (product == null) {
            throw new NotFoundException("Product not found");
        }

        dto.getTitle().ifPresent(product::setTitle);
        dto.getBrand().ifPresent(product::setBrand);
        dto.getCategory().ifPresent(product::setCategory);
        dto.getColor().ifPresent(product::setColor);
        dto.getMaterial().ifPresent(product::setMaterial);
        dto.getSize().ifPresent(product::setSize);
        dto.getUnitQuantity().ifPresent(product::setUnitQuantity);
        dto.getDescription().ifPresent(product::setDescription);
        dto.getImageUrl().ifPresent(product::setImageUrl);
        dto.getAvailable().ifPresent(product::setAvailable);

        product.setUpdatedAt(LocalDateTime.now());

        productRepository.persist(product);
        return ProductMapper.toDTO(product);
    }

    @Transactional
    public void deleteProduct(UUID id) {
        ProductEntity product = productRepository.findById(id);
        if (product == null) {
            throw new ApiException(Response.Status.NOT_FOUND, "Produto não encontrado");
        }

        productRepository.delete(product);
    }
}