package com.pharvinex.pharvinexGroup.management;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ManagementService {
    management saveManagement(String name, String description, MultipartFile img);
    management updateManagement(int id, String name, String description, MultipartFile img);
    void deleteManagement(int id);
    management getManagementById(int id);
    List<management> getAllManagement();
    List<management> getAllManagementByClient();
}
