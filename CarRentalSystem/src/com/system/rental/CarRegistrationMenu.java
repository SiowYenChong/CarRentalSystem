package com.system.rental;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.system.model.CarRentalModel;
import com.system.pojo.CarRegistration;
public class CarRegistrationMenu {
	private String brandName ;
	private String modelName ;
	private String plateNumber ;
	private String regNumber ;
	private double basePrice ;
	
	public static void main(String[] args) {
		CarRegistrationMenu obj = new CarRegistrationMenu();
		System.out.println(obj.checkAlphabet("Claire"));

	}
	public void doRegistration() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("WELCOME TO CAR REGISTRATION MENU");
		System.out.println("Enter Car Brand name: ");
		brandName = scanner.nextLine();
		System.out.println("Enter Car Model name: ");
		modelName = scanner.nextLine();
		System.out.println("Enter Carplate number: ");
		plateNumber = scanner.nextLine();
		System.out.println("Enter Car Registration number: ");
		regNumber = scanner.nextLine();
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
		Map<String, List<CarRegistration>> map = CarRentalModel.reg;		//import list
		if(map.get("regList")!=null) { 	//registration details
			List list = map.get("regList");
			list.add(carReg);
			map.put("regList",list);
		}else {
			List list = new ArrayList();		//ctrl space
			list.add(carReg);
			map.put("regList",list);
		}
		System.out.println("The details are "+map);
	}
	public boolean checkAlphabet(String input) {
		boolean result = false;
		
		result = input.matches(".*\\d.*");
		return !result;
		
	}
	
}
