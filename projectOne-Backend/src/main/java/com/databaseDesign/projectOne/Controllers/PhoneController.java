package com.databaseDesign.projectOne.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.databaseDesign.projectOne.Services.PhoneService;
import com.databaseDesign.projectOne.Entities.PhoneEntity;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/app")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @PostMapping(path="/contact/{id}/phone")
    public @ResponseBody PhoneEntity addPhone(@PathVariable("id") Integer id
                    ,@RequestBody PhoneEntity newPhone) {
        if (newPhone.getNumber().length() < 7) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid phone number");
        newPhone = phoneService.addPhone(id, newPhone);
        if (newPhone == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact " + id + " not found");
        return newPhone;
    }

    @PutMapping(path="/phone/{id}")
    public @ResponseBody PhoneEntity modifyPhone(@PathVariable("id") Integer id
                    ,@RequestBody PhoneEntity modifiedPhone) {
        if (modifiedPhone.getNumber().length() < 7) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid phone number");
        modifiedPhone = phoneService.modifyPhone(id, modifiedPhone);
        if (modifiedPhone == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone " + id + " not found");
        }
        return modifiedPhone;
    }

    @DeleteMapping(path="/phone/{id}")
    public @ResponseBody String deletePhone(@PathVariable("id") Integer id) {
        phoneService.deletePhone(id);
        return "deleted";
    }
}