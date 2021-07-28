package com.system.rental;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//package com.system.rental;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
import java.util.Scanner;
//import java.util.*;
//import com.system.model.CarRentalModel;
//import com.system.pojo.CarRegistration;

import com.system.model.CarRentalModel;
import com.system.pojo.CarRegistration;
import com.system.pojo.Customer;
import com.system.util.SequenceGenerator;
import com.system.util.Utility;

public class CustomerMenu {
	//private String customerID;
		private String customerName;
		private String customerBirthDate;
		private String customerMobileNumber;
		private String customerEmail;
		private String customerAddress;
		
		/*public static void main(String[] args) 
		{
			CustomerMenu cus = new CustomerMenu();
			System.out.println(cus.checkAlphabet("Claire"));

		}*/
		
		public void customerRegistration(String emailID) 
		{
			//here
			String result = Utility.validUser(emailID);
			if(result.equals("")) {
				return;
			}
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("\nWELCOME TO CUSTOMER REGISTRATION MENU");
			
			//this must be changed to auto-generated format
			//System.out.println("Customer ID: ");
			//customerID = scanner.nextLine();
			
			/*for (int id = 0; id < 11; id++)     
			{
				//System.out.printf("%d\t%d\t%d", "CUST", id);
				System.out.printf("CUST", id);
			}*/
			
			System.out.println("Name: ");
			customerName = scanner.nextLine();
			
			System.out.println("DOB (dd/mm/yy):  ");
			customerBirthDate = scanner.nextLine();
			
			System.out.println("Mobile number: ");
			customerMobileNumber = scanner.nextLine();
			
			System.out.println("Email: ");
			customerEmail = scanner.nextLine();
			
			System.out.println("Address: ");
			customerAddress = scanner.nextLine();
			
			//get memory to class    instantiate
			Customer cust = new Customer();		//ctrl-space import a class
			//ctrl-space
			
			if(customerName != null && !"".equals(customerName)) //not empty and not null
			{		
				if(checkAlphabet(customerName)){
					cust.setCustomerName(customerName);	
				}else{
					System.out.println("Name should not contain digits");
				}
						
			}
					
			if(customerBirthDate != null && !"".equals(customerBirthDate)) 
			{		
				cust.setCustomerBirthDate(customerBirthDate);	
			}
					
			if(customerMobileNumber != null && !"".equals(customerMobileNumber)) 
			{		
				cust.setCustomerMobileNumber(customerMobileNumber);	
			}
					
			if(customerEmail != null && !"".equals(customerEmail)) 
			{		
				cust.setCustomerEmail(customerEmail);	
			}
					
			if(customerAddress != null && !"".equals(customerAddress)) 
			{		
				cust.setCustomerAddress(customerAddress);
			}
			if(cust != null) {
				cust.setCustomerID(SequenceGenerator.getCustomerNext());
			}
			//HERE
			Map<String,List<Customer>> user = CarRentalModel.user;
			if(user.get("userList")!=null) { 	//registration details
				List userList = user.get("userList");
				userList.add(cust);						//use cust
				user.put("userList",userList);
			}else {
				List userList = new ArrayList<>();		//ctrl space
				userList.add(cust);
				user.put("userList",userList);
			}
			System.out.println("The customer details are "+user);
			
			//scanner.close();
		}
		
		public boolean checkAlphabet(String input) {
			boolean result = false;
			
			result = input.matches(".\\d.");
			return !result;
			
		}

}
