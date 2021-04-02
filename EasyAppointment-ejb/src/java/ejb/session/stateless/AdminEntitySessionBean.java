package ejb.session.stateless;

import entity.AdminEntity;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.AdminNotFoundException;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author zhijun
 */
@Local(AdminEntitySessionBeanLocal.class)
@Remote(AdminEntitySessionBeanRemote.class)
@Stateless
public class AdminEntitySessionBean implements AdminEntitySessionBeanRemote, AdminEntitySessionBeanLocal {

    @PersistenceContext(unitName = "EasyAppointment-ejbPU")
    private EntityManager em;
    
    @Override
    public Long createAdminEntity(AdminEntity adminEntity) {
        em.persist(adminEntity);
        em.flush();
        
        return adminEntity.getAdminId();
    }
    
    @Override
    public AdminEntity retrieveAdminById(Long id) throws AdminNotFoundException {
        AdminEntity adminEntity = em.find(AdminEntity.class, id);
        if (adminEntity != null) {
            return adminEntity;
        } else {
            throw new AdminNotFoundException("Admin id : " + id + " does not exist!");
        }
    }
    
    @Override
    public AdminEntity retrieveAdminByEmail(String email) throws AdminNotFoundException {
        Query query = em.createQuery("SELECT a FROM AdminEntity a WHERE a.email = :inEmail");
        query.setParameter("inEmail", email);
        try {
            return (AdminEntity)query.getSingleResult();
        } catch(NoResultException | NonUniqueResultException ex) {
            throw new AdminNotFoundException("Admin email : " + email + " does not exist!");
        }
    }
    
    @Override
    public AdminEntity adminLogin(String email, String password) throws InvalidLoginCredentialException {
        try {
            AdminEntity adminEntity = retrieveAdminByEmail(email);
            if (adminEntity.getPassword().equals(password)) {
                return adminEntity;
            } else {
                throw new InvalidLoginCredentialException("Wrong password!");
            }
        } catch (AdminNotFoundException ex) {
            throw new InvalidLoginCredentialException("Email does not exist or wrong email has been entered!");
        }
    }
    
    @Override
    public void updateAdmin(AdminEntity adminEntity) {
        em.merge(adminEntity);
    }
    
    @Override
    public void deleteAdmin(Long id) {
        try {
            AdminEntity adminEntity = retrieveAdminById(id);
            em.remove(adminEntity);
        } catch (AdminNotFoundException ex) {
            System.out.println("Error deleting Admin! " + ex.getMessage());
        }
    }
}
