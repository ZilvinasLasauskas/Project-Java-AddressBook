package lt.bit.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lt.bit.dao.AddressDao;
import lt.bit.dao.PersonDao;
import lt.bit.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonController {

    @Autowired
    PasswordEncoder pe;

    @Autowired
    private PersonDao personDao;
    @Autowired
    private AddressDao addressDao;

    @RequestMapping(path = "pass", method = RequestMethod.GET)
    @ResponseBody
    public String pass(@RequestParam(name = "pass", required = false) String pass) {
        return pe.encode(pass);
    }

    @GetMapping("/")
    public ModelAndView list(HttpServletRequest request,
            @RequestParam(value = "ord", required = false) String ord,
            @RequestParam(value = "filter", required = false) String filter) {
        List<Person> list;
        if (filter != null) {
            list = personDao.filtered("%" + filter + "%");
        } else if ("fn".equals(ord)) {
            list = personDao.orderedByFirstName();
        } else if ("ln".equals(ord)) {
            list = personDao.orderedByLastName();
        } else if ("salary".equals(ord)) {
            list = personDao.orderedBySalary();
        } else {
            list = personDao.findAll();
        }
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
