package com.pharvinex.pharvinexGroup.certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @PostMapping("/admin/create-certificates")
    public ResponseEntity<Certificate> createCertificate(
            @RequestParam("certificateName") String certificateName,
            @RequestParam("certificateDescription") String certificateDescription,
            @RequestParam("certificateImg") MultipartFile certificateImg) {

        try {
            Certificate certificate = new Certificate();
            certificate.setCertificateName(certificateName);
            certificate.setCertificateDescription(certificateDescription);
            certificate.setCertificateImg(certificateImg.getBytes());
            Certificate createCertificate = certificateService.createCertificate(certificate);
            return new ResponseEntity<>(createCertificate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a single certificate by ID
    @GetMapping("/admin/list-certificates/{id}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable Long id) {
        Optional<Certificate> certificate = certificateService.getCertificateById(id);
        return certificate.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all certificates
    @GetMapping("/admin/list-certificates")
    public ResponseEntity<List<Certificate>> getAllCertificates() {
        List<Certificate> certificates = certificateService.getAllCertificates();
        return ResponseEntity.ok(certificates);
    }

    // Update an existing certificate
    @PutMapping("/admin/update-certificates/{id}")
    public ResponseEntity<Certificate> updateCertificate(
            @PathVariable Long id,
            @RequestParam("certificateName") String certificateName,
            @RequestParam("certificateDescription") String certificateDescription,
            @RequestParam(value = "certificateImg", required = false) MultipartFile certificateImg) {

        try {
            // Retrieve the existing certificate
            Optional<Certificate> existingCertificate = certificateService.getCertificateById(id);
            if (existingCertificate.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Update the fields of the existing certificate
            Certificate certificate = existingCertificate.get();
            certificate.setCertificateName(certificateName);
            certificate.setCertificateDescription(certificateDescription);

            // Check if a new image is provided, and update if so
            if (certificateImg != null && !certificateImg.isEmpty()) {
                certificate.setCertificateImg(certificateImg.getBytes());
            }

            // Save the updated certificate
            Certificate updatedCertificate = certificateService.updateCertificate(id, certificate);
            return new ResponseEntity<>(updatedCertificate, HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a certificate by ID
    @DeleteMapping("/admin/delete-certificates/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable Long id) {
        if (certificateService.deleteCertificate(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


     //    getting certificate for client
     @GetMapping("/public/list-certificates")
     public ResponseEntity<List<Certificate>> getAllCertificatesForClient() {
        List<Certificate> certificates = certificateService.getAllCertificates();
        return ResponseEntity.ok(certificates);
     }
}