package org.elsys;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Rentals {
	
	private Statement stmt;
	private static Connection conn;
	private Scanner scanner = new Scanner(System.in);


	public Rentals(Connection conn) {
		this.conn = conn;
	}


	public void viewRentals() {
		
	}
	
	
	public void addRental () {
		String regPlate;
		
		try {
			System.out.println("Welcome to our rental platform");
			System.out.println("---------------------------\n");
			System.out.println("Please enter your email address: ");
			
			String email = "'" + scanner.nextLine() + "'";
			
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM shanokoli4ki.people WHERE Email = " + email;
			
			ResultSet result = stmt.executeQuery(sql);
			System.out.println(result);
			
			if(!result.next()) {
				System.out.println("\n Please make sure that you have made an account! \n");
				
				System.out.println("Press Enter to continue...");
		        scanner.nextLine();
		        
			} else {
				
				System.out.println("\n Login successfull!\n");
				System.out.print("Enter a registration plate of the car: ");
				
				regPlate = "'" + scanner.nextLine() + "'";
				
				stmt = conn.createStatement();
				
				sql = "SELECT * FROM shanokoli4ki.car WHERE RegistrationPlate = " + regPlate;
				
				result = stmt.executeQuery(sql);
				
				if(!result.next()) {
					System.out.println("\n Please make sure that the registartion plate is valid!\n");
					
					System.out.println("Press Enter to continue...");
			        scanner.nextLine();
			        
				} else {
					
					System.out.print("Rent a car from : ");
					String fromDate = "'" + scanner.nextLine() + "'";
					
					System.out.print("To:");
					String toDate = "'" + scanner.nextLine() + "'";
					
					stmt = conn.createStatement();
					
					sql = "SELECT * FROM shanokoli4ki.rents WHERE 'RegistrationPlate' = " + regPlate + " AND " +
					"'FromDate' >= " + fromDate + " AND " + "'ToDate' <= " + toDate ;
					
					
					result = stmt.executeQuery(sql);
					System.out.println("tuka vliza");
					
					if(!result.next()) {
						System.out.println("tuka syshto vliza");
						stmt = conn.createStatement();
						
						sql = "INSERT INTO shanokoli4ki.rents (RegistrationPlate, Email, FromDate, ToDate) VALUES(" + regPlate + " ," 
								+ email + " ," + fromDate + " ," + toDate + ")";
						
						stmt.executeUpdate(sql);
						
						System.out.println("\n* Rental successfully placed! * \n");
						
						System.out.println("Press Enter to continue...");
				        scanner.nextLine();
				        
					} else {
						
						System.out.println("\n The car is not available for the date range you have provided! \n");
						
						System.out.println("Press Enter to continue...");
				        scanner.nextLine();
					}
				}	
			}
		} catch (SQLException e) {}
			
	}
			
	
	public void removeRental() {
		
	}
}
