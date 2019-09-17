package mm.com.springboot.oauthjwtsecurity.ak.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mm.com.springboot.oauthjwtsecurity.ak.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);

}
