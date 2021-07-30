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
		System.out.println("4. Register New Car");  //[done - no delete,edit]
		System.out.println("   41. Edit Registration number");
		System.out.println("   42. Edit Car Brand");
		System.out.println("   43. Edit Car Model");
		System.out.println("   44. Edit Car Number");
		System.out.println("   45. Edit Car Description");
		System.out.println("   46. Edit Base Price");
		System.out.println("   47. Edit All");
		System.out.println("   48. Delete existing car registration");
		System.out.println("5. Rent a Car"); //[done - hard-code]
		System.out.println("6. Admin");
		System.out.println("7. Logout");
		String email = "";
		Utility.loadData(System.getProperty("user.dir")+"/inputData.txt");
		CarRegistrationMenu carObj = new CarRegistrationMenu();
		String regID;
		
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
				System.out.print("REGISTER NEW CAR");
				carObj.doRegistration();
				break;
			case 41:
				System.out.print("EDIT EXISTING CAR REGISTRATION NUMBER");
				regID = carObj.getRegID();
				carObj.editCarRegNum(regID);
				break;
			case 42:
				System.out.println("EDIT EXISTING CAR BRAND");
				regID = carObj.getRegID();
				carObj.editCarBrand(regID);
				break;
			case 43:
				System.out.print("EDIT EXISTING CAR MODEL");
				regID = carObj.getRegID();
				carObj.editCarModel(regID);
				break;
			case 44:
				System.out.print("EDIT EXISTING CAR NUMBER");
				regID = carObj.getRegID();
				carObj.editCarNumber(regID);
				break;
			case 45:
				System.out.print("EDIT EXISTING CAR DESCRIPTION");
				regID = carObj.getRegID();
				carObj.editCarDescription(regID);
				break;
			case 46:
				System.out.print("EDIT EXISTING BASE PRICE");
				regID = carObj.getRegID();
				carObj.editCarBasePrice(regID);
				break;
			case 47:
				System.out.print("EDIT ALL EXISTING PROPERTIES");
				regID = carObj.getRegID();
				carObj.editCarRegNum(regID);
				carObj.editCarBasePrice(regID);
				carObj.editCarBrand(regID);
				carObj.editCarDescription(regID);
				carObj.editCarModel(regID);
				carObj.editCarNumber(regID);
				break;
			case 48:
				System.out.print("DELETE EXISTING CAR REGISTRATION");
				regID = carObj.getRegID();
				carObj.deleteCar(regID);
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
