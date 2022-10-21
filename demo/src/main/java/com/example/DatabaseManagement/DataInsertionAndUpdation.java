package com.example.DatabaseManagement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface DataInsertionAndUpdation {
	 	public void putData() throws SQLException;
	    public void updateData() throws SQLException;
}
