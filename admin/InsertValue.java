package admin;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import user.InsertAndSetUserValue;
class InvalidInput extends Exception {
	private static final long serialVersionUID = 1L;

	InvalidInput(){System.out.println("Press F11 To try again");}
}
public class  InsertValue {
	private Connection con;
	Scanner sc=new Scanner(System.in);
	public InsertValue(){
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
	public int ShowRecords(){ 
		int r=0;
		try {
		String q="select *from admin";
		PreparedStatement pst=con.prepareStatement(q);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			r++;
		   }
		 }catch(SQLException e) {System.out.println(e);}
		return r;
	}
	//Method to set user input
    public int InsertAdminValues(String user,String pass)
		{ 
		 int r=0;
		 try {
				String q="insert into admin(user,pass)values(?,?)";
				//create Statement
				PreparedStatement pst=con.prepareStatement(q);
				//submitting the inputs
				pst.setString(1,user);
				pst.setString(2,pass);
				//Execute Query
				r=pst.executeUpdate();
			 }catch(SQLException e) {System.out.println(e);}
			return r;
		}
	public int SetUserName() throws SQLException,InvalidInput{
		InsertValue iv=new InsertValue();
		int r=iv.ShowRecords();
		if(r==0) {
			System.out.println("Username: ");
			String u1=sc.nextLine();
			System.out.println("Password: ");
			String p1=sc.nextLine();
			int res=iv.InsertAdminValues(u1,p1);
			System.out.println((res>0)?"Registered":"Failed");
			return res;
		}
		else{
			System.out.print("To reset Admin Credentials: Enter - 1 :\nTo view User Details Enter - 2 : \nTo Update/Delete UserDetails Enter - 3 : ");
		    int ch=sc.nextInt();
		    if(ch==1) {
			    System.out.println("Admin Portal!!");
			    System.out.print("Enter Current Uname : ");
			    String u=sc.next();
			    System.out.print("Enter current password : ");
			    String p=sc.next();
			      if(iv.login(u,p)) {
				      while(true) {
				      System.out.println("Welcome Admin "+u);
				      {
			          System.out.println("\nTo Update UserName Enter - 1 :\nTo Update Password Enter - 2 :");
			          int n=sc.nextInt();		
			          if(n==1) {
			               try {
			               String q1="update admin set user=?";
			               PreparedStatement pst1=con.prepareStatement(q1);
			               System.out.print("Enter new Username:");
			               String u1=sc.next();
			               pst1.setString(1,u1);
						   r=pst1.executeUpdate(); 
						   System.out.println((r>0)?"Registered\nPress F11 to Update Password":"Failed");
						   break;
			               }catch(SQLException e) {System.out.println(e);}
		              }else if(n==2){
						   try {
						   String q1="update admin set pass=?";
					       PreparedStatement pst1=con.prepareStatement(q1);
						   System.out.print("Enter new Password: ");
						   String p1=sc.next();
						   pst1.setString(1,p1);
						   r=pst1.executeUpdate(); 
						   System.out.println((r>0)?"Registered\nPress F11 to Update UserName":"Failed");
						   break;
					       }catch(SQLException e) {System.out.println(e);}
		                }
			          }
		            }
			      }else {System.out.println("Incorrect Username  or Password\nPress F11 to try again");}
		    }else if(ch==2) {
			    	  System.out.println("Admin Portal!!");
			    	      System.out.println("To view in Asscending order : 1\nTo view in Descending order : 2\n To view Recent Updates : 3");
			    	      String q2="";
			    	      try{int i=sc.nextInt();	
			    	      if(i==1){
			    		  q2="select *from user order by User_name";
			    	      }else if(i==2) {
			    		  q2="select *from user order by User_name desc";
			    	      }else if(i==3) {
			    		  q2="select *from user";
			    	      }else {
			    		  System.out.println("InValid Input");
			    	      }}catch(InputMismatchException e) {System.out.println("---Enter in Numbers---");}
			  		      PreparedStatement pst=con.prepareStatement(q2);
			  		      ResultSet rs=pst.executeQuery();
			  		      while(rs.next()) {
			  		      System.out.println("Username: "+rs.getString(1));
			  		      //System.out.println("Password: "+rs.getString(2));
			  	          System.out.println("Email_Id: "+rs.getString(3));
			  		      //System.out.println("Email_Pass: "+rs.getString(4));
			  		      System.out.println("Aadhar_No: "+rs.getString(5));
			  		      System.out.println("PAN_No: "+rs.getString(6));
			  	          System.out.println("DL_No: "+rs.getString(7));
			  	          System.out.println("Voter_Id: "+rs.getString(8));
			  		      System.out.println("-----------------------------------");
			  		      }System.out.println("---End of Records---");
		                 }
		            else if(ch==3) {
		            System.out.println("Admin Portal!!");
					System.out.print("Enter Admin Username: ");
					String u=sc.next();
					System.out.print("Enter Admin password : ");
					String p=sc.next();
					if(iv.login(u,p)) {
				    while(true) {iv.ValuesInsert();break;}
		            }else {System.out.println("---Invalid UserName/Password---");}
		            }
		}return r;
	}
	//Validation and Updating Code
	public boolean login(String u,String p) {
		boolean r=false;
				try {
				String q="select *from admin where user=? and pass=?";
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
	public void ValuesInsert() throws SQLException
	{
		   InsertAndSetUserValue isv=new InsertAndSetUserValue();
		   System.out.print("Enter Username to Update/Delete : ");
		   String u1=sc.next();
	       if(isv.ExistingUser(u1)) {
	       while(true) {
	    	   System.out.println("To Update UserDetailsEnter : 1\nTo Delete UserDetails Enter : 2 ");
	    	   int i=sc.nextInt();
		     if(i==1) {
	             isv.UpdateUserDetails(u1);
		     }else {
			     isv.delete(u1);
		     }
		    break;
		    }
	       }else {System.out.println("---Invalid Username or Password---");}
	}
}
