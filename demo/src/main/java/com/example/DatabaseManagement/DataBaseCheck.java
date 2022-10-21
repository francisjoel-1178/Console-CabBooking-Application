package com.example.DatabaseManagement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public abstract class DataBaseCheck {
	 	protected String url; 
	    protected Connection connection;
	    protected Statement statement;

	    
	    protected PreparedStatement preparedStatement;
	    public abstract boolean checkData(String firstName,String lastName) throws SQLException;
	    public abstract boolean checkUserName(String userName,String Password) throws SQLException;
}
