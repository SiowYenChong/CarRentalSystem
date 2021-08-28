package com.system.rental;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.sql.Timestamp;		//to have timestamp: hh mm ss
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.system.model.CarRentalModel;
import com.system.pojo.CarRegistration;
import com.system.util.Utility;

public class CarRentalProcess {
	
	public Map <String,List<CarRegistration>> getAvailableCars(String custID) throws Exception{
			Map <String,List<CarRegistration>> map = Utility.loadData();
		for(Map.Entry <String,List<CarRegistration>> set: map.entrySet()) {
			String key = set.getKey();
			List<CarRegistration> cars = set.getValue();
			if(!cars.isEmpty()) {
				CarRegistration car = cars.get(0);
				if(custID.equals(car.getUserID())) {
					car.setDetails(true);
					System.out.println(car);
				}else {
					car.setDetails(false);
					System.out.println(car);
				}
			}
		}
		return map;		
	}
	public void returnCar(CarRegistration car) throws Exception{
		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime currentDate = LocalDateTime.now(); 
		String current = formatter.format(currentDate);
		LocalDateTime currentDateTime = LocalDateTime.parse(current, formatter);
		
		String endDate = formatter.format(car.getEndDate());
		double actualRent = getRent(car.getBasePrice(), endDate, current);
		
		LocalDateTime endDatets = LocalDateTime.parse(endDate);
		LocalDateTime currentDatets = LocalDateTime.parse(current);
		long hours = Duration.between(endDatets, currentDatets).toHours();
		if(car.getEndDate().isBefore(currentDate) && hours < 1) {
			actualRent = getRent(car.getBasePrice(), endDate, current);
			//System.out.println("Actual rent will be "+(actualRent-car.getCarDeposit()));
		}else {
			System.out.println("You are "+hours+" hours late for returning car.");
			System.out.println("You will be penalized with (2*"+ hours + "*" + car.getBasePrice() +") : RM" + (2*hours*car.getBasePrice()));
			car.setPenalty(2*hours*car.getBasePrice());
		}
		
		/*System.out.println("Enter rented car registration ID to return: ");
		String inputCarID = scanner.nextLine();
		if(inputCarID.equalsIgnoreCase(car.getRegID())) {
			System.out.println("Confirm return car "+inputCarID+"? (Y/N)");
			String ans = scanner.nextLine();
			if(ans.equalsIgnoreCase("Y")) {
				if(actualRent > 0) {
					System.out.println("\n Amount to be paid = "+ (actualRent + car.getPenalty())) ;
					System.out.println("Directing to payment gateway...");
					System.out.println("You have successfully returned the car");
					releaseCar(car);
				}	
				else {
					System.out.println("\n Amount to be refunded = "+ (-actualRent));
					System.out.println("The amount will be refunded to your input payment method in 3 working days");
				}
			}else if(ans.equalsIgnoreCase("N")) {
				
			}else {
				System.out.println("Invalid input.");
			}
		
		}
		*/
	}
	
