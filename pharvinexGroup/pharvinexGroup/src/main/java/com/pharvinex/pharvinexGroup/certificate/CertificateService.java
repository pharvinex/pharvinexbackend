package com.pharvinex.pharvinexGroup.certificate;
import java.util.List;
import java.util.Optional;

public interface CertificateService {

    Certificate createCertificate(Certificate certificate);
    Optional<Certificate> getCertificateById(Long id);
    List<Certificate> getAllCertificates();
    Certificate updateCertificate(Long id, Certificate certificate);
    boolean deleteCertificate(Long id);
    List<Certificate> getCertificatesForClient();
//    Certificate getCertificateById(Long id);



}