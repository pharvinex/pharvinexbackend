package com.pharvinex.pharvinexGroup.event;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] img;

    @Lob
    private String description;
}
