package lt.bit.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lt.bit.dao.AddressDao;
import lt.bit.dao.PersonDao;
import lt.bit.data.Address;
import lt.bit.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddressController {

    @Autowired
    private AddressDao addressDao;
    @Autowired
    private PersonDao personDao;

    @GetMapping("/personAddressList")
    public ModelAndView personAddressList(
            HttpServletRequest request,
            @RequestParam(value = "id") Integer id) {
        Person p = personDao.getOne(id);
        List<Address> addressList = p.getAddresses();
        ModelAndView maw = new ModelAndView("personAddressList");
        maw.addObject("addressList", addressList);
        maw.addObject("personId", id);
        return maw;
    }

    @Transactional
    @GetMapping("/deleteAddress")
    public String delete(HttpServletRequest request,
            @RequestParam(value = "id") Integer id) {
        Address a = addressDao.getOne(id);
        addressDao.deleteById(id);
        return "redirect:/personAddressList?id=" + a.getPerson().getId();
    }

    @GetMapping("/editAddress")
    public ModelAndView edit(HttpServletRequest request,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "personId", required = false) Integer personId){
        ModelAndView maw = new ModelAndView("editAddress");
        if (id != null) {
            Address a = addressDao.getOne(id);
            if (a != null) {
                maw.addObject("address", a);
                maw.addObject("personId", a.getPerson().getId());
            }else{
                maw.addObject("personId", personId);
            }
        }else{
            maw.addObject("personId", personId);
        }
        return maw;
    }

    @Transactional
    @PostMapping("/saveAddress")
    public String save(HttpServletRequest request,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "personId", required = false) Integer personId,
            @RequestParam(value = "address", required = true) String address,
            @RequestParam(value = "city", required = true) String city,
            @RequestParam(value = "postalCode", required = false) String postalCode
    ) {
        Person p = personDao.getOne(personId);
        Address a = null;
        if (id != null) {
            a = addressDao.getOne(id);
        }
        if (a == null) {
            a = new Address();
            a.setPerson(p);
        }
        a.setAddress(address);
        a.setCity(city);
        a.setPostalCode(postalCode);
        addressDao.save(a);
        return "redirect:/personAddressList?id=" + a.getPerson().getId();
    }
}
