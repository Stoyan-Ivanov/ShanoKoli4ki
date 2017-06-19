package org.elsys;

import java.sql.*;
import java.util.Scanner;

public class Garage {
	
	private Statement stmt = null;
	private static Connection conn = null;
	private Scanner scanner = new Scanner(System.in);
	
	public Garage(Connection conn) {
		Garage.conn = conn;
	}

	public void viewAllCars() {
		
		try {
			stmt = conn.createStatement();
	
			String sql = "SELECT Manufacturer, Model, FuelConsumption, Cost FROM cars.car";
			ResultSet rs = stmt.executeQuery(sql);
	 
			while(rs.next()){
				String manufacturer = rs.getString("Manufacturer");
				String model = rs.getString("Model");
		        float fuelConsump = rs.getFloat("FuelConsumption");
		        int cost = rs.getInt("Cost");
	
		         //Display values
		         System.out.println("---------------------------");
		         System.out.println("Manufacturer: " + manufacturer);
		         System.out.println("Model: " + model);
		         System.out.println("Fuel Consumption: " + fuelConsump);
		         System.out.println("Rent for a day: " + cost + "\n");
		      }
			rs.close();
	      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void addCar () {
		
		System.out.println("Add a car to the garage");
		System.out.println("---------------------------");
		
		System.out.print("Manufacturer: ");
		String manufacturer = "'" + scanner.nextLine() + "'";
		
        System.out.print("Model: ");
        String model = "'" + scanner.nextLine() + "'";
        
        System.out.print("Fuel consumption: ");
        double fuelConsum = scanner.nextDouble();
        
        System.out.print("Rent for a day: ");
        int cost = scanner.nextInt();
        
        System.out.print("Number of doors: ");
        int doors = scanner.nextInt();
		
		try {
			
			stmt = conn.createStatement();
			
			String sql = "INSERT INTO cars.car(Manufacturer, Model, FuelConsumption, Cost, DoorCount) " +
					"VALUES (" + manufacturer + "," + model + "," + fuelConsum + "," + cost + "," + doors + ")";
			stmt.executeUpdate(sql);
			
		} catch(SQLException se){
		      se.printStackTrace();
		      
		} catch(Exception e){
		      e.printStackTrace();
		}	
		
		System.out.println("Car succesfully added!");
	}
	
	public void removeCar() {
		
		System.out.println("Remove a car from garage");
		System.out.println("---------------------------");
		
		System.out.print("Manufacturer: ");
		String manufacturer = "'" + scanner.nextLine() + "'";
		
        System.out.print("Model: ");
        String model = "'" + scanner.nextLine() + "'";
        
        try {
        	Statement stmt = conn.createStatement();
        	String sql = "DELETE FROM cars.car " +
                     "WHERE Manufacturer = " + manufacturer + " AND " + "Model = " + model;
        	stmt.executeUpdate(sql);
        	
        }catch(SQLException se){
        	System.out.println("Erron in SQL! Trying again ...\n");
		     removeCar();
		}
        System.out.println("Car succesfully removed!");
	}
}
