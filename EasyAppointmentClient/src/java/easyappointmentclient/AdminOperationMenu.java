/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyappointmentclient;

import ejb.session.stateless.CustomerEntitySessionBeanRemote;
import ejb.session.stateless.ServiceProviderEntitySessionBeanRemote;
import entity.AdminEntity;
import entity.AppointmentEntity;
import entity.CustomerEntity;
import entity.ServiceProviderEntity;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import util.exception.CustomerNotFoundException;
import util.exception.ServiceProviderNotFoundException;
import util.exception.ServiceProviderApproveException;
import util.exception.ServiceProviderBlockedException;
import static util.helper.StringUtil.*;

/**
 *
 * @author zhijun
 */
public class AdminOperationMenu {
    
    private AdminEntity adminEntity;
    private CustomerEntitySessionBeanRemote customerEntitySessionBeanRemote;
    private ServiceProviderEntitySessionBeanRemote serviceProviderEntitySessionBeanRemote;
    
    public AdminOperationMenu() {
    }

    public AdminOperationMenu(AdminEntity adminEntity, CustomerEntitySessionBeanRemote customerEntitySessionBeanRemote, ServiceProviderEntitySessionBeanRemote serviceProviderEntitySessionBeanRemote) {
        this.adminEntity = adminEntity;
        this.customerEntitySessionBeanRemote = customerEntitySessionBeanRemote;
        this.serviceProviderEntitySessionBeanRemote = serviceProviderEntitySessionBeanRemote;
    }
    
    public void adminOperationMainMenu() {
        Scanner sc = new Scanner(System.in);
        Integer response = 0;
        
        while (true) {
            System.out.println("*** Admin Terminal :: Main\n");
            System.out.println("You are login as Admin " + adminEntity.getAdminId());
            System.out.println("1: View Appointment for customers");
            System.out.println("2: View Appointments for service providers");
            System.out.println("3: View service providers");
            System.out.println("4: Approve service provider");
            System.out.println("5: Block service provider");
            System.out.println("6: Add Business category");
            System.out.println("7: Remove Business category");
            System.out.println("8: Send reminder email");
            System.out.println("9: Logout");
                    
            response = 0;

            while (response < 1 || response > 9) {
                try {
                    System.out.print("> ");
                    response = sc.nextInt();
                } catch (InputMismatchException ex) {
                    sc.nextLine();
                }
                if (response == 1) {
                    viewCustomerAppointments();
                } else if (response == 2) {
                    viewServiceProviderAppointments();
                } else if (response == 3) {
                    viewServiceProvider();
                } else if (response == 4) {
                    approveServiceProviders();
                } else if (response == 5) {
                    blockServiceProviders();
                } else if (response == 6) {
                    
                } else if (response == 7) {
                    
                } else if (response == 8) {
                    
                } else if (response == 9) {
                    break;
                } else {
                System.out.println("Invalid option! Please try again!");
                }
            }
            if (response == 9) {
                break;
            }
        }
    }
    
    public void viewCustomerAppointments() {
        Scanner sc = new Scanner(System.in);
        CustomerEntity customerEntity = new CustomerEntity();
        System.out.println("*** Admin terminal :: View Appointments for customers ***\n");
        
        while (true) {
            System.out.print("Enter customer Id> \n");
            
            try {
                Long customerId = sc.nextLong();
                //exit if input 0
                if (customerId.equals(0L)) {
                    break;
                }
                customerEntity = customerEntitySessionBeanRemote.retrieveCustomerById(customerId);
                List<AppointmentEntity> appointmentArray = customerEntity.getAppointmentEntity();
                System.out.println("Appointments:\n");
                System.out.println("Name        | Business category | Date  | Time | Appointment No.");
                
                //iterating through customer's appointments
                for (AppointmentEntity appointment : appointmentArray) {
                    System.out.println(customerEntity.getFirstName() + " " + customerEntity.getLastName() + 
                    "   | " + appointment.getServiceProviderEntity().getBusinessCategory() + "              | " + 
                    appointment.getAppointmentDate() + " | " + appointment.getAppointmentTime() + " | " + 
                    appointment.getAppointmentNo());
                }
            System.out.println("Enter 0 to go back to the previous menu\n");
            } catch (CustomerNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input! Please enter a numeric value!");
            }
            sc.nextLine();
        }
    }
    
