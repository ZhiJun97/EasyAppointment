/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.ws;

import ejb.session.stateless.ServiceProviderEntitySessionBeanLocal;
import entity.ServiceProviderEntity;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author ivanlim
 */
@WebService(serviceName = "ServiceProviderEntityWebService")
@Stateless()
public class ServiceProviderEntityWebService {

    @EJB
    private ServiceProviderEntitySessionBeanLocal serviceProviderSBLocal;
    
    @WebMethod
    public Long createServiceProviderEntity(@WebParam String businessRegNumber,@WebParam String businessCategory,@WebParam String name,@WebParam String address,@WebParam String city,@WebParam String email,@WebParam String phone,@WebParam String password) {
        ServiceProviderEntity newServiceProviderEntity = new ServiceProviderEntity();
        
        newServiceProviderEntity.setBusinessRegNumber(businessRegNumber);
        newServiceProviderEntity.setBusinessCategory(businessCategory);
        newServiceProviderEntity.setName(name);
        newServiceProviderEntity.setAddress(address);
        newServiceProviderEntity.setCity(city);
        newServiceProviderEntity.setEmail(email);
        newServiceProviderEntity.setPhone(phone);
        newServiceProviderEntity.setPassword(password);
        
        return serviceProviderSBLocal.createServiceProviderEntity(newServiceProviderEntity);
    }
}

