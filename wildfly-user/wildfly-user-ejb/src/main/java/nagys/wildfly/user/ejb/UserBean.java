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

}
