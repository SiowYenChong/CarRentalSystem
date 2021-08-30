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
import java.util.Scanner;
import com.system.pojo.Admin;
import com.system.pojo.CarRegistration;
import com.system.pojo.Customer;
import com.system.util.Utility;

public class Main {
	public static void displayMenu() {
		System.out.println("WELCOME TO CAR RENTAL SYSTEM");
		System.out.println("Press ctrl-z to exit existing function");
		System.out.println("Options:");
		System.out.println("1. Login");	
		System.out.println("2. Main Menu"); 
		System.out.println("3. Register Customer"); 
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
		System.out.println("5. Rent a Car");  //remove, not a function!
		System.out.println("   51. Show available cars");	//enter carRegID, startDate & endDate
		System.out.println("   52. Already hired cars");
		System.out.println("   53. Hire a car");
		System.out.println("6. Return a Car");
		System.out.println("7. Display customer details");
	}
	public static void main(String[] args) {	//handling error
		int inputValue;
		displayMenu();
		String email = "";
		String regID;
		String custID;
		String loggedInCustomer;
		try {
		CarRentalProcess carRental = new CarRentalProcess();
		Admin admin = new Admin();
		CarRegistrationMenu carObj = new CarRegistrationMenu();
		Customer cust = new Customer();
		CustomerMenu custObj = new CustomerMenu();
		do {
			inputValue=Input.inputInt("Select option (Press 2 - View menu): ");
			switch(inputValue) {
			case 1:
				System.out.println("LOGIN");
				LoginTest loginObj = new LoginTest();
				email = loginObj.logIn();							//("user1", "abcd123");											//("admin", "abcd123");
				break;
			case 2:
				displayMenu();
				break;
			case 3:
				//loggedInCustomer = Utility.validUser(email);
				//cust = Utility.getCustomer(loggedInCustomer);
				//System.out.print("REGISTER CUSTOMER");
				custID = custObj.customerRegistration(email);
				break;
			case 31:
				loggedInCustomer = Utility.validUser(email);		//check if user login
				if(!"".equals(loggedInCustomer))
					System.out.println("EDIT EXISTING CUSTOMER NAME");
				cust = Utility.getCustomer(loggedInCustomer);
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					custID = custObj.getCustID("edit");
					if(custObj.validateCust(cust.getCustomerID(), custID))
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
				System.out.println("EDIT EXISTING CUSTOMER BIRTH DATE");
				loggedInCustomer = Utility.validUser(email);		//check if user login
				cust = Utility.getCustomer(loggedInCustomer);
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					custID = custObj.getCustID("edit");
					if(custObj.validateCust(cust.getCustomerID(), custID))
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
				System.out.println("EDIT EXISTING CUSTOMER MOBILE NUMBER");
				loggedInCustomer = Utility.validUser(email);		//check if user login
				cust = Utility.getCustomer(loggedInCustomer);
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					custID = custObj.getCustID("edit");
					if(custObj.validateCust(cust.getCustomerID(), custID))
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
				System.out.println("EDIT EXISTING CUSTOMER EMAIL");
				loggedInCustomer = Utility.validUser(email);		//check if user login
				cust = Utility.getCustomer(loggedInCustomer);
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					custID = custObj.getCustID("edit");
					if(custObj.validateCust(cust.getCustomerID(), custID))
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
				System.out.println("EDIT EXISTING CUSTOMER ADDRESS");
				loggedInCustomer = Utility.validUser(email);		//check if user login
				cust = Utility.getCustomer(loggedInCustomer);
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					custID = custObj.getCustID("edit");
					if(custObj.validateCust(cust.getCustomerID(), custID))
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
				cust = Utility.getCustomer(loggedInCustomer);
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					System.out.println("EDIT ALL EXISTING PROPERTIES");
					custID = custObj.getCustID("edit");
					if(custObj.validateCust(cust.getCustomerID(), custID))
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
				cust = Utility.getCustomer(loggedInCustomer);
				System.out.println("DELETE EXISTING CUSTOMER REGISTRATION");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					custID = custObj.getCustID("delete");
					if(custObj.validateCust(cust.getCustomerID(), custID))
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
				Scanner scanner = new Scanner(System.in);
				loggedInCustomer = Utility.validUser(email);
				if(!"".equals(loggedInCustomer))
					System.out.println("REGISTER NEW CAR");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList() && email.equals(admin.getCustomerEmail())) {
						carObj.doRegistration();
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("Only admin has access to this functionality");
					}
				}
				break;
			case 41:
				loggedInCustomer = Utility.validUser(email);		//check if user login
				if(!"".equals(loggedInCustomer))
					System.out.println("\nEDIT EXISTING CAR REGISTRATION NUMBER");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList() && email.equals(admin.getCustomerEmail())) {
					regID = carObj.getRegID("edit");
					carObj.editCarRegNum(regID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("Only admin has access to this functionality");
						
					}
				}
				break;
			case 42:
				loggedInCustomer = Utility.validUser(email);		//check if user login
				if(!"".equals(loggedInCustomer))
					System.out.println("EDIT EXISTING CAR BRAND");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList() && email.equals(admin.getCustomerEmail())) {
					regID = carObj.getRegID("edit");
					carObj.editCarBrand(regID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("Only admin has access to this functionality");
					}
				}
				break;
			case 43:
				loggedInCustomer = Utility.validUser(email);		//check if user login
				if(!"".equals(loggedInCustomer))
					System.out.println("EDIT EXISTING CAR MODEL");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList() && email.equals(admin.getCustomerEmail())) {
					regID = carObj.getRegID("edit");
					carObj.editCarModel(regID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("Only admin has access to this functionality");
					}
				}
				break;
			case 44:
				loggedInCustomer = Utility.validUser(email);		//check if user login
				if(!"".equals(loggedInCustomer))
					System.out.println("EDIT EXISTING CAR NUMBER");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList() && email.equals(admin.getCustomerEmail())) {
					regID = carObj.getRegID("edit");
					carObj.editCarNumber(regID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("Only admin has access to this functionality");
					}
				}
				break;
			case 45:
				loggedInCustomer = Utility.validUser(email);
				if(!"".equals(loggedInCustomer))
					System.out.println("EDIT EXISTING CAR DESCRIPTION");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList() && email.equals(admin.getCustomerEmail())) {
					regID = carObj.getRegID("edit");
					carObj.editCarDescription(regID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("Only admin has access to this functionality");
					}
				}
				break;
			case 46:
				loggedInCustomer = Utility.validUser(email);
				if(!"".equals(loggedInCustomer))
					System.out.println("EDIT EXISTING BASE PRICE");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList() && email.equals(admin.getCustomerEmail())) {
					regID = carObj.getRegID("edit");
					carObj.editCarBasePrice(regID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("Only admin has access to this functionality");
					}
				}
				break;
			case 47:
				loggedInCustomer = Utility.validUser(email);
				if(!"".equals(loggedInCustomer))
					System.out.println("EDIT ALL EXISTING PROPERTIES");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList() && email.equals(admin.getCustomerEmail())) {
					regID = carObj.getRegID("edit");
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
						System.out.println("Only admin has access to this functionality");
					}
				}
				break;
			case 48:
				loggedInCustomer = Utility.validUser(email);
				if(!"".equals(loggedInCustomer))
					System.out.print("DELETE EXISTING CAR REGISTRATION");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList() && email.equals(admin.getCustomerEmail())) {
					regID = carObj.getRegID("delete");
					carObj.deleteCar(regID);
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}else {
						System.out.println("Only admin has access to this functionality");
					}
				}
				break;
			case 5:
				System.out.println("RENT A CAR");  //Create menu -
				System.out.println(" \n");
				break;
			case 51:
				System.out.println("\n\t\t\t SHOW AVAILABLE CARS ");  //Create menu -
				System.out.println("-------------------------------------------------------------------------------------------------------");	
				loggedInCustomer = Utility.validUser(email);		//check if user login
				cust = Utility.getCustomer(loggedInCustomer); //pass customer id
				carRental.getAvailableCars(cust.getCustomerID());
				System.out.println(" \n");
				break;
			case 52:
				System.out.println("\n\t\t\t SHOW HIRED CARS");  //Create menu -
				System.out.println("-------------------------------------------------------------------------------------------------------");	
				loggedInCustomer = Utility.validUser(email);		//check if user login
				Customer cust1 = Utility.getCustomer(loggedInCustomer); //pass customer id
				carRental.hiredCarsByCustomer(cust1.getCustomerID());
				break;
			case 53:
				System.out.println("\n\t\t\t HIRE A CAR");  //Create menu -
				loggedInCustomer = Utility.validUser(email);		//check if user login
				//System.out.println("Login Customer: " + loggedInCustomer);
				if(!"".equals(loggedInCustomer)) {
					System.out.println("-------------------------------------------------------------------------------------------------------");	
					Customer customer = Utility.getCustomer(loggedInCustomer);
					Map <String,List<CarRegistration>> map = carRental.getAvailableCars(customer.getCustomerID());
					carRental.hireCar(map, customer.getCustomerID());
				}else {
					//System.out.println("You are not logged in. Kindly select 1 to login.");
				}
				break;
			case 6:
				System.out.println("\n\t\t\t RETURN A CAR");	//show number of registered customers, registered cars
				loggedInCustomer = Utility.validUser(email);
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList()) {
					Customer customer = Utility.getCustomer(loggedInCustomer);
					carRental.showHiredCars(customer.getCustomerID());
				}else {
					if("".equals(email)) {
						//System.out.println("User is not logged in");  
					}
				}
				
				break;
			case 7:
				loggedInCustomer = Utility.validUser(email);
				if(!"".equals(loggedInCustomer))
					System.out.println("CUSTOMER DETAILS (ADMIN ONLY)");
				if(!"".equals(loggedInCustomer) && !custObj.checkCustomerList() && email.equals(admin.getCustomerEmail())) {
					custObj.showCustList();
				}else {
					if("".equals(email)) {
						
					}else {
						System.out.println("Only admin has access to this functionality");
					}
				}
				break;
			case 8:
				System.out.println("LOGOUT");
				break;
			default:
				break;
			}
		}while(true);
		}catch(Exception e) {
			System.out.println("Car Rental System has encountered some error " + e.getMessage());
		}
	}
	
	

}
