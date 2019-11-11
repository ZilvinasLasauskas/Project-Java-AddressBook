/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.db;

/**
 *
 * @author zills
 */
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import lt.bit.data.Address;
import lt.bit.data.Contact;
import lt.bit.data.Person;

public class DB {

    private static final List<Person> LIST = new ArrayList<>();
    public static final String CONN_URL = "jdbc:mysql://localhost:3306/address_book";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static List<Person> getAll(EntityManager em) {
        Query q = em.createQuery("select p from Person p");
        return q.getResultList();
    }

    public static List<Address> getAllAddresses(EntityManager em) {
        Query q = em.createQuery("select a from Address a");
        return q.getResultList();
    }

    public static List<Contact> getAllContacts(EntityManager em) {
        Query q = em.createQuery("select c from Contact c");
        return q.getResultList();
    }

    public static List<Address> getPersonAddresses(EntityManager em, Integer id) {
        Person p = em.find(Person.class, id);
        return p.getAddresses();
    }

    public static List<Contact> getPersonContacts(EntityManager em, Integer id) {
        Person p = em.find(Person.class, id);
        return p.getContacts();
    }

    public static Person getById(EntityManager em, Integer id) {
        Person p = em.find(Person.class, id);
        return p;
    }

    public static Person getByAddress(EntityManager em, Address a) {
        Address aDb = em.find(Address.class, a.getId());
        return aDb.getPerson();
    }

    public static Address getAddressById(EntityManager em, Integer id) {
        Address a = em.find(Address.class, id);
        return a;
    }

    public static Person getByContact(EntityManager em, Contact c) {
        Contact cDb = em.find(Contact.class, c.getId());
        return cDb.getPerson();
    }

    public static Contact getContactById(EntityManager em, Integer id) {
        Contact c = em.find(Contact.class, id);
        return c;
    }

    public static Person add(EntityManager em, Person p) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(p);
        tx.commit();
        return p;
    }

    public static Address addAddress(EntityManager em, Integer personId, Address a) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Person p = em.find(Person.class, personId);
        a.setPerson(p);
        em.persist(a);
        tx.commit();
        return a;
    }

    public static Contact addContact(EntityManager em, Integer personId, Contact c) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Person p = em.find(Person.class, personId);
        c.setPerson(p);
        em.persist(c);
        tx.commit();
        return c;
    }

    public static Person update(EntityManager em, Person p) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Person pst = em.find(Person.class, p.getId());
        pst.setFirstName(p.getFirstName());
        pst.setLastName(p.getLastName());
        pst.setBirthDate(p.getBirthDate());
        pst.setSalary(p.getSalary());
        em.flush();
        tx.commit();
        return pst;
    }

    public static Address updateAddress(EntityManager em, Address a) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Address ad = em.find(Address.class, a.getId());
        ad.setAddress(a.getAddress());
        ad.setCity(a.getCity());
        ad.setPostalCode(a.getPostalCode());
        em.flush();
        tx.commit();
        return ad;
    }

    public static Contact updateContact(EntityManager em, Contact c) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Contact ct = em.find(Contact.class, c.getId());
        ct.setType(c.getType());
        ct.setContact(c.getContact());
        em.flush();
        tx.commit();
        return ct;
    }

    public static void delete(EntityManager em, Integer id) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Person p = em.find(Person.class, id);
        if (p != null) {
            em.remove(p);
        }
        tx.commit();
    }

    public static void deleteAddress(EntityManager em, Integer id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Address a = em.find(Address.class, id);
        if (a != null) {
            em.remove(a);
        }
        tx.commit();
    }

    public static void deleteContact(EntityManager em, Integer id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Contact c = em.find(Contact.class, id);
        if (c != null) {
            em.remove(c);
        }
        tx.commit();
    }
}
