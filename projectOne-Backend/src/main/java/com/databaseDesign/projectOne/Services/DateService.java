package com.databaseDesign.projectOne.Services;

import java.util.ArrayList;
import java.util.List;

import com.databaseDesign.projectOne.Entities.DateEntity;
import com.databaseDesign.projectOne.Repositories.ContactRepository;
import com.databaseDesign.projectOne.Repositories.DateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DateService {
    @Autowired
    DateRepository dateRepository;

    @Autowired
    ContactRepository contactRepository;

    public List<DateEntity> getAllDates(Integer contactId) {
        List<DateEntity> dates = dateRepository.findByContactId(contactId);
        if (dates.size() > 0) {
            return dates;
        } else {
            return new ArrayList<DateEntity>();
        }
    }

    public DateEntity addDate(Integer contactId, DateEntity date) {
        return contactRepository.findById(contactId)
                .map(contact -> {
                    date.setContact(contact);
                    return dateRepository.save(date);
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    public DateEntity modifyDate(Integer dateId, DateEntity modifiedDate) {
        return dateRepository.findById(dateId)
                .map(date -> {
                    date.setDateType(modifiedDate.getDateType());
                    date.setDate(modifiedDate.getDate());
                    return dateRepository.save(date);
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    public void deleteDate(Integer dateId) {
        dateRepository.deleteById(dateId);
    }
}