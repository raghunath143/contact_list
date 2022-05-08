package com.databaseDesign.projectOne.Services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.databaseDesign.projectOne.Entities.AddressEntity;
import com.databaseDesign.projectOne.Repositories.AddressRepository;
import com.databaseDesign.projectOne.Repositories.ContactRepository;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ContactRepository contactRepository;
    public List<AddressEntity> getAllAddresses(Integer contactId) {
        List<AddressEntity> addresses = addressRepository.findByContactId(contactId);
        if (addresses.size() > 0) {
            return addresses;
        } else {
            return new ArrayList<AddressEntity>();
        }
    }

    public AddressEntity addAddress(Integer contactId, AddressEntity address) {
        return contactRepository.findById(contactId)
                .map(contact -> {
                    address.setContact(contact);
                    return addressRepository.save(address);
                })
                .orElseGet(() -> { return null; });
        
    }

    public AddressEntity modifyAddress(Integer addressId, AddressEntity modifiedAddress) {
        return addressRepository.findById(addressId)
                .map(address -> {
                    address.setAddressType(modifiedAddress.getAddressType());
                    address.setAddress(modifiedAddress.getAddress());
                    address.setCity(modifiedAddress.getCity());
                    address.setState(modifiedAddress.getState());
                    address.setZip(modifiedAddress.getZip());
                    return addressRepository.save(address);
                })
                .orElseGet(() -> { return null; });
    }

    public void deleteAddress(Integer addressId) {
        addressRepository.deleteById(addressId);
    }
}