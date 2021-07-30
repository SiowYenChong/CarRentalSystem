//hi
package com.system.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.system.pojo.CarRegistration;
import com.system.pojo.Customer;


public class CarRentalModel {
	//				  Key   | Value 
	public static Map<String,List<CarRegistration>> reg = new LinkedHashMap<>(); //order by 1
	//Map->collection: add sth to memory
	public static Map<String,List<Customer>> user = new LinkedHashMap<>();
	public static Map<String, Boolean> loginUsers = new LinkedHashMap<>();
}
