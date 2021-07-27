package com.system.rental;
import com.system.util.Utility;

public class Main {
	
	public static void main(String[] args)throws Exception {	//handling error
		int inputValue;
		
		System.out.println("WELCOME TO CAR RENTAL SYSTEM");
		System.out.println("Options:");
		System.out.println("1. Login");	//[done]
		System.out.println("2. Main Menu"); //done
		System.out.println("3. Register User"); //[done - no delete,edit]
		System.out.println("4. Register Car");  //[done - no delete,edit]
		System.out.println("5. Rent a Car"); //[done - hard-code]
		System.out.println("6. Admin");
		System.out.println("7. Logout");
		String email = "";
		Utility.loadData(System.getProperty("user.dir")+"/inputData.txt");
		do {
			inputValue=Input.inputInt("Select option: ");
			switch(inputValue) {
			case 1:
				System.out.print("LOGIN");
				LoginTest loginObj = new LoginTest();
				email = loginObj.logIn();							//("user1", "abcd123");											//("admin", "abcd123");
				break;
			case 2:
				System.out.print("MAIN MENU");
				break;
			case 3:
				System.out.print("REGISTER USER");
				CustomerMenu custObj = new CustomerMenu();
				custObj.customerRegistration(email);
				break;
			case 4:
				System.out.print("REGISTER CAR");
				CarRegistrationMenu carObj = new CarRegistrationMenu();
				carObj.carRegMenu();
				break;
			case 5:
				System.out.print("RENT A CAR");
				break;
			case 6:
				System.out.print("ADMIN");	//show number of registered customers, registered cars
				break;
			case 7:
				System.out.print("LOGOUT");
				break;
			default:
				System.out.print("INVALID OPTION!");
				break;
			}
		}while(true);
	}
	
	

}
