package com.pharvinex.pharvinexGroup.productCategories;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory save(ProductCategory category);
    ProductCategory getById(Long id);
    List<ProductCategory> getAll();
    ProductCategory update(Long id, ProductCategory category);
    void delete(Long id);

    ProductCategory getProductById(Long id);
    List<ProductCategory> getAllProductCategory();
}
