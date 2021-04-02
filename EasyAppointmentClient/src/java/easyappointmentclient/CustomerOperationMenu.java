/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyappointmentclient;

import ejb.session.stateless.ServiceProviderEntitySessionBeanRemote;
import entity.AppointmentEntity;
import entity.CustomerEntity;
import entity.ServiceProviderEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author zhijun
 */
public class CustomerOperationMenu {
    
    private CustomerEntity customerEntity;
    private ServiceProviderEntitySessionBeanRemote serviceProviderEntitySessionBeanRemote;

    public CustomerOperationMenu(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }
    
    public void customerOperationMainMenu() {
        Scanner sc = new Scanner(System.in);
        Integer response = 0;
        
        while (true) {
            System.out.println("*** Customer terminal :: Main ***\n");
            System.out.println("You are login as " + customerEntity.getFirstName() + customerEntity.getLastName());
            System.out.println("1: Search Operation");
            System.out.println("2: Add Appointment");
            System.out.println("3: View Appointment");
            System.out.println("4: Cancel Appointment");
            System.out.println("5: Rate Service Provider");
            System.out.println("6: Logout\n");
            response = 0;

            while (response < 1 || response > 5) {
                try {
                    System.out.print("> ");
                    response = sc.nextInt();
                } catch (InputMismatchException ex) {
                    sc.nextLine();
                }
                if (response == 1) {
                    doSearchOperation();
                } else if (response == 2) {

                } else if (response == 3) {

                } else if (response == 4) {

                } else if (response == 5) {

                } else if (response == 6) {
                    break;
                } else {
                System.out.println("Invalid option! Please try again!");
                }
            }
            if (response == 6) {
                break;
            }
        }
    }
    
    public void doSearchOperation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("*** Customer terminal :: Search ***\n");
        
        System.out.println("1 Health | 2 Fashion | 3 Education");
        System.out.print("Enter business category> ");
        String businessCategory = sc.nextLine().trim();
        System.out.print("Enter date (YYYY-MM-DD)> ");
        String date = sc.nextLine().trim();
        System.out.print("Enter city> ");
        String city = sc.nextLine().trim();
        
        // process date
        String[] dateArray = date.split("-");
        Integer[] dateArrayInt = new Integer[dateArray.length];
        for (int i = 0; i < dateArrayInt.length; i++) {
            dateArrayInt[i] = Integer.parseInt(dateArray[i]);
        }
        Integer year = dateArrayInt[0];
        Integer month = dateArrayInt[1];
        Integer day = dateArrayInt[2];
        
        Date dateProcessed = new Date(year, month, day);
        // if entered date is a sunday, inform user and try again
        if (dateProcessed.getDay() == 0) {
        
        } else {
            // get list of appointments of all service providers
            List<ServiceProviderEntity> serviceProviders = serviceProviderEntitySessionBeanRemote.retrieveAllServiceProvider();
            // check if service provider is full on that day, if full, remove from list
            for (ServiceProviderEntity s : serviceProviders) {
                // get list of appointments for each service provider
                List<AppointmentEntity> appointments = s.getAppointmentEntity();
                // filter appointments for date entered by user
                for (AppointmentEntity a : appointments) {
                    if (!a.getAppointmentDate().equals(dateProcessed)) {
                        appointments.remove(a);
                    }
                }
                // check if service provider is full on date entered by user
                if (appointments.size() == 10) {
                    serviceProviders.remove(s);
                }
            }
            System.out.println("Service provider Id | Name | First Available Time | Address | Overall rating");
            for (ServiceProviderEntity s : serviceProviders) {
                List<AppointmentEntity> appointments = s.getAppointmentEntity();
                // get first available slot
                Integer earliestSlot = 0;
                dateProcessed.setMinutes(30);
                for (int i = 8; i <= 18; i++) {
                    dateProcessed.setHours(i);
                    for (AppointmentEntity a : appointments) {  
                        if (a.getAppointmentTime().equals(dateProcessed)) {
                            break;
                        } else {
                            earliestSlot = dateProcessed.getHours();
                        }
                    }
                }
                // incomplete
                System.out.print(s.getUniqueIdNumber() + " | " + s.getName() + " | " + earliestSlot.toString() + ":" + "30 | " + s.getAddress() + " | " + s.getRating());
            }
        }
    }
}
