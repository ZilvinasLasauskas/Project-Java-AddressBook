package lt.bit.dao;

import lt.bit.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDao extends JpaRepository<Person, Integer> {

//    @Query("select p from Person p order by p.firstName")
//    public List<Person> orderedByFirstName();
//    @Query(name = "Person.byFirstName")
//    public List<Person> orderedByFirstName();
//
//    @Query("select p from Person p order by p.lastName")
//    public List<Person> orderedByLastName();
//    @Query(name = "Person.byLastName")
//    public List<Person> orderedByLastName();
//
//    @Query("select p from Person p where by p.lastName like :name or p.firstName like :name")
//    public List<Person> filtered(@Param("name") String name);
}
