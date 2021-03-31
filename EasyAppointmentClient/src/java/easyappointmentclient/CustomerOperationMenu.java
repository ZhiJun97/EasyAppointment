/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyappointmentclient;

import entity.CustomerEntity;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author zhijun
 */
public class CustomerOperationMenu {
    
    private CustomerEntity customerEntity;

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
}
