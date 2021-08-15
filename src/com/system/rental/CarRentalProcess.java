package com.system.rental;

import java.util.ArrayList;
import java.util.Date;
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
	Map <String,List<CarRegistration>> map = null;
	public Map <String,List<CarRegistration>> getAvailableCars() throws Exception{
		if(map != null) {
			map = Utility.loadData();
		}
		for(Map.Entry <String,List<CarRegistration>> set: map.entrySet()) {
			String key = set.getKey();
			List<CarRegistration> cars = set.getValue();
			if(!cars.isEmpty()) {
				CarRegistration car = cars.get(0);
				if(car.getEndDate() != null) {
					System.out.println( "regID = " + car.getRegID()+ ", regNumber = " +car.getRegNumber()+ ", carBrand = " + car.getCarBrand() + 
					"\n carModel = " + car.getCarModel() + ", carNumber = " + car.getCarNumber()+ " carDescription = " + car.getCarDescription() + 
					"\n  price(Per Hour) = "+ car.getFormat(car.getBasePrice())+ ", carRental = "+ car.getFormat(car.getCarRental())+ 
					"\n startDate = "+ car.formatDate(""+car.getStartDate()) + ", endDate = " + car.formatDate(""+car.getEndDate())+
					"\n This car is ONLY AVAILABLE BEFORE "+ car.formatDate(""+car.getStartDate()) + " OR AFTER "+ car.formatDate(""+car.getEndDate())+
					"\n -------------------------------------------------------------------------------------------------------");
				}else {
					System.out.println(car);
				}
			}
			
		}
		return map;		
	}
	public void returnCar() {
		System.out.println("");
		//continue
	}
	public void showHiredCars() throws Exception{
			Map <String,List<CarRegistration>> hiredCarMap = Utility.loadData();
		
		boolean noCar = true;
		for(Map.Entry <String,List<CarRegistration>> set: hiredCarMap.entrySet()) {
			String key = set.getKey();
			List<CarRegistration> cars = set.getValue();
			if(!cars.isEmpty()) {
				CarRegistration car = cars.get(0);
				if(car.getStartDate() != null && car.getEndDate() != null) {
					System.out.println(car);
					noCar = false;
				}
			}
		}
		if(noCar)
			System.out.println("\t\t\t There is no hired car.");
		
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
	public boolean validateCar(String regID) {
		CarRegistration car = map.get(regID).get(0);
		return compareCurrentDate(car.getEndDate());
	}
	public void hireCar(Map <String,List<CarRegistration>> map, String custID) throws Exception{
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter registration ID of car: ");
		String regID = scanner.nextLine();
		if(map.get(regID) != null) {
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
				
					CarRegistration obj = map.get(regID).get(0);
					boolean isAlreadyHired = (obj.getStartDate() != null && obj.getEndDate()!=null)? true: false;
					if(!"null".equals(obj.getStartDate()) && !"null".equals(obj.getEndDate())) {
						LocalDateTime inputStDate = LocalDateTime.parse(stDate);
						LocalDateTime inputEndDate = LocalDateTime.parse(enDate);
						if(isAlreadyHired && (inputStDate.isAfter(obj.getStartDate()) || inputStDate.isEqual(obj.getStartDate()) 
						|| inputEndDate.isAfter(obj.getStartDate())|| inputEndDate.isEqual(obj.getEndDate()))){
							System.out.println("This car is already hired. Probable end time is "+CarRegistration.formatDate(""+obj.getEndDate())+". Select 53 again.");
						}else {
							if(!isError) {
								System.out.println("This car's base price is "+ Utility.getFormat(obj.getBasePrice()));
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
									List list = new ArrayList();		//ctrl space
									list.add(obj);
									//map.put("regList",list);
									map.put(regID, list);
									Utility.storeData(map);
									System.out.println("Directing to payment gateway..");
									System.out.println("You have booked the car.");
								}
							}
							
						}
					}else {
					System.out.println("Invalid option.");
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
	public double getRent(double basePrice, String startDate, String endDate) {
		 //DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
		 //format.toFormat(startDate);
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
