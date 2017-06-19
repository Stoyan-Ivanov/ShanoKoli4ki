package org.elsys;

import java.util.Scanner;

import java.sql.*;

public class Menu {
	
	public Connection conn;
	private Scanner scanner = new Scanner(System.in);
	private int choice;

	public Menu(Connection connection) {
		conn = connection;
		displayMenu();
	}
	
	public void displayMenu() {
		Garage garage = new Garage(conn);
		Registrations reg = new Registrations(conn);
		Rentals rent = new Rentals(conn);
		clearScreen();
		
		System.out.println("Choose from these :");
		System.out.println("-------------------------\n");
	    System.out.println("1 - View car garage");
	    System.out.println("2 - Add a car to the garage");
	    System.out.println("3 - Remove a car from the garage");
	    System.out.println("4 - View list of people who have a registration");
	    System.out.println("5 - Add a registration for a person");
	    System.out.println("6 - Remove a registration");
	    System.out.println("7 - List of acive rentals");
	    System.out.println("8 - Add a rental");
	    System.out.println("9 - Remove a renral");
	        
	    while (true) {
	    	
	    	choice = scanner.nextInt();
	    
		    switch(choice) {
			    case 1: clearScreen(); garage.viewAllCars(); break;
			    case 2:	clearScreen(); garage.addCar(); break;
			    case 3: clearScreen(); garage.removeCar(); break;
			    case 4: clearScreen(); reg.viewPeople(); break;
			    case 5: clearScreen(); reg.addPerson(); break;
			    case 6: clearScreen(); reg.removePerson(); break;
			    case 7:	clearScreen(); rent.viewRentals(); break;
			    case 8: clearScreen(); rent.addRental(); break;
			    case 9:	clearScreen(); rent.removeRental(); break;
			    default: 
			    	System.out.println("Wrong input!");
			    	clearScreen();
			    	displayMenu();
			    break;
		    }
		    displayMenu();
	    }
	}
	
	static void clearScreen() {
		for (int i = 0; i < 10; ++i) {
			System.out.println();
		}
	}

}
