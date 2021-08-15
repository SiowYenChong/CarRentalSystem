/*
 * Rental car
 * Make email validation for registered user
 * make list horizontal
 * show selected customer id to edit
 * map repeating after display edit all (car)
 */

package com.system.rental;
import java.util.List;
import java.util.Map;

import com.system.pojo.CarRegistration;
import com.system.pojo.Customer;
import com.system.util.Utility;

public class Main {
	
	public static void main(String[] args)throws Exception {	//handling error
		int inputValue;
		
		System.out.println("WELCOME TO CAR RENTAL SYSTEM");
		System.out.println("Options:");
		System.out.println("1. Login");	//[done]
		System.out.println("2. Main Menu"); //done
		System.out.println("3. Register Customer"); //	[done - Sat]
		System.out.println("   31. Edit Customer Name");
		System.out.println("   32. Edit Customer Birth Date");
		System.out.println("   33. Edit Customer Mobile Number");
		System.out.println("   34. Edit Customer Email");
		System.out.println("   35. Edit Customer Address");
		System.out.println("   36. Edit All");
		System.out.println("   37. Delete existing customer registration");
		System.out.println("4. Register New Car");  //[done]
		System.out.println("   41. Edit Registration number");
		System.out.println("   42. Edit Car Brand");
		System.out.println("   43. Edit Car Model");
		System.out.println("   44. Edit Car Number");
		System.out.println("   45. Edit Car Description");
		System.out.println("   46. Edit Base Price");
		System.out.println("   47. Edit All");
		System.out.println("   48. Delete existing car registration");
		System.out.println("5. Rent a Car"); 
		System.out.println("   51. Show available cars");	//enter carRegID, startDate & endDate
		System.out.println("   52. Already hired cars");
		System.out.println("   53. Hire a car");
		System.out.println("6. Return a Car");
		System.out.println("7. Logout");    
		String email = "";
		//Utility.loadData(System.getProperty("user.dir")+"/inputData.txt");
		//Utility.loadData();
		//Utility.loadCustData(System.getProperty("user.dir")+"/custData.txt");
		CarRegistrationMenu carObj = new CarRegistrationMenu();
		String regID;
		String custID;
		CustomerMenu custObj = new CustomerMenu();
		String loggedInCustomer;
		CarRentalProcess carRental = new CarRentalProcess();
		do {
			inputValue=Input.inputInt("Select option: ");
			switch(inputValue) {
			case 1:
				System.out.print("LOGIN");
				LoginTest loginObj = new LoginTest();
				email = loginObj.logIn();							//("user1", "abcd123");											//("admin", "abcd123");
				break;
			case 2:
				System.out.print("MAIN MENU");
				break;
			case 3:
				loggedInCustomer = Utility.validUser(email);	
				System.out.print("REGISTER CUSTOMER");
				if(!"".equals(loggedInCustomer)) {
					custID = custObj.customerRegistration(email);
				}
				break;
			case 31:
				System.out.println("EDIT EXISTING CUSTOMER NAME");
				//regID = carObj.getRegID();
				loggedInCustomer = Utility.validUser(email);		//check if user login
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					custID = custObj.getCustID("edit");
					custObj.editCustomerName(custID, true);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("There is no registered customer.");  
						System.out.println("Please select 3 to register customer.");
					}
				}
				break;
			case 32:
				System.out.print("EDIT EXISTING CUSTOMER BIRTH DATE");
				loggedInCustomer = Utility.validUser(email);		//check if user login
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					custID = custObj.getCustID("edit");
					custObj.editCustomerBirthDate(custID, true);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("There is no registered customer.");  
						System.out.println("Please select 3 to register customer.");
					}
				}
				break;
			case 33:
				System.out.print("EDIT EXISTING CUSTOMER MOBILE NUMBER");
				loggedInCustomer = Utility.validUser(email);		//check if user login
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					custID = custObj.getCustID("edit");
					custObj.editCustomerMobileNumber(custID, true);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("There is no registered customer.");  
						System.out.println("Please select 3 to register customer.");
					}
				}
				break;
			case 34:
				System.out.print("EDIT EXISTING CUSTOMER EMAIL");
				loggedInCustomer = Utility.validUser(email);		//check if user login
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					custID = custObj.getCustID("edit");
					custObj.editCustomerEmail(custID, true);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("There is no registered customer.");  
						System.out.println("Please select 3 to register customer.");
					}
				}
				break;
			case 35:
				System.out.print("EDIT EXISTING CUSTOMER ADDRESS");
				loggedInCustomer = Utility.validUser(email);		//check if user login
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					custID = custObj.getCustID("edit");
					custObj.editCustomerAddress(custID, true);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("There is no registered customer.");  
						System.out.println("Please select 3 to register customer.");
					}
				}
				break;
			case 36:
				loggedInCustomer = Utility.validUser(email);		//check if user login
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					System.out.print("EDIT ALL EXISTING PROPERTIES");
					custID = custObj.getCustID("edit");
					custObj.editAll(custID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("There is no registered customer.");  
						System.out.println("Please select 3 to register customer.");
					}
				}
				break;
			case 37:
				loggedInCustomer = Utility.validUser(email);		//check if user login
				System.out.print("DELETE EXISTING CUSTOMER REGISTRATION");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					custID = custObj.getCustID("delete");
					custObj.deleteCustomer(custID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("There is no registered customer.");  
						System.out.println("Please select 3 to register customer.");
					}
				}
				break;
			case 4:
				loggedInCustomer = Utility.validUser(email);
				System.out.print("REGISTER NEW CAR");
				if(!"".equals(loggedInCustomer)) {
					carObj.doRegistration();
				}
				break;
			case 41:
				loggedInCustomer = Utility.validUser(email);		//check if user login
				System.out.print("EDIT EXISTING CAR REGISTRATION NUMBER");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					regID = carObj.getRegID();
					carObj.editCarRegNum(regID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("There is no registered car.");  
						System.out.println("Please select 4 to register car.");
					}
				}
				break;
			case 42:
				loggedInCustomer = Utility.validUser(email);		//check if user login
				System.out.println("EDIT EXISTING CAR BRAND");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					regID = carObj.getRegID();
					carObj.editCarBrand(regID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("There is no registered car.");  
						System.out.println("Please select 4 to register car.");
					}
				}
				break;
			case 43:
				loggedInCustomer = Utility.validUser(email);		//check if user login
				System.out.print("EDIT EXISTING CAR MODEL");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					regID = carObj.getRegID();
					carObj.editCarModel(regID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("There is no registered car.");  
						System.out.println("Please select 3 to register car.");
					}
				}
				break;
			case 44:
				loggedInCustomer = Utility.validUser(email);		//check if user login
				System.out.print("EDIT EXISTING CAR NUMBER");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					regID = carObj.getRegID();
					carObj.editCarNumber(regID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("There is no registered car.");  
						System.out.println("Please select 3 to register car.");
					}
				}
				break;
			case 45:
				loggedInCustomer = Utility.validUser(email);
				System.out.print("EDIT EXISTING CAR DESCRIPTION");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					regID = carObj.getRegID();
					carObj.editCarDescription(regID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("There is no registered car.");  
						System.out.println("Please select 3 to register car.");
					}
				}
				break;
			case 46:
				loggedInCustomer = Utility.validUser(email);
				System.out.print("EDIT EXISTING BASE PRICE");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					regID = carObj.getRegID();
					carObj.editCarBasePrice(regID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("There is no registered car.");  
						System.out.println("Please select 3 to register car.");
					}
				}
				break;
			case 47:
				loggedInCustomer = Utility.validUser(email);
				System.out.print("EDIT ALL EXISTING PROPERTIES");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					regID = carObj.getRegID();
					carObj.editCarRegNum(regID);
					carObj.editCarBasePrice(regID);
					carObj.editCarBrand(regID);
					carObj.editCarDescription(regID);
					carObj.editCarModel(regID);
					carObj.editCarNumber(regID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("There is no registered car.");  
						System.out.println("Please select 3 to register car.");
					}
				}
				break;
			case 48:
				loggedInCustomer = Utility.validUser(email);
				System.out.print("DELETE EXISTING CAR REGISTRATION");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					regID = carObj.getRegID();
					carObj.deleteCar(regID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("There is no registered car.");  
						System.out.println("Please select 3 to register car.");
					}
				}
				break;
			case 5:
				System.out.print("RENT A CAR");  //Create menu -
				System.out.println(" \n");
				break;
			case 51:
				System.out.println("\n\t\t\t SHOW AVAILABLE CARS ");  //Create menu -
				System.out.println("-------------------------------------------------------------------------------------------------------");	
				carRental.getAvailableCars();
				System.out.println(" \n");
				break;
			case 52:
				System.out.println("\n\t\t\t SHOW HIRED CARS");  //Create menu -
				System.out.println("-------------------------------------------------------------------------------------------------------");	
				carRental.showHiredCars();
				break;
			case 53:
				System.out.println("\n\t\t\t HIRE A CAR");  //Create menu -
				loggedInCustomer = Utility.validUser(email);		//check if user login
				System.out.println("Login Customer: " + loggedInCustomer);
				if(!"".equals(loggedInCustomer)) {
					System.out.println("-------------------------------------------------------------------------------------------------------");	
					Customer cust = Utility.getCustomer(loggedInCustomer);
					Map <String,List<CarRegistration>> map = carRental.getAvailableCars();
					carRental.hireCar(map, cust.getCustomerID());
				}else {
					//System.out.println("You are not logged in. Kindly select 1 to login.");
				}
				break;
			case 6:
				System.out.print("\n\t\t\t RETURN A CAR");	//show number of registered customers, registered cars
				break;
			case 7:
				System.out.print("LOGOUT");
				break;
			default:
				System.out.print("INVALID OPTION!");
				break;
			}
		}while(true);
	}
	
	

}
