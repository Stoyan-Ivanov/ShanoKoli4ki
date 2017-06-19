package org.elsys;

import java.sql.*;
import java.util.Scanner;

public class Registrations {
	
	private Statement stmt;
	private static Connection conn;
	private Scanner scanner = new Scanner(System.in);


	public Registrations(Connection conn) {
		this.conn = conn;
	}

	public void viewPeople() {
		try {
			stmt = conn.createStatement();
	
			String sql = "SELECT FirstName, LastName, Age, Email FROM cars.people";
			ResultSet rs = stmt.executeQuery(sql);
	 
			while(rs.next()){
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
		        int age = rs.getInt("Age");
		        String email = rs.getString("Email");
	
		         //Display values
		         System.out.println("---------------------------");
		         System.out.println("FirstName: " + firstName);
		         System.out.println("LastName: " + lastName);
		         System.out.println("Age: " + age);
		         System.out.println("Email address: " + email + "\n");
		      }
			rs.close();
	      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Press key to continue...");
		scanner.nextLine();
	}
	
	
	public void addPerson () {
		
		System.out.println("Add a person");
		System.out.println("---------------------------");
		
		System.out.print("First name: ");
		String firstName = "'" + scanner.nextLine() + "'";
		
        System.out.print("Last name: ");
        String lastName = "'" + scanner.nextLine() + "'";
        
        System.out.print("Age: ");
        int age = scanner.nextInt();
        
        System.out.print("Email address: ");
        String email = "'" + (new Scanner(System.in).nextLine()) + "'";
        
		try {
			stmt = conn.createStatement();
			
			String sql = "INSERT INTO cars.people(FirstName, LastName, Age, Email) " +
						 "VALUES (" + firstName + "," + lastName + "," + age + "," + email + ")";
			stmt.executeUpdate(sql);
			
		} catch(SQLException se){
		    System.out.println("Error in SQL! Trying again ...\n");
		    addPerson();
		} catch(Exception e){
			System.out.println("Error! Trying again ...\n");
		    addPerson();
		} 
		System.out.println("Person successfully added!");
		System.out.println("Press key to continue...");
		scanner.nextLine();
	}
	
	public void removePerson() {
		
		System.out.println("Remove a person from registrations");
		System.out.println("---------------------------");
        
        System.out.print("Email address of registration : ");
        String email = "'" + scanner.nextLine() + "'";
        
        try {
        	Statement stmt = conn.createStatement();
        	String sql = "DELETE FROM cars.people WHERE Email = " + email; 
        	stmt.executeUpdate(sql);
        	
        } catch(SQLException se){
        	System.out.println("Erron in SQL! Trying again ...\n");
		     removePerson();
		} catch(Exception e){
			System.out.println("Error! Trying again ...\n");
		    removePerson();
		} 
        System.out.println("Person successfully removed!");
        System.out.println("Press key to continue...");
        scanner.nextLine();
	}
}
