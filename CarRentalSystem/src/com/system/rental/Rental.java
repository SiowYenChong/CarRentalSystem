import java.text.*;  
import java.util.*;
public class Rental {

	
	
Scanner input= new Scanner(System.in);

	public static void displayRentedCar()
	{
	  //if the rented car is not false, display the detail
		
		
	}
public static void displayCar()
{
	
	
	ArrayList<CarRegistration> arrayCar = new ArrayList<CarRegistration>();
	arrayCar.add(new CarRegistration("Toyota", "Model Z","ABC123","Register num", "GOOD", 0, 50, "A1"));
	arrayCar.add(new CarRegistration("Perodua", "Myvi","CBA123","Register num1", "Bad", 0, 20, "A2"));
	System.out.println("Brand\tModel\tNumber plate\tRegister num\tDescription\tBase price\tRental price\tRegisterID");
	for (int i = 0;i<arrayCar.size();i++)
	{
		CarRegistration c = arrayCar.get(i);
		System.out.println(c.getCarBrand()+"\t"+c.getCarModel()+"\t"+c.getCarNumber()+"\t"+c.getRegNumber()+"\t"+c.getCarDescription()+"\t"+c.getBasePrice()+"\t"+c.getCarRental()+"\t"+c.getRegID());
	}
	
	Map<String, List<CarRegistration>> map = CarRentalModel.reg;		//import list
for(int i = 0;i<map.size();i++) {
	
	
}
	
	Scanner input= new Scanner(System.in);
	String rentedCar;

	System.out.print("Enter the car's car plate that you want to rent(enter 'no' to exit):");
	rentedCar = input.nextLine();
do
	{
	
		for(int i=0;i<arrayCar.size();i++) {
			CarRegistration c = arrayCar.get(i);
		if (rentedCar.equals(c.getCarNumber()))
	{
		System.out.print("When do you want to rent (dd/mm/yyyy):");
		String sDate;
		sDate = input.nextLine();
		try {
			Date date=new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("How long you would rent the car?(days):");
		int day;
		day = input.nextInt();
		double cost =c.getCarRental()*day;
		input.nextLine(); 
		
		System.out.printf("The rental cost will be RM%.2f\n",cost);
		System.out.printf("Confirm rental of car %s(y-Yes, n- No):",c.getCarNumber());
		String answer;
		answer= input.nextLine();
		if (answer.equals ("y")||(answer.equals("Y"))) {
			
		//get the data save
		
		System.out.print("You have successfully rented the car!\n");
		}
		}
		else if(i==(arrayCar.size())-1){
		System.out.print("Input declined, try again.\n");
			break;
		}
		}
		System.out.print("Enter the car's car plate that you want to rent(enter 'no' to exit):");
		rentedCar = input.nextLine();
	}while (!rentedCar.equals("no"));
	input.close();
}




public static boolean rentCar(String aCarNumber,Date aStartDate, Date aEndDate)
{
	
	System.out.print("Please enter the car plate number that you want to rent:");
	
	
	return true;
}

public double computeCost(String carNumber,Date startDate, Date endDate)
{
	double cost=0;
	
	
	
	return cost;
}

public static void main(String[] args) {
	
	
	displayCar();
}






}
