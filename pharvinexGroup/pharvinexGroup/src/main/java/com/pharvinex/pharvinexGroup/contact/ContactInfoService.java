package com.pharvinex.pharvinexGroup.contact;

import java.util.List;

public interface ContactInfoService {
    List<ContactInfo> getAllContactInfo();

    ContactInfo saveContactInfo(ContactInfo contactInfo);
    ContactInfo updateContactInfo(int id, ContactInfo contactInfo);
    void deleteContactInfo(int id);
}
