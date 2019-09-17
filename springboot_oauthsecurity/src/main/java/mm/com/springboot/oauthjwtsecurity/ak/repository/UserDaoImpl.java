package mm.com.springboot.oauthjwtsecurity.ak.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mm.com.springboot.oauthjwtsecurity.ak.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserCustomDao{

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findPaginate(int curPage, int pageSize) {
		
		String q ="select * from user";
		Query query = em.createQuery(q);
		return query.getResultList();
		
		
	}
}
