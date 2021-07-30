package com.system.util;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.system.model.CarRentalModel;
import com.system.pojo.CarRegistration;

public class Utility {
	
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
	public static void loadData(String fileName)throws Exception {		//file handling
		File file = new File ("inputData.txt");
		Scanner scanner = new Scanner(file);							//reading from file
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;		//import list
		
		while(scanner.hasNextLine()) {
			CarRegistration carReg = new CarRegistration (); //car object
			String data = scanner.nextLine();
			String[] dataArray = data.split(",");
			carReg.setCarBrand(dataArray[0]);
			carReg.setCarModel(dataArray[1]);
			carReg.setCarNumber(dataArray[2]);
			carReg.setRegNumber(dataArray[3]);
			carReg.setBasePrice(Double.parseDouble(dataArray[5]));	//Double.parseDouble	
			carReg.setRegID(SequenceGenerator.getCarNext());
			carReg.setCarDescription(dataArray[4]);	
			
			if(map.get("regList")!=null) { 					//registration details
				List list = map.get("regList");
				list.add(carReg);
				map.put("regList",list);
			}else {
				List list = new ArrayList();				//ctrl space
				list.add(carReg);
				//map.put("regList",list);
				map.put(carReg.getRegID(), list);
			}
		}
	}
	public static void storeData(Map <String,List <CarRegistration>> map, String fileName)throws Exception {
		for(Map.Entry <String,List<CarRegistration>> set: map.entrySet()) {
			String regID = set.getKey();
			CarRegistration car = set.getValue().get(0);
			//continue
		}
	}
}
