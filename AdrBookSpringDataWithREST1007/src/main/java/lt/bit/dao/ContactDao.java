package lt.bit.dao;

import java.util.List;
import lt.bit.data.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactDao extends JpaRepository<Contact, Integer> {
//    @Query(name = "Contact.byType")
//    public List<Contact> orderedByType(@Param("id") Integer id);
//    
//    @Query("select c from Contact c where c.person.id = :id and c.type like :nameC")
//    public List<Contact> filteredType(@Param("id") Integer id, @Param("nameC") String nameC);
    
}
