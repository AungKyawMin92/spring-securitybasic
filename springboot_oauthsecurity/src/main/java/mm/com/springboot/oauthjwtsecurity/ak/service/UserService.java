package mm.com.springboot.oauthjwtsecurity.ak.service;

import java.util.List;

import mm.com.springboot.oauthjwtsecurity.ak.entity.User;

public interface UserService {

    User save(User user);
    List<User> findAll();
    User findOne(long id);
    void delete(long id);
	List<User> findPaginate(int curPage, int pageSize);
}
