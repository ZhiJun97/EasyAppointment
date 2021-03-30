/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyappointmentclient;

import ejb.session.stateless.AdminEntitySessionBeanRemote;
import ejb.session.stateless.CustomerEntitySessionBeanRemote;
import ejb.session.stateless.ServiceProviderEntitySessionBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author zhijun
 */
public class Main {

    @EJB
    private static CustomerEntitySessionBeanRemote customerEntitySessionBeanRemote;
    @EJB
    private static ServiceProviderEntitySessionBeanRemote serviceProviderEntitySessionBeanRemote;
    @EJB
    private static AdminEntitySessionBeanRemote adminEntitySessionBeanRemote;
    
    public static void main(String[] args) {
        Startup startup = new Startup(customerEntitySessionBeanRemote, serviceProviderEntitySessionBeanRemote, adminEntitySessionBeanRemote);
        startup.start();
    }
    
}
