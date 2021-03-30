/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyappointmentclient;

import ejb.session.stateless.AdminEntitySessionBeanRemote;
import ejb.session.stateless.ServiceProviderEntitySessionBeanRemote;
import entity.AdminEntity;
import entity.CustomerEntity;
import entity.ServiceProviderEntity;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author zhijun
 */
public class Startup {
    
    private ServiceProviderEntitySessionBeanRemote serviceProviderEntitySessionBeanRemote;
    private AdminEntitySessionBeanRemote adminEntitySessionBeanRemote;
    
    private AdminEntity adminEntity;
    private ServiceProviderEntity serviceProviderEntity;
    private CustomerEntity customerEntity;

    public Startup() {
    }
    
    public void start() {
        Scanner sc = new Scanner(System.in);
        Integer response = 0;
        
        while (true) {
            System.out.println("*** Welcome to the Easy Appointment Portal! ***");
            System.out.println("Please choose your respective terminal!\n");
            System.out.println("1: Admin terminal");
            System.out.println("2: Service provider terminal");
            System.out.println("3: Custmomer terminal");
            System.out.println("4: Exit");
            response = 0;
            
            while (response < 1 || response > 4) {
                try {
                    System.out.print("> ");
                    response = sc.nextInt();
                } catch (InputMismatchException ex) {  
                    sc.nextLine();
                }
                if (response == 1) {
                    loginTerminal("Admin");
                } else if (response == 2) {
                    loginTerminal("Service Provider");
                } else if (response == 3) {
                    loginTerminal("Customer");
                } else if (response == 4) {
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("Invalid option! Please try again!\n");
                    break;
                }
            } if (response == 4) {
                break;
            }
        }
    }
    
    public void loginTerminal(String entity) {
        Scanner sc = new Scanner(System.in);
        Integer response = 0;
        
        while (true) {
            System.out.println("*** Welcome to " + entity + " terminal ***\n");
            System.out.println("1: Login");
            System.out.println("2: Exit\n");
            response = 0;
            
            while (response < 1 || response > 2) {
                try {
                    System.out.print("> ");
                    response = sc.nextInt();
                } catch (InputMismatchException ex) {  
                    sc.nextLine();
                }
                if (response == 1) {
                    try {
                        login(entity);
                        System.out.println("Login successful!");
                    } catch (InvalidLoginCredentialException ex) {
                        System.out.println("Login error! " + ex.getMessage() + "\n");
                    }
                } else if (response == 2) {
                    System.out.println("Returning to main page...\n");
                } else {
                    System.out.println("Invalid option! Please try again!\n");
                    break;
                }
            } if (response == 2) {
                break;
            }
        }
    }
    
    public void login(String entity) throws InvalidLoginCredentialException {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("*** " + entity + " terminal :: Login ***\n");
        System.out.print("Enter Email Address> ");
        String email = sc.nextLine().trim();
        System.out.print("Enter password> ");
        String password = sc.nextLine().trim();
        
        if (entity.equals("Admin")) {
            if (email.length() > 0 && password.length() > 0) {
                adminEntity = adminEntitySessionBeanRemote.adminLogin(email, password);
            } else {
                throw new InvalidLoginCredentialException("Missing login credentials");
            }
        } if (entity.equals("Service Provider")) {
            if (email.length() > 0 && password.length() > 0) {
                serviceProviderEntity = serviceProviderEntitySessionBeanRemote.serviceProviderLogin(email, password);
            } else {
                throw new InvalidLoginCredentialException("Missing login credentials");
            }
//        } if (entity.equals("Customer")) {
//            if (email.length() > 0 && password.length() > 0) {
//                customerEntity = customerEntitySessionBeanRemote.customerLogin(email, password);
//            } else {
//                throw new InvalidLoginCredentialException("Missing login credentials");
//            }
        }
    }
    
}
