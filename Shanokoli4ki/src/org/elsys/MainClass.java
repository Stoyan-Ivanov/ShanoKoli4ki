package org.elsys;

import java.sql.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MainClass {
	
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	public static String getConnectionInformation() {
		
		String connectionURL = "jdbc:mysql://";
		String line;
		
		try {
			System.out.print("Welcome to the JDBC & MYSQL console application" +
							 "\nEnter the host : ");
			line = input.readLine();
			connectionURL += line + ":";
			System.out.print("Enter the port : ");
			line = input.readLine();
			connectionURL += line + "/";
			System.out.print("Enter database name : ");
			line = input.readLine();
			connectionURL += line;
		} catch (IOException e1) {
			e1.printStackTrace();	
		}
		
		return connectionURL;
		
	}
	
	public static Properties getProperties() {

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
		
		String connectionURL = getConnectionInformation();
		
		Properties properties = getProperties();
		
		try {
			Connection connection = DriverManager.getConnection(connectionURL, properties);
			System.out.println("Connected to " + connectionURL +" successfully...");
			
			Menu menu = new Menu(connection);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
