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
		try {
			System.out.println("----- All rentals -----\n");
			
			stmt = conn.createStatement();
			
			String sql = "Select p.FirstName, p.LastName,"
					+ "p.Age, p.Email, c.RegistrationPlate, c.Manufacturer, c.Model, c.FuelConsumption, "
					+ "c.Colour, c.DoorCount, c.YearOfRelease, c.Cost, r.FromDate, r. ToDate"
					+ " FROM shanokoli4ki.people p "
					+ "INNER JOIN shanokoli4ki.rents r ON p.Email = r.Email "
					+ "INNER JOIN shanokoli4ki.car c ON c.RegistrationPlate = r.RegistrationPlate";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
		        System.out.println("---------------------------");
		        System.out.println("First Name: " + rs.getString("FirstName"));
		        System.out.println("Last Name: " + rs.getString("LastName"));
		        System.out.println("Email: " + rs.getString("Email"));
		        System.out.println("Age: " + rs.getString("Age"));
		        System.out.println("***************************");
		        System.out.println("Registration plate: " + rs.getString("RegistrationPlate"));
		        System.out.println("Manufacturer: " + rs.getString("Manufacturer"));
		        System.out.println("Model: " + rs.getString("Model"));
		        System.out.println("Year of release: " + rs.getInt("YearOfRelease"));
		        System.out.println("Colour: " + rs.getString("Colour"));
		        System.out.println("Number of Doors: " + rs.getInt("DoorCount"));
		        System.out.println("Fuel Consumption: " + rs.getFloat("FuelConsumption"));
		        System.out.println("Rent for a day: " + rs.getInt("Cost"));
		        System.out.println("***************************");
		        System.out.println("From Date: " + rs.getString("FromDate"));
		        System.out.println("To Date: " + rs.getString("ToDate") + "\n");
		        
		      }
			rs.close();
			
			System.out.println("Press Enter to continue...");
	        scanner.nextLine();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
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
					
					if(!result.next()) {
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
		
		System.out.println("Delete a rental");
		System.out.println("---------------------------\n");
		
		System.out.print("Enter an email: ");
		String email = "'" + scanner.nextLine() + "'";
		
		System.out.print("Enter registration plate of he car: ");
		String regPlate = "'" + scanner.nextLine() + "'";
		
		System.out.print("Enter the starting date of the rental: ");
		String fromDate = "'" + scanner.nextLine() + "'";
		
		try {
			stmt = conn.createStatement();
			String sql = "DELETE FROM shanokoli4ki.rents WHERE Email = " + email + " AND " + "RegistrationPlate = "
					+ regPlate + " AND " + "FromDate = " + fromDate;
				
			stmt.executeUpdate(sql);
			
			System.out.println("\n*Rental successfully deleted!*\n");
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("Press Enter to continue...");
		scanner.nextLine();
		
	}
}
