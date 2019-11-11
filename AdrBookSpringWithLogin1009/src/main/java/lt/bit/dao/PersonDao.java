package lt.bit.dao;

import java.util.List;
import lt.bit.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonDao extends JpaRepository<Person, Integer> {
    
//    @Query("select p from Person p order by p.firstName")
    @Query(name = "Person.byFirstName")
    public List<Person> orderedByFirstName();
    
//    @Query("select p from Person p order by p.lastName")
    @Query(name = "Person.byLastName")
    public List<Person> orderedByLastName();
    
    @Query(name = "Person.bySalary")
    public List<Person> orderedBySalary();
    
    @Query("select p from Person p where p.lastName like :name or p.firstName like :name")
    public List<Person> filtered(@Param("name") String n);
    
}
