package db_test;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.PreparedStatement;


    

public class Db_sampleTest {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		
		  ResultSet rset = null;

		  Connection con = DriverManager.getConnection("jdbc:phoenix:server:2181:/hbase-unsecure");

		  PreparedStatement statement = con.prepareStatement("select * from osquery");

		  rset = statement.executeQuery();

		  while (rset.next()) {

		   System.out.printf("\n COMPUTER_NAME [%s] FILENAME \n",

		    rset.getString("COMPUTER_NAME"),

		    rset.getString("FILENAME")

		   );

		  }

		  statement.close();

		  con.close();
	    
		
	}

}
