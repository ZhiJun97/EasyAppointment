/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CustomerEntity;
import util.exception.CustomerNotFoundException;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author ryanl
 */
public interface CustomerEntitySessionBeanLocal {
   
    public Long createCustomerEntity(CustomerEntity newCustomerEntity);
    public CustomerEntity retrieveCustomerById(Long Id) throws CustomerNotFoundException;
    public CustomerEntity retrieveCustomerByEmail(String emailAddress) throws CustomerNotFoundException;
    public CustomerEntity customerLogin(String emailAddress, String password) throws InvalidLoginCredentialException;
    public void updateCustomerEntity(CustomerEntity customerEntity);
    public void deleteCustomerEntity(Long Id) throws CustomerNotFoundException;

}
