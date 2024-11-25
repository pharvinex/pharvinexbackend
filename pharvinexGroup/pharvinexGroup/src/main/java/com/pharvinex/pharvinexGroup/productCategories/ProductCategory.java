package com.pharvinex.pharvinexGroup.productCategories;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    @Lob
    private String categoryDescription;


}
