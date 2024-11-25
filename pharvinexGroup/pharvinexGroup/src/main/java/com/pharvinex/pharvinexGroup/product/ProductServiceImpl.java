package com.pharvinex.pharvinexGroup.product;

import com.pharvinex.pharvinexGroup.exception.ResourceNotFoundException;
import com.pharvinex.pharvinexGroup.productCategories.ProductCategory;
import com.pharvinex.pharvinexGroup.productCategories.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductDto addProduct(ProductDto productDto) throws IOException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setDetails(productDto.getDetails());
        product.setImg(productDto.getImg().getBytes());

        ProductCategory category = productCategoryRepository.findById(productDto.getCategoryId()).orElseThrow();
        product.setCategory(category);
        return productRepository.save(product).getDto();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductDto> findAllForClient() {
        return productRepository.findAll().stream()
                .map(product -> {
                    ProductDto dto = product.getDto();
                    // Do not set byteImg to null. Just return the DTO with the image data.
                    return dto;
                })
                .collect(Collectors.toList());
    }



    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setDetails(productDto.getDetails());

        if (productDto.getImg() != null) {
            product.setImg(productDto.getImg().getBytes());
        }

        ProductCategory category = productCategoryRepository.findById(productDto.getCategoryId()).orElseThrow();
        product.setCategory(category);
        return productRepository.save(product).getDto();
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        return product.getDto(); // Assuming `Product` has a method `getDto` to convert to `ProductDto`
    }

    @Override
    public List<ProductDto> findProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);  // Assuming the method exists in the repository
        return products.stream()
                .map(Product::getDto)  // Convert each product to ProductDto
                .collect(Collectors.toList());
    }



}

