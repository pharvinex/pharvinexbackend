package com.pharvinex.pharvinexGroup.product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductDto addProduct(ProductDto productDto) throws IOException;

    List<Product> findAll();

    ProductDto updateProduct(Long id, ProductDto productDto) throws IOException; // Method to update product

    void deleteProduct(Long id); // Method to delete product

    List<ProductDto> findAllForClient();

    ProductDto findById(Long id); // Add this


    List<ProductDto> findProductsByCategoryId(Long categoryId);
}

