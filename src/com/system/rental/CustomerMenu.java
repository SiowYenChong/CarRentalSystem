
 //Customer menu

package com.system.rental;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import com.system.model.CarRentalModel;
import com.system.pojo.CarRegistration;
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
		private String customerPassword;
		private String reenterPassword;
		
		public void showCustList() throws Exception{
			Map<String, List<Customer>> custMap = Utility.loadCustData(System.getProperty("user.dir")+"/custData.txt");	
			//Map <String,List<CarRegistration>> hiredCarMap = Utility.loadData(); //need to load the cars
			boolean noCar = true;
			for(Map.Entry <String,List<Customer>> set: custMap.entrySet()) {
				String key = set.getKey();
				List<Customer> custList = set.getValue();
				if(!custList.isEmpty()) {
					for(Customer cust : custList) {
						System.out.println(cust);
					}
				}
			}
		}
		public Entry<String,List<Customer>> getLastEntry(Map<String,List<Customer>> map){
			List<Entry<String,List<Customer>>> entryList =  new ArrayList<Map.Entry<String,List<Customer>>>(map.entrySet());
			Entry<String, List<Customer>> lastEntry = (entryList.size()>0)?entryList.get(entryList.size()-1):null;
			return lastEntry ;
		}
		public String customerRegistration(String emailID) throws Exception{
			Scanner scanner = new Scanner(System.in);
			System.out.println("\nWELCOME TO CUSTOMER REGISTRATION MENU");

			System.out.println("Email: ");
			customerEmail = scanner.nextLine();
			boolean valid = Utility.isValidEmail(customerEmail);
			List<String> emails = Utility.getCustEmail();
			while(emails.contains(customerEmail) || !valid){
				if(emails.contains(customerEmail)) {
					System.out.println("This email is registered in our system. Kindly insert another email.");
				}
				if(valid) {
					
				}else {
					System.out.println("Email pattern is wrong.");
				}
				System.out.println("Email: ");
				customerEmail = scanner.nextLine();
				valid =  Utility.isValidEmail(customerEmail);
			}
			
			System.out.println("Password: ");
			customerPassword = scanner.nextLine();
			do {
				System.out.println("Re-enter password: ");
				reenterPassword = scanner.nextLine();
				if(reenterPassword.equals(customerPassword)) {
					
				}else {
					System.out.println("Password mismatch. Retype new password again");
				}
			}while(!reenterPassword.equals(customerPassword));
			
			System.out.println("Name: ");
			customerName = scanner.nextLine();

			System.out.println("DOB (dd/mm/yy):  ");
			customerBirthDate = scanner.nextLine();

			System.out.println("Mobile number: ");
			customerMobileNumber = scanner.nextLine();

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

			if(customerPassword != null && !"".equals(customerPassword))
			{
				cust.setPassword(customerPassword);
			}
			
			if(customerAddress != null && !"".equals(customerAddress))
			{
				cust.setCustomerAddress(customerAddress);
			}
			
			if(cust != null) 
			{
				Entry <String,List<Customer>> resultMap = getLastEntry(Utility.loadCustData(System.getProperty("user.dir")+"/custData.txt"));		//Entry: Map property gove both key and value
				int prevRegID = 0; 
				String key = "";		//reg id
				if(resultMap != null) {
						key = resultMap.getKey();		//dunno key but return last key, no entry return null
						key = key.replace("C", "");	
						prevRegID = Integer.parseInt(key);	//store previous reg id without 'V'
				}
				String reg = "C00";
				if(prevRegID <= 8) {
					reg = reg + (prevRegID + 1);
				}else if(prevRegID > 8 && prevRegID < 98) {
					reg = "C0" + (prevRegID + 1);
				}else {
					reg = "C" + (prevRegID + 1);
				}
				cust.setCustomerID(reg);
			}
			
			//HERE
			Map<String,List<Customer>> user =  CarRentalModel.user;
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
				Utility.storeCustData(user);
			}
			System.out.println("The customer details are " + user.get(cust.getCustomerID()));
			return cust.getCustomerID();
		}

		public boolean checkAlphabet(String input) 
		{
			boolean result = false;

			result = input.matches(".\\d.");
			return !result;

		}
		
		
		public void custEdit(String customerID,String msg) //msg=message
		{		
			Scanner scanner = new Scanner(System.in);
			Map<String, List<Customer>> map =  CarRentalModel.user;
			List<Customer> obj = map.get(customerID);					//<Customer>
			System.out.println(msg);
			String input = scanner.nextLine();
			
		}
		
		public void getCustomerDetails() {
			String adminUsername;
			String adminPassword;
			
		}
		public String getPassword(String adminUsername) {
			String pwd = "";			//pwd=password
			if(!adminUsername.equalsIgnoreCase("admin")) {
				throw new NullPointerException("Invalid admin ID");
			}else {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dt = new Date();
				String date = sdf.format(dt);
				pwd = "admin" + date;
			}
			return pwd;
		}
		public boolean checkCustomerList() {
			Map<String, List<Customer>> map = CarRentalModel.user;
			return map.isEmpty();
		}
		
		public void editCustomerName(String customerID, boolean flag) throws Exception
		{
			Scanner scanner1 = new Scanner(System.in);
			Map<String, List<Customer>> map = Utility.loadCustData(System.getProperty("user.dir")+"/custData.txt");		
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
					System.out.println("after edit map is "+map.get(customerID));
			}else {
				System.out.println("Customer ID doesnt exist.");
			}
		}
		
		public void editCustomerBirthDate(String customerID, boolean flag) throws Exception
		{
			Scanner scanner1 = new Scanner(System.in);
			Map<String, List<Customer>> map =  Utility.loadCustData(System.getProperty("user.dir")+"/custData.txt");				
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
					System.out.println("after edit map is "+map.get(customerID));
			}else {
				System.out.println("Customer ID doesnt exist.");
			}
		}
		
		public void editCustomerMobileNumber(String customerID, boolean flag) throws Exception
		{
			Scanner scanner1 = new Scanner(System.in);
			Map<String, List<Customer>> map =  Utility.loadCustData(System.getProperty("user.dir")+"/custData.txt");				
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
					System.out.println("after edit map is "+map.get(customerID));
			}else {
				System.out.println("Customer ID doesnt exist.");
			}
		}
		
		public void editCustomerEmail(String customerID, boolean flag) throws Exception
		{
			Scanner scanner1 = new Scanner(System.in);
			Map<String, List<Customer>> map =  Utility.loadCustData(System.getProperty("user.dir")+"/custData.txt");				
			String inputValue;
			System.out.println("Enter new email: ");
			inputValue = scanner1.nextLine();
			List<String> emails = Utility.getCustEmail();
			boolean valid = Utility.isValidEmail(inputValue);
			do {
				if(emails.contains(inputValue)) {
					System.out.println("This email is registered in our system. Kindly insert another email.");
				}
				if(valid) {
				
				}else {
					System.out.println("Email pattern is wrong.");
				}
				System.out.println("Enter new email: ");
				inputValue = scanner1.nextLine();
				valid =  Utility.isValidEmail(inputValue);
			}while(emails.contains(inputValue) || !valid);
			Customer obj = (null!=map.get(customerID))? map.get(customerID).get(0): null;
			if(null != obj) {
				obj.setCustomerEmail(inputValue);
				List<Customer> list = new ArrayList<>();
				list.add(obj);
				Utility.storeCustData(map);	
				map.put(customerID, list);
				if(flag)
					System.out.println("after edit map is "+map.get(customerID));
			}else {
				System.out.println("Customer ID doesnt exist.");
			}
		}
		
		public void editCustomerAddress(String customerID, boolean flag) throws Exception
		{
			Scanner scanner1 = new Scanner(System.in);
			Map<String, List<Customer>> map =  Utility.loadCustData(System.getProperty("user.dir")+"/custData.txt");				
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
					System.out.println("after edit map is "+map.get(customerID));
			}else {
				System.out.println("Customer ID doesnt exist.");
			}
		}
		
		public void editAll(String customerID) throws Exception
		{
			Map<String, List<Customer>> map =  Utility.loadCustData(System.getProperty("user.dir")+"/custData.txt");			
			Customer obj = (null!=map.get(customerID))? map.get(customerID).get(0): null;
			if(null != obj) {	
				editCustomerName(customerID, false);
				editCustomerBirthDate(customerID, false);
				editCustomerMobileNumber(customerID, false);
				editCustomerEmail(customerID, false);
				editCustomerAddress(customerID, false);
				Utility.storeCustData(map);	
				System.out.println("after edit map is "+map.get(customerID));
			}else {
				System.out.println("Customer ID doesnt exist.");
			}
		}
		
		public void deleteCustomer(String customerID) throws Exception
		{
			Scanner scanner1 = new Scanner(System.in);
			
			Utility.loadCustData(System.getProperty("user.dir")+"/custData.txt");
			Map<String, List<Customer>> map = CarRentalModel.user;		
			System.out.println(map);
			String inputValue;
			System.out.println("Are you sure to delete " + customerID + " : (Y/N)");
			inputValue = scanner1.nextLine();
			if(inputValue.equalsIgnoreCase("Y")) 
			{
					System.out.println(">"+customerID+"<");
					List<Customer> obj = map.remove(customerID);
					if(null!=obj) {
						System.out.println("Customer's information has been deleted successfully.");
					}
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
		public boolean validateCust(String custID1, String custID2) {
			boolean result = true;
			if(!custID1.equals(custID2)) {
				result = false;
				System.out.println("You are not allowed to edit "+custID2+" details");
			}
			return result;
		}
		public String getCustID(String msg) throws Exception
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("\nConfirm customer registration ID to "+msg+" : ");
			String inputValue = scanner.nextLine();
			return inputValue;
		}

		
		public void editCustomerName(Scanner scanner, Customer obj) 
		{
			String inputValue;
			System.out.println("\nConfirm customer name to edit: ");
			inputValue = scanner.nextLine();
			//obj.setCustomerName(inputValue);
		}

		private void editCustomerBirthDate(Scanner scanner, Customer obj) 
		{
			String inputValue;
			System.out.println("\nConfirm customer birth date to edit: ");
			inputValue = scanner.nextLine();
			obj.setCustomerBirthDate(inputValue);
		}
		
		private void editCustomerMobileNumber(Scanner scanner, Customer obj) 
		{
			String inputValue;
			System.out.println("\nConfirm customer mobile number to edit: ");
			inputValue = scanner.nextLine();
			obj.setCustomerMobileNumber(inputValue);
		}
		
		private void editCustomerEmail(Scanner scanner, Customer obj) 
		{
			String inputValue;
			System.out.println("\nConfirm customer email to edit: ");
			inputValue = scanner.nextLine();
			obj.setCustomerEmail(inputValue);
		}
		
		private void editCustomerAddress(Scanner scanner, Customer obj) 
		{
			String inputValue;
			System.out.println("\nConfirm customer address to edit: ");
			inputValue = scanner.nextLine();
			obj.setCustomerAddress(inputValue);
		}
}

