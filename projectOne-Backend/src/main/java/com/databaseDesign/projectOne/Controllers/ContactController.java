package com.databaseDesign.projectOne.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PathVariable;

import com.databaseDesign.projectOne.Services.ContactService;
import com.databaseDesign.projectOne.Services.FileService;
import com.databaseDesign.projectOne.Views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.databaseDesign.projectOne.Entities.AddressEntity;
import com.databaseDesign.projectOne.Entities.Contact;
import com.databaseDesign.projectOne.Entities.DateEntity;
import com.databaseDesign.projectOne.Entities.PhoneEntity;


@Controller // This means that this class is a Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="/app") // This means URL's start with /demo (after Application path)
public class ContactController {
  @Autowired // This means to get the bean called contactRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private ContactService contactService;

  @Autowired
  private FileService fileService; 

  @JsonView(Views.Public.class)
  @GetMapping(path="/contacts")
  public @ResponseBody Iterable<Contact> getAllContacts() {
    // This returns a JSON or XML with the users
    return contactService.getAllContacts();
  }

  @JsonView(Views.Internal.class)
  @GetMapping(value="/contact/{id}")
  public @ResponseBody Contact putMethodName(@PathVariable("id") Integer id) {
      // This returns the JSON with contact matching the given id
      Contact contact = contactService.getContactById(id);
      if (contact == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found");
      return contact;
  }

  @GetMapping(path="/contact/{id}/addresses")
    public @ResponseBody Iterable<AddressEntity> getAllAddresses(@PathVariable("id") Integer id) {
        return contactService.getAllAddresses(id);
  }

  @GetMapping(path="/contact/{id}/phones")
  public @ResponseBody Iterable<PhoneEntity> getAllPhones(@PathVariable("id") Integer id) {
      return contactService.getAllPhones(id);
  }

  @GetMapping(path = "/contact/{id}/dates")
    public @ResponseBody Iterable<DateEntity> getAllDates(@PathVariable("id") Integer id) {
        return contactService.getAllDates(id);
  }
  @JsonView(Views.Public.class)
  @GetMapping(value="/contacts/search")
  public @ResponseBody Iterable<Contact> getContactsBySearch(@RequestParam("search") String searchString) {
    // System.out.println("Search string: " + searchString);
    return contactService.getContactBySearch(searchString);
  }

  @PostMapping(path="/contact/add") // Map ONLY POST Requests
  public @ResponseBody Contact addNewContact (@RequestBody Contact newContact) {
    // @ResponseBody means the returned String is the response, not a view namHttpStatus.NOT_FOUND, "Contact not found"
    // @RequestParam means it is a parameter from the GET or POST request
    if (newContact.getfName() == null || newContact.getfName().length() == 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid firstname");
    }
    return contactService.createContact(newContact);
  }

  @PostMapping(path="/contact/add/file") 
  public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file) {
		return fileService.handleFileUpload(file);
	}

  @PutMapping(path="/contact/{id}")
  public @ResponseBody Contact modifyContact (@PathVariable("id") Integer id
      , @RequestBody Contact modifiedContact) {
      modifiedContact.setContactId(id);
      modifiedContact = contactService.modifyContact(modifiedContact);
      if (modifiedContact == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found");
      return modifiedContact;
  }

  @DeleteMapping(path="/contact/{id}") // Map ONLY DELETE Requests
  public @ResponseBody String deleteContact(@PathVariable("id") Integer id) {
    return contactService.deleteContact(id);
  }

}