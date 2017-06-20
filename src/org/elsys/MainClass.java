package org.elsys;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;


public class MainClass {
	
	public static void main(String[] args) {
		
		DatabaseConnection sqlConnection = new DatabaseConnection();
		String connectionURL;
		Connection connection;
		Scanner a = new Scanner(System.in);
		
		System.out.println("Welcome to ShanoKoli4ki & Co.\n"
				+ "Log in the database and we will setup everything for you!\n"
				+ "If you want to exit just type 'Exit' .");
		
		while(a.nextLine() != "Exit") {
			try {
				connectionURL = sqlConnection.getURLDetails();
				connection = DriverManager.getConnection(connectionURL);
				System.out.println("Connection successful!");
				sqlConnection.createDatabaseStructure(connection);
				System.out.println("Database structure added!\n"
									+ "Loging into the new database...");
				connection = DriverManager.getConnection(connectionURL);
				System.out.println("Connected to the new database successfully!");
				new Menu(connection);
			} catch (Exception e) {
				System.out.println("Connection unsuccessful, try again...");
				sqlConnection.setFirstLogin(true);
			}
		}
		
		System.out.println("Thanks for using our application.");
	}
}
