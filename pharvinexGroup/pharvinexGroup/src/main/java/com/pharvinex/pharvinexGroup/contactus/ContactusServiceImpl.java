package com.pharvinex.pharvinexGroup.contactus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactusServiceImpl implements ContactusService {

    private final ContactusRepository contactusRepository;

    @Autowired
    public ContactusServiceImpl(ContactusRepository contactusRepository) {
        this.contactusRepository = contactusRepository;
    }

    @Override
    public Contactus createContactus(Contactus contactus) {
        return contactusRepository.save(contactus);
    }

    @Override
    public Optional<Contactus> getContactusById(int id) {
        return contactusRepository.findById(id);
    }

    @Override
    public List<Contactus> getAllContactus() {
        return contactusRepository.findAll();
    }

    @Override
    public Contactus updateContactus(int id, Contactus updatedContactus) {
        return contactusRepository.findById(id).map(contactus -> {
            contactus.setAddressTitle(updatedContactus.getAddressTitle());
            contactus.setStreetAddress(updatedContactus.getStreetAddress());
            contactus.setAddressLine2(updatedContactus.getAddressLine2());
            contactus.setCity(updatedContactus.getCity());
            contactus.setZipCode(updatedContactus.getZipCode());
            contactus.setEmail(updatedContactus.getEmail());
            contactus.setNumber(updatedContactus.getNumber());
            return contactusRepository.save(contactus);
        }).orElseThrow(() -> new RuntimeException("Contactus not found with id " + id));
    }

    @Override
    public void deleteContactus(int id) {
        if (contactusRepository.existsById(id)) {
            contactusRepository.deleteById(id);
        } else {
            throw new RuntimeException("Contactus not found with id " + id);
        }
    }

    @Override
    public List<Contactus> getAllViewContactus() {
        return  contactusRepository.findAll();
    }
}
