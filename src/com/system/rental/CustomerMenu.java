
 //Customer menu

package com.system.rental;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.system.model.CarRentalModel;
import com.system.pojo.Customer;
import com.system.util.SequenceGenerator;
import com.system.util.Utility;

public class CustomerMenu 
{
		private String customerName;
		private String customerBirthDate;
		private String customerMobileNumber;
		private String customerEmail;
		private String customerAddress;

		public String customerRegistration(String emailID)
		{
			//here
			String result = Utility.validUser(emailID);
			if(result.equals("")) 
			{
				return "";
			}

			Scanner scanner = new Scanner(System.in);
			System.out.println("\nWELCOME TO CUSTOMER REGISTRATION MENU");

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
			
			if(cust != null) 
			{
				cust.setCustomerID(SequenceGenerator.getCustomerNext());
			}
			
			//HERE
			Map<String,List<Customer>> user = CarRentalModel.user;
			if(user.get("userList")!=null) //registration details
			{ 	
				List userList = user.get("userList");
				userList.add(cust);						//use cust
				user.put(cust.getCustomerID(),userList);
			}
			
			else 
			{
				List userList = new ArrayList<>();		//ctrl space
				userList.add(cust);
				user.put(cust.getCustomerID(),userList);
			}
			System.out.println("The customer details are " + user);
			return cust.getCustomerID();
		}

		public boolean checkAlphabet(String input) 
		{
			boolean result = false;

			result = input.matches(".\\d.");
			return !result;

		}
		
		/*
		 * public void customerMenu() { Scanner scanner = new Scanner(System.in);
		 * System.out.println("\nSelect option: ");
		 * System.out.println("1. REGISTER CUSTOMER");
		 * System.out.println("2. EDIT EXISTING CUSTOMER");
		 * System.out.println("3. DELETE EXISITNG CUSTOMER"); int input =
		 * scanner.nextInt();
		 * 
		 * 
		 * if(input==1) { customerRegistration(null); }
		 * 
		 * else if(input==2) { editCustomer(); }
		 * 
		 * else if(input == 3) {
		 * 
		 * }
		 * 
		 * else { System.out.println("INVALID OPTION!"); } }
		 */
		
		/*
		 * public String editCustomer() { Scanner scanner = new Scanner(System.in);
		 * Map<String, List<Customer>> map = CarRentalModel.user;
		 * 
		 * System.out.println("Enter Customer ID: "); String input = scanner.nextLine();
		 * if(map.get(input) != null) { Customer obj = (Customer) map.get(input); //obj
		 * = object System.out.println("Values are " + obj); editOption(input); }
		 * 
		 * else {
		 * System.out.println("You have yet to register. Please kindly register."); }
		 * 
		 * return input; }
		 */
		
		public void custEdit(String customerID,String msg) //msg=message
		{		
			Scanner scanner = new Scanner(System.in);
			Map<String, List<Customer>> map = CarRentalModel.user;
			List<Customer> obj = map.get(customerID);					//<Customer>
			System.out.println(msg);
			String input = scanner.nextLine();
			
		}
		
		/*
		 * public void editOption(String customerID) { Scanner scanner = new
		 * Scanner(System.in);
		 * System.out.println("Select car properties to be edited: ");
		 * System.out.println("1. Name"); System.out.println("2. DOB (dd/mm/yy)");
		 * System.out.println("3. Mobile number"); System.out.println("4. Email");
		 * System.out.println("5. Address"); System.out.println("6. Edit All");
		 * 
		 * int input = scanner.nextInt();
		 * 
		 * //Map<String, List<CarRegistration>> map = CarRentalModel.reg; Customer obj =
		 * null;
		 * 
		 * String inputValue = ""; System.out.println("Customer ID"+customerID); do {
		 * input=Input.inputInt("Select option: "); switch(input) { case 1:
		 * //editCustomerName(customerID, obj); break; case 2:
		 * editCustomerBirthDate(scanner, obj); break; case 3:
		 * editCustomerMobileNumber(scanner, obj); break; case 4:
		 * editCustomerEmail(scanner, obj); break; case 5: editCustomerAddress(scanner,
		 * obj); break; case 6: //editCarRegNum(obj); editCustomerName(scanner, obj);
		 * editCustomerBirthDate(scanner, obj); editCustomerMobileNumber(scanner, obj);
		 * editCustomerEmail(scanner, obj); editCustomerAddress(scanner, obj); break;
		 * default: System.out.println("INVALID OPTION!"); break; }
		 * 
		 * }while(true);
		 * 
		 * }
		 */
		
