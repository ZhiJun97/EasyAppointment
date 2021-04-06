/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyappointmentclient;

import ejb.session.stateless.AppointmentEntitySessionBeanRemote;
import ejb.session.stateless.CustomerEntitySessionBeanRemote;
import ejb.session.stateless.ServiceProviderEntitySessionBeanRemote;
import entity.AppointmentEntity;
import entity.CustomerEntity;
import entity.ServiceProviderEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import util.exception.ServiceProviderNotFoundException;
import util.helper.DateUtil;

/**
 *
 * @author zhijun
 */
public class CustomerOperationMenu {
    
    private CustomerEntity customerEntity;
    private ServiceProviderEntitySessionBeanRemote serviceProviderEntitySessionBeanRemote;
    private CustomerEntitySessionBeanRemote customerEntitySessionBeanRemote;
    private AppointmentEntitySessionBeanRemote appointmentEntitySessionBeanRemote;
    
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
            System.out.println("3: View Appointments");
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
                    doAddAppointment();
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
    
    public Date doSearchOperation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("*** Customer terminal :: Search ***\n");
        
        System.out.println("1 Health | 2 Fashion | 3 Education");
        System.out.print("Enter business category> ");
        Integer businessCategory = sc.nextInt();
        sc.nextLine();
        String category = "";
        switch (businessCategory) {
            case 1:
                category = "Health";
                break;
            case 2:
                category = "Fashion";
                break;
            case 3:
                category = "Education";
                break;
        }
        System.out.print("Enter city> ");
        String city = sc.nextLine().trim();
        System.out.print("Enter date (YYYY-MM-DD)> ");
        String date = sc.nextLine().trim();
        
        Date dateProcessed = processDate(date);
        // if entered date is a sunday, inform user and try again
        while (dateProcessed.getDay() == 0) {
            System.out.println("Entered date is a Sunday, service providers do not operate un Sundays. Please enter another date.");
            System.out.print("Enter Date (YYYY-MM-DD)> ");
            date = sc.nextLine().trim();
            dateProcessed = processDate(date);
        }
        // get list of appointments of all service providers
        List<ServiceProviderEntity> serviceProviders = serviceProviderEntitySessionBeanRemote.retrieveAllServiceProvider();
        // check if service provider is full on that day, if full, remove from list
        for (ServiceProviderEntity s : serviceProviders) {
            // remove serviceProvider that does not match desired category
            if (!s.getBusinessCategory().equals(category)) {
                serviceProviders.remove(s);
            }
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
            System.out.print(s.getUniqueIdNumber() + " | " + s.getName() + " | " + earliestSlot.toString() + ":" + "30 | " + s.getAddress() + " | " + s.getRatingAverage());
        }
        return dateProcessed;
    }
    
    public void doAddAppointment() {
        Scanner sc = new Scanner(System.in);
        
        Date date = doSearchOperation();
        
        // customer chooses service provider
        System.out.println("Enter 0 to go back to the previous menu.");
        System.out.print("Service provider Id> ");
        Long serviceProviderId = sc.nextLong();
        sc.nextLine();
        
        // show customer the service providers available slots
        ArrayList<Integer> slots = new ArrayList<>(Arrays.asList(8, 9, 10, 12, 13, 14, 16, 17));
        try {
            ServiceProviderEntity serviceProvider = serviceProviderEntitySessionBeanRemote.retrieveServiceProviderByUniqueIdNumber(serviceProviderId);
            List<AppointmentEntity> appointments = serviceProvider.getAppointmentEntity();
            // remove slots that are already filled
            for (AppointmentEntity a : appointments) {
                Integer hour = a.getAppointmentTime().getHours();
                if (slots.contains(hour)) {
                    slots.remove(hour);
                }
            }
            System.out.println("Available Appointment slots:");
            // print out the available slots, seperated by |
            for (Integer i : slots) {
                if (slots.indexOf(i) == slots.size() - 1) {
                    System.out.println(i + ":30");
                } else {
                    System.out.print(i + ":30 | ");
                }
            }
            System.out.println("Enter 0 to go back to the previous menu.");
            System.out.print("Enter time> ");
            String time = sc.nextLine().trim();
            
            Date processedTime = processTime(date, time);
            // check if serviceProvider is available during time entered
            while (!slots.contains(processedTime.getHours())) {
                System.out.println(serviceProvider.getName() + " is not available at the selected time. Please enter another time");
                System.out.print("Enter time> ");
                time = sc.nextLine().trim();
                processedTime = processTime(date, time);
            }
            // create new appointment
            DateUtil du = new DateUtil();
            Date appointmentTime = du.getDate(2021, 1, 1, date.getHours(), date.getMinutes());
            String appointmentNumber = serviceProvider.getUniqueIdNumber().toString() + date.getDate() + date.getMonth() + appointmentTime.getHours() + appointmentTime.getMinutes();
            AppointmentEntity newAppointment = new AppointmentEntity(appointmentNumber, date, appointmentTime);
            // add it to service provider
            appointments.add(newAppointment);
            serviceProvider.setAppointmentEntity(appointments);
            // add appointment to customer
            List<AppointmentEntity> customerAppointments = customerEntity.getAppointmentEntity();
            customerAppointments.add(newAppointment);
            customerEntity.setAppointmentEntity(customerAppointments);
            // update service provider in database
            serviceProviderEntitySessionBeanRemote.updateServiceProviderEntity(serviceProvider);
            // update customer in db
            customerEntitySessionBeanRemote.updateCustomerEntity(customerEntity);
            // add appointment in db
            appointmentEntitySessionBeanRemote.createAppointmentEntity(newAppointment);
        } catch (ServiceProviderNotFoundException e) {
            
        }
    }
    
    public Date processTime(Date date, String time) {
        String[] timeArray = time.split(":");
        Integer[] timeArrayInt = new Integer[timeArray.length];
        for (int i = 0; i < timeArray.length; i++) {
            timeArrayInt[i] = Integer.parseInt(timeArray[i]);
        }
        Integer hours = timeArrayInt[0];
        Integer minutes = timeArrayInt[1];
        date.setHours(hours);
        date.setMinutes(minutes);
        return date;
    }
    
    public Date processDate(String date) {
        String[] dateArray = date.split("-");
        Integer[] dateArrayInt = new Integer[dateArray.length];
        for (int i = 0; i < dateArrayInt.length; i++) {
            dateArrayInt[i] = Integer.parseInt(dateArray[i]);
        }
        Integer year = dateArrayInt[0];
        Integer month = dateArrayInt[1];
        Integer day = dateArrayInt[2];
        Date processedDate = new Date(year, month, day);
        return processedDate;
    }

    private CustomerEntitySessionBeanRemote lookupCustomerEntitySessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (CustomerEntitySessionBeanRemote) c.lookup("java:comp/env/CustomerEntitySessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private AppointmentEntitySessionBeanRemote lookupAppointmentEntitySessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (AppointmentEntitySessionBeanRemote) c.lookup("java:comp/env/AppointmentEntitySessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
