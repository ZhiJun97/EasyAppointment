/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyappointmentclient;

import entity.ServiceProviderEntity;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author zhijun
 */
public class ServiceProviderOperationMenu {
    
    private ServiceProviderEntity serviceProviderEntity;

    public ServiceProviderOperationMenu(ServiceProviderEntity serviceProviderEntity) {
        this.serviceProviderEntity = serviceProviderEntity;
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

                } else if (response == 2) {

                } else if (response == 3) {

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
    
}
