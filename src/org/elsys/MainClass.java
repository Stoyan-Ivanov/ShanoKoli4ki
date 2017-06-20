package org.elsys;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;


public class MainClass {
	
	public static void main(String[] args) {
		
		DatabaseConnection sqlConnection = new DatabaseConnection();
		String connectionURL;
		Connection connection;
		Scanner a = new Scanner(System.in);
		
		System.out.println(" _____ _                       _   __      _ _   ___ _    _ \n"
				+ "/  ___| |                     | | / /     | (_) /   | |  (_)\n"
				+ "\\ `--.| |__   __ _ _ __   ___ | |/ /  ___ | |_ / /| | | ___ \n"
				+ " `--. \\ '_ \\ / _` | '_ \\ / _ \\|    \\ / _ \\| | / /_| | |/ / |\n"
				+ "/\\__/ / | | | (_| | | | | (_) | |\\  \\ (_) | | \\___  |   <| |\n"
				+ "\\____/|_| |_|\\__,_|_| |_|\\___/\\_| \\_/\\___/|_|_|   |_/_|\\_\\_|\n");
		System.out.println("\n\nPress Enter to continue or type 'Exit' to exit");
		
		while(!a.nextLine().equals("Exit")) {
			try {
				connectionURL = sqlConnection.getURLDetails();
				connection = DriverManager.getConnection(connectionURL);
				System.out.println("Connection to server successful!");
				sqlConnection.createDatabaseStructure(connection);
		    	connection = DriverManager.getConnection(connectionURL);
		    	System.out.println("Connected to the database successfully!");
				
				new Menu(connection);
			} catch (Exception e) {
				System.out.println("Connection unsuccessful, try again...");
				sqlConnection.setFirstLogin(true);
			}
		}
		System.out.println("Thanks for using our application.");
	}
}
