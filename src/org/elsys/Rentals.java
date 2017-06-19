package org.elsys;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Rentals {
	
	private Statement stmt = null;
	private static Connection conn = null;
	private Scanner scanner = new Scanner(System.in);


	public Rentals(Connection conn) {
		this.conn = conn;
	}


	public void viewRentals() {
		
	}
	
	
	public void addRental () {
		
	}
	
	public void removeRental() {
		
	}
}
