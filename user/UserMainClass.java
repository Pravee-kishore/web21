package user;

import java.sql.SQLException;
import java.util.Scanner;
public class UserMainClass 
{
	public UserMainClass() throws SQLException{	
	InsertAndSetUserValue isv=new InsertAndSetUserValue();
            Scanner sc = new Scanner(System.in);
			System.out.println("To Register Enter - 1\nTo Log in Enter - 2\n---------------------------------------");
			int i=sc.nextInt();
			if(i==1) {
				isv.SetUserName1();
			}else if(i==2) {
				System.out.print("Enter Uname : ");
			    String u1=sc.next();
			    System.out.print("Enter password : ");
			    String p1=sc.next();
    	    if(isv.login(u1,p1)) {
			    while(true) {
			    System.out.println("Welcome "+u1);
				System.out.println("To reset the Username or Password Enter - 1\nTo Update/Delete your Details Enter - 2\nTo view your Details Enter - 3");
				int i1=sc.nextInt();
				switch (i1) {
				case 1:isv.UpdateUserAndPass(u1,p1);
				break;
				case 2:isv.ValuesInsert(u1) ;	
				break;
				case 3:isv.ViewUserDeatils(u1);	
				break;
				}
				break;
			    }
    	    }else {System.out.print("---Invalid Username or Password---\nPress F11 To Try again!!!");}
			}else
			{
				System.out.println("Press F11 to Try again");
			}
	}
}
