package lt.bit.dao;

import java.util.List;
import lt.bit.data.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressDao extends JpaRepository<Address, Integer> {
    
    @Query("select a from Address a where a.city like :city")
    public List<Address> filteredCity(@Param("city") String city);
    
}
