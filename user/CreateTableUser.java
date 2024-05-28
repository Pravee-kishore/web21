package user;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class CreateTableUser {
		public static void main(String[] args) {
			//Server Details
			String url="jdbc:mysql://localhost:3306/bankingdetails";
			String u="root";
			String p="accord";
			try {
				//Connection Establishment
				Connection con =DriverManager.getConnection(url,u,p);
				//Create Statement
				String q="create table user(User_Name varchar(20) not null,Password varchar(20) not null,Email_ID varchar(30) unique,EmailID_Pass varchar(15),Aadhar_Number varchar(12) unique,PAN_Number varchar(10) unique,DL_Number varchar(20) unique,Voter_ID varchar(15) unique)";
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