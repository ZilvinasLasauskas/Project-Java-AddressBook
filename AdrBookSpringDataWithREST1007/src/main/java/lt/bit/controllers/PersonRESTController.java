/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.controllers;

import java.util.List;
import lt.bit.dao.PersonDao;
import lt.bit.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zills
 */
@RestController
public class PersonRESTController {

    @Autowired
    private PersonDao personDao;
    
    @GetMapping(value = "/rest/person", produces = "application/json")
    public List<Person> personList() {
        return personDao.findAll();
    }
    
    @GetMapping(value = "/rest/person/{id}", produces = "application/json", consumes = "application/json")
    public Person getPerson (@PathVariable (value="id") Integer personId) {
        return personDao.getOne(personId);
    }
    
    @PostMapping(value = "/rest/person", produces = "application/json", consumes = "application/json")
    @Transactional
    public Person addPerson (@RequestBody Person p) {
        p.setId(null);
        personDao.save(p);
        return p;
    }
    
    @PutMapping(value = "/rest/person/{id}", produces = "application/json", consumes = "application/json")
    @Transactional
    public Person updatePerson (@PathVariable (value="id") Integer personId, @RequestBody Person p) {
        personDao.save(p);
        return p;
    }
    
    @DeleteMapping(value = "/rest/person/{id}", produces = "application/json", consumes = "application/json")
    @Transactional
    public void deletePerson(@PathVariable(value="id") Integer id) {
        personDao.deleteById(id);
    }
    
}
