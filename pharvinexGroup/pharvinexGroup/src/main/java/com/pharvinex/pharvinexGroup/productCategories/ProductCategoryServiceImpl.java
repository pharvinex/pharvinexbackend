package com.pharvinex.pharvinexGroup.productCategories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository categoryRepository;

    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductCategory save(ProductCategory category) {
        return categoryRepository.save(category);
    }

    @Override
    public ProductCategory getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    @Override
    public List<ProductCategory> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public ProductCategory update(Long id, ProductCategory categoryDetails) {
        ProductCategory category = getById(id);
        category.setCategoryName(categoryDetails.getCategoryName());
        category.setCategoryDescription(categoryDetails.getCategoryDescription());
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        ProductCategory category = getById(id);
        categoryRepository.delete(category);
    }

    @Override
    public ProductCategory getProductById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    @Override
    public List<ProductCategory> getAllProductCategory() {
        return categoryRepository.findAll();
    }
}
