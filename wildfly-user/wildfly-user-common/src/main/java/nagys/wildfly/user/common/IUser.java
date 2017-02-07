/**
 * 
 */
package nagys.wildfly.user.common;

import java.util.List;

import model.User;

/**
 * @author nagys
 *
 */
public interface IUser {
	public static final String jndiNAME = "java:global/wildfly-user-ear-0.0.1-SNAPSHOT/wildfly-user-ejb-0.0.1-SNAPSHOT/UserBean";

	public List<User> getAllUsers();

}
