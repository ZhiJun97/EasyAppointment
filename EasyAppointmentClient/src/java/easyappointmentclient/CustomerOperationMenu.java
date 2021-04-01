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
            ArrayList<List<AppointmentEntity>> appointments = new ArrayList<>();
            List<ServiceProviderEntity> serviceProviders = serviceProviderEntitySessionBeanRemote.retrieveAllServiceProvider();
            for (ServiceProviderEntity s : serviceProviders) {
                appointments.add(s.getAppointmentEntity());
            }
            // search through list of appointments, discard appointments that match user input
            for (List<AppointmentEntity> l : appointments) {
                for (AppointmentEntity a : l) {
                    if (dateProcessed.equals(a.getAppointmentDate())) {
                        appointments.remove(l);
                    }
                }
            }
            // return each unique service provider available on that day
            ArrayList<ServiceProviderEntity> availableServiceProviders = new ArrayList<>();
            for (List<AppointmentEntity> l : appointments) {
                availableServiceProviders.add(l.get(0).getServiceProviderEntity());
            }
            ArrayList<ServiceProviderEntity> availableServiceProvidersWithoutDuplicates = removeDuplicates(availableServiceProviders);
            
            // format the list
        }
    }
    
    public <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
        ArrayList<T> newList = new ArrayList<>();
        
        for (T element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        
        return newList;
    }
}
