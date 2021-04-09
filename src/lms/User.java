package lms;

import java.sql.*;
import java.util.*;

public class User<T extends Contractor> {
	// extends Labourer to be added
	Scanner sc = new Scanner(System.in);
	Connection con;
	Validators validators=new Validators();

	DBConnection<T> db=new DBConnection<>();

	String query;
	T user;

	User() {
		
	}
	User(T user){
		this.user=user;
	}
	public void register(int userType) {
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
		
		
		if (userType == 1) {
			// contractor
			user = (T) new Contractor();
			user.setCFname(fname);
			user.setCLname(lname);
			user.setCDOB(dob);
			user.setPhone_num(phone_num);
			con = db.startConnection();
			
			query = "insert into contractor(Fname,Lname,DOB) values(?,?,?)";
			user = db.insert(query, user);
			
			
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
	public T login(int userType) {
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
