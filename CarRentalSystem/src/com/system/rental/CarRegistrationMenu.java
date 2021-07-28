package com.system.rental;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.system.model.CarRentalModel;
import com.system.pojo.CarRegistration;
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
		Utility.loadData(System.getProperty("user.dir")+"/inputData.txt");
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
	public void carRegMenu() {
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
	public String editCarReg() {
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
	public void editOption(String regID) {
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
					System.out.println("Confirm car registration number to edit: ");
					inputValue = scanner.nextLine();
					obj.setRegNumber(inputValue);
					break;
				case 2:
					editCarBrand(scanner, regID, obj);
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
					editCarRegNum(obj);
					editCarBrand(scanner, inputValue, obj);
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
	private void editCarRegNum(CarRegistration obj) {
		String inputValue;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Confirm car registration number to edit: ");
		inputValue = scanner.nextLine();
		obj.setRegNumber(inputValue);
	}
	private void editCarBrand(Scanner scanner, String regID, CarRegistration obj) {
		Scanner scanner1 = new Scanner(System.in);
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;		
		String inputValue;
		System.out.println("Confirm car brand to edit: ");
		inputValue = scanner1.nextLine();
		System.out.println("Reg ID"+regID);
		obj = map.get(regID).get(0);
		obj.setCarBrand(inputValue);
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
