/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyappointmentclient;

import ejb.session.stateless.CustomerEntitySessionBeanRemote;
import entity.AdminEntity;
import entity.CustomerEntity;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.exception.CustomerNotFoundException;

/**
 *
 * @author zhijun
 */
public class AdminOperationMenu {
    
    private AdminEntity adminEntity;
    private CustomerEntitySessionBeanRemote customerEntitySessionBeanRemote;
    
    public AdminOperationMenu() {
    }

    public AdminOperationMenu(AdminEntity adminEntity, CustomerEntitySessionBeanRemote customerEntitySessionBean) {
        this.adminEntity = adminEntity;
        this.customerEntitySessionBeanRemote = customerEntitySessionBean;
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
                    
                } else if (response == 2) {
                    
                } else if (response == 3) {
                    
                } else if (response == 4) {
                    
                } else if (response == 5) {
                    
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
    
    public void viewCustomerAppointment() {
        Scanner sc = new Scanner(System.in);
        System.out.println("*** Admin terminal :: View Appointments for customers ***\n");
        System.out.println("Enter customer Id> \n");
        while (true) {
            try {
                Long customerId = sc.nextLong();
                CustomerEntity customerEntity = customerEntitySessionBeanRemote.retrieveCustomerById(customerId);
                customerEntity.getAppointmentEntity().size();
                break;
            } catch (CustomerNotFoundException ex) {
                System.out.println(ex.getCause());
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input! Please enter a numeric value!");
            }
            sc.nextLine();
        }
    }
    
}
