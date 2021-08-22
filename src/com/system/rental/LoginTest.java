package com.system.rental;
import java.util.Map;
import java.util.Scanner;

import com.system.model.CarRentalModel;
import com.system.pojo.Login;

public class LoginTest {

	public static String logIn() throws Exception{
		Scanner input = new Scanner(System.in);
		boolean result = false;
		String id, password;
		boolean idCorrect,correctPassword;

		do
		{
		System.out.println("\nPlease enter email: ");
    	id = input.next();
    	System.out.println("Password: ");
    	password = input.next();
    	
    	Login lg1 = new Login(id,password);
    	
    	idCorrect = lg1.doLogin();
    	correctPassword = lg1.verifyUser();
    	
    	if(idCorrect && correctPassword) {
    		String msg = id.substring(0, id.indexOf("@"));
    		
    		if(id.contains("admin")) 
    			System.out.println("Welcome Admin "+msg);
    		else 
    			System.out.println("Welcome User "+msg);
    		
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
