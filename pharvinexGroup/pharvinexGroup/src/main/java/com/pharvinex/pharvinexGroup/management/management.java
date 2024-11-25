package com.pharvinex.pharvinexGroup.management;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class management {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] img;


    private String description;


}
