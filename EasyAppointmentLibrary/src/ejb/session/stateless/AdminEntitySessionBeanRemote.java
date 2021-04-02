package ejb.session.stateless;

import entity.AdminEntity;
import util.exception.AdminNotFoundException;
import util.exception.InvalidLoginCredentialException;


/**
 *
 * @author zhijun
 */
public interface AdminEntitySessionBeanRemote {
    
    public Long createAdminEntity(AdminEntity adminEntity);

    public AdminEntity retrieveAdminByEmail(String email) throws AdminNotFoundException;

    public AdminEntity retrieveAdminById(Long id) throws AdminNotFoundException;

    public AdminEntity adminLogin(String email, String password) throws InvalidLoginCredentialException;

    public void updateAdmin(AdminEntity adminEntity);

    public void deleteAdmin(Long id);
    
}
