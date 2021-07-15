package com.system.rental;

public class Input {
	public static void print(String input) {
		System.out.println(input + " ");
		System.out.flush();
		
	}
	
	public static void inputFlush() {
		int dummy;
		int bAvail;	//buffer available
		try {
			while((System.in.available())!=0) {
				dummy=System.in.read();
				
			}	
		}catch(Exception e) {
			System.out.println("Invalid input!");
		}	
	}
	
	public static String inputString() {
		int aChar;		//char
		String s = "";
		boolean finished = false;
		while(!finished) {
			try{
				aChar=System.in.read();
				if(aChar<0 || (char)aChar=='\n') {
					finished=true;
				}else if((char)aChar!='\r'){
					s=s+(char)aChar;
				}
			}catch(Exception e) {
				System.out.println("Invalid input!");
				finished=true;
			}
		}
		return s;
	}
	public static int inputInt(String input) {
		while(true) {
			inputFlush();
			print(input);
			try {
				return Integer.valueOf(inputString().trim()).intValue();	//trim-doesnt matter if space input
			}catch(Exception e) {
				System.out.println("Input is not integer.");
			}
		}
	}
}
