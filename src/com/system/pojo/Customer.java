package com.system.pojo; //plain old java object

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Customer 
{
	/*
    -customerID:String
	-customerName:String
	-customerBirthDate:String
	-customerMobileNumber:String
	-customerEmail:String
	-customerAddress:String
*/
	private String customerID;
	private String customerName;
	private String customerBirthDate;
	private String customerMobileNumber;
	private String customerEmail;
	private String customerAddress;
	private double carRental;
	private String password = "";
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerID() 
	{
		return customerID;
	}
	
	public void setCustomerID(String customerID) 
	{
		this.customerID = customerID;
	}
	
	public String getCustomerName() 
	{
		return customerName;
	}
	
	public void setCustomerName(String customerName) 
	{		
		this.customerName = customerName;
	}
	
	public String getCustomerBirthDate() 
	{
		return customerBirthDate;
	}
	
	public void setCustomerBirthDate(String customerBirthDate) 
	{
		this.customerBirthDate = customerBirthDate;
	}
	
	public String getCustomerMobileNumber() 
	{
		return customerMobileNumber;
	}
	
	public void setCustomerMobileNumber(String customerMobileNumber) 
	{
		this.customerMobileNumber = customerMobileNumber;
	}
	
	public String getCustomerEmail() 
	{
		return customerEmail;
	}
	
	public void setCustomerEmail(String customerEmail) 
	{
		this.customerEmail = customerEmail;
	}
	
	public String getCustomerAddress() 
	{
		return customerAddress;
	}
	
	public void setCustomerAddress(String customerAddress) 
	{
		this.customerAddress = customerAddress;
	}
	
	public double getCarRental() 
	{
		return carRental;
	}
	
	public void setCarRental(double carRental) 
	{
		this.carRental = carRental;
	}
	
	//@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();		//not threadSafe
		for(int i = 0; i<getPassword().length(); i++) {
			sb.append("*");
		}
		return "Customer [customerID = " + customerID + ", customerName = " + customerName + ", customerBirthDate = " + customerBirthDate +
			   ",\n\t customerMobileNumber = " + customerMobileNumber + ", customerEmail = " + customerEmail + ", customerAddress = " + customerAddress +  ", password = "+ sb.toString() + "]" + 
			   "\n----------------------------------------------------------------------------------------------------------------------------------------------------------------";
  
	}
}