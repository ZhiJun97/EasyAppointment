/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CustomerEntity;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.CustomerNotFoundException;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author ryanl
 */
@Stateless
@Local(CustomerEntitySessionBeanLocal.class)
@Remote(CustomerEntitySessionBeanRemote.class)
public class CustomerEntitySessionBean implements CustomerEntitySessionBeanRemote, CustomerEntitySessionBeanLocal {

    @PersistenceContext(unitName = "EasyAppointment-ejbPU")
    private EntityManager em;    

    @Override
    public Long createCustomerEntity(CustomerEntity newCustomerEntity) {
        em.persist(newCustomerEntity);
        em.flush();
        
        return newCustomerEntity.getId();
    }
    
    @Override
    public CustomerEntity retrieveCustomerById(Long Id) throws CustomerNotFoundException {
        CustomerEntity customerEntity = em.find(CustomerEntity.class, Id);
        
        if(customerEntity != null) {
            customerEntity.getAppointmentEntity().size();
            return customerEntity;
        }
        else {
            throw new CustomerNotFoundException("Unique ID Number " + Id + " does not exist!");
        }
    }
    
    @Override
    public CustomerEntity retrieveCustomerByEmail(String emailAddress) throws CustomerNotFoundException {
        Query query = em.createQuery("SELECT c FROM CustomerEntity c WHERE c.emailAddress = :inEmailAddress");
        query.setParameter("inEmailAddress", emailAddress);
        
        try {
            return (CustomerEntity)query.getSingleResult();
        } catch(NoResultException | NonUniqueResultException ex) {
            throw new CustomerNotFoundException("Service Provider Email " + emailAddress + " does not exist!");
        }
    }
    
    @Override
    public CustomerEntity loginCustomer(String emailAddress, String password) throws InvalidLoginCredentialException {
        try {
            CustomerEntity customerEntity = retrieveCustomerByEmail(emailAddress);
            if(customerEntity.getPassword().equals(password)) {              
                return customerEntity;
            } else {
                throw new InvalidLoginCredentialException("Email does not exist or invalid password!");
            }
        } catch(CustomerNotFoundException ex) {
            throw new InvalidLoginCredentialException("Email does not exist or invalid password!");
        }
    }

    @Override
    public void updateCustomerEntity(CustomerEntity customerEntity) {
        //incomplete, have to do careful update and merging
        em.merge(customerEntity);
    }
    
    @Override
    public void deleteCustomerEntity(Long Id) throws CustomerNotFoundException {
        try {
            CustomerEntity customerEntity = retrieveCustomerById(Id);
            em.remove(customerEntity);
        } catch (CustomerNotFoundException ex) {
            System.out.println("Unique ID Number " + Id + " does not exist!" + ex.getMessage());
        }
    }
    
}
