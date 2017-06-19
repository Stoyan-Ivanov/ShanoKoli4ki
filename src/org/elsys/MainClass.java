package org.elsys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MainClass {
	
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	public String getConnectionInformation() {
		
		String connectionURL = "jdbc:mysql://localhost:3306/cars";
//		String line;
		
//		try {
//			System.out.print("Welcome to the JDBC & MYSQL console application" +
//							 "\nEnter the host : ");
//			line = input.readLine();
//			connectionURL += line + ":";
//			System.out.print("Enter the port : ");
//			line = input.readLine();
//			connectionURL += line + "/";
//			System.out.print("Enter database name : ");
//			line = input.readLine();
//			connectionURL += line;
//		} catch (IOException e1) {
//			e1.printStackTrace();	
//		}
		
		return connectionURL;
		
	}
	
	public Properties getProperties() {

		Properties properties = new Properties();
		String line;
		
		try {
			System.out.print("Enter username : ");
			line = input.readLine();
			properties.setProperty("user", line);
			System.out.print("Enter password : ");
			line = input.readLine();
			properties.setProperty("password", line);
			properties.setProperty("useSSL", "false");
			properties.setProperty("autoReconnect", "true");
		} catch (IOException e1) {
			e1.printStackTrace();	
		}
	
		return properties;
	}

	public static void main(String[] args) {
		
		MainClass sqlConnection = new MainClass();
		
		String connectionURL = sqlConnection.getConnectionInformation();
		
		Properties properties = sqlConnection.getProperties();
		
		try {
			Connection connection = DriverManager.getConnection(connectionURL, properties);
			System.out.println("Connected to " + connectionURL + " successfully...");
			
			new Menu(connection);
			
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful, try again...");
			main(null);
		}
		
	}
}
