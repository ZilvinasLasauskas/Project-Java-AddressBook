package lt.bit.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import lt.bit.dao.AddressDao;
import lt.bit.dao.ContactDao;
import lt.bit.dao.PersonDao;
import lt.bit.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonController {

    @Autowired
    private PersonDao personDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private ContactDao contactDao;

    @GetMapping("/")
    public ModelAndView list(HttpServletRequest request) {
        List<Person> list = personDao.findAll();
        ModelAndView maw = new ModelAndView("personList");
        maw.addObject("list", list);
        return maw;
    }

    @Transactional
    @GetMapping("/delete")
    public String delete(HttpServletRequest request,
            @RequestParam(value = "id") Integer id) {
        personDao.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public ModelAndView edit(HttpServletRequest request,
            @RequestParam(value = "id", required = false) Integer id) {
        ModelAndView maw = new ModelAndView("editPerson");
        if (id != null) {
            EntityManager em = (EntityManager) request.getAttribute("em");
            Person p = personDao.getOne(id);
            if (p != null) {
                maw.addObject("person", p);
            }
        }
        return maw;
    }

    @Transactional
    @PostMapping("/save")
    public String save(HttpServletRequest request,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "fn", required = true) String fn,
            @RequestParam(value = "ln", required = true) String ln,
            @RequestParam(value = "bd", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date bd,
            @RequestParam(value = "salary", required = false) BigDecimal salary
    ) {
        Person p = null;
        if (id != null) {
            p = personDao.getOne(id);
        }
        if (p == null) {
            p = new Person();
        }
        p.setFirstName(fn);
        p.setLastName(ln);
        p.setBirthDate(bd);
        p.setSalary(salary);
        personDao.save(p);
        return "redirect:/";
    }
}
