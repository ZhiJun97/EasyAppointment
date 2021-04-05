package ejb.session.stateless;

import entity.AdminEntity;
import util.exception.AdminNotFoundException;
import util.exception.CategoryNotFoundException;
import util.exception.InvalidLoginCredentialException;


/**
 *
 * @author zhijun
 */
public interface AdminEntitySessionBeanLocal {

    public Long createAdminEntity(AdminEntity adminEntity);

    public AdminEntity retrieveAdminByEmail(String email) throws AdminNotFoundException;

    public AdminEntity retrieveAdminById(Long id) throws AdminNotFoundException;

    public AdminEntity adminLogin(String email, String password) throws InvalidLoginCredentialException;

    public void updateAdmin(AdminEntity adminEntity);

    public void deleteAdmin(Long id);

    public void addNewBusinessCategory(String businessCategory) throws AdminNotFoundException;

    public void removeBusinessCategory(String businessCategory) throws AdminNotFoundException, CategoryNotFoundException;
    
}
