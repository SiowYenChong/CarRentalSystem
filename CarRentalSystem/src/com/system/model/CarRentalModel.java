package com.system.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.system.pojo.CarRegistration;
import com.system.pojo.Customer;


public class CarRentalModel {
	//				  Key   | Value 
	public static Map<String,List<CarRegistration>> reg = new HashMap<>(); 
	//Map->collection: add sth to memory
	public static Map<String,List<Customer>> user = new HashMap<>();
	public static Map<String,Boolean> loginUsers = new HashMap<>();
}
