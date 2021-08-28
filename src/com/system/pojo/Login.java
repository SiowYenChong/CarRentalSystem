package com.system.pojo;
import java.util.*;

import com.system.util.Utility;

public class Login {
	Scanner input = new Scanner(System.in);

	protected String id, password;
	boolean correctPassword = false;
	boolean haveId = false;
	String user, admin;
	
	// Create an empty hash map
	HashMap<String, String> details = new HashMap<String, String>();
	
	//login function for user to login
    public boolean doLogin()throws Exception
    {
    	boolean result = false;
    	/*details.put("user1@gmail.com", "abcd123");
		details.put("admin1@gmail.com", "abcd123");*/
    	Customer cust = Utility.getCustomer(id);
    	if(!cust.getCustomerID().equals("")) {
    		if(cust.getCustomerEmail().equals(id.trim())) {		//remove blank space
    				result = true;
    		}
    	}
    	return result;
    }
    
    //To check if user is our user
    public boolean verifyUser()throws Exception
    {
    	Customer cust = Utility.getCustomer(id);
    	if((!cust.getCustomerID().equals("")) && cust.getPassword().equals(password))	//user input not equals null
    	{
    		return correctPassword=true;
    	}
    	else 
    		return correctPassword=false;
    	
    }
    
    public Login(String nId, String nPassword)
    {
    	id = nId;
    	password = nPassword;
    }
}
