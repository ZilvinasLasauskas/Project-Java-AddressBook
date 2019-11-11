package lt.bit.dao;

import java.util.List;
import lt.bit.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonDao extends JpaRepository<Person, Integer> {
    @Query(name = "Person.byFirstName")
    public List<Person> orderedByFirstName();
    
    @Query(name = "Person.byLastName")
    public List<Person> orderedByLastName();
}
