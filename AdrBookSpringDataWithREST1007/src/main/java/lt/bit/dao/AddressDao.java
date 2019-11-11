package lt.bit.dao;

import java.util.List;
import lt.bit.data.Address;
import lt.bit.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressDao extends JpaRepository<Address, Integer> {
    
    @Query(name = "Address.byAddress")
    public List<Address> orderedByAddress(@Param("id") Integer id);
    
    @Query(name = "Address.byCity")
    public List<Address> orderedByCity(@Param("id") Integer id);
    
    @Query("select a from Address a where a.person.id = :id and (a.address like :nameA or a.city like :nameA)")
    public List<Address> filteredCity(@Param("id") Integer id, @Param("nameA") String nameA);
}
