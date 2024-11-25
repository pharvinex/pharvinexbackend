package com.pharvinex.pharvinexGroup.ourClient;

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
public class ourClientController {

    @Autowired
    private ourClientService clientService;

    // Create Client
    @PostMapping("/admin/create-client")
    public ResponseEntity<ourClient> createClient(@RequestParam("clientUrl") String clientUrl,
                                                  @RequestParam("img") MultipartFile image) {
        if (image.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Return error if no image is provided
        }
        ourClient client = new ourClient();
        client.setClientUrl(clientUrl);
        ourClient savedClient = clientService.createClient(client, image);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    // Update Client
    @PutMapping("/admin/client/{id}")
    public ResponseEntity<ourClient> updateClient(@PathVariable int id,
                                                  @RequestParam("clientUrl") String clientUrl,
                                                  @RequestParam("img") MultipartFile image) {
        Optional<ourClient> existingClientOpt = clientService.getClientById(id);

        if (!existingClientOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return error if client is not found
        }

        ourClient client = existingClientOpt.get();
        client.setClientUrl(clientUrl);

        // Handle image update
        if (image != null && !image.isEmpty()) {
            try {
                client.setImg(image.getBytes());
            } catch (IOException e) {
                e.printStackTrace(); // Log the error
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return server error on failure
            }
        }

        ourClient updatedClient = clientService.updateClient(id, client, image);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }


    // Delete Client
    @DeleteMapping("/admin/client/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get All Clients
    @GetMapping("/admin/list-client")
    public ResponseEntity<List<ourClient>> getAllClients() {
        List<ourClient> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    // Get All Clients
    @GetMapping("/public/list-client")
    public ResponseEntity<List<ourClient>> getAllViewClients() {
        List<ourClient> clients = clientService.getAllViewClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    // Get Client by ID
    @GetMapping("/admin/list-client/{id}")
    public ResponseEntity<ourClient> getClientById(@PathVariable int id) {
        Optional<ourClient> client = clientService.getClientById(id);
        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
