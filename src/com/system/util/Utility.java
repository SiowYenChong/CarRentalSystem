package com.system.util;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.system.model.CarRentalModel;
import com.system.pojo.CarRegistration;
import com.system.pojo.Customer;

public class Utility {
	public static String getFormat(double input) {
		DecimalFormat df = new DecimalFormat("###.00");
		BigDecimal bd = new BigDecimal(input);
		return df.format(input);	
	}
	public boolean checkLogin(Scanner scanner) {
		boolean result = false;
		System.out.println("Kindly enter your email..");	//prompt user to login first
		String email = scanner.nextLine();
		Map<String,Boolean> users = CarRentalModel.loginUsers;		
		if(users != null && users.get(email) == null) {		//map is not null, map not user id
			System.out.println("You are not login. Kindly select option 1.");
			result = true;
		}
		return result;
	}
	public static String validUser(String emailID) {
		String email=null;
		String result = "";
		Scanner scanner = new Scanner(System.in);
		Map<String,Boolean> users = CarRentalModel.loginUsers; //map is not null, map not user id
		if(users != null && users.get(emailID) == null) {	
			System.out.println("\nKindly enter your email..");	//prompt user to login first
			email = scanner.nextLine();	
			System.out.println("You are not login. Kindly select option 1.");
		}else {
			result = emailID;
		}
		return result;
	}
	public static Map<String, List<CarRegistration>> loadData()throws Exception {		//file handling
		File file = new File ("inputData.txt");
		Scanner scanner = new Scanner(file);							//reading from file
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;		//import list
		
		while(scanner.hasNextLine()) {
			CarRegistration carReg = new CarRegistration (); //car object
			String data = scanner.nextLine();
			String[] dataArray = data.split(",");
			carReg.setRegID(dataArray[0]);
			carReg.setCarBrand(dataArray[1]);
			carReg.setCarModel(dataArray[2]);
			carReg.setCarNumber(dataArray[3]);
			carReg.setRegNumber(dataArray[4]);
			carReg.setBasePrice(Double.parseDouble(dataArray[6]));	//Double.parseDouble	
			carReg.setRegID(SequenceGenerator.getCarNext());
			carReg.setCarDescription(dataArray[5]);	
			
			if(map.get(carReg.getRegID())!=null) { 					//registration details
				List list = map.get(carReg.getRegID());
				list.add(carReg);
				map.put(carReg.getRegID(), list);
			}else {
				List list = new ArrayList();				//ctrl space
				list.add(carReg);
				//map.put("regList",list);
				map.put(carReg.getRegID(), list);
			}
		}
		return map;
	}
	//Toyota,Vios,AW123,1001,Used 2 years,100000
	public static void storeData(Map <String,List <CarRegistration>> map)throws Exception {
		String fileName = System.getProperty("user.dir")+"/inputData.txt";
		String regID;
		String name;
		String model;
		String plate;
		String regNum;
		String desc;
		double basePrice;
		String startDate;
		String endDate;
		String rental;
		String data = "";
		CarRegistration car;
		for(Map.Entry <String,List<CarRegistration>> set: map.entrySet()) {
			//System.out.println("Testing");
			regID = set.getKey();
			car = set.getValue().get(0);
			name = car.getCarBrand();
			model = car.getCarModel();
			plate = car.getCarNumber();
			regNum = car.getRegNumber();
			desc = car.getCarDescription();
			basePrice = car.getBasePrice();
			startDate = ""+car.getStartDate();	//convert to string with ""
			endDate = ""+car.getEndDate();
			rental = ""+car.getCarRental();
			data = data + regID + "," + name + "," + model + "," + plate + "," + regNum + "," + desc + "," + basePrice;
			if(!startDate.equals("") && !endDate.equals("")) {
				data = data + "," + startDate + "," + endDate + "," + rental;
			}
			data = data + "\n";
			Path path = Paths.get(fileName);		//Get old obj from file 
			Files.write(path, data.getBytes());		//getBytes() = convert data to byte
		}
	}
	public static void storeCustData(Map <String,List <Customer>> map)throws Exception {
		String fileName = System.getProperty("user.dir")+"/custData.txt";
		String custID;
		String name;
		String birthDate;
		String phoneNum;
		String email;
		String address;
		String data = "";
		Customer cust;
		for(Map.Entry <String,List<Customer>> set: map.entrySet()) {
			//System.out.println("Testing");
			custID = set.getKey();
			cust = set.getValue().get(0);
			name = cust.getCustomerName();
			birthDate = cust.getCustomerBirthDate();
			phoneNum = cust.getCustomerMobileNumber();
			email = cust.getCustomerEmail();
			address = cust.getCustomerAddress();
			data = data + custID + ";" + name + ";" + birthDate + ";" + phoneNum + ";" + email + ";" + address + "\n";
			Path path = Paths.get(fileName);		//Get old obj from file 
			Files.write(path, data.getBytes());		//getBytes() = convert data to byte
		}
	}
	public static void loadCustData(String fileName)throws Exception {		//file handling
		File file = new File (fileName);
		Scanner scanner = new Scanner(file);							//reading from file
		Map<String, List<Customer>> map = CarRentalModel.user;		//import list
		
		while(scanner.hasNextLine()) {
			Customer cust = new Customer (); //car object
			String data = scanner.nextLine();
			String[] dataArray = data.split(";");
			cust.setCustomerID(dataArray[0]);
			cust.setCustomerName(dataArray[1]);
			cust.setCustomerBirthDate(dataArray[2]);
			cust.setCustomerMobileNumber(dataArray[3]);
			cust.setCustomerEmail(dataArray[4]);
			cust.setCustomerAddress(dataArray[5]);
			SequenceGenerator.getCustomerNext();
			
			if(map.get(dataArray[0])!=null) { 					//registration details
				List list = map.get(dataArray[0]);
				list.add(cust);
				map.put("regList",list);
			}else {
				List list = new ArrayList();				//ctrl space
				list.add(cust);
				//map.put("regList",list);
				map.put(cust.getCustomerID(), list);
			}
		}
	}
}
