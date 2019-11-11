package lt.bit.dao;

import java.util.List;
import lt.bit.data.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersDAO extends JpaRepository<Users, Integer> {
    
    @Query("select u from Users u where u.username = :name")
    public List<Users> findByName (@Param("name") String name);
    
}
