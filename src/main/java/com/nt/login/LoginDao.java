package com.nt.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

@Repository
public class LoginDao {
	public static final String LOGIN_QUEYR="SELECT COUNT(*) FORM LOGIN_DETAILS WHERE USER_NAME=? AND PASSWORD=?";
	
	public static final String ROLE_QUERY="SELECT ROLE FROM LOGIN_DETAILS WHERE USERNAME=? AND PASSWORD=?";
	
	
	public int login(String username,String password) throws Exception{
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		//load the driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521", "scott", "tiger");
		//create ps obj
		ps=con.prepareStatement(LOGIN_QUEYR);		
		//set q p values
		ps.setString(1, username);
		ps.setString(2, password);
		
		count=ps.executeUpdate();
		return count;
	}
	
	public String role(String username,String password)throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		String role=null;
		//load the driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521", "scott", "tiger");
		//create ps obj
		ps=con.prepareStatement(ROLE_QUERY);		
		//set q p values
		ps.setString(1, username);
		ps.setString(2, password);
		//execute the query
		
		ps.executeUpdate();
		
		while(rs.next())
		{
			role=rs.getString(1);
		}
		return role;
	}
}