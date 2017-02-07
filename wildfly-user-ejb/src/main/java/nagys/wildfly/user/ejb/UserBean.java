/**
 * 
 */
package nagys.wildfly.user.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.User;
import nagys.wildfly.user.common.IUser;

/**
 * @author nagys
 *
 */
@Stateless
public class UserBean implements IUser {

	@PersistenceContext(unitName = "wildfly-user-jpa")
	private EntityManager oEntityManager;

	@Override
	public List<User> getAllUsers() {
		CriteriaBuilder builder = oEntityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		Root<User> selection = criteriaQuery.from(User.class);
		criteriaQuery.select(selection).orderBy(builder.asc(selection.get("username")));
		return oEntityManager.createQuery(criteriaQuery).getResultList();
	}
	
	@Override
	public User getUserById(int p_id) {
		User user = (User)oEntityManager.createNamedQuery("User.findByID").setParameter("id", p_id).getSingleResult();
		return user;
	}

	@Override
	public int updateUser(int id,String name) {
		
		User user = getUserById(id);
		user.setUsername(name);
		oEntityManager.merge(user);
		
		return 0;
	}

	

}
