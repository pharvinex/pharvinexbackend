package com.pharvinex.pharvinexGroup.certificate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String certificateName;

    @Lob
    private String certificateDescription;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] certificateImg; // To store the image in binary format
}