		public boolean checkCustomerList() {
			Map<String, List<Customer>> map = CarRentalModel.user;
			return map.isEmpty();
		}
		
		public void editCustomerName(String customerID, boolean flag) throws Exception
		{
			Scanner scanner1 = new Scanner(System.in);
			Map<String, List<Customer>> map = CarRentalModel.user;		
			String inputValue;
			System.out.println("Enter new name: ");
			inputValue = scanner1.nextLine();
			Customer obj = (null!=map.get(customerID))? map.get(customerID).get(0): null;
			if(null != obj) {
				obj.setCustomerName(inputValue);
				List<Customer> list = new ArrayList<>();
				list.add(obj);
				Utility.storeCustData(map);	
				map.put(customerID, list);
				if(flag) 
					System.out.println("after edit map is "+map);
			}else {
				System.out.println("Customer ID doesnt exist.");
			}
		}
		
		public void editCustomerBirthDate(String customerID, boolean flag) throws Exception
		{
			Scanner scanner1 = new Scanner(System.in);
			Map<String, List<Customer>> map = CarRentalModel.user;		
			String inputValue;
			System.out.println("Enter new date of birth: ");
			inputValue = scanner1.nextLine();
			Customer obj = (null!=map.get(customerID))? map.get(customerID).get(0): null;
			if(null != obj) {
					obj.setCustomerBirthDate(inputValue);
					List<Customer> list = new ArrayList<>();
					list.add(obj);
					Utility.storeCustData(map);	
					map.put(customerID, list);
				if(flag) 
					System.out.println("after edit map is "+map);
			}else {
				System.out.println("Customer ID doesnt exist.");
			}
		}
		
		public void editCustomerMobileNumber(String customerID, boolean flag) throws Exception
		{
			Scanner scanner1 = new Scanner(System.in);
			Map<String, List<Customer>> map = CarRentalModel.user;		
			String inputValue;
			System.out.println("Enter new mobile number: ");
			inputValue = scanner1.nextLine();
			Customer obj = (null!=map.get(customerID))? map.get(customerID).get(0): null;
			if(null != obj) {
				obj.setCustomerMobileNumber(inputValue);
				List<Customer> list = new ArrayList<>();
				list.add(obj);
				Utility.storeCustData(map);	
				map.put(customerID, list);
				if(flag)
					System.out.println("after edit map is "+map);
			}else {
				System.out.println("Customer ID doesnt exist.");
			}
		}
		
		public void editCustomerEmail(String customerID, boolean flag) throws Exception
		{
			Scanner scanner1 = new Scanner(System.in);
			Map<String, List<Customer>> map = CarRentalModel.user;		
			String inputValue;
			System.out.println("Enter new email: ");
			inputValue = scanner1.nextLine();
			Customer obj = (null!=map.get(customerID))? map.get(customerID).get(0): null;
			if(null != obj) {
				obj.setCustomerEmail(inputValue);
				List<Customer> list = new ArrayList<>();
				list.add(obj);
				Utility.storeCustData(map);	
				map.put(customerID, list);
				if(flag)
					System.out.println("after edit map is "+map);
			}else {
				System.out.println("Customer ID doesnt exist.");
			}
		}
		
