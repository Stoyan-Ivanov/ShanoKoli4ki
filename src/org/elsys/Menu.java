package org.elsys;

import java.util.Scanner;

import java.sql.*;

public class Menu {
	
	private Connection conn;
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
		System.out.println("------------------\n");
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
	    	
	    	clearScreen();
	    
		    switch(choice) {
			    case 1: garage.viewAllCars(); break;
			    case 2: garage.addCar(); break;
			    case 3: garage.removeCar(); break;
			    case 4: reg.viewPeople(); break;
			    case 5: reg.addPerson(); break;
			    case 6: reg.removePerson(); break;
			    case 7:	rent.viewRentals(); break;
			    case 8: rent.addRental(); break;
			    case 9:	rent.removeRental(); break;
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
		for (int i = 0; i < 10; i++) {
			System.out.println();
		}
	}

}
