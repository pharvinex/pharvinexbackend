package com.pharvinex.pharvinexGroup.product;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDto {

    private Long id;

    private String name;

    @Lob
    private String description;

    private String details;

    private byte[] byteImg;
    private Long categoryId;

    private MultipartFile img;
}
