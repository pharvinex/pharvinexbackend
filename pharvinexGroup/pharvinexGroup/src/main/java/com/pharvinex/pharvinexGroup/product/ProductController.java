package com.pharvinex.pharvinexGroup.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/admin/create-product")
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException {
        ProductDto createdProduct = productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping("/admin/list-product")
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        List<Product> products = productService.findAll();
        List<ProductDto> productDtos = products.stream().map(Product::getDto).collect(Collectors.toList());
        return ResponseEntity.ok(productDtos);
    }

    @PutMapping("/admin/update-product/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @ModelAttribute ProductDto productDto) throws IOException {
        ProductDto updatedProduct = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/admin/delete-product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/public/product")
    public List<ProductDto> getProductsForClient() {
        List<ProductDto> productDtos = productService.findAllForClient();
        // Check if byteImg is being populated
        for (ProductDto productDto : productDtos) {
            System.out.println(productDto.getByteImg());  // Log it to verify
        }
        return productDtos;
    }

    @GetMapping("/public/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto productDto = productService.findById(id);
        return ResponseEntity.ok(productDto);


    }

    // New method to get products by category ID

    @GetMapping("/public/products-by-category/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<ProductDto> products = productService.findProductsByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }


}
