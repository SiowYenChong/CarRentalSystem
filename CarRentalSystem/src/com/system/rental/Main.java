package com.system.rental;

public class Main {
	
	public static void main(String[] args) {
		int inputValue;
		
		System.out.println("WELCOME TO CAR RENTAL SYSTEM");
		System.out.println("Options:");
		System.out.println("1. Login");
		System.out.println("2. Main Menu");
		System.out.println("3. Register User");
		System.out.println("4. Register Car");
		System.out.println("5. Rent a Car");
		System.out.println("6. Logout");

		do {
			inputValue=Input.inputInt("Select option: ");
			switch(inputValue) {
			case 1:
				System.out.print("Calling 1.Login..");
				break;
			case 2:
				System.out.print("Calling 2.Main Menu..");
				break;
			case 3:
				System.out.print("Calling 3.Register User..");
				CarRegistrationMenu obj = new CarRegistrationMenu();
				obj.doRegistration();
				break;
			case 4:
				System.out.print("Calling 4.Rent a Car..");
				break;
			case 5:
				System.out.print("Calling 5.Logout..");
				break;
			//case 6:
			default:
				System.out.print("Invalid option!");
				break;
			}
		}while(true);
	}
	
	

}