		public void editCustomerAddress(String customerID, boolean flag) throws Exception
		{
			Scanner scanner1 = new Scanner(System.in);
			Map<String, List<Customer>> map = CarRentalModel.user;		
			String inputValue;
			System.out.println("Enter new address: ");
			inputValue = scanner1.nextLine();
			Customer obj = (null!=map.get(customerID))? map.get(customerID).get(0): null;
			
			if(null != obj) {
				obj = map.get(customerID).get(0);
				obj.setCustomerAddress(inputValue);
				List<Customer> list = new ArrayList<>();
				list.add(obj);
				Utility.storeCustData(map);	
				map.put(customerID, list);
				if(flag)
					System.out.println("after edit map is "+map);
			}else {
				System.out.println("Customer ID doesnt exist.");
			}
		}
		
		public void editAll(String customerID) throws Exception
		{
			Map<String, List<Customer>> map = CarRentalModel.user;	
			Customer obj = (null!=map.get(customerID))? map.get(customerID).get(0): null;
			if(null != obj) {	
				editCustomerName(customerID, false);
				editCustomerBirthDate(customerID, false);
				editCustomerMobileNumber(customerID, false);
				editCustomerEmail(customerID, false);
				editCustomerAddress(customerID, false);
				Utility.storeCustData(map);	
				System.out.println("after edit map is "+map);
			}else {
				System.out.println("Customer ID doesnt exist.");
			}
		}
		
		public void deleteCustomer(String customerID) throws Exception
		{
			Scanner scanner1 = new Scanner(System.in);
			Map<String, List<Customer>> map = CarRentalModel.user;		
			String inputValue;
			System.out.println("Are you sure to delete " + customerID + " : (Y/N)");
			inputValue = scanner1.nextLine();
			if(inputValue.equalsIgnoreCase("Y")) 
			{
			
					List<Customer> obj = map.remove(customerID);
					if(null!=obj)
						System.out.println("Customer's information has been deleted successfully.");
					else
						System.out.println("The customer ID is invalid.");
	
				
			}
			
			else if(inputValue.equalsIgnoreCase("N")) 
			{
				System.out.println("You selected not to delete customer's information.");
			}
			
			else 
			{
				System.out.println("INVALID OPTION! Please try again.");
			}
			Utility.storeCustData(map);	
			System.out.println("After deletion, map is "+map);

		}
		
		/*
		 * private void editCarBrand(String regID, CarRegistration obj) { Scanner
		 * scanner1 = new Scanner(System.in); Map<String, List<CarRegistration>> map =
		 * CarRentalModel.reg; String inputValue;
		 * System.out.println("Confirm car brand to edit: "); inputValue =
		 * scanner1.nextLine(); System.out.println("Reg ID"+regID); obj =
		 * map.get(regID).get(0); obj.setCarBrand(inputValue); }
		 */
		public String getCustID(String msg) throws Exception
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("Confirm customer registration ID to "+msg+" : ");
			String inputValue = scanner.nextLine();
			return inputValue;
		}

		
		public void editCustomerName(Scanner scanner, Customer obj) 
		{
			String inputValue;
			System.out.println("Confirm customer name to edit: ");
			inputValue = scanner.nextLine();
			//obj.setCustomerName(inputValue);
		}

		private void editCustomerBirthDate(Scanner scanner, Customer obj) 
		{
			String inputValue;
			System.out.println("Confirm customer birth date to edit: ");
			inputValue = scanner.nextLine();
			obj.setCustomerBirthDate(inputValue);
		}
		
		private void editCustomerMobileNumber(Scanner scanner, Customer obj) 
		{
			String inputValue;
			System.out.println("Confirm customer mobile number to edit: ");
			inputValue = scanner.nextLine();
			obj.setCustomerMobileNumber(inputValue);
		}
		
		private void editCustomerEmail(Scanner scanner, Customer obj) 
		{
			String inputValue;
			System.out.println("Confirm customer email to edit: ");
			inputValue = scanner.nextLine();
			obj.setCustomerEmail(inputValue);
		}
		
		private void editCustomerAddress(Scanner scanner, Customer obj) 
		{
			String inputValue;
			System.out.println("Confirm customer address to edit: ");
			inputValue = scanner.nextLine();
			obj.setCustomerAddress(inputValue);
		}
}

