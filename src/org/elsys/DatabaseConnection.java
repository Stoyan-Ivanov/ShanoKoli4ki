package org.elsys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseConnection {
	
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private boolean firstLogin = true;
	
	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	private String connectionURL;
	
	public DatabaseConnection() { 
		setConnectionURL("jdbc:mysql://");
	}
	
	public void setConnectionURL(String s) {
		this.connectionURL = s;
	}
	
	public String getConnectionURL() {
		return this.connectionURL;
	}
	
	public String getURLDetails() {

		if (isFirstLogin()) {
			try {
				System.out.print("Enter host : ");
				connectionURL += input.readLine() + ":";
				System.out.print("Enter port : ");
				connectionURL += input.readLine() + "/?user=";
				System.out.print("Enter username : ");
				connectionURL += input.readLine();
				System.out.print("Enter password : ");
				connectionURL += "&password=" + input.readLine() + "&autoReconnect=true&useSSL=false";
			} catch (IOException e1) {
				e1.printStackTrace();	
			}
			setFirstLogin(false);
		} else {
			setConnectionURL(connectionURL);
		}
		return connectionURL;
	}
	
	public void createDatabaseStructure(Connection connection) {
		try {
			
			Statement stmt = connection.createStatement();
			String sql = "CREATE DATABASE IF NOT EXISTS shanokoli4ki";
		    
		    stmt.executeUpdate(sql);
		    
			sql = "CREATE TABLE IF NOT EXISTS `shanokoli4ki`.`car` ("
					+ "`RegistrationPlate` VARCHAR(45) NOT NULL UNIQUE,"
					+ "`Manufacturer` VARCHAR(45) NOT NULL,"
					+ "`Model` VARCHAR(45) NOT NULL,"
					+ "`Cost` INT NOT NULL,"
					+ "`FuelConsumption` DECIMAL(4,2) NOT NULL,"
					+ "`DoorCount` INT NOT NULL,"
					+ "`Colour` VARCHAR(45) NOT NULL,"
					+ "`YearOfRelease` INT NOT NULL,"
					+ "PRIMARY KEY (`RegistrationPlate`))";
			stmt.executeUpdate(sql);
				
			sql ="CREATE TABLE IF NOT EXISTS `shanokoli4ki`.`people` ("
					+ "`FirstName` VARCHAR(45) NOT NULL,"
					+ "`LastName` VARCHAR(45) NOT NULL,"
					+ "`Age` INT NOT NULL,"
					+ "`Email` VARCHAR(45) NOT NULL UNIQUE,"
					+ "PRIMARY KEY (`Email`))";
				
			stmt.executeUpdate(sql);
				
			sql = "CREATE TABLE IF NOT EXISTS `shanokoli4ki`.`rents` ("
					+ "`idRents` INT NOT NULL AUTO_INCREMENT,"
					+ "`RegistrationPlate` VARCHAR(45) NOT NULL,"
					+ "`Email` VARCHAR(45) NOT NULL,"
					+ "`FromDate` DATE NOT NULL,"
					+ "`ToDate` DATE NOT NULL,"
					+ "PRIMARY KEY (`idRents`),"
					+ "CONSTRAINT `RegistrationPlate`"
					+ " FOREIGN KEY (`RegistrationPlate`)"
					+ " REFERENCES `shanokoli4ki`.`car` (`RegistrationPlate`)"
					+ " ON DELETE NO ACTION"
					+ " ON UPDATE NO ACTION,"
					+ "CONSTRAINT `Email`"
					+ " FOREIGN KEY (`Email`)"
					+ " REFERENCES `shanokoli4ki`.`people` (`Email`)"
					+ " ON DELETE NO ACTION"
					+ " ON UPDATE NO ACTION)";
				
			stmt.executeUpdate(sql);
			
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
	}
}
