package com.system.rental;
import java.util.ArrayList;
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
public class CarRegistrationMenu {
	private String brandName ;
	private String modelName ;
	private String plateNumber ;
	private String regNumber ;
	private double basePrice ;
	private String desc ;
	
	
	public void doRegistration() throws Exception{
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nWELCOME TO CAR REGISTRATION MENU");
		System.out.println("Enter Car Brand name: ");
		brandName = scanner.nextLine();
		System.out.println("Enter Car Model name: ");
		modelName = scanner.nextLine();
		System.out.println("Enter Carplate number: ");
		plateNumber = scanner.nextLine();
		System.out.println("Enter Car Registration number: ");
		regNumber = scanner.nextLine();
		System.out.println("Enter Car Description: ");
		desc = scanner.nextLine();
		System.out.println("Enter Car Base Price: ");
		basePrice = scanner.nextDouble();
		
		
		//get memory to class    instantiate
		CarRegistration carReg = new CarRegistration ();		//ctrl-space import a class
		if(brandName != null && !"".equals(brandName)) {		//not empty and not null
			if(checkAlphabet(brandName)) {
				carReg.setCarBrand(brandName);	
			}else {
				System.out.println("Brand name should not contain digits");
			}
			
		}
		if(modelName != null && !"".equals(modelName)) {		//not empty and not null
			carReg.setCarModel(modelName);	
		}
		if(plateNumber != null && !"".equals(plateNumber)) {		//not empty and not null
			carReg.setCarNumber(plateNumber);	
		}
		if(regNumber != null && !"".equals(regNumber)) {		//not empty and not null
			carReg.setRegNumber(regNumber);	
		}
		
		if(basePrice != 0) {		//not empty and not null
			carReg.setBasePrice(basePrice);	
		}
		Map <String,List<CarRegistration>> fileMap = null;
		if(carReg != null) {
			fileMap = Utility.loadData();
			int size = fileMap.size();
			Entry <String,List<CarRegistration>> resultMap = getLastEntry(fileMap);		//Entry: Map property gove both key and value
			int prevRegID = 0; 
			String key = "";		//reg id
			if(resultMap != null) {
				key = resultMap.getKey();		//dunno key but return last key, no entry return null
				key = key.replace("V", "");	
				prevRegID = Integer.parseInt(key);	//store previous reg id without 'V'
			}
			String reg = "V00";
			if(prevRegID <= 8) {
				reg = reg + (prevRegID + 1);
			}else if(prevRegID > 8 && prevRegID < 98) {
				reg = "V0" + (prevRegID + 1);
			}else {
				reg = "V" + (prevRegID + 1);
			}
			carReg.setRegID(reg);		//bring in sequence method
		}
		if(desc != null && !"".equals(desc)) {		//not empty and not null
			carReg.setCarDescription(desc);	
		}
		
													//Hashmap that retains insertion order
		Map<String, List<CarRegistration>> map = (fileMap == null)?
										new LinkedHashMap<String, List<CarRegistration>>(): fileMap;		//import list
		if(map.get("regList")!=null) { 	//registration details
			List list = map.get("regList");
			list.add(carReg);
			map.put("regList",list);
		}else {
			List list = new ArrayList();		
			list.add(carReg);
			//map.put("regList",list);
			map.put(carReg.getRegID(), list);
		}
		Utility.storeData(map,"add");		//What is exception
		System.out.println("The details are "+map);
	}
	//return last entry of map, add to previous value for reg num
	public Entry<String,List<CarRegistration>> getLastEntry(Map<String,List<CarRegistration>> map){
		List<Entry<String,List<CarRegistration>>> entryList =  new ArrayList<Map.Entry<String,List<CarRegistration>>>(map.entrySet());
		Entry<String, List<CarRegistration>> lastEntry = (entryList.size()>0)?entryList.get(entryList.size()-1):null;
		return lastEntry ;
	}
	public boolean checkAlphabet(String input) {
		Scanner scanner = new Scanner(System.in);
		boolean result = false;
		
		result = input.matches(".*\\d.*");
		return !result;
		
	}
	public void carRegMenu() throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSelect option: ");
		System.out.println("1. REGISTER NEW CAR");
		System.out.println("2. EDIT EXISTING CAR REGISTRATION");
		System.out.println("3. DELETE EXISITNG CAR REGISTRATION");
		int input = scanner.nextInt();
		
		
		if(input==1) {
			doRegistration();
		}else if(input==2) {
			editCarReg();
		}else if(input == 3) {
			
		}else {
			System.out.println("INVALID OPTION!");
		}
	}
	public String editCarReg() throws Exception {
		Scanner scanner = new Scanner(System.in);
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;
		
		System.out.println("Enter Car Registration ID: ");
		String input = scanner.nextLine();
		if(map.get(input) != null) {
			List obj = map.get(input);			//obj = object
			System.out.println("Values are "+obj);
			editOption(input);
		}else {
			System.out.println("You haven't register car.Kindly register car first.");
		}
		return input;
	}
	public void doEdit(String regID,String msg) {		//msg=message
		Scanner scanner = new Scanner(System.in);
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;
		List obj = map.get(regID);
		System.out.println(msg);
		String input = scanner.nextLine();
		
	}
	public void editOption(String regID) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Select car properties to be edited: ");
		System.out.println("1. Registration number");
		System.out.println("2. Car Brand");
		System.out.println("3. Car Model");
		System.out.println("4. Car Number");
		System.out.println("5. Car Description");
		System.out.println("6. Base Price");
		System.out.println("7. Edit All");
		int input = scanner.nextInt();
		
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;
		CarRegistration obj = null;			//get first value of car registration
		
		String inputValue = "";
		System.out.println("Reg ID"+regID);
		do {
			input=Input.inputInt("Select option: ");
			switch(input) {
				case 1:
					//editCarRegNum(obj);
					System.out.println("Confirm car registration ID to edit: ");
					inputValue = scanner.nextLine();
					obj.setRegNumber(inputValue);
					break;
				case 2:
					editCarBrand(regID);
					List <CarRegistration>list = new ArrayList(); 
					list.add(obj);
					map.put(regID, list);
					System.out.println("Map "+map);
					break;
				case 3:
					editCarModel(scanner, obj);
					break;
				case 4:
					editCarNumber(scanner, obj);
					break;
				case 5:
					editCarDescription(scanner, obj);
					break;
				case 6:
					editCarBasePrice(scanner, obj);
					break;
				case 7:
					//editCarRegNum(obj);
					editCarBrand(inputValue);
					editCarModel(scanner, obj);
					editCarNumber(scanner, obj);
					editCarDescription(scanner, obj);
					editCarBasePrice(scanner, obj);
					break;
				default:
					System.out.println("INVALID OPTION!");
					break;		
			}
			
		}while(true);
		
	}
	public void editCarRegNum(String regID) throws Exception{
		Scanner scanner1 = new Scanner(System.in);
		Map<String, List<CarRegistration>> map = Utility.loadData();		
		String inputValue;
		CarRegistration obj = (null!=map.get(regID))? map.get(regID).get(0): null;
		if(null != obj) {
			System.out.println("Enter new car registration number : ");
			inputValue = scanner1.nextLine();
			System.out.println("Reg ID"+regID);
			obj.setRegNumber(inputValue);
			List<CarRegistration> list = new ArrayList<>();
			list.add(obj);
			map.put(regID, list);
			Utility.storeData(map, "edit");		//What is exception
			System.out.println("after edit map is "+map);
		}else {
			System.out.println("Car ID doesnt exist.");
		}
	}
	public void editCarModel(String regID) throws Exception {
		Scanner scanner1 = new Scanner(System.in);
		Map<String, List<CarRegistration>> map = Utility.loadData();		
		String inputValue;
		CarRegistration obj = map.get(regID).get(0);
		if(null != obj) {
			System.out.println("Enter new car model : ");
			inputValue = scanner1.nextLine();
			System.out.println("Reg ID"+regID);
			obj.setCarModel(inputValue);
			List<CarRegistration> list = new ArrayList<>();
			list.add(obj);
			map.put(regID, list);
			Utility.storeData(map, "edit");	
			System.out.println("after edit map is "+map);
		}else {
			System.out.println("Car ID doesnt exist.");
		}
	}
	public void editCarNumber(String regID) throws Exception {
		Scanner scanner1 = new Scanner(System.in);
		Map<String, List<CarRegistration>> map = Utility.loadData();		
		String inputValue;
		CarRegistration obj = map.get(regID).get(0);
		if(null != obj) {
			System.out.println("Enter new car number : ");
			inputValue = scanner1.nextLine();
			System.out.println("Reg ID"+regID);
			obj.setCarNumber(inputValue);
			List<CarRegistration> list = new ArrayList<>();
			list.add(obj);
			map.put(regID, list);
			Utility.storeData(map, "edit");	
			System.out.println("after edit map is "+map);
		}else {
			System.out.println("Car ID doesnt exist.");
		}
	}
	public void editCarDescription(String regID) throws Exception  {
		Scanner scanner1 = new Scanner(System.in);
		Map<String, List<CarRegistration>> map = Utility.loadData();		
		String inputValue;
		CarRegistration obj = map.get(regID).get(0);
		if(null != obj) {
			System.out.println("Enter new car decription : ");
			inputValue = scanner1.nextLine();
			System.out.println("Reg ID"+regID);
			obj.setCarDescription(inputValue);
			List<CarRegistration> list = new ArrayList<>();
			list.add(obj);
			map.put(regID, list);
			Utility.storeData(map, "edit");
			System.out.println("after edit map is "+map);
		}else {
			System.out.println("Car ID doesnt exist.");
		}
	}
	public void editCarBasePrice(String regID) throws Exception {
		Scanner scanner1 = new Scanner(System.in);
		Map<String, List<CarRegistration>> map = Utility.loadData();		
		String inputValue;
		CarRegistration obj = map.get(regID).get(0);
		if(null != obj) {
			System.out.println("Enter new car base price : ");
			inputValue = scanner1.nextLine();
			System.out.println("Reg ID"+regID);
			obj.setBasePrice(Double.parseDouble(inputValue));
			List<CarRegistration> list = new ArrayList<>();
			list.add(obj);
			map.put(regID, list);
			Utility.storeData(map, "edit");
			System.out.println("after edit map is "+map);
		}else {
			System.out.println("Car ID doesnt exist.");
		}
	}
	public void editAll(String regID) throws Exception{
		Map<String, List<CarRegistration>> map = Utility.loadData();
		editCarModel(regID);
		editCarBrand(regID);
		editCarNumber(regID);
		editCarDescription(regID);
		editCarBasePrice(regID);
		editCarRegNum(regID);
		Utility.storeData(map, "edit");
	}
	public void deleteCar(String regID) throws Exception{
		Scanner scanner1 = new Scanner(System.in);
		Utility.loadData();
		
		boolean isInvalid = false;
		
		Map<String, List<CarRegistration>> map = Utility.loadData();
		//System.out.println(map);
		String inputValue = "";
		if(map.containsKey(regID)) {
			System.out.println("Are you sure to delete "+regID+" : (Y/N)");
			inputValue = scanner1.nextLine();
		}else {
			System.out.println("Invalid registration ID");
			isInvalid = true;
		}
		
		if(inputValue.equalsIgnoreCase("Y")) {
			try {
				map.remove(regID);
				System.out.println("Car has been deleted successfully.");
			}catch(Exception e) {
				System.out.println("The registration ID is not valid.");
			}	
		}else if(inputValue.equalsIgnoreCase("N")) {
			System.out.println("You selected not to delete car.");
		}else {
			if(!isInvalid)
				System.out.println("INVALID OPTION! Please try again.");
		}
		Utility.storeData(map, "delete");
		

	}
	
	public String getRegID(String msg) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(" Confirm car registration ID to " + msg + " :");
		String inputValue = scanner.nextLine();
		return inputValue;
	}
	public void editCarBrand(String regID) throws Exception {
		Scanner scanner1 = new Scanner(System.in);
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;		
		String inputValue;
		System.out.println("Enter new car brand : ");
		inputValue = scanner1.nextLine();
		System.out.println("Reg ID"+regID);
		CarRegistration obj = map.get(regID).get(0);
		obj.setCarBrand(inputValue);
		List<CarRegistration> list = new ArrayList<>();
		list.add(obj);
		map.put(regID, list);
		Utility.storeData(map, "edit");
		System.out.println("after edit map is "+map);
	}

	private void editCarModel(Scanner scanner, CarRegistration obj) {
		String inputValue;
		System.out.println("Confirm car model to edit: ");
		inputValue = scanner.nextLine();
		obj.setCarModel(inputValue);
	}
	private void editCarNumber(Scanner scanner, CarRegistration obj) {
		String inputValue;
		System.out.println("Confirm car number to edit: ");
		inputValue = scanner.nextLine();
		obj.setCarNumber(inputValue);
	}
	private void editCarDescription(Scanner scanner, CarRegistration obj) {
		String inputValue;
		System.out.println("Confirm car description to edit: ");
		inputValue = scanner.nextLine();
		obj.setCarDescription(inputValue);
	}
	private void editCarBasePrice(Scanner scanner, CarRegistration obj) {
		String inputValue;
		System.out.println("Confirm car base price to edit: ");
		inputValue = scanner.nextLine();
		obj.setBasePrice(Double.parseDouble(inputValue));		//string -> double
	}
}
