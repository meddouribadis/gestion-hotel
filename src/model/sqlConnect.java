package model;
import java.sql.*;
import javax.swing.*;


public class sqlConnect {
	Connection conn = null;
	
	
	public static Connection dbConnector(String dbName){
	
	String pathtofile = new String();
	pathtofile = System.getProperty("user.dir");
	try{
		Class.forName("org.sqlite.JDBC");
		System.out.println(pathtofile+"\\config_files\\"+dbName);
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+pathtofile+"\\config_files\\"+dbName);
		System.out.println("Sucess connection");
		return conn;		
	}
	catch (Exception e){
		System.out.println("Erreur "+e);
		return null;
	}
	}

}
