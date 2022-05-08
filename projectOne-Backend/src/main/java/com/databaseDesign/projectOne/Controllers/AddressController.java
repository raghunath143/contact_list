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

import com.databaseDesign.projectOne.Services.AddressService;
import com.databaseDesign.projectOne.Entities.AddressEntity;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/app")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping(path="/contact/{id}/address")
    public @ResponseBody AddressEntity addAddress(@PathVariable("id") Integer id
            ,@RequestBody AddressEntity newAddress) {
        if (newAddress.getAddress().length() == 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address cannot be null");
        return addressService.addAddress(id, newAddress);
    }

    @PutMapping(path="/address/{id}")
    public @ResponseBody AddressEntity modifyAddress(@PathVariable("id") Integer id
            ,@RequestBody AddressEntity modifiedAddress) {
        return addressService.modifyAddress(id, modifiedAddress);
    }

    @DeleteMapping(path="/address/{id}")
    public @ResponseBody String deleteAddress(@PathVariable("id") Integer id) {
        addressService.deleteAddress(id);
        return "deleted";
    }

}