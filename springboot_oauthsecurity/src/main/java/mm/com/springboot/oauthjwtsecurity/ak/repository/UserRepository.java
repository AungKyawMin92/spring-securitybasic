package mm.com.springboot.oauthjwtsecurity.ak.repository;

import org.springframework.data.repository.CrudRepository;

import mm.com.springboot.oauthjwtsecurity.ak.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
