package ejb.session.singleton;

import ejb.session.stateless.AdminEntitySessionBeanLocal;
import ejb.session.stateless.AppointmentEntitySessionBeanLocal;
import ejb.session.stateless.CustomerEntitySessionBeanLocal;
import ejb.session.stateless.ServiceProviderEntitySessionBeanLocal;
import entity.AdminEntity;
import entity.AppointmentEntity;
import entity.CustomerEntity;
import entity.ServiceProviderEntity;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import util.exception.AdminNotFoundException;

@Singleton
@LocalBean
@Startup
public class DataInitSessionBean {

    @EJB(name = "AdminEntitySessionBeanLocal")
    private AdminEntitySessionBeanLocal adminEntitySessionBeanLocal;
    @EJB(name = "AppointmentEntitySessionBeanLocal")
    private AppointmentEntitySessionBeanLocal appointmentEntitySessionBeanLocal;
    @EJB(name = "ServiceProviderEntitySessionBeanLocal")
    private ServiceProviderEntitySessionBeanLocal serviceProviderEntitySessionBeanLocal;
    @EJB(name = "CustomerEntitySessionBeanLocal")
    private CustomerEntitySessionBeanLocal customerEntitySessionBeanLocal;
    
    public DataInitSessionBean()
    {
    }
    
    @PostConstruct
    public void postConstruct()
    {
        try
        {
            adminEntitySessionBeanLocal.retrieveAdminByEmail("admin@easyadm.com");
        }
        catch(AdminNotFoundException ex)
        {
            initializeData();
        }
    }
    
    
    
    private void initializeData()
    {  
        adminEntitySessionBeanLocal.createAdminEntity(new AdminEntity("admin@easyadm.com", "password"));
        
        serviceProviderEntitySessionBeanLocal.createServiceProviderEntity(new ServiceProviderEntity("A1101", "Restaurant", "John's Special", "Airport Road, Avenue 6", "Singapore", "john@easysp.com", "91234567", "password"));

        customerEntitySessionBeanLocal.createCustomerEntity(new CustomerEntity("S1234567F", "password", "Adam", "Tan", "Male", 23, "82345678", "Yishun Ring Road", "Singapore", "adam@easycust.com"));
    }
}