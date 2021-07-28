package com.system.pojo;

public class Customer {
	/*
    -customerID:String
	-customerName:String
	-customerBirthDate:String
	-customerMobileNumber:String
	-customerEmail:String
	-customerAddress:String
*/
	String customerID;
	String customerName;
	String customerBirthDate;
	String customerMobileNumber;
	String customerEmail;
	String customerAddress;
	
	//public String getCustomerID() 
	//{
		//return customerID;
	//}
	
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
	
	//@Override
	public String toString() 
	{
		return "Customer [customerID = " + customerID + ", customerName = " + customerName + ", customerBirthDate = " + customerBirthDate
				+ ", customerMobileNumber = " + customerMobileNumber + ", customerEmail = " + customerEmail + ", customerAddress = " + customerAddress + "]";	  
	}
}