	public void releaseCar(CarRegistration returnCar) throws Exception{

		//Map <String,List<CarRegistration>> hiredCarMap = Utility.loadData();
		Map <String,List<CarRegistration>> hiredCarMap = getHiredCars();
		boolean noCar = true;
		for(Map.Entry <String,List<CarRegistration>> set: hiredCarMap.entrySet()) {
			String key = set.getKey();
			List<CarRegistration> cars = set.getValue();
			if(!cars.isEmpty()) {
				for(CarRegistration car : cars) {
					if(car.getRegID().equals(returnCar.getRegID())) {
						car.setUserID(null);
						car.setStartDate(null);
						car.setEndDate(null);
						car.setCarRental(0);
						car.setCarDeposit(0);
					}
					
				}
			}
		}
		Utility.storeData(hiredCarMap, "edit");
	}
	public void showHiredCars(String customerID) throws Exception{
		Scanner scanner = new Scanner(System.in);
		Map <String,List<CarRegistration>> hiredCarMap = Utility.loadData();
		
		boolean noCar = true;
		List <CarRegistration> hiredCars = new ArrayList <CarRegistration> ();
		for(Map.Entry <String,List<CarRegistration>> set: hiredCarMap.entrySet()) {
			String key = set.getKey();
			List<CarRegistration> cars = set.getValue();
			if(!cars.isEmpty()) {
				for(CarRegistration car : cars) {
					if(car.getUserID() != null && !"null".equals(car.getUserID()) &&  customerID.equals(car.getUserID())) {
						hiredCars.add(car);
						car.setDetails(true);
						returnCar(car);
						System.out.println(car);
					}else {
						noCar = false;
					}
				}
			}
		}
		if(noCar) {
		System.out.println("Enter rented car registration ID to return: ");
		String inputCarID = scanner.nextLine();
		if(verifyCarByRegID(hiredCars, inputCarID)) {
			System.out.println("Confirm return car "+inputCarID+"? (Y/N)");
			String ans = scanner.nextLine();
			if(ans.equalsIgnoreCase("Y")) {
				CarRegistration car = hiredCarMap.get(inputCarID).get(0);
				if(car != null && car.getActualRent() > 0) {
					System.out.println("\n Amount to be paid = "+ (car.getActualRent() + car.getPenalty())) ;
					System.out.println("Directing to payment gateway...");
					System.out.println("You have successfully returned the car");
					releaseCar(car);
				}	
				else {
					System.out.println("\n Amount to be refunded = "+ (-car.getActualRent() ));
					System.out.println("The amount will be refunded to your input payment method in 3 working days");
				}
			}else if(ans.equalsIgnoreCase("N")) {
				
			}else {
				System.out.println("Invalid input.");
			}
		
		}
		}
		if(noCar)
			System.out.println("\t\t\t There is no hired car.");
		
	}
	public boolean verifyCarByRegID(List<CarRegistration> cars, String regID) {
		boolean carExist = false;
		for(CarRegistration car : cars) {
			if(car.getRegID().equals(regID)) {
				carExist = true;
				break;
			}
		}
		return carExist;
	}
	public void hiredCarsByCustomer(String custID) throws Exception{
		Map <String,List<CarRegistration>> hiredCarMap = Utility.loadData();
		
		boolean noCar = true;
		for(Map.Entry <String,List<CarRegistration>> set: hiredCarMap.entrySet()) {
			String key = set.getKey();
			List<CarRegistration> cars = set.getValue();
			if(!cars.isEmpty()) {
				CarRegistration car = cars.get(0);
				if(car.getStartDate() != null && car.getEndDate() != null && custID.equals(car.getUserID())) {
					car.setDetails(true);
					System.out.println(car);
					noCar = false;
				}
			}
		}
		if(noCar)
			System.out.println("\t\t\t There is no hired car.");
		
	}
	public Map<String,List<CarRegistration>> getHiredCars() throws Exception{ 
		Map <String,List<CarRegistration>> hiredCarMap = Utility.loadData();
		Map <String,List<CarRegistration>> hiredCars = new LinkedHashMap <String,List<CarRegistration>>();
		boolean noCar = true;
		for(Map.Entry <String,List<CarRegistration>> set: hiredCarMap.entrySet()) {
			String key = set.getKey();
			List<CarRegistration> cars = set.getValue();
			if(!cars.isEmpty()) {
				for(CarRegistration car : cars) {
					if(car.getStartDate() != null && car.getEndDate() != null) {
						//List<CarRegistration> list = new ArrayList<CarRegistration> ();
						//list.add(car);				//add carObj into list
						hiredCars.put(key,cars);
					}
				}
			}
		}
		return hiredCars;
	}
	public String inputDate(String input) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the "+ input +" date (DD/MM/YYYY HH:mm (24hr system)) to hire: ");
		String startDate = scanner.nextLine();
		String stDate = ""; 
		if(validateDate(startDate)) {
			stDate = convertDate(startDate);		//Date is validated
			if(!compareCurrentDate(stringToDate(stDate))) {
				System.out.println(" " + input + " date time should not be before current date time.Try again.");
				stDate = "";
			}
		}
		return stDate;
	}
	public boolean validateCar(String regID) throws Exception{
		Map <String,List<CarRegistration>> map = Utility.loadData();
		CarRegistration car = map.get(regID).get(0);
		return compareCurrentDate(car.getEndDate());
	}
	public boolean validateDates(String startDate, String endDate) {
		//check date valid?
		boolean result = false;
		 DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); 
		 try {
			 format.parse(startDate);
			 format.parse(endDate);
			 result = true;
		 }catch (Exception e){
			 System.out.println("Kindly enter date in yyyy-MM-dd HH:mm");
		 }
		return result; 
	}
	public void hireCar(Map <String,List<CarRegistration>> map, String custID) throws Exception{
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter registration ID of car: ");
		String regID = scanner.nextLine();
		if(map.get(regID) != null) { //pass map here
			String stDate = "";
			String enDate = "";
			do {
				stDate = inputDate("Start");
			}while(stDate.equals(""));			//Do while unless there's ""
			do {
				enDate = inputDate("End");
			}while(enDate.equals(""));
				
				boolean isError = false;
				if(validateDates(stDate, enDate) && dateValidation2(stDate, enDate)) {

				}else {
					isError = true;
					System.out.println("Invalid date.");
					return;
				}
				
				/*end date>start date
				 * date include hrs
				*/
				
					List<CarRegistration> objList = map.get(regID);
					for(CarRegistration obj : objList) {
					boolean isAlreadyHired = (obj.getStartDate() != null && obj.getEndDate()!=null)? true: false;
					if(!"null".equals(obj.getStartDate()) && !"null".equals(obj.getEndDate())) {
						LocalDateTime inputStDate = LocalDateTime.parse(stDate);
						LocalDateTime inputEndDate = LocalDateTime.parse(enDate);
						if(isAlreadyHired && ((inputStDate.isEqual(obj.getStartDate())  && inputEndDate.isEqual(obj.getEndDate()) )		
								|| (inputStDate.isBefore(obj.getStartDate()) && inputEndDate.isBefore(obj.getEndDate()) )
								//||(inputStDate.isAfter(obj.getStartDate()) && inputEndDate.isAfter(obj.getEndDate()) )	
								||(inputStDate.isAfter(obj.getStartDate()) && inputEndDate.isBefore(obj.getEndDate()) )	)) {
								System.out.println("This car is already hired. Probable end time is "+CarRegistration.formatDate(""+obj.getEndDate())+". Select 53 again.");
						}else {
							double deposit =  getDeposit(obj.getBasePrice(), stDate, enDate);
							if(!isError) {
								System.out.println("This car's base price is "+ Utility.getFormat(obj.getBasePrice()));
								System.out.println("Initial deposit for this car to be paid now will be RM" + deposit);
								System.out.println("Are you sure you want to rent this car? (Y/N): ");
								String agree = scanner.nextLine();
								
								if(agree.equalsIgnoreCase("Y")) {
									double rent =  getRent(obj.getBasePrice(), stDate, enDate);
									
									LocalDateTime dt = stringToDate(stDate);
									obj.setStartDate(dt);
									LocalDateTime enDt = stringToDate(enDate);
									obj.setEndDate(enDt);
									obj.setCarRental(rent);
									obj.setUserID(custID);
									obj.setCarDeposit(deposit);
									obj.setCarRental(rent);
									List list = new ArrayList();		//ctrl space
									list.add(obj);
									//map.put("regList",list);
									map.put(regID, list);
									Utility.storeData(map, "edit");
									System.out.println("Total rent to be paid after return car will be: RM"+rent);
									System.out.println("Initial deposit for this car will be RM" + deposit);
									System.out.println("Directing to payment gateway..");
									System.out.println("You have booked the car.");
								}
							}
							
						}
					}else {
					System.out.println("Invalid option.");
					}
					}
		
			
		}else {
			System.out.println("Kindly enter valid registration ID of car.");
		}
		
	}
	public String convertDate(String date) {
		String[] tempStDate = date.split(" ");
		String tempYr = tempStDate[0].split("/")[2];
		String tempMth = tempStDate[0].split("/")[1];
		String tempDate = tempStDate[0].split("/")[0];
		String tempHr = tempStDate[1].split(":")[0];
		String tempMin = tempStDate[1].split(":")[1];
		//String tempSec = tempStDate[1].split(":")[2];
		String stDate = tempYr + "-" + tempMth + "-" + tempDate + "T" + tempHr + ":" + tempMin;
		
		//System.out.println(stDate);
		return stDate;
	}
	public LocalDateTime stringToDate(String input) throws Exception{
		LocalDateTime localDateTime = LocalDateTime.parse(input);	//dd:MM::yyyy without am pm
		return localDateTime;
	}
	public boolean compareCurrentDate(LocalDateTime input) {	//working fine
		boolean result = true;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime currentDate = LocalDateTime.now(); 
		String current = formatter.format(currentDate);
		/*System.out.println("Current date is "+current);
		System.out.println("Input Date: "+input);*/
		LocalDateTime currentDateTime = LocalDateTime.parse(current, formatter);
		if(input.isBefore(currentDateTime)) {
			result = false;
		}
		return result;
	}
	public boolean dateValidation(Date firstDate, Date lastDate) {
		boolean result = false;
		if(firstDate.compareTo(lastDate) > 0) {
			System.out.println("Start date should not be greater than end date.");
			return false;
		}else if(firstDate.compareTo(lastDate) < 0) {
			return true;
		}else {
			System.out.println("Both dates inserted are same.");
		}
		return result;
	}
	public static void main(String[] args) throws Exception{
		CarRentalProcess obj = new CarRentalProcess();
		String date = "09/08/2021 00:12:00";
		String date1 = obj.convertDate(date);
		System.out.println("Difference: "+ obj.compareCurrentDate(obj.stringToDate(date1)));
		
	}
	public double computeRent(double basePrice, String startDate, String endDate) {
		//user hiding car for few hours, car price different
		//days = 100, hrs = 80
		
		return 100;
	}
	public double getDeposit(double basePrice, String startDate, String endDate) {
		 //DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
		 //format.toFormat(startDate);
		LocalDateTime startDatets = LocalDateTime.parse(startDate);
		LocalDateTime endDatets = LocalDateTime.parse(endDate);
		//ts = timestamp
		//LocalDate endDatets = endDate.toLocalDateTime().toLocalDate();
		long hours = Duration.between(startDatets, endDatets).toHours();
		long days = Duration.between(startDatets, endDatets).toDays();
		double deposit = 100.0;
		if(days>1) {
			deposit += days * basePrice;	
		}
		
		
		return deposit;
	}
	public double getRent(double basePrice, String startDate, String endDate) {
		LocalDateTime startDatets = LocalDateTime.parse(startDate);
		LocalDateTime endDatets = LocalDateTime.parse(endDate);
		//ts = timestamp
		//LocalDate endDatets = endDate.toLocalDateTime().toLocalDate();
		long hours = Duration.between(startDatets, endDatets).toHours();
		long days = Duration.between(startDatets, endDatets).toDays();
		double rent = hours*basePrice;
		//System.out.println(days+" days "+hours+" hrs");
		return rent;
	}
	
	public boolean validateDate(String startDate) {
		//check date valid?
		boolean result = false;
		 DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm"); 
		 LocalDateTime currentDate = LocalDateTime.now();
		 try {
			 TemporalAccessor d1 = format.parse(startDate);
			 //TemporalAccessor d2 = format.parse(currentDate);
			 result = true;
		 }catch (Exception e){
			 System.out.println("Kindly enter date in dd/MM/YYYY HH:mm (24hr System)");
			 //e.printStackTrace();
		 }
		return result; 
	}
	public boolean dateValidation2(String startDate, String endDate) {
		boolean result = true;
		LocalDateTime stDate = LocalDateTime.parse(startDate);
		LocalDateTime enDate = LocalDateTime.parse(endDate);
		LocalDateTime currentDate = LocalDateTime.now();
		long hours = Duration.between(stDate, enDate).toHours();
		long days = Duration.between(stDate, enDate).toDays();

		if(days>180) {		//Hire car not more than 6 months
			System.out.println("Kindly enter valid date. You cannot hire car more than 6 months.");
			return false;
		}
		//System.out.println(days+" days "+hours+" hrs");
		if(stDate.isAfter(enDate)) {
			System.out.println("Start date should not be greater than end date.");
			result = false;
		}else if(stDate.isBefore(currentDate)) {
			System.out.println("Start date should not be lesser than current date");
			result = false;
		}else if(enDate.isBefore(currentDate)) {
			System.out.println("End date should not be lesser than current date");
			result = false;
		}else {

		}
		return result;
	}
	public boolean dateValidation2(String startDate) {	//dd/mm/yyyy
		boolean result = true;
		LocalDateTime stDate = LocalDateTime.parse(startDate);
		LocalDateTime currentDate = LocalDateTime.now();

		if(stDate.isBefore(currentDate)) {
			System.out.println("Date should not be lesser than current date");
			result = false;
		}
		
		return result;
	}
	
}