    public void viewServiceProviderAppointments() {
        Scanner sc = new Scanner(System.in);
        ServiceProviderEntity serviceProviderEntity = new ServiceProviderEntity();
        System.out.println("*** Admin terminal :: View Appointments for service providers ***");
        
        while (true) {
            System.out.print("Enter customer Id> \n");
            
            try {
                Long serviceProviderId = sc.nextLong();
                //exit if 0
                if (serviceProviderId.equals(0)) {
                    break;
                }
                serviceProviderEntity = serviceProviderEntitySessionBeanRemote.retrieveServiceProviderByUniqueIdNumber(serviceProviderId);
                List<AppointmentEntity> appointmentArray = serviceProviderEntity.getAppointmentEntity();
                System.out.println("Appointments: \n");
                System.out.println("Name        | Business category | Date | Time | Appointment No.");
                //iterate through service provider appointments
                for (AppointmentEntity appointment : appointmentArray) {
                    System.out.println(serviceProviderEntity.getName() + " | " + 
                    serviceProviderEntity.getBusinessCategory() + " | " + 
                    appointment.getAppointmentDate() + 
                    appointment.getAppointmentTime() + 
                    appointment.getAppointmentId());
                }
                System.out.println("Enter 0 to go back to the previous menu\n");
            } catch (ServiceProviderNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input! Please enter a numeric value!");
            }
            sc.nextLine();
        }
    }
    
    public void viewServiceProvider() {
        List<ServiceProviderEntity> serviceProviderList = serviceProviderEntitySessionBeanRemote.retrieveAllServiceProvider();
        System.out.println("*** Admin terminal :: View all service provider");
        viewServiceProviderTableFormat(serviceProviderList);
        
        //have to fix for only approved status
        for (ServiceProviderEntity serviceProvider : serviceProviderList) {
            int i = 1;
            System.out.println(serviceProvider);
            i ++;
        }
        System.out.print("\n");
    }
    
    public void approveServiceProviders() {
        Scanner sc = new Scanner(System.in);
        List<ServiceProviderEntity> pendingList = serviceProviderEntitySessionBeanRemote.retrievePendingServiceProviders();
        System.out.println("*** Admin terminal :: Approve service provider ***\n");
        serviceProviderApproveAndBlockTableFormat(pendingList);

        for (ServiceProviderEntity serviceProvider : pendingList) {
            serviceProvider.toStringWithBusinessNo();
        }
        System.out.print("\n");
        while (true) {
            System.out.println("Enter 0 to go back to the previous menu.");
            System.out.print("Enter service provider Id> ");
            try {
                Long serviceProviderId = sc.nextLong();
                if (serviceProviderId.equals(0L)) {
                    System.out.print("\n");
                    break;
                }
                ServiceProviderEntity serviceProviderToApprove  = serviceProviderEntitySessionBeanRemote.retrieveServiceProviderByUniqueIdNumber(serviceProviderId);
                serviceProviderEntitySessionBeanRemote.approveServiceProvider(serviceProviderToApprove);
                System.out.println(serviceProviderToApprove.getName() + " 's registration is approved!");
            }
            catch (ServiceProviderNotFoundException | ServiceProviderApproveException ex) {
                System.out.println("Approval failed! " + ex.getMessage());
            }
            catch (InputMismatchException ex) {
                System.out.println("Invalid input! Please enter a numeric value!");
            } 
            sc.nextLine();
        }
    }
    
    public void blockServiceProviders() {
        Scanner sc = new Scanner(System.in);
        List<ServiceProviderEntity> pendingList = serviceProviderEntitySessionBeanRemote.retrievePendingServiceProviders();
        List<ServiceProviderEntity> approveList = serviceProviderEntitySessionBeanRemote.retrieveApprovedServiceProviders();
        List<ServiceProviderEntity> pendingAndApprove = Stream.concat(pendingList.stream(), approveList.stream()).collect(Collectors.toList());
        System.out.println("*** Admin terminal :: Block service provider ***\n");
        serviceProviderApproveAndBlockTableFormat(pendingAndApprove);
        
        for (ServiceProviderEntity serviceProvider : pendingAndApprove) {
            serviceProvider.toStringWithBusinessNo();
        }
        System.out.print("\n");
        while (true) {
            System.out.println("Enter 0 to go back to the previous menu");
            System.out.print("Enter service provider Id> ");
            try {
                Long serviceProviderId = sc.nextLong();
                if(serviceProviderId.equals(0L)) {
                    System.out.print("\n");
                    break;
                }
                ServiceProviderEntity serviceProviderToBlock = serviceProviderEntitySessionBeanRemote.retrieveServiceProviderByUniqueIdNumber(serviceProviderId);
                serviceProviderEntitySessionBeanRemote.blockServiceProvider(serviceProviderToBlock);
                System.out.println(serviceProviderToBlock.getName() + " successfully blocked!");
            }
            catch (ServiceProviderNotFoundException | ServiceProviderBlockedException ex) {
                    System.out.println("Block failed! " + ex.getMessage());
            } 
            catch (InputMismatchException ex) {
                System.out.println("Invalid input! Please enter a numeric value!");
            }
            sc.nextLine();
        }
    }
    
    public void addBusinessCategory() {
        Scanner sc = new Scanner(System.in);
        System.out.println("*** Admin terminal :: Add a Business category ***\n");
        System.out.println("Enter 0 to go back to the previous menu.");
        System.out.print("Enter a new business category> ");
        String businessCategory = sc.nextLine().trim();
        
    }
}
