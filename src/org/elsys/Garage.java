package org.elsys;

import java.sql.*;
import java.util.Scanner;

public class Garage {
	
	private Statement stmt;
	private static Connection conn;
	private Scanner scanner = new Scanner(System.in);
	
	public Garage(Connection conn) {
		Garage.conn = conn;
	}

	public void viewAllCars(String regPlate) {
		
		try {
			stmt = conn.createStatement();
	
			String sql = (regPlate == null) ? 
						"SELECT RegistrationPlate, Manufacturer, Model, YearOfRelease, "
						+ "Colour, FuelConsumption, Cost FROM cars.car"
						: "SELECT RegistrationPlate, Manufacturer, Model, YearOfRelease, "
						+ "Colour, FuelConsumption, Cost FROM cars.car"
						+ " WHERE RegistrationPlate = " + regPlate;
			
			ResultSet rs = stmt.executeQuery(sql);
	 
			while(rs.next()){
				String registrationPlate = rs.getString("RegistrationPlate");
				String manufacturer = rs.getString("Manufacturer");
				String model = rs.getString("Model");
				int yearOfRelease = rs.getInt("YearOfRelease");
				String colour = rs.getString("Colour");
		        float fuelConsump = rs.getFloat("FuelConsumption");
		        int cost = rs.getInt("Cost");
	
		        System.out.println("---------------------------");
		        System.out.println("Registration plate: " + registrationPlate);
		        System.out.println("Manufacturer: " + manufacturer);
		        System.out.println("Model: " + model);
		        System.out.println("Year of release: " + yearOfRelease);
		        System.out.println("Colour: " + colour);
		        System.out.println("Fuel Consumption: " + fuelConsump);
		        System.out.println("Rent for a day: " + cost + "\n");
		      }
			rs.close();
	      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Press key to continue...");
		scanner.nextLine();
	}
	
	
	public void addCar () {
		
		try {
		
			System.out.println("Add a car to the garage");
			System.out.println("-------------------------");
			
			System.out.print("Registration plate: ");
			String registrationPlate = "'" + scanner.nextLine() + "'";
			
			System.out.print("Manufacturer: ");
			String manufacturer = "'" + scanner.nextLine() + "'";
			
	        System.out.print("Model: ");
	        String model = "'" + scanner.nextLine() + "'";
	        
	        System.out.print("Year of release: ");
			String yearOfRelease = "'" + new Scanner(System.in).nextInt() + "'";
			
			System.out.print("Colour: ");
			String colour = "'" + scanner.nextLine() + "'";
	        
	        System.out.print("Fuel consumption: ");
	        double fuelConsum = scanner.nextDouble();
	        
	        System.out.print("Rent for a day: ");
	        int cost = scanner.nextInt();
	        
	        System.out.print("Number of doors: ");
	        int doors = scanner.nextInt();
			stmt = conn.createStatement();
				
			String sql = "INSERT INTO cars.car(RegistrationPlate, Manufacturer, "
												+ "Model, YearOfRelease, Colour, FuelConsumption,"
												+ " Cost, DoorCount) " +
												"VALUES (" + registrationPlate + "," + manufacturer + ","
												+ model + "," + yearOfRelease + "," + colour + "," + fuelConsum + "," 
												+ cost + "," + doors + ")";
			stmt.executeUpdate(sql);
			
		} catch(SQLException se){
			System.out.println("Error in SQL! Trying again ...\n");
		    addCar();
		} catch(Exception e){
			System.out.println("Error! Trying again ...\n");
		    addCar();
		}
		
		System.out.println("Car successfully added!");
		System.out.println("Press key to continue...");
		scanner.nextLine();
	}
	
	public void removeCar() {
		
		System.out.println("Remove a car from garage");
		System.out.println("---------------------------");
		
		System.out.print("Registration plate: ");
		String registrationPlate = "'" + scanner.nextLine() + "'";

        try {
        	Statement stmt = conn.createStatement();
        	String sql = "DELETE FROM cars.car " +
                     "WHERE RegistrationPlate = " + registrationPlate;
        	stmt.executeUpdate(sql);
        	
        } catch(SQLException se){
        	System.out.println("Erron in SQL! Trying again ...\n");
		    removeCar();
		} catch(Exception e){
			System.out.println("Error! Trying again ...\n");
		    removeCar();
		}
        
        System.out.println("Car successfully removed!");
        System.out.println("Press key to continue...");
        scanner.nextLine();
	}
}
