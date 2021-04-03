package ejb.session.stateless;

import entity.ServiceProviderEntity;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.enumeration.StatusEnum;
import util.exception.InvalidLoginCredentialException;
import util.exception.ServiceProviderNotFoundException;
import util.exception.ServiceProviderApproveException;
import util.exception.ServiceProviderBlockedException;

/**
 *
 * @author ivanlim
 */

@Stateless
@Local(ServiceProviderEntitySessionBeanLocal.class)
@Remote(ServiceProviderEntitySessionBeanRemote.class)
public class ServiceProviderEntitySessionBean implements ServiceProviderEntitySessionBeanRemote, ServiceProviderEntitySessionBeanLocal {
    @PersistenceContext(unitName = "EasyAppointment-ejbPU")
    private EntityManager em;

    @Override
    public Long createServiceProviderEntity(ServiceProviderEntity newServiceProviderEntity) {
        em.persist(newServiceProviderEntity);
        em.flush();
        
        return newServiceProviderEntity.getUniqueIdNumber();
    }
    
    
    @Override
    public ServiceProviderEntity retrieveServiceProviderByUniqueIdNumber(Long uniqueIdNumber) throws ServiceProviderNotFoundException 
    {
        ServiceProviderEntity serviceProviderEntity = em.find(ServiceProviderEntity.class, uniqueIdNumber);
        
        if(serviceProviderEntity != null)
        {
            //serviceProviderEntity.getAppointmentEntity().size();
            return serviceProviderEntity;
        }
        else
        {
            throw new ServiceProviderNotFoundException("Service Provider ID Number " + uniqueIdNumber + " does not exist!");
        }
    }
    
    @Override
    public ServiceProviderEntity retrieveServiceProviderByEmail(String email) throws ServiceProviderNotFoundException
    {
        Query query = em.createQuery("SELECT s FROM ServiceProviderEntity s WHERE s.email = :inEmail");
        query.setParameter("inEmail", email);
        
        try
        {
            return (ServiceProviderEntity)query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new ServiceProviderNotFoundException("Service Provider Email " + email + " does not exist!");
        }
    }
    
    @Override
    public List<ServiceProviderEntity> retrieveAllServiceProvider() {
        Query query = em.createQuery("SELECT s FROM ServiceProviderEntity s");
        
        return query.getResultList();
    }
    
    @Override
    public ServiceProviderEntity serviceProviderLogin(String emailAddress, String password) throws InvalidLoginCredentialException
    {
        try
        {
            ServiceProviderEntity serviceProviderEntity = retrieveServiceProviderByEmail(emailAddress);
            if(serviceProviderEntity.getPassword().equals(password))
            {              
                //Query query = em.createQuery("SELECT a FROM AppointmentEntity a WHERE a.email = :inEmail");
                //query.setParameter("inEmail", email);
                //Query apptlist = em.createQuery("SELECT a FROM AppointmentEntity a WHERE a.serviceProviderEntity.email = 'john@easysp.com'");
                //serviceProviderEntity.setAppointmentEntity(apptlist.getResultList());
                //updateServiceProviderEntity(serviceProviderEntity);
                return serviceProviderEntity;
            }
            else
            {
                throw new InvalidLoginCredentialException("Email does not exist or invalid password!");
            }
        }
        catch(ServiceProviderNotFoundException ex)
        {
            throw new InvalidLoginCredentialException("Email does not exist or invalid password!");
        }
    }
    
    @Override
    public List<ServiceProviderEntity> retrievePendingServiceProviders() {
        Query query = em.createQuery("SELECT s FROM ServiceProviderEntity s WHERE s.statusEnum = util.enumeration.StatusEnum.PENDING");
        return query.getResultList();
    }
    
    public List<ServiceProviderEntity> retrieveApprovedServiceProviders() {
        Query query = em.createQuery("SELECT s FROM ServiceProviderEntity s WHERE s.statusEnum = util.enumeration.StatusEnum.APPROVE");
        return query.getResultList();
    }
//    
    @Override
    public void approveServiceProvider(ServiceProviderEntity serviceProviderEntity) throws ServiceProviderNotFoundException, ServiceProviderApproveException {
            
        ServiceProviderEntity serviceProviderToApprove = retrieveServiceProviderByUniqueIdNumber(serviceProviderEntity.getServiceProviderId());
        if (serviceProviderToApprove.getStatusEnum() == StatusEnum.APPROVE) {
            throw new ServiceProviderApproveException (serviceProviderEntity.getName() + " is already approved!");
        }
        serviceProviderToApprove.setStatusEnum(StatusEnum.APPROVE);
    }   

    @Override
    public void blockServiceProvider(ServiceProviderEntity serviceProviderEntity) throws ServiceProviderNotFoundException, ServiceProviderBlockedException {
        
        ServiceProviderEntity serviceProviderToBlock = retrieveServiceProviderByUniqueIdNumber(serviceProviderEntity.getServiceProviderId());
        if (serviceProviderToBlock.getStatusEnum() == StatusEnum.BLOCK) {
            throw new ServiceProviderBlockedException (serviceProviderEntity.getName() + " is already blocked!");
        }
        serviceProviderToBlock.setStatusEnum(StatusEnum.BLOCK);
    }
    @Override
    public void updateServiceProviderEntity(ServiceProviderEntity serviceProviderEntity) {
        //incomplete, have to do careful update and merging
        em.merge(serviceProviderEntity);
    }
    
    @Override
    public void deleteServiceProviderEntity(Long uniqueIdNumber) throws ServiceProviderNotFoundException {
        try {
            ServiceProviderEntity serviceProviderEntity = retrieveServiceProviderByUniqueIdNumber(uniqueIdNumber);
            em.remove(serviceProviderEntity);
        } catch (ServiceProviderNotFoundException ex) {
            System.out.println("Unique ID Number " + uniqueIdNumber + " does not exist!" + ex.getMessage());
        }
    }
}
