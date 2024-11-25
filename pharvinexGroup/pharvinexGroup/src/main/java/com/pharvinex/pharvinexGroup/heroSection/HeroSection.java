package com.pharvinex.pharvinexGroup.heroSection;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeroSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String heading;

    @Column(columnDefinition = "LONGBLOB", nullable = true)
    private byte[] logo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
}

