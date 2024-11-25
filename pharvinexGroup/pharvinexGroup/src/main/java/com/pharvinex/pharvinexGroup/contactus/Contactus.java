package com.pharvinex.pharvinexGroup.contactus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Contactus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String addressTitle;

    private String streetAddress;

    private String addressLine2;

    private String city;

    private String zipCode;

    private String email;

    private Long number;
}
