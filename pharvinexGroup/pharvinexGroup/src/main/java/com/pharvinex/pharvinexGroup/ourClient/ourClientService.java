package com.pharvinex.pharvinexGroup.ourClient;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

public interface ourClientService {
    ourClient createClient(ourClient client, MultipartFile image);
    ourClient updateClient(int id, ourClient client, MultipartFile image);
    void deleteClient(int id);
    List<ourClient> getAllClients();
    Optional<ourClient> getClientById(int id);
    List<ourClient> getAllViewClients();

}
