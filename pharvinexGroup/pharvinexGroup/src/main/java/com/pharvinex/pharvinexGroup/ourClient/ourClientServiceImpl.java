package com.pharvinex.pharvinexGroup.ourClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ourClientServiceImpl implements ourClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ourClient createClient(ourClient client, MultipartFile img) {
        try {
            if (img != null && !img.isEmpty()) {
                client.setImg(img.getBytes());
            } else {
                throw new IllegalArgumentException("Image file cannot be empty");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Consider logging this exception
            throw new RuntimeException("Error saving image", e);
        }
        return clientRepository.save(client);
    }

    @Override
    public ourClient updateClient(int id, ourClient client, MultipartFile image) {
        Optional<ourClient> existingClient = clientRepository.findById(id);
        if (existingClient.isPresent()) {
            ourClient updatedClient = existingClient.get();
            updatedClient.setClientUrl(client.getClientUrl());

            // Only update the image if a valid image file is provided
            if (image != null && !image.isEmpty()) {
                try {
                    updatedClient.setImg(image.getBytes());
                } catch (IOException e) {
                    e.printStackTrace(); // Log the error
                    throw new RuntimeException("Error updating image", e);
                }
            }

            return clientRepository.save(updatedClient);
        }
        return null; // Client not found
    }


    @Override
    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<ourClient> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<ourClient> getClientById(int id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<ourClient> getAllViewClients() {
        return clientRepository.findAll();
    }
}
