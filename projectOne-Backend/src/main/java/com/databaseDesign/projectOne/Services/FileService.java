package com.databaseDesign.projectOne.Services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.databaseDesign.projectOne.Entities.*;

@Service
public class FileService {
    @Autowired
    ContactService contactService;
    @Autowired
    PhoneService phoneService;
    @Autowired
    AddressService addressService;
    @Autowired
    DateService dateService;

    public String handleFileUpload(MultipartFile file) {
        try {
            InputStream is = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = bufferedReader.readLine();
            int count = 0;
            while (line != null) {
                String[] values = line.split(",", -1);
                // System.out.println(line);
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].trim();
                }
                if (values[1].length() > 0) {
                    Contact c = new Contact(values[1], values[2], values[3]);
                    c = contactService.createContact(c);
                    if (values[4].length() >= 10) {
                        PhoneEntity p = new PhoneEntity("Home", values[4].substring(0, 3), values[4].substring(4));
                        phoneService.addPhone(c.getContactId(), p);
                    }
                    if (values[5].length() >= 10) {
                        PhoneEntity p = new PhoneEntity("Cell", values[5].substring(0, 3), values[5].substring(4));
                        phoneService.addPhone(c.getContactId(), p);
                    }
                    if (values[6].length() > 0) {
                        AddressEntity a = new AddressEntity("Home", values[6], values[7], values[8], values[9]);
                        addressService.addAddress(c.getContactId(), a);
                    }
                    if (values[10].length() >= 10) {
                        PhoneEntity p = new PhoneEntity("Work", values[10].substring(0, 3), values[10].substring(4));
                        phoneService.addPhone(c.getContactId(), p);
                    }
                    if (values[11].length() > 0) {
                        AddressEntity a = new AddressEntity("Work", values[11], values[12], values[13], values[14]);
                        addressService.addAddress(c.getContactId(), a);
                    }
                    if (values.length >= 16) {
                        String[] dateComps = values[15].split("-");
                        if (dateComps.length > 2) {
                            String date = dateComps[1] + "/" + dateComps[2] + "/" + dateComps[0];
                            DateEntity d = new DateEntity("Birthdate", new SimpleDateFormat("MM/dd/yyyy").parse(date));
                            dateService.addDate(c.getContactId(), d);
                        }
                    }
                    count++;
                    if (count%100 == 0) System.out.println("Created " + count + " contacts");
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            return "Created " + count + " new contacts";

        } catch(IOException e) {
            System.out.println(e.toString());
            return "error occurred";
        } catch(ParseException e) {
            System.out.println(e.toString());
            return "error occurred";
        }
    }
}