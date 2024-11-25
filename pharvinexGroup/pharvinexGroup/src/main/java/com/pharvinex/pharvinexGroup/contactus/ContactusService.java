package com.pharvinex.pharvinexGroup.contactus;

import java.util.List;
import java.util.Optional;

public interface ContactusService {
    Contactus createContactus(Contactus contactus);
    Optional<Contactus> getContactusById(int id);
    List<Contactus> getAllContactus();
    Contactus updateContactus(int id, Contactus contactus);
    void deleteContactus(int id);
    List<Contactus> getAllViewContactus();
}
