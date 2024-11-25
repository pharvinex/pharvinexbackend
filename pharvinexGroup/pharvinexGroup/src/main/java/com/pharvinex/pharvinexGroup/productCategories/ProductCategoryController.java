package com.pharvinex.pharvinexGroup.productCategories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProductCategoryController {

    private final ProductCategoryService categoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create a new ProductCategory
    @PostMapping("/admin/create-product-category")
    public ResponseEntity<ProductCategory> createCategory(@RequestBody ProductCategory category) {
        ProductCategory createdCategory = categoryService.save(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    // Get a ProductCategory by ID
    @GetMapping("/admin/list-product-category/{id}")
    public ResponseEntity<ProductCategory> getCategoryById(@PathVariable Long id) {
        ProductCategory category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }

    // Get all ProductCategories
    @GetMapping("/admin/list-product-category")
    public ResponseEntity<List<ProductCategory>> getAllCategories() {
        List<ProductCategory> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    // Update a ProductCategory by ID
    @PutMapping("/admin/update-product-category/{id}")
    public ResponseEntity<ProductCategory> updateCategory(
            @PathVariable Long id, @RequestBody ProductCategory categoryDetails) {
        ProductCategory updatedCategory = categoryService.update(id, categoryDetails);
        return ResponseEntity.ok(updatedCategory);
    }

    // Delete a ProductCategory by ID
    @DeleteMapping("/admin/delete-product-category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/public/list-product-category/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable Long id) {
        ProductCategory category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }

    // Get all ProductCategories
    @GetMapping("/public/list-product-category")
    public ResponseEntity<List<ProductCategory>> getAllProductCategories() {
        List<ProductCategory> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }


}
