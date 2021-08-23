package com.system.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.system.model.CarRentalModel;
import com.system.pojo.Customer;

public class UtilityTest {

	public static void main(String[] args) throws Exception{
		File file = new File (System.getProperty("user.dir")+"/custData.txt");
		Scanner scanner = new Scanner(file);							//reading from file
		Map<String, List<Customer>> map = CarRentalModel.user;		//import list
		
		while(scanner.hasNextLine()) {
			Customer cust = new Customer (); //car object
			String data = scanner.nextLine();
			String[] dataArray = data.split(";");
			cust.setCustomerID(dataArray[0]);
			cust.setCustomerName(dataArray[1]);
			cust.setCustomerBirthDate(dataArray[2]);
			cust.setCustomerMobileNumber(dataArray[3]);
			cust.setCustomerEmail(dataArray[4]);
			cust.setCustomerAddress(dataArray[5]);
			SequenceGenerator.getCustomerNext();
			cust.setPassword(dataArray[6]);
			
			if(map.get(dataArray[0])!=null) { 					//registration details
				List list = map.get(dataArray[0]);
				list.add(cust);
				map.put(dataArray[0],list);
			}else {
				List list = new ArrayList();				//ctrl space
				list.add(cust);
				//map.put("regList",list);
				map.put(cust.getCustomerID(), list);
			}
			System.out.println(map);
			map.remove("C001");
			System.out.println(map);
		}

	}

}
