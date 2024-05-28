 package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;
public class InsertAndSetUserValue {
	    private Connection con;
	    public InsertAndSetUserValue(){
	    	try {
	    		//Server details
	    		String url="jdbc:mysql://localhost:3306/bankingdetails";
	    		String u="root";
	    		String p="accord";
	    		//Connection Establishment
	    	    con =DriverManager.getConnection(url,u,p);
	    	    }catch(SQLException e) {
	    		System.out.println(e);
	    	    }
	        }
		Scanner sc=new Scanner(System.in);
		public int InsertAdminValues1(String User_Name,String Password)
		{ 
			 InsertAndSetUserValue isv=new InsertAndSetUserValue();
			int r=0;
			if(isv.ExistingUser(User_Name)) {
				Random rand = new Random();
				int r1=rand.nextInt(100);
				int r2=rand.nextInt(10000);
				int r3=rand.nextInt(1000);
			    while(true) {
			    System.out.println("Username already exists\nTry a new username like:"+User_Name+r1+" or "+User_Name+r2+" or "+User_Name+r3);
			    break;}
			}else {
			try {
				String q="insert into user(User_Name,Password)values(?,?)";
				//create Statement
				PreparedStatement pst=con.prepareStatement(q);
				//submitting the inputs
				pst.setString(1,User_Name);
				pst.setString(2,Password);
			   r = pst.executeUpdate();
			}catch(SQLException e) {System.out.println(e);}
			      }
			return r;
		}
		public int SetUserName1(){
			    InsertAndSetUserValue isv=new InsertAndSetUserValue();
				System.out.print("Username: ");
				String u1=sc.next();
				System.out.print("Password: ");
				String p1=sc.next();
				int res=isv.InsertAdminValues1(u1,p1);
				System.out.println((res>0)?"Registered":"Press F11 to try again");
				return res;
		}
		//LOGIN CODE
		public boolean login(String u,String p) {
				boolean r=false;
				try {
				String q="select *from user where User_Name=? and Password=?";
				PreparedStatement pst=con.prepareStatement(q);
				pst.setString(1, u);
				pst.setString(2, p);
				ResultSet rs=pst.executeQuery();
				while(rs.next()) {
				r=true;
			}
			}catch(Exception e) { System.out.println("Error "+e);	}
			return r;
		}
		//METHOD FOR UDATING USERNAME AND PASSWORD
		public int UpdateUserAndPass(String u1, String p1)
		{
			    System.out.println("Welcome Admin "+u1);
		        System.out.println("\nTo Update UserName Enter - 1 :\nTo Update Password Enter - 2 :");
		        int n=sc.nextInt();		
		        int r;
			 if(n==1) {
		        try {
		        String q1="update user set User_Name=? where Password=?";
		        PreparedStatement pst1=con.prepareStatement(q1);
		        System.out.print("Enter new Username:");
		        String u=sc.next();
		        pst1.setString(1,u);
		        pst1.setString(2,p1);
		        r=pst1.executeUpdate();
		        System.out.println((r>0)?"UserName Registered":"Failed");
		        }catch(SQLException e) {System.out.println(e);}
	            }else if(n==2){
				try {
		     	String q1="update user set Password=? where User_Name=?";
	     		PreparedStatement pst1=con.prepareStatement(q1);
				System.out.print("Enter new Password: ");
				String P=sc.next();
				pst1.setString(1,P);
				pst1.setString(2,u1);
				r=pst1.executeUpdate(); 
				System.out.println((r>0)?"Registered":"Failed");
			    }catch(SQLException e) {System.out.println(e);}
		    }else {
			    System.out.println("---Try again---"); }
			return 0;
		}
		//CHECK USERNAME EXISTING OR NOT
		public boolean ExistingUser(String u)
		{
				boolean r=false;
				try {
				String q="select *from user where User_Name=?";
				PreparedStatement pst=con.prepareStatement(q);
				pst.setString(1, u);
				ResultSet rs=pst.executeQuery();
				while(rs.next()) {r=true;}
			    }catch(Exception e) { System.out.println("Error "+e);}
			    return r;
		}
		public  void ViewUserDeatils(String u1) throws SQLException
		{
				InsertAndSetUserValue isv=new InsertAndSetUserValue();
			    System.out.println("Welcome "+u1);
				String q="select *from user where User_Name=?";
				PreparedStatement pst=con.prepareStatement(q);
				pst.setString(1, u1);
				ResultSet rs=pst.executeQuery();
				while(rs.next()) {
		  		System.out.println("Username: "+rs.getString(1));
		  		System.out.println("Password: "+rs.getString(2));
		  	    System.out.println("Email_Id: "+rs.getString(3));
		  		System.out.println("Email_Pass: "+rs.getString(4));
		  		System.out.println("Aadhar_No: "+rs.getString(5));
		  		System.out.println("PAN_No: "+rs.getString(6));
		  	    System.out.println("DL_No: "+rs.getString(7));
		  		System.out.println("-----------------------------------");
		  		}System.out.print("---End Of Records---");
		    }
		public int UpdateUserDetails(String u) throws SQLException 
		{
		   System.out.println("To Upate Email_ID : 1\nTo Update Email_ID Password : 2\nTo Update Aadhar_No : 3\nTo Update PAN_No : 4\nTo Update DL_No :5\nTo Update Voter_Id : 6\n To END : 7 ");
		   int r=0; 
		   try{   	  
		   while (true) {
		   try { 
			  int i=sc.nextInt();
			  if(i==1) {
			   System.out.println("Enter Email_ID : ");
			   String Eid=sc.next();
			   String q="update user set Email_ID=? where User_Name=?";
			   //create Statement
			   PreparedStatement pst=con.prepareStatement(q);
			   //submitting the inputs
			   pst.setString(1,Eid);
			   pst.setString(2,u);
			   r=pst.executeUpdate();
			   System.out.println((r>0)?"User Details Registered":"Failed To Register User Details");
			   }else if(i==2) {
			   System.out.println("Enter Email_ID Password: ");
			   String Epwd=sc.next();
			   String q1="update user set EmailID_Pass=? where User_Name=?";
			   //create Statement
			   PreparedStatement pst1=con.prepareStatement(q1);
			   //submitting the inputs
			   pst1.setString(1,Epwd);
			   pst1.setString(2,u);
			   r=pst1.executeUpdate();
			   System.out.println((r>0)?"User Details Registered":"Failed To Register User Details");
			   }else if(i==3) {
			   System.out.println("Enter Aadhar_No : ");
			   String An=sc.next();
			   String q2="update user set Aadhar_Number=? where User_Name=?";
			   //create Statement
			   PreparedStatement pst2=con.prepareStatement(q2);
			   //submitting the inputs
			   pst2.setString(1,An);
			   pst2.setString(2,u);
			   r=pst2.executeUpdate();
			   System.out.println((r>0)?"User Details Registered":"Failed To Register User Details");
			   }else if(i==4) {
			   System.out.println("Enter PAN_No : ");
			   String Pan_No=sc.next();
			   String q3="update user set PAN_Number=? where User_Name=?";
			   //create Statement
			   PreparedStatement pst3=con.prepareStatement(q3);
			   //submitting the inputs
			   pst3.setString(1,Pan_No);
			   pst3.setString(2,u);
			   r=pst3.executeUpdate();
			   System.out.println((r>0)?"User Details Registered":"Failed To Register User Details");
			   }else if(i==5) {
			   System.out.println("Enter DL_No: ");
			   String DL_No=sc.next(); 
			   String q4="update user set DL_Number=? where User_Name=?";
			   //create Statement
			   PreparedStatement pst4=con.prepareStatement(q4);
			   //submitting the inputs
			   pst4.setString(1,DL_No);
			   pst4.setString(2,u);
			   r=pst4.executeUpdate();
			   System.out.println((r>0)?"User Details Registered":"Failed To Register User Details");
			   }else if(i==6) {
	    	   System.out.println("Enter Vote_Id: ");
	    	   String Vt_ID=sc.next();  
	    	   String q5="update user set Voter_ID =? where User_Name=?";
	    	   //create Statement
	    	   PreparedStatement pst5=con.prepareStatement(q5);
	    	   //submitting the inputs
	    	   pst5.setString(1,Vt_ID);
	    	   pst5.setString(2,u);
	    	   r=pst5.executeUpdate();
	    	   System.out.println((r>0)?"User Details Registered":"Failed To Register User Details");
	    	   }else if(i==7) {break;}
	    	   else{ System.out.println("---Invalid Input---");}
	    	   }catch(SQLException e) {System.out.println(e);}
		       }
		    }catch(InputMismatchException e) {System.out.println("---Enter an Integer---\nPress F11 to try again");}
		       return r;
		  }
		//METHOD TO DELETE USER DETAILS USING USERNAME
		public int delete(String u) {
			int r=0;
			try {
			System.out.println("Enter : Y/y to Delete\nEnter N/n to cancel");
			char ch=sc.next().charAt(0);
			if(ch=='y'||ch=='Y') {
			String q="delete from user where User_Name=?";	
			PreparedStatement pst=con.prepareStatement(q);	
			pst.setString(1, u);
			r=pst.executeUpdate();
			System.out.println("---USER DELETED---");
				}else {System.out.println("Failed to delete");}
			}catch(Exception e) { System.out.println("Error "+e);	}
			return r;
		}
		//METHOD FOR INSERTING USER INPUTS
		public void ValuesInsert(String u1) throws SQLException
		{
			   InsertAndSetUserValue isv=new InsertAndSetUserValue();
			   System.out.println("To Update UserDetailsEnter : 1\nTo Delete UserDetails Enter : 2 ");
		       int i=sc.nextInt();
			   if(i==1) {
		           isv.UpdateUserDetails(u1);
			   }else {
				   isv.delete(u1);
			   }
	   }
}