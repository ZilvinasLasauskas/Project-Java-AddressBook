/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.controllers;

import java.util.List;
import lt.bit.dao.ContactDao;
import lt.bit.dao.PersonDao;
import lt.bit.data.Contact;
import lt.bit.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zills
 */
@RestController
public class ContactRESTController {
    
    @Autowired
    private PersonDao personDao;
    @Autowired
    private ContactDao contactDao;
    
    @GetMapping(value = "rest/person/" + "{id}" + "/contact", produces = "application/json")
    public List<Contact> contactList(@PathVariable (value="id") Integer personId) {
        Person p = personDao.getOne(personId);
        List<Contact> contactList = p.getContacts();
        return contactList;
    }
    
    @GetMapping(value = "rest/person/" + "{personId}" + "/contact/" + "{contactId}", produces = "application/json", consumes = "application/json")
    public Contact getContact (@PathVariable (value="contactId") Integer contactId) {
        return contactDao.getOne(contactId);
    }
    
    @PostMapping(value = "rest/person/" + "{id}" + "/contact", produces = "application/json", consumes = "application/json")
    @Transactional
    public Contact addContact (@PathVariable (value="id") Integer personId, @RequestBody Contact c) {
        Person p = personDao.getOne(personId);
        c.setPerson(p);
        contactDao.save(c);
        return c;
    }
    
    @PutMapping(value = "rest/person/" + "{personId}" + "/contact/" + "{contactId}", produces = "application/json", consumes = "application/json")
    @Transactional
    public Contact updateContact (@PathVariable (value="personId") Integer personId, @RequestBody Contact c) {
        c.setId(personId);
        contactDao.save(c);
        return c;
    }
    
    @DeleteMapping(value = "rest/person/" + "{personId}" + "/contact/" + "{contactId}", produces = "application/json", consumes = "application/json")
    @Transactional
    public void deleteContact(@PathVariable(value="contactId") Integer contactId) {
        contactDao.deleteById(contactId);
    }
}
