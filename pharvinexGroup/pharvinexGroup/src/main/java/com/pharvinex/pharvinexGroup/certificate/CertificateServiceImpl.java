package com.pharvinex.pharvinexGroup.certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    // Create a new certificate
    public Certificate createCertificate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    // Get all certificates
    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    // Get a single certificate by ID
    public Optional<Certificate> getCertificateById(Long id) {
        return certificateRepository.findById(id);
    }

    // Update an existing certificate
    public Certificate updateCertificate(Long id, Certificate certificate) {
        certificate.setId(id);
        return certificateRepository.save(certificate);
    }

    // Delete a certificate by ID
    public boolean deleteCertificate(Long id) {
        try {
            certificateRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false; // Return false if deletion fails
        }
    }

    @Override
    public List<Certificate> getCertificatesForClient() {
        return certificateRepository.findAll();
    }
}