package com.system.pojo;

import java.sql.Date;
import java.time.LocalDateTime;

public class CarRental {
	private String itemId;
	private double baseFee;
	private double hourPrice;
	private LocalDateTime rentalStartDate;
	private LocalDateTime rentalEndDate;
	private String clientName;
	public final double TAXES_RATE = 0.07;
	
	private String type;
	
	public CarRental(String itemId)
	{
		this.itemId = itemId;
		this.rentalStartDate = null;
		this.clientName = null;
		this.baseFee = 0.0;
		this.hourPrice = 0.0;
	}
	public CarRental(String itemId, String type, double baseFee, double hourPrice)
	{
		this.itemId = itemId;
		this.type=type;
		this.baseFee = baseFee;
		this.hourPrice = hourPrice;
		this.rentalStartDate = null;
		this.clientName = null;
	}
	/*public void rent(String clientName)
	{
		setClientName(clientName);
		setRentalStartDate(new Date(0));
	}*/
	public boolean available()
	{
		if(getRentalStartDate()== null)
			return true;
		else
			return false;
	}
	/*public Receipt returnItem()
	{
		Date now = new Date(0);
		long hours = (getRentalStartDate().getTime()-now.getTime());
		double subTotal = getBaseFee() + (getHourPrice() * hours);
		double taxes = TAXES_RATE * subTotal;
		Receipt receipt = new Receipt(itemId, type, baseFee, hourPrice, rentalStartDate, clientName, subTotal, taxes);
		rentalStartDate = null;
		clientName = null;
		return receipt;
	}*/
	public String getItemId()
	{
		return itemId;
	}
	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public double getBaseFee()
	{
		return baseFee;
	}
	public void setBaseFee(double baseFee)
	{
		this.baseFee = baseFee;
	}
	public double getHourPrice()
	{
		return hourPrice;
	}
	public void setHourPrice(double hourPrice)
	{
		this.hourPrice = hourPrice;
	}
	public LocalDateTime getRentalStartDate()
	{
		return rentalStartDate;
	}
	public void setRentalStartDate(LocalDateTime rentalStartDate)
	{
		this.rentalStartDate = rentalStartDate;
	}
	public LocalDateTime getRentalEndDate() {
		return rentalEndDate;
	}
	public void setRentalEndDate(LocalDateTime rentalEndDate) {
		this.rentalEndDate = rentalEndDate;
	}
	public String getClientName()
	{
		return clientName;
	}
	public void setClientName(String clientName)
	{
		this.clientName = clientName;
	}
}
