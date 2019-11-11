/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit;

import java.util.List;
import lt.bit.dao.PersonDao;
import lt.bit.data.Contact;
import lt.bit.data.Person;
import lt.bit.service.ContactService;
import lt.bit.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author zills
 */
public class Run {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        PersonService ps = ac.getBean(PersonService.class);
        List <Person> list = ps.findAll();
        List <Person> listO = ps.orderedByFirstName();
        
        ContactService cnts = ac.getBean(ContactService.class);
        List <Contact> listC = cnts.findAll();
        
        for (Person person : list){
            System.out.println(person.getFirstName() + " " + person.getLastName() + " " + person.getBirthDate());
        }
        System.out.println("------------------------------------------------------");
        
        for (Person person : listO){
            System.out.println(person.getId()+ " " + person.getFirstName());
        }
        System.out.println("------------------------------------------------------");
        
        
        
        
        for (Contact contact : listC){
            System.out.println(contact.getPersonId().getFirstName()+ " : " + contact.getType()+ " " + contact.getContact());
        }
        System.out.println("------------------------------------------------------");
    }
}
