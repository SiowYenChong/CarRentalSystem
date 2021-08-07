package com.system.util;	//utilities

public class SequenceGenerator {
	private static long value = 1;
	private static long custValue = 1;
	public static String getCarNext() {
		return "V00"+value++;			//Customer->C001		Car->V001
	}
	public static String getCustomerNext() {
		return "C00"+custValue++;
	}
}
