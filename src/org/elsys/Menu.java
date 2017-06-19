package org.elsys;

import java.util.Scanner;

import java.sql.*;

public class Menu {
	
	public static Connection conn = null;
	private static Scanner scanner = new Scanner(System.in);

	public Menu(Connection connection) {
		conn = connection;
		displayMenu();
	}
	
	public static void displayMenu() {
		Garage garage = new Garage(conn);
		clearScreen();
		
		System.out.println("Choose from these :\n");
		System.out.println("-------------------------\n");
	    System.out.println("1 - View car garage");
	    System.out.println("2 - Rent a car");
	    System.out.println("3 - Cancel a rental");
	    System.out.println("4 - Add a car to the garage");
	    System.out.println("5 - Remove a car from the garage");
	        
	   
	    while (true) {
	    	int choice = scanner.nextInt();
	    
		    switch(choice) {
			    case 1: garage.viewAllCars(); break;
			    case 2: break;
			    case 3: break;
			    case 4:  garage.addCar(); break;
			    case 5: break;
			    default: 
			    	System.out.println("Wrong input!");
			    	clearScreen();
			    	displayMenu();
			    break;
		    }
	    }
	}
	
	static void clearScreen() {
		for (int i = 0; i < 20; ++i) System.out.println();
	}

}
