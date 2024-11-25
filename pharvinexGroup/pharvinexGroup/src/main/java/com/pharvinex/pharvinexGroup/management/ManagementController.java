package com.pharvinex.pharvinexGroup.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ManagementController {

    @Autowired
    private ManagementService managementService;

    @PostMapping("/admin/create-management")
    public ResponseEntity<management> createManagement(@RequestParam("name") String name,
                                                       @RequestParam("description") String description,
                                                       @RequestParam("img") MultipartFile img) {
        management management = managementService.saveManagement(name, description, img);
        return new ResponseEntity<>(management, HttpStatus.CREATED);
    }

    @PutMapping("/admin/update-management/{id}")
    public ResponseEntity<management> updateManagement(@PathVariable int id,
                                                       @RequestParam("name") String name,
                                                       @RequestParam("description") String description,
                                                       @RequestParam(value = "img", required = false) MultipartFile img) {
        management updatedManagement = managementService.updateManagement(id, name, description, img);
        return updatedManagement != null ? new ResponseEntity<>(updatedManagement, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/admin/delete-management/{id}")
    public ResponseEntity<Void> deleteManagement(@PathVariable int id) {
        managementService.deleteManagement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/admin/management/{id}")
    public ResponseEntity<management> getManagementById(@PathVariable int id) {
        management management = managementService.getManagementById(id);
        return management != null ? new ResponseEntity<>(management, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @GetMapping("/admin/all-management")
    public ResponseEntity<List<management>> getAllManagement() {
        List<management> managementList = managementService.getAllManagement();
        return new ResponseEntity<>(managementList, HttpStatus.OK);
    }

    @GetMapping("/public/all-management")
    public ResponseEntity<List<management>> getAllManagementforClient() {
        List<management> managementList = managementService.getAllManagement();
        return new ResponseEntity<>(managementList, HttpStatus.OK);
    }
}
