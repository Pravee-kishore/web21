package admin;

import java.sql.SQLException;
import java.util.Scanner;

import user.UserMainClass;
class Invalid extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Invalid(){
		System.out.println("---Try again By pressing F11---");
	}
	
}
public class MainClass {
	public static void main(String[] args) throws SQLException, InvalidInput,Invalid {
        System.out.println("For Admin : 0\nFor User  : 1");    
		try (Scanner sc = new Scanner(System.in)) {
			int i=sc.nextInt();
			try{
				if(i==0) {
			    System.out.println("Admin Portal!!");
			    System.out.print("Enter Current Uname : ");
			    String u=sc.next();
			    System.out.print("Enter current password : ");
			    String p=sc.next();
			    InsertValue iv=new InsertValue();
			      if(iv.login(u,p)) {
			    	  while(true) {
				      System.out.println("Welcome Admin "+u);
			           iv.ShowRecords();
			           iv.SetUserName();
			           break;
			           }
			      }else {System.out.println("---Invalid Username or Password---\nPress F11 To Try again!!!");}
			}else if(i==1) {
				UserMainClass UM=new UserMainClass();
			}else {
				System.out.println("Invalid Input");
			}
			}catch(Exception Invalid) {throw new Invalid();}
		}
		}
}