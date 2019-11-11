package lt.bit.controllers;

import java.util.List;
import lt.bit.dao.AddressDao;
import lt.bit.dao.PersonDao;
import lt.bit.data.Address;
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

@RestController
public class AddressRESTController {
    
    @Autowired
    private PersonDao personDao;
    @Autowired
    private AddressDao addressDao;
    
    @GetMapping(value = "rest/person/" + "{id}" + "/address", produces = "application/json")
    public List<Address> addressList(@PathVariable (value="id") Integer personId) {
        Person p = personDao.getOne(personId);
        List<Address> addressList = p.getAddresses();
        return addressList;
    }
    
    @GetMapping(value = "rest/person/" + "{personId}" + "/address/" + "{addressId}", produces = "application/json", consumes = "application/json")
    public Address getAddress (@PathVariable (value="addressId") Integer addressId) {
        return addressDao.getOne(addressId);
    }
    
    @PostMapping(value = "rest/person/" + "{id}" + "/address", produces = "application/json", consumes = "application/json")
    @Transactional
    public Address addAddress (@PathVariable (value="id") Integer personId, @RequestBody Address a) {
        Person p = personDao.getOne(personId);
        a.setPerson(p);
        addressDao.save(a);
        return a;
    }
    
    @PutMapping(value = "rest/person/" + "{personId}" + "/address/" + "{addressId}", produces = "application/json", consumes = "application/json")
    @Transactional
    public Address updateAddress (@PathVariable (value="personId") Integer personId, @RequestBody Address a) {
        a.setId(personId);
        addressDao.save(a);
        return a;
    }
    
    @DeleteMapping(value = "rest/person/" + "{personId}" + "/address/" + "{addressId}", produces = "application/json", consumes = "application/json")
    @Transactional
    public void deleteAddress(@PathVariable(value="addressId") Integer addressId) {
        addressDao.deleteById(addressId);
    }
}
