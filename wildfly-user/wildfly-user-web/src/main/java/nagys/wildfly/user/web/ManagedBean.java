/**
 * 
 */
package nagys.wildfly.user.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.User;
import nagys.wildfly.user.common.IUser;

/**
 * @author nagys
 *
 */
@Named("user")
@ApplicationScoped
public class ManagedBean implements Serializable, IUser {

	private static final long serialVersionUID = 3552689775892796975L;
	private IUser oUser = null;

	private IUser getUserBean() {
		if (oUser == null) {
			try {
				InitialContext jndi = new InitialContext();
				oUser = (IUser) jndi.lookup(IUser.jndiNAME);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return oUser;
	}
	
	public String kiir(){
		return "hello";
	}

	@Override
	public List<User> getAllUsers() {
		return getUserBean().getAllUsers();
	}
	public static void main(String args[]){
		ManagedBean bean = new ManagedBean();
		System.out.println(bean.getAllUsers());
	}

}
