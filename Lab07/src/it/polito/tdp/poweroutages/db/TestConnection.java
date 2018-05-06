package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;

public class TestConnection {

	public static void main(String[] args) {
		PowerOutageDAO dao = new PowerOutageDAO();
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Test PASSED");

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}
		
	
		
	}

}
