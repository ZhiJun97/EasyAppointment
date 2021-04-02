/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyappointmentclient;

import ejb.session.stateless.AppointmentEntitySessionBeanRemote;
import ejb.session.stateless.ServiceProviderEntitySessionBeanRemote;
import entity.AppointmentEntity;
import entity.ServiceProviderEntity;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author zhijun
 */
public class ServiceProviderOperationMenu {
    
    private ServiceProviderEntity serviceProviderEntity;
    private ServiceProviderEntitySessionBeanRemote serviceProviderEntitySessionBeanRemote;
    private AppointmentEntitySessionBeanRemote appointmentEntitySessionBeanRemote;

    public ServiceProviderOperationMenu(ServiceProviderEntity serviceProviderEntity, ServiceProviderEntitySessionBeanRemote serviceProviderEntitySessionBeanRemote, AppointmentEntitySessionBeanRemote appointmentEntitySessionBeanRemote) {
        this.serviceProviderEntity = serviceProviderEntity;
        this.serviceProviderEntitySessionBeanRemote = serviceProviderEntitySessionBeanRemote;
        this.appointmentEntitySessionBeanRemote = appointmentEntitySessionBeanRemote;
    }
    
    public void serviceProviderMainMenu() {
        Scanner sc = new Scanner(System.in);
        Integer response = 0;
        
        while (true) {
            System.out.println("*** Service provider terminal :: Main ***\n");
            System.out.println("You are login as " + serviceProviderEntity.getName());
            System.out.println("1: View Profile");
            System.out.println("2: Edit Profile");
            System.out.println("3: View Appointments");
            System.out.println("4: Cancel Appointments");
            System.out.println("5: Logout\n");
            response = 0;

            while (response < 1 || response > 5) {
                try {
                    System.out.print("> ");
                    response = sc.nextInt();
                } catch (InputMismatchException ex) {
                    sc.nextLine();
                }
                if (response == 1) {
                    viewProfile();
                } else if (response == 2) {
                    editProfile();
                } else if (response == 3) {
                    viewAppointments();
                } else if (response == 4) {

                } else if (response == 5) {
                    break;
                } else {
                System.out.println("Invalid option! Please try again!");
                }
            }
            if (response == 5) {
                break;
            }
        }
    }
    
    public void viewProfile() {
        System.out.println("Current Profile: " + serviceProviderEntity.toString());
    }
    
    public void editProfile() {
        Scanner sc = new Scanner(System.in);
        String input = "";
                
        System.out.println("*** Service provider terminal :: Edit Profile ***\n");
        System.out.println("Enter your new city: (Enter '0' if no change)");
        System.out.print("> ");
        input = sc.nextLine().trim();
        if(!input.equals("0")) {
            serviceProviderEntity.setCity(input);
        }
        System.out.println("Enter your new business address: (Enter '0' if no change)");
        System.out.print("> ");
        input = sc.nextLine().trim();
        if(!input.equals("0")) {
            serviceProviderEntity.setAddress(input);
        }
        System.out.println("Enter your new email address: (Enter '0' if no change)");
        System.out.print("> ");
        input = sc.nextLine().trim();
        if(!input.equals("0")) {
            serviceProviderEntity.setEmail(input);
        }
        System.out.println("Enter your phone number: (Enter '0' if no change)");
        System.out.print("> ");
        input = sc.nextLine().trim();
        if(!input.equals("0")) {
            serviceProviderEntity.setPhone(input);
        }
        System.out.println("Enter your new password: (Enter '0' if no change)");
        System.out.print("> ");
        input = sc.nextLine().trim();
        if(!input.equals("0")) {
            serviceProviderEntity.setPassword(input);
        }
        
        serviceProviderEntitySessionBeanRemote.updateServiceProviderEntity(serviceProviderEntity);
        System.out.println("Your information has been successfully saved!");
    }
    
    //in-progress
    public void viewAppointments() {
        System.out.println("Updating service provider's appointments list...");
        serviceProviderEntitySessionBeanRemote.newestAppointmentList(serviceProviderEntity);
        System.out.print("Appointments:");
        for (AppointmentEntity appointment : serviceProviderEntity.getAppointmentEntity()) {
            System.out.println(appointment.getCustomerEntity().getEmailAddress()+ " | " + 
            "   | " + appointment.getAppointmentDate().toString() + "  | " + 
            appointment.getAppointmentTime().toString() + " | " + 
            appointment.getAppointmentNo());
        }
    }
}
