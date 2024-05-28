package admin;
//import Statements
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable{

	public static void main(String[] args) {
		//Server Details
		String url="jdbc:mysql://localhost:3306/bankingdetails";
		String u="root";
		String p="accord";
		try {
			//Connection Establishment
			Connection con =DriverManager.getConnection(url,u,p);
			//Create Statement
			String q="create table admin( varchar(20) not null,pass varchar(20) not null)";
			Statement smt=con.createStatement();
			//Execute Query
			smt.execute(q);
			System.out.println("Table Created!!");
			//close connection 
			con.close();
		}catch(SQLException e)
		{
			System.out.println(e);
		}
	}

}
