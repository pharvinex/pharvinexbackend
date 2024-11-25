package com.pharvinex.pharvinexGroup.ourClient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
@Entity
public class ourClient {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID
   private int id;

   private String clientUrl;

   @Lob
   @Column(columnDefinition = "LONGBLOB")
   private byte[] img;
}
