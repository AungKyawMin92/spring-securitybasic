package mm.com.springboot.oauthjwtsecurity.ak.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mm.com.springboot.oauthjwtsecurity.ak.entity.User;
import mm.com.springboot.oauthjwtsecurity.ak.repository.UserCustomDao;
import mm.com.springboot.oauthjwtsecurity.ak.repository.UserDao;


@Service(value = "userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired private UserCustomDao userCustomDao;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		System.out.println("try to login");
		User user = userDao.findByUsername(userId);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public User findOne(long id) {
		return userDao.findById(id).get();
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
    public User save(User user) {
        return userDao.save(user);
    }

	@Override
	public List<User> findPaginate(int curPage, int pageSize) {
		List<User> list = new ArrayList<>();
		userCustomDao.findPaginate(curPage, pageSize).iterator().forEachRemaining(list::add);
		return list;
	
	}
}
