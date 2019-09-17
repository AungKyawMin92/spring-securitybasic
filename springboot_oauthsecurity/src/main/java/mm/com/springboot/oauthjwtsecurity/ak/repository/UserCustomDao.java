package mm.com.springboot.oauthjwtsecurity.ak.repository;

import java.util.List;

import mm.com.springboot.oauthjwtsecurity.ak.entity.User;

public interface UserCustomDao {

	List<User> findPaginate(int curPage, int pageSize);

}
