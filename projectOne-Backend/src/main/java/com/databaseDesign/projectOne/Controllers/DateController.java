package com.databaseDesign.projectOne.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import com.databaseDesign.projectOne.Services.DateService;

import com.databaseDesign.projectOne.Entities.DateEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/app")
public class DateController {
    @Autowired
    private DateService dateService;

    @PostMapping(path = "/contact/{id}/date")
    public @ResponseBody DateEntity addDate(@PathVariable("id") Integer id
            ,@RequestBody DateEntity newDate) {
        newDate = dateService.addDate(id, newDate);
        if (newDate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact " + id + " not found");
        }
        return newDate;
    }

    @PutMapping(path="/date/{id}")
    public @ResponseBody DateEntity modifyDate(@PathVariable("id") Integer id
            ,@RequestBody DateEntity modifiedDate) {
        modifiedDate = dateService.modifyDate(id, modifiedDate);
        if (modifiedDate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Date " + id + " not found");
        }
        return modifiedDate;
    }

    @DeleteMapping(path="/date/{id}")
    public @ResponseBody String deleteDate(@PathVariable("id") Integer id) {
        dateService.deleteDate(id);
        return "deleted";
    }

}