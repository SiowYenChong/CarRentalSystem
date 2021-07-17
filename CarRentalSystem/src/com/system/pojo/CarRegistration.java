package com.system.pojo; //plain old java object

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
	String regID;
	
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
	@Override
	public String toString() {
		return "CarRegistration [regID= " +regID+ ", regNumber=" +regNumber+ ", carBrand=" + carBrand + ", carModel=" + carModel + ", carNumber=" + carNumber
				+ ", carDescription=" + carDescription + ", basePrice=%.2f" + basePrice
				+ ", carRental=%.2f" + carRental + "]";	  
	}
	

}


