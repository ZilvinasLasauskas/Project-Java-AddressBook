package lt.bit.dao;

import java.util.List;
import lt.bit.data.Address;
import lt.bit.data.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactDao extends JpaRepository<Contact, Integer> {
    
    @Query("select c from Contact c where c.type like :type")
    public List<Contact> filteredType(@Param("type") String type);
    
}
