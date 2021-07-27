package com.system.pojo;
import java.util.*;

public class Login {
	Scanner input = new Scanner(System.in);

	protected String id, password;
	boolean correctPassword = false;
	boolean haveId = false;
	String user, admin;
	// Create an empty hash map
	HashMap<String, String> details = new HashMap<String, String>();
	
	
    public boolean doLogin()
    {
    	details.put("user1@gmail.com", "abcd123");
		details.put("admin1@gmail.com", "abcd123");
    	if(details.containsKey(id))
    		return true;
    	else 
    		return false;
    	
    }

    
    public boolean verifyUser()
    {
    	if((details != null && details.get(id) != null) && details.get(id).equals(password))	//user input not equals null
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
