/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.service;

import java.util.List;
import lt.bit.dao.ContactDao;
import lt.bit.dao.PersonDao;
import lt.bit.data.Contact;
import lt.bit.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author zills
 */
@Component
public class ContactService {
    @Autowired
    private ContactDao contactDao;
    
    public List<Contact> findAll(){
        return contactDao.findAll();
    } 
}
