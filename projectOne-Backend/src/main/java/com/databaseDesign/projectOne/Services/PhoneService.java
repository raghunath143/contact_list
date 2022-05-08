package com.databaseDesign.projectOne.Services;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.databaseDesign.projectOne.Entities.PhoneEntity;
import com.databaseDesign.projectOne.Repositories.PhoneRepository;
import com.databaseDesign.projectOne.Repositories.ContactRepository;

@Service
public class PhoneService {
    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    ContactRepository contactRepository;

    public List<PhoneEntity> getAllPhones(Integer contactId) {
        List<PhoneEntity> phones = phoneRepository.findByContactId(contactId);
        if (phones.size() > 0) {
            return phones;
        } else {
            return new ArrayList<PhoneEntity>();
        }
    }

    public PhoneEntity addPhone(Integer contactId, PhoneEntity phone) {
        return contactRepository.findById(contactId)
                .map(contact -> {
                    phone.setContact(contact);
                    return phoneRepository.save(phone);
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    public PhoneEntity modifyPhone(Integer phoneId, PhoneEntity modifiedPhone) {
        return phoneRepository.findById(phoneId)
                .map(phone -> {
                    phone.setAreaCode(modifiedPhone.getAreaCode());
                    phone.setPhoneType(modifiedPhone.getPhoneType());
                    phone.setNumber(modifiedPhone.getNumber());
                    return phoneRepository.save(phone);
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    public void deletePhone(Integer phoneId) {
        try {
            phoneRepository.deleteById(phoneId);
        }
        catch(StaleStateException e) {
            System.out.println(e.toString());
            return;
        } 
    }
}