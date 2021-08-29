package com.system.pojo; //plain old java object

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.text.SimpleDateFormat;		//Formatting date
import java.time.Duration;
import java.time.LocalDateTime;
public class CarRegistration {

	/*-carBrand:String
	-carModel:String
	-carNumber:String
	-carDescription:String
	-registrationDetails:String        //name,startDate,endDate,address
	-carRental:double
	-basePrice:double
*/
	String carBrand;
	String carModel;
	String carNumber;
	String carDescription;
	String regNumber;
	double basePrice;
	double carRental;	
	double carDeposit;
	double penalty;
	double lateRent;
	double refund;
	public double getRefund() {
		double refund = 0.0;
		double amount = getCarRental() + getPenalty() - getCarDeposit();
		if(amount < 0) 
			refund = amount;
		return refund;
	}
	boolean details;
	
	public boolean isDetails() {	//equivalent getDetails
		return details;
	}
	public void setDetails(boolean details) {
		this.details = details;
	}
	public double getLateRent() {
		return lateRent;
	}
	public void setLateRent(double lateRent) {
		this.lateRent = lateRent;
	}
	public double getPenalty() {
		return penalty;
	}
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	public double getCarDeposit() {
		return carDeposit;
	}
	public void setCarDeposit(double carDeposit) {
		this.carDeposit = carDeposit;
	}
	String regID;
	LocalDateTime startDate;
	LocalDateTime endDate;
	String userID;
	
	
	//source>getter,setter
	public String getRegID() {
		return regID;
	}
	public void setRegID(String regID) {
		this.regID = regID;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {		
		this.carModel = carModel;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getCarDescription() {
		return carDescription;
	}
	public void setCarDescription(String carDescription) {
		this.carDescription = carDescription;
	}
	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public double getCarRental() {
		return carRental;
	}
	public void setCarRental(double carRental) {
		this.carRental = carRental;
	}
	public static String getFormat(double input) {
		DecimalFormat df = new DecimalFormat("###.00");
		BigDecimal bd = new BigDecimal(input);
		return df.format(input);	
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime dt) {
		this.startDate = dt;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime enDt) {
		this.endDate = enDt;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public static String formatDate(String date) {
		//2021-08-11T12:00
		String stDate = "";
		if(null != date && !"null".equals(date)) {	//null unidentified and null string
			String[] tempStDate = date.split("T");
			String tempYr = tempStDate[0].split("-")[0];
			String tempMth = tempStDate[0].split("-")[1];
			String tempDate = tempStDate[0].split("-")[2];
			String tempHr = tempStDate[1].split(":")[0];
			String tempMin = tempStDate[1].split(":")[1];
			String tempSec = "00"; //tempStDate[1].split(":")[2];
			String tempAmPm = tempStDate[0];
			if(tempAmPm.toUpperCase().startsWith("P") ) {
				tempHr = Integer.parseInt(tempHr)+12+"";	//cant store int value as string, end with ""
			}
			stDate = tempDate + "/" + tempMth + "/" + tempYr + " " + tempHr + ":" + tempMin + ":" + tempSec;
		}
		return stDate;
	}
	@Override
	public String toString() {
		if(getStartDate() != null && !"null".equals(getStartDate())) {
			long hours = Duration.between(getStartDate(), getEndDate()).toHours();
			System.out.println("Rental = " + getCarRental() + " Difference in hours = " + hours + " hours");
		}
		String hiredBy = (null != getUserID() && !"null".equals(getUserID()))? "Hired by = "+getUserID(): "";
		String date = "";
		String returnString = "";
		double amount = getCarRental() + getPenalty() - getCarDeposit();
		if(amount > 0) 
			returnString = "\n Amount to be paid = "+ (amount);
		else
			returnString = "\n Amount to be refunded = "+ (-1*(amount));
		if(getStartDate() != null && !"null".equals(getStartDate())) {
			date = "startDate = "+ formatDate(""+ getStartDate()) + ", endDate = " + formatDate(""+getEndDate()) + 
					"\n deposit = "+ getCarDeposit() + 
					"\n rent = " + getCarRental() +
					returnString ;
		}
		String result = "regID = " +regID+ ", regNumber = " +regNumber+ ", carBrand = " + carBrand + 
				"\n carModel = " + carModel + ", carNumber = " + carNumber+ " carDescription = " + carDescription + 
				"\n price(Per Hour) = "+getFormat(basePrice);
				if(isDetails()) {
					result = result + "\n " + date +
					"\n " + hiredBy;  
				}else {
					if(getStartDate() != null && !"null".equals(getStartDate())) {
						result = result + 
						"\n available startDate = before "+ formatDate(""+ getStartDate()) + 
						"\n available endDate = after " + formatDate(""+getEndDate()); 
					}
				}
					result = result + "\n ------------------------------------------------------------------------------------";	  
				return result;
	}
	

}


