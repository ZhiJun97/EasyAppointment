package ejb.session.stateless;

import entity.ServiceProviderEntity;
import java.util.List;
import util.exception.InvalidLoginCredentialException;
import util.exception.ServiceProviderNotFoundException;

/**
 *
 * @author ivanlim
 */
public interface ServiceProviderEntitySessionBeanRemote{
    
    public Long createServiceProviderEntity(ServiceProviderEntity newServiceProviderEntity);
    
    public ServiceProviderEntity retrieveServiceProviderByUniqueIdNumber(Long uniqueIdNumber) throws ServiceProviderNotFoundException ;
    
    public ServiceProviderEntity retrieveServiceProviderByEmail(String emailAddress) throws ServiceProviderNotFoundException;
    
    public ServiceProviderEntity serviceProviderLogin(String emailAddress, String password) throws InvalidLoginCredentialException;

    public void updateServiceProviderEntity(ServiceProviderEntity serviceProviderEntity);
    
    public void deleteServiceProviderEntity(Long uniqueIdNumber) throws ServiceProviderNotFoundException;
    
    public List<ServiceProviderEntity> retrieveAllServiceProvider();
    
    public List<ServiceProviderEntity> retrievePendingServiceProviders();
}
