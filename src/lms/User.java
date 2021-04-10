package lms;

import java.sql.*;
import java.util.*;

public class User<T> {
	// extends Labourer to be added
	Scanner sc = new Scanner(System.in);
	Connection con;
	Validators validators=new Validators();

	DBConnection db=new DBConnection();

	String query;
	T user;
	int userType;
	User() {
		
	}
	User(int userType){
		if(userType==1) {
			this.user=(T) new Contractor();
		}
		else if(userType==2) {
			this.user=(T) new Labourer();
		}
		else {
			//admin
		}
		this.userType=userType;
	}
	public void register() {
		String fname, lname, dob;
		String phone_num;
		boolean v = false;
		System.out.println("---------------------------------------------------------------");
		do {
			System.out.println("Enter phone number of the user :");
			phone_num = sc.nextLine();
			v = validators.validatePhone(phone_num);
		} while (v == false);
		v = false;
		do {
			System.out.println("Enter first name of the user :");
			fname = sc.nextLine();
			v = validators.validString(fname);
		} while (v == false);
		v = false;
		do {
			System.out.println("Enter last name of the user :");
			lname = sc.nextLine();
			v = validators.validString(fname);
		} while (v == false);
		v = false;
		do {
			System.out.println("Enter Date of birth of the user :");
			dob = sc.nextLine();
			v = validators.validDate(dob);
		} while (v == false);
		
		if(userType==1)
		{
			
			((Contractor) user).setCFname(fname);
			((Contractor) user).setCLname(lname);
			((Contractor) user).setCDOB(dob);
			((Contractor) user).setPhone_num(phone_num);
			con = db.startConnection();
			
			query = "insert into contractor(Fname,Lname,DOB) values(?,?,?)";
			user = db.insert(query,user, userType);
			
			
			System.out.println("User inserted to contractor table");
			System.out.println("user registered as contractor");

		} else if (userType == 2) {
			// laborer
			// user=(T) new Labourer();
			System.out.println("user registered as labourer");
		} else {
			// admin
			// user=(T) new Admin();
			System.out.println("user registered as admin");
		}
		System.out.println("---------------------------------------------------------------");
	}
	public T login() {
		String phone_num;
		boolean v = false;
		do {
			System.out.println("Enter phone number of the user :");
			phone_num = sc.nextLine();
			v = validators.validatePhone(phone_num);
		} while (v == false);
		if(userType==1) {
			con = db.startConnection();
			user=db.fetchCurrentUser(user,phone_num,userType);
			System.out.println("Contractor Logged in");
			return user;
		}
		else {
			return null;
		}
	}
//	public T login(String email,String password) {
//		//login for admin
////		return new User();
//	}

}
