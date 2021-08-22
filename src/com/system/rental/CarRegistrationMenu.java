package com.system.rental;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
	
	public static void main(String[] args) throws Exception {
		//Utility.loadData(System.getProperty("user.dir")+"/inputData.txt");
		CarRegistrationMenu carObj = new CarRegistrationMenu();
		carObj.carRegMenu();

	}
	public void doRegistration() {
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
		if(carReg != null) {
			carReg.setRegID(SequenceGenerator.getCarNext());		//bring in sequence method
		}
		if(desc != null && !"".equals(desc)) {		//not empty and not null
			carReg.setCarDescription(desc);	
		}
		
		
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;		//import list
		if(map.get("regList")!=null) { 	//registration details
			List list = map.get("regList");
			list.add(carReg);
			map.put("regList",list);
		}else {
			List list = new ArrayList();		//ctrl space
			list.add(carReg);
			//map.put("regList",list);
			map.put(carReg.getRegID(), list);
		}
		System.out.println("The details are "+map);
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
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;		
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
			Utility.storeData(map);		//What is exception
			System.out.println("after edit map is "+map);
		}else {
			System.out.println("Car ID doesnt exist.");
		}
	}
	public void editCarModel(String regID) throws Exception {
		Scanner scanner1 = new Scanner(System.in);
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;		
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
			Utility.storeData(map);	
			System.out.println("after edit map is "+map);
		}else {
			System.out.println("Car ID doesnt exist.");
		}
	}
	public void editCarNumber(String regID) throws Exception {
		Scanner scanner1 = new Scanner(System.in);
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;		
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
			Utility.storeData(map);	
			System.out.println("after edit map is "+map);
		}else {
			System.out.println("Car ID doesnt exist.");
		}
	}
	public void editCarDescription(String regID) throws Exception  {
		Scanner scanner1 = new Scanner(System.in);
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;		
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
			Utility.storeData(map);
			System.out.println("after edit map is "+map);
		}else {
			System.out.println("Car ID doesnt exist.");
		}
	}
	public void editCarBasePrice(String regID) throws Exception {
		Scanner scanner1 = new Scanner(System.in);
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;		
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
			Utility.storeData(map);
			System.out.println("after edit map is "+map);
		}else {
			System.out.println("Car ID doesnt exist.");
		}
	}
	public void editAll(String regID) throws Exception{
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;
		editCarModel(regID);
		editCarBrand(regID);
		editCarNumber(regID);
		editCarDescription(regID);
		editCarBasePrice(regID);
		editCarRegNum(regID);
		Utility.storeData(map);
	}
	public void deleteCar(String regID) throws Exception{
		Scanner scanner1 = new Scanner(System.in);
		Utility.loadData();
		
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;
		System.out.println(map);
		String inputValue;
		System.out.println("Are you sure to delete "+regID+" : (Y/N)");
		inputValue = scanner1.nextLine();
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
			System.out.println("INVALID OPTION! Please try again.");
		}
		Utility.storeData(map);
		//System.out.println("After deletion, map is "+map);

	}
	/*private void editCarBrand(String regID, CarRegistration obj) {
		Scanner scanner1 = new Scanner(System.in);
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;		
		String inputValue;
		System.out.println("Confirm car brand to edit: ");
		inputValue = scanner1.nextLine();
		System.out.println("Reg ID"+regID);
		obj = map.get(regID).get(0);
		obj.setCarBrand(inputValue);
	}*/
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
		Utility.storeData(map);
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
