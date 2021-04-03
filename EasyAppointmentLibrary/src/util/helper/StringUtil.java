/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.helper;

import entity.ServiceProviderEntity;
import java.util.List;

/**
 *
 * @author zhijun
 */
public class StringUtil {

    public StringUtil() {
    }
    
    //helper method to pad strings. Pads the output with spaces to align with headers.
    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
    
    public static void viewServiceProviderTableFormat(List<ServiceProviderEntity> list) {
        String nameHeader = "Name";
        String businessCategoryHeader = "Business Category";
        String cityHeader = "City";
        String ratingHeader = "Overall Rating";
        String statusHeader = "Status";
        for (ServiceProviderEntity serviceProvider : list) {
            if (serviceProvider.getName().length() > nameHeader.length()) {
                int i = serviceProvider.getName().length();
                nameHeader = padRight(nameHeader, i);
            }
            if (serviceProvider.getBusinessCategory().length() > businessCategoryHeader.length()) {
                int i = serviceProvider.getBusinessCategory().length();
                businessCategoryHeader = padRight(businessCategoryHeader, i);
            }
            if (serviceProvider.getCity().length() > cityHeader.length()) {
                int i = serviceProvider.getCity().length();
                cityHeader = padRight(cityHeader, i);
            }
        }
        System.out.println("Id| " + nameHeader + " | " + businessCategoryHeader + " | " + cityHeader + " | " + ratingHeader + " | " + statusHeader);
    }
    
    public static void serviceProviderApproveAndBlockTableFormat(List<ServiceProviderEntity> list) {
        String nameHeader = "Name";
        String businessCategoryHeader = "Business Category";
        String businessRegNoHeader = "Business Reg. No.";
        String cityHeader = "City";
        String addressHeader = "Address";
        String emailHeader = "Email";
        String phoneHeader = "Phone";
        for (ServiceProviderEntity serviceProvider : list) {
            if (serviceProvider.getName().length() > nameHeader.length()) {
                int i = serviceProvider.getName().length();
                nameHeader = padRight(nameHeader, i);
            }
            if (serviceProvider.getBusinessCategory().length() > businessCategoryHeader.length()) {
                int i = serviceProvider.getBusinessCategory().length();
                businessCategoryHeader = padRight(businessCategoryHeader, i);
            }
            if (serviceProvider.getBusinessRegNumber().length() > businessRegNoHeader.length()) {
                int i = serviceProvider.getBusinessRegNumber().length();
                businessRegNoHeader = padRight(businessRegNoHeader, i);
            }
            if (serviceProvider.getCity().length() > cityHeader.length()) {
                int i = serviceProvider.getCity().length();
                cityHeader = padRight(cityHeader, i);
            }
            if (serviceProvider.getAddress().length() > addressHeader.length()) {
                int i = serviceProvider.getAddress().length();
                addressHeader = padRight(addressHeader, i);
            }
            if (serviceProvider.getEmail().length() > emailHeader.length()) {
                int i = serviceProvider.getEmail().length();
                emailHeader = padRight(emailHeader, i);
            }
            if (serviceProvider.getPhone().length() > phoneHeader.length()) {
                int i = serviceProvider.getPhone().length();
                phoneHeader = padRight(phoneHeader, i);
            }
        }
        System.out.println("Id| " + nameHeader + " | " + businessCategoryHeader + " | " + businessRegNoHeader + " | " + cityHeader + " | " + addressHeader + " | " + emailHeader + " | " + phoneHeader);
    }
    
}
