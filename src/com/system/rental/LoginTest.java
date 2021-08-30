package com.system.rental;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import com.system.model.CarRentalModel;
import com.system.pojo.Customer;
import com.system.pojo.Login;
import com.system.util.Utility;

public class LoginTest {

	public static String logIn() throws Exception{
		Scanner input = new Scanner(System.in);
		boolean result = false;
		String id, password;
		boolean idCorrect,correctPassword;
		do
		{
		System.out.println("Please enter email: ");
    	id = input.next();
    	System.out.println("Password: ");
    	password = input.next();
    	
    	Login lg1 = new Login(id,password);
    	
    	idCorrect = lg1.doLogin();
    	correctPassword = lg1.verifyUser();
    	
    	if(idCorrect && correctPassword) {
    		String msg = id.substring(0, id.indexOf("@"));
    		
    		if(id.contains("admin")) 
    			System.out.println("Welcome Admin " + msg + "("+Utility.getCustomer(id).getCustomerID()+")");
    		else 
    			System.out.println("Welcome User " + msg + "("+Utility.getCustomer(id).getCustomerID()+")");
    		
    		Map<String,Boolean> loginUsers = CarRentalModel.loginUsers;
    		loginUsers.put(id, true);										
    		result = true;
    	}else {
    		System.out.println("Invalid email id/password. Please try again...");
    		id = "";			//If user input wrong password,id is assigned to blank
    	}
		}while(!idCorrect  || !correctPassword);
		
		return id;
	}

}
