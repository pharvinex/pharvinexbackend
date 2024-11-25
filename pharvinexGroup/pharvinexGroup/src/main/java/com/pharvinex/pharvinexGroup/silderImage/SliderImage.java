package com.pharvinex.pharvinexGroup.silderImage;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SliderImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Change to IDENTITY for better compatibility
    private int id;

    private String name;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] displayPicture;
}
