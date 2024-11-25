package com.pharvinex.pharvinexGroup.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ManagementServiceImpl implements ManagementService {

    @Autowired
    private ManagementRepository managementRepository;

    @Override
    public management saveManagement(String name, String description, MultipartFile img) {
        management management = new management();
        management.setName(name);
        management.setDescription(description);
        try {
            management.setImg(img.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return managementRepository.save(management);
    }

    @Override
    public management updateManagement(int id, String name, String description, MultipartFile img) {
        Optional<management> optionalManagement = managementRepository.findById(id);
        if (optionalManagement.isPresent()) {
            management management = optionalManagement.get();
            management.setName(name);
            management.setDescription(description);
            try {
                if (img != null && !img.isEmpty()) {
                    management.setImg(img.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return managementRepository.save(management);
        }
        return null;
    }

    @Override
    public void deleteManagement(int id) {
        managementRepository.deleteById(id);
    }

    @Override
    public management getManagementById(int id) {
        return managementRepository.findById(id).orElse(null);
    }

    @Override
    public List<management> getAllManagement() {
        return managementRepository.findAll();
    }

    @Override
    public List<management> getAllManagementByClient() {
        return managementRepository.findAll();
    }
}
