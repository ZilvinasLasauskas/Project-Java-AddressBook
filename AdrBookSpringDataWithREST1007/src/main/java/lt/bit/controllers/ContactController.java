package lt.bit.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lt.bit.dao.ContactDao;
import lt.bit.dao.PersonDao;
import lt.bit.data.Contact;
import lt.bit.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
    
    @Autowired
    private ContactDao contactDao;
    @Autowired
    private PersonDao personDao;
    
    @GetMapping("/personContactList")
    public ModelAndView personContactList(
            HttpServletRequest request,
            @RequestParam(value = "id") Integer id) {
        Person p = personDao.getOne(id);
        List<Contact> contactList = p.getContacts();
        ModelAndView maw = new ModelAndView("personContactList");
        maw.addObject("contactList", contactList);
        maw.addObject("personId", id);
        return maw;
    }
    
    @Transactional
    @GetMapping("/deleteContact")  
    public String deleteContact(HttpServletRequest request,
            @RequestParam(value="id") Integer id) {
        Contact conn = contactDao.getOne(id);
        contactDao.deleteById(id);
        return "redirect:/personContactList?id=" + conn.getPerson().getId(); 
    }
    
    @GetMapping("/editContact")
    public ModelAndView editContact(HttpServletRequest request,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "personId", required = false) Integer personId) {
        ModelAndView maw = new ModelAndView("editContact");
        if (id != null) {
            Contact c = contactDao.getOne(id);
            if (c != null) {
                maw.addObject("contact", c);
                maw.addObject("personId", c.getPerson().getId());
            } else {
                maw.addObject("personId", personId);
            }
        } else {
            maw.addObject("personId", personId);
        }
        return maw;
    }
    
    @Transactional
    @PostMapping("/saveContact")
    public String saveContact(HttpServletRequest request,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "personId", required = false) Integer personId,
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "contact", required = true) String contact
    ) {
        Person p = personDao.getOne(personId);
        Contact c = null;
        if (id != null) {
            c = contactDao.getOne(id);
        }
        if (c == null) {
            c = new Contact();
            c.setPerson(p);
        }
        c.setType(type);
        c.setContact(contact);
        contactDao.save(c);
        return "redirect:/personContactList?id=" + c.getPerson().getId();
    }
}
