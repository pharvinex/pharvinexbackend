package com.pharvinex.pharvinexGroup.contactus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ContactusController {

    private final ContactusService contactusService;

    @Autowired
    public ContactusController(ContactusService contactusService) {
        this.contactusService = contactusService;
    }

    @PostMapping("admin/create-contact")
    public ResponseEntity<Contactus> createContactus(@RequestBody Contactus contactus) {
        Contactus createdContactus = contactusService.createContactus(contactus);
        return new ResponseEntity<>(createdContactus, HttpStatus.CREATED);
    }

    @GetMapping("/admin/contact/{id}")
    public ResponseEntity<Contactus> getContactusById(@PathVariable int id) {
        return contactusService.getContactusById(id)
                .map(contactus -> new ResponseEntity<>(contactus, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/admin/contact")
    public ResponseEntity<List<Contactus>> getAllContactus() {
        List<Contactus> contactusList = contactusService.getAllContactus();
        return new ResponseEntity<>(contactusList, HttpStatus.OK);
    }

    @PutMapping("/admin/update-contact/{id}")
    public ResponseEntity<Contactus> updateContactus(@PathVariable int id, @RequestBody Contactus contactus) {
        try {
            Contactus updatedContactus = contactusService.updateContactus(id, contactus);
            return new ResponseEntity<>(updatedContactus, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/admin/delete-contact/{id}")
    public ResponseEntity<Void> deleteContactus(@PathVariable int id) {
        try {
            contactusService.deleteContactus(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/public/contact")
    public ResponseEntity<List<Contactus>> getAllViewContactus() {
        List<Contactus> contactusList = contactusService.getAllViewContactus();
        return new ResponseEntity<>(contactusList, HttpStatus.OK);
    }

}
