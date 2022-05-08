package com.databaseDesign.projectOne.Services;

import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.databaseDesign.projectOne.Entities.AddressEntity;
import com.databaseDesign.projectOne.Entities.Contact;
import com.databaseDesign.projectOne.Entities.DateEntity;
import com.databaseDesign.projectOne.Entities.PhoneEntity;
import com.databaseDesign.projectOne.Repositories.ContactRepository;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    public List<Contact> getAllContacts()  {
        List<Contact> contacts = contactRepository.findAll();
        if (contacts.size() > 0) {
            return contacts;
        } else {
            return new ArrayList<Contact>();
        }
    }

    public Set<AddressEntity> getAllAddresses(Integer contactId) {
        return contactRepository.findById(contactId)
                .map(contact -> {
                    return contact.getAddresses();
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    public Set<PhoneEntity> getAllPhones(Integer contactId) {
        return contactRepository.findById(contactId)
                .map(contact -> {
                    return contact.getPhones();
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    public Set<DateEntity> getAllDates(Integer contactId) {
        return contactRepository.findById(contactId)
                .map(contact -> {
                    return contact.getDates();
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    public Set<Contact> getContactBySearch(String searchString) {
        String[] tokens = searchString.split(" ");
        Set<Contact> result = new HashSet<Contact>();
        for (int i = 0; i < tokens.length; i++) {
            String token = "%" + tokens[i] + "%";
            result.addAll(contactRepository.findByContactToken(token));
        }
        return result;
    }

    public Contact getContactById(Integer id) {
        return contactRepository.findById(id)
                .map(contact -> {
                    return contact;
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    public Contact createContact(Contact entity) {
        return contactRepository.save(entity);
    }

    public Contact modifyContact(Contact modifiedContact) {
        return contactRepository.findById(modifiedContact.getContactId())
                .map(contact -> {
                    contact.setfName(modifiedContact.getfName());
                    contact.setmName(modifiedContact.getmName());
                    contact.setlName(modifiedContact.getlName());
                    return contactRepository.save(contact);
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    public String deleteContact(Integer id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if(contact.isPresent()) {
            contactRepository.delete(contact.get());
            return "deleted";
        }
        else return "deleted";
    }
}
