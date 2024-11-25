package com.pharvinex.pharvinexGroup.contact;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ContactInfoController {

    @Autowired
    private ContactInfoService contactInfoService;

    // Get all contact information
    @GetMapping("/admin/contact-info")
    public List<ContactInfo> getAllContactInfo() {
        return contactInfoService.getAllContactInfo();
    }

    // Get a single contact info by ID
    @GetMapping("/public/contact-info")
    public List<ContactInfo> getAllContactInfoPublic() {
        return contactInfoService.getAllContactInfo();
    }
    // Create a new contact info
    @PostMapping("/admin/create-contact-info")
    public ContactInfo createContactInfo(@RequestBody ContactInfo contactInfo) {
        return contactInfoService.saveContactInfo(contactInfo);
    }

    // Update an existing contact info
    @PutMapping("/admin/update-contact-info/{id}")
    public ResponseEntity<ContactInfo> updateContactInfo(@PathVariable int id, @RequestBody ContactInfo contactInfo) {
        return ResponseEntity.ok(contactInfoService.updateContactInfo(id, contactInfo));
    }

    // Delete a contact info
    @DeleteMapping("/admin/delete-contact-info/{id}")
    public ResponseEntity<Void> deleteContactInfo(@PathVariable int id) {
        contactInfoService.deleteContactInfo(id);
        return ResponseEntity.noContent().build();
    }
}
