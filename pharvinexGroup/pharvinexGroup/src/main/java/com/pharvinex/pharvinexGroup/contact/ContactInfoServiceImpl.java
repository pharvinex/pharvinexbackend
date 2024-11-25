package com.pharvinex.pharvinexGroup.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactInfoServiceImpl implements ContactInfoService {

    @Autowired
    private ContactInfoRepository contactInfoRepository;

    @Override
    public List<ContactInfo> getAllContactInfo() {
        return contactInfoRepository.findAll();
    }


    @Override
    public ContactInfo saveContactInfo(ContactInfo contactInfo) {
        return contactInfoRepository.save(contactInfo);
    }

    @Override
    public ContactInfo updateContactInfo(int id, ContactInfo contactInfo) {
        Optional<ContactInfo> existingContactInfo = contactInfoRepository.findById(id);
        if (existingContactInfo.isPresent()) {
            ContactInfo updatedContact = existingContactInfo.get();
            updatedContact.setPhone(contactInfo.getPhone());
            updatedContact.setEmail(contactInfo.getEmail());
            return contactInfoRepository.save(updatedContact);
        }
        return null; // or throw a custom exception if not found
    }

    @Override
    public void deleteContactInfo(int id) {
        contactInfoRepository.deleteById(id);
    }
}
