package lms;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Contractor {
	int Contractor_ID;
	String CFname, CLname, CDOB;
	String phone_num;
	int userType=1;
	int Contract_ID;
	Scanner sc = new Scanner(System.in);
	DBConnection db = new DBConnection();
	Validators v=new Validators();
	Connection con=null;
	Contractor() {

	}

	public Contractor(int contractor_Id, String cFname, String cLname, String cDOB,String phone_num) {
		super();
		Contractor_ID = contractor_Id;
		CFname = cFname;
		CLname = cLname;
		CDOB = cDOB;
		this.phone_num=phone_num;
	}
	
	public void CMenu() {
		boolean flag = true;
		do {
			System.out.println("Welcome, "+CFname+" "+CLname+" \n\n1. Personal information \n"
					+ "2. Update details \n3. Published contract \n4. Accept Contract Request\n5. Display Hiring Records\n" + "6. Logout\n" + "7. Delete account\n");
			
			int choice = Integer.parseInt(sc.nextLine().trim());
			switch(choice) {
			case 1:
				System.out.println(toString());
				break;
			case 2:
				updateDetails();
				break;
			case 3:
				publishContract();
				break;
			case 4:acceptContractRequest();
				break;
			case 5:
				displayHiringRecords();
				break;
			case 6:
				System.out.println("You have successfully logged out");
				flag = false;
				break;
			case 7:
				deleteAccount();
				break;
			}

		}while (flag);
	}
	
	public void publishContract() {
		System.out.println("Publish Contracts");
		boolean valid=false;
		int days,wage,Postal_Code;
		String street,city,state;
		String skill;
		do {
			valid=true;
			System.out.println("Enter number of days of work");
			days=sc.nextInt();
			valid=days>0;
			if(!valid) {
				System.out.println("Enter valid input");
			}
		}while(valid==false);
		
		do {
			System.out.println("Enter wage:");
			wage=sc.nextInt();
			sc.nextLine();
			valid=wage>0;
			if(!valid) {
				System.out.println("Invalid wage");
			}
		}while(valid==false);
		do {
			System.out.println("Enter Street :");
			street = sc.nextLine().trim();
			valid=true;
			if (!street.matches("[0-9\\sa-zA-Z]+")) {
				System.out.println("error aagya");
				valid = false;
			}
			if(street.length()>100) {
				System.out.println("Address Too Long");
				valid=false;;
			}
		} while (valid==false);
		do {
			
			System.out.println("Enter City :");
			city = sc.nextLine().trim();
			valid=v.validString(city);
			if(!valid) {
				System.out.println("Invalid input ");
			}
			if(city.length()>25) {
				System.out.println("City Name Too Long");
				valid=false;
			}
		
		}while(valid==false);
		do {
			
			System.out.println("Enter State :");
			state = sc.nextLine().trim();
			valid=v.validString(state);
			if(!valid) {
				System.out.println("Invalid input ");
			}
			
			if(state.length()>25) {
				System.out.println("State Name Too Long");
				valid=false;
			}
		
		}while(valid==false);
		
		do {
			valid=true;
			System.out.println("Enter Postal Code :");
			Postal_Code = Integer.parseInt(sc.nextLine().trim());
			if(Postal_Code<100000||Postal_Code>999999) {
				System.out.println("Invalid Postal Code:");
				valid=false;
			}
		}while(valid==false);
		do {
			valid=true;
			System.out.println("Enter Skills required Separated by Space");
			skill = sc.nextLine();
			if(!skill.matches("[a-zA-z]+(\\s[a-zA-z]+)*")) {
				System.out.println("Invalid Input");
				valid=false;
			}
		}while(valid==false);
		String[] skills=skill.split(" ");
	
		
		PreparedStatement ps;
		
		try {
			con=db.startConnection();
			ps=con.prepareStatement("select Contract_ID from published_contract where CID=?");
			
			
			ps.setInt(1, Contractor_ID);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				Contract_ID=rs.getInt(1);
				System.out.println("Contract already published");
			}
			else {
				ps=con.prepareStatement("INSERT INTO published_contract(CID,Days,Wage,Street,City,State,Postal_code) VALUES(?,?,?,?,?,?,?)");
				
				ps.setInt(1,Contractor_ID);
				ps.setInt(2,days);
				ps.setInt(3, wage);
				ps.setString(4, street);
				ps.setString(5, city);
				ps.setString(6, state);
				ps.setInt(7, Postal_Code);
				
				ps.executeUpdate();
				
				ps=con.prepareStatement("select Contract_ID from published_contract where CID=?");
				Contract_ID=0;
				
				ps.setInt(1, Contractor_ID);
				rs=ps.executeQuery();
				
				if(rs.next()) {
					Contract_ID=rs.getInt(1);
				}
				
				for(int i=0;i<skills.length;i++) {
					ps=con.prepareStatement("Insert into cskill_directory values(?,?)");
					ps.setInt(1, Contract_ID);
					ps.setString(2, skills[i]);
					ps.execute();
				}
				System.out.println("Contract Published");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection(con);
	}
	public void acceptContractRequest() {
		try {
			con=db.startConnection();
			PreparedStatement ps=con.prepareStatement("select Contract_ID from published_contract where CID=?");
			
			
			ps.setInt(1, Contractor_ID);
			ResultSet rs=ps.executeQuery();
			rs.next();
			if(rs.getInt(1)!=0) {
				Contract_ID=rs.getInt(1);
				System.out.println("LID\tStatus");
				getContractRequests(Contract_ID);
				boolean valid=false;
				int LID;
				do {
					System.out.println("Enter LID:");
					LID=sc.nextInt();
					sc.nextLine();
					valid=checkLID(Contract_ID,LID);
					if(!valid) {
						System.out.println("LID invalid re enter");
					}
				}while(valid==false);
				System.out.println("Enter 1 to Accept 2 to Reject");
				int st=sc.nextInt();
				sc.nextLine();
				if(st==1) {
					ps=con.prepareStatement("update contract_request set status=true where Contract_ID=? and LID=?");
					ps.setInt(1, Contract_ID);
					ps.setInt(2, LID);
					ps.executeUpdate();
				}
				else {
					System.out.println("contract request rejected");
				}
			}
			else {
				System.out.println("There are no Contracts Published by you!");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection(con);
	}

	public void displayHiringRecords() {
		try {
			con=db.startConnection();
//			System.out.println(Contractor_ID);
			PreparedStatement ps=con.prepareStatement("select * from hiring_record where CID=?");
			ps.setInt(1, Contractor_ID);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("Record_ID: "+rs.getInt(1)
						+ "\nLID: "+rs.getInt(2)+
						"\nCID: "+rs.getInt(3)+
						"\nDays: "+rs.getInt(4)+
						"\nWage: "+rs.getInt(5)+
						"\nAddress: "+rs.getString(6)+", "
								+ rs.getString(7)+", "
								+ rs.getString(8)+" - "+rs.getInt(9));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateDetails() {
		System.out.println(toString());
		System.out.println("Please Choose a option");
		System.out.println("1. First Name");
		System.out.println("2. Last Name");
		int choice = sc.nextInt();
		sc.nextLine();
		
		switch (choice) {
		case 1:
			System.out.println("Enter Your First Name");

			CFname=sc.nextLine().trim();

			try {
				con=db.startConnection();
				PreparedStatement ps = con.prepareStatement("Update contractor set Fname=? where Contractor_ID=?");
				ps.setString(1, CFname);
				ps.setInt(2, Contractor_ID);
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.closeConnection(con);
			break;
		case 2:
			System.out.println("Enter Your Last Name");
			CLname=sc.nextLine().trim();
			try {
				con=db.startConnection();
				PreparedStatement ps = con.prepareStatement("Update contractor set Lname=? where Contractor_ID=?");
				ps.setString(1, CLname);
				ps.setInt(2, Contractor_ID);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(con);
			break;
		}
		
	}
	public void deleteAccount() {
		System.out.println("Are you sure you want to delete your account?");
		System.out.println("1. To Confirm" + "\n" + "2. To abort");
		int choice = Integer.parseInt(sc.nextLine().trim());
		switch (choice) {
		case 1:
			con=db.startConnection();
			try {
				PreparedStatement ps = con.prepareStatement("Delete from contractor where Contractor_ID=?");
				ps.setInt(1, Contractor_ID);
				ps.execute();
				System.out.println("Account Deleted");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.closeConnection(con);
			break;
		case 2:
			break;
		}
		
	}

	@Override
	public String toString() {
		return "Contractor Details \nContractor Id=" + Contractor_ID + "\nFirst name : " + CFname + "\nLast name : " + CLname + "\nCDOB="
				+ CDOB + "\nphone_num=" + phone_num;
	}
	
	public void getContractRequests(int contract_ID) {
		try {
			con=db.startConnection();
			PreparedStatement ps=con.prepareStatement("Select LID,status from contract_request where Contract_ID=?");
			ps.setInt(1, contract_ID);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getInt(2));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkLID(int contract_ID,int LID) {
		try {
			con=db.startConnection();
			PreparedStatement ps=con.prepareStatement("select count(*) from contract_request where Contract_ID=? and LID=?");
			ps.setInt(1, contract_ID);
			ps.setInt(2, LID);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)!=0;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
