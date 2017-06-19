package org.elsys;

import java.sql.*;
import java.util.Scanner;

public class Garage {
	static Connection conn = null;
	private Scanner scanner;
	
	public Garage(Connection conn) {
		Garage.conn = conn;
	}

	public void viewAllCars() {
		Statement stmt;
		try {
			stmt = conn.createStatement();
	
			String sql = "SELECT idCar, Manufacturer, Model, FuelConsumption, Cost FROM cars.car";
			ResultSet rs = stmt.executeQuery(sql);
	 
			while(rs.next()){
				int id = rs.getInt("idCar");
				String manufacturer = rs.getString("Manufacturer");
				String model = rs.getString("Model");
		        float fuelConsump = rs.getFloat("FuelConsumption");
		        int cost = rs.getInt("Cost");
	
		         //Display values
		         System.out.println("---------------------------");
		         System.out.println("CarId: " + id);
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
		
		scanner = new Scanner(System.in);
		
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
		
		Statement stmt = null;
		
		try {
			
			stmt = conn.createStatement();
			
			String sql = "INSERT INTO cars.car(Manufacturer, Model, FuelConsumption, Cost, DoorCount) " +
					"VALUES (" + manufacturer + "," + model + "," + fuelConsum + "," + cost + "," + doors + ")";
			stmt.executeUpdate(sql);
			
		}catch(SQLException se){
		      se.printStackTrace();
		      
		}catch(Exception e){
		      e.printStackTrace();
		}	
		
		 System.out.println("Car succesfully added!");
	}
}
