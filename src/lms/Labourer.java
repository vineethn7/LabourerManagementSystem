package lms;

import java.util.*;
import java.sql.*;

public class Labourer {
	int Labour_ID;
	String LFname, LLname, LDOB;
	String phone_num;
	String Street;
	String Gender;
	String City;
	String State;
	int Postal_Code;
	String Hiring_Status;
	String Skills;
	int userType = 2;
	Scanner sc = new Scanner(System.in);
	DBConnection db = new DBConnection();
	Connection con=null;
	Labourer() {

	}

	public Labourer(int labour_ID, String lFname, String lLname, String gender, String lDOB, String street, String city,
			String state, int postal_Code, String hiring_Status, String skills, String phone_num) {
		super();
		Labour_ID = labour_ID;
		LFname = lFname;
		LLname = lLname;
		Gender = gender;
		LDOB = lDOB;
		Street = street;
		City = city;
		State = state;
		Postal_Code = postal_Code;
		Hiring_Status = hiring_Status;
		Skills = skills;
		this.phone_num = phone_num;

	}

	public void LMenu() {
		boolean flag = true;
		do {
			System.out.println("Welcome, "+LFname+" "+LLname+" \n\n1. Personal information \n"
					+ "2. Update details \n3. Find published contract \n" + "4. Logout\n" + "5. Delete account");
			
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1:
				System.out.println(toString());
				break;
			case 2:
				updateDetails();
				break;
			case 3:
				findPublishedContract();
				break;
			case 4:
				System.out.println("You have successfully logged out");
				flag = false;
				break;
			case 5:

				deleteAccount();
				break;
			}

		} while (flag);
	}

	public void deleteAccount() {
		System.out.println("Are you sure you want to delete your account?");
		System.out.println("1. To Confirm" + "\n" + "2. To abort");
		int choice = Integer.parseInt(sc.nextLine().trim());
		
		switch (choice) {
		case 1:
			con=db.startConnection();
			try {
				PreparedStatement ps = con.prepareStatement("Delete from labourer where Labour_ID=?");
				ps.setInt(1, Labour_ID);
				ps.execute();
				System.out.println("Account Deleted");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(con);
			break;
		case 2:
			break;
		}

	}

	public void updateDetails() {
		System.out.println(toString());
		System.out.println("Please Choose a option");
		System.out.println("1. First Name");
		System.out.println("2. Last Name");
		System.out.println("3. Gender");
		System.out.println("4. Street");
		System.out.println("5. City");
		System.out.println("6. State");
		System.out.println("7. Postal Code");
		System.out.println("8. Skills");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			System.out.println("Enter Your First Name");

			LFname=sc.nextLine().trim();

			try {
				con=db.startConnection();
				PreparedStatement ps = con.prepareStatement("Update Labourer set Fname=? where Labour_ID=?");
				ps.setString(1, LFname);
				ps.setInt(2, Labour_ID);
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.closeConnection(con);
			break;
		case 2:
			System.out.println("Enter Your Last Name");
			LLname=sc.nextLine().trim();
			try {
				con=db.startConnection();
				PreparedStatement ps = con.prepareStatement("Update labourer set Lname=? where Labour_ID=?");
				ps.setString(1, LLname);
				ps.setInt(2, Labour_ID);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(con);

			break;
		case 3:
			System.out.println("Choose Your Gender:");
			System.out.println("Enter 1. For Male");
			System.out.println("Enter 2. For Female");
			System.out.println("Enter 3. For Others");

			int Gchoice = sc.nextInt();
			con=db.startConnection();
			
			try {
				PreparedStatement ps = con.prepareStatement("Update labourer set Gender=? where Labour_ID=?");
				if (Gchoice == 1) {
					ps.setString(1, "Male");
					ps.setInt(2, Labour_ID);
					Gender = "Male";
				}
				if (Gchoice == 2) {
					ps.setString(1, "Female");
					ps.setInt(2, Labour_ID);
					Gender = "Female";
				}
				if (Gchoice == 3) {
					ps.setString(1, "Others");
					ps.setInt(2, Labour_ID);
				Gender = "Others";
			}
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			db.closeConnection(con);

			break;
		case 4:
			System.out.println("Enter Your Street");
			Street=sc.nextLine().trim();
			con=db.startConnection();
			try {
				PreparedStatement ps = con.prepareStatement("Update labourer set Street=? where Labour_ID=?");
				ps.setString(1, Street);
				ps.setInt(2, Labour_ID);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(con);

			break;
		case 5:
			System.out.println("Enter Your City");
			City=sc.nextLine().trim();
			con=db.startConnection();
			try {
				PreparedStatement ps = con.prepareStatement("Update labourer set City=? where Labour_ID=?");
				ps.setString(1, City);
				ps.setInt(2, Labour_ID);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(con);

			break;
		case 6:
			System.out.println("Enter Your State");
			State=sc.nextLine().trim();
			con=db.startConnection();
			try {
				PreparedStatement ps = con.prepareStatement("Update labourer set State=? where Labour_ID=?");
				ps.setString(1, State);
				ps.setInt(2, Labour_ID);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(con);

			break;
		case 7:
			System.out.println("Enter Your Postal Code");
			Postal_Code=Integer.parseInt(sc.nextLine().trim());
			con=db.startConnection();
			try {
				PreparedStatement ps = con.prepareStatement("Update labourer set Postal_Code=? where Labour_ID=?");
				ps.setInt(1, Postal_Code);
				ps.setInt(2, Labour_ID);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(con);

			break;
		case 8:
			StringBuilder ss = new StringBuilder();
			try {
				con=db.startConnection();
				PreparedStatement ps = con.prepareStatement("Delete from skill_directory where LID=?");
				ps.setInt(1, Labour_ID);
				ps.execute();
				boolean flag = true;
				do {
					System.out.println("Add a Skill:");
					String s = sc.nextLine().trim();
					ss.append(s);
					ps = con.prepareStatement("Insert into skill_directory values (?,?)");
					ps.setInt(1, Labour_ID);
					ps.setString(2, s);
					ps.execute();
					System.out.println("Do you want to add more skills:");
					String c = sc.nextLine();
					if (c.equalsIgnoreCase("yes")) {
						ss.append(',');
						continue;
					} else {
						Skills = ss.toString();
						flag = false;
					}
				} while (flag);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closeConnection(con);

			break;
		}

	}

	public void findPublishedContract() {
		
		con=db.startConnection();
		try {
			PreparedStatement ps = con.prepareStatement("call Find_Contracts(?)");
			ps.setInt(1, Labour_ID);
			ResultSet rs = ps.executeQuery();
			System.out.println("Contract_ID" + "\t" + "Days" + "\t" + "Wage" + "\t" + "Adress" + "\t" + "City"
					+ "\t" + "Skills");
			
			while(rs.next()) {
					String Contract_ID = rs.getString(1);
					int Days = rs.getInt(3);
					int wage = rs.getInt(4);
					String Address = rs.getString(5);
					String City = rs.getString(6) + "," + rs.getString(8);
					String skills = rs.getString(9);
					System.out.println("-----------------------------------------------------------------------------------------------");
					System.out.println(Contract_ID + "\t" + Days + "\t" + wage + "\t" + Address + "\t" + City + "\t" + skills);
			}
						
				System.out.println("Do you want to apply for a contract:");
				System.out.println("Enter Yes or No:");
				String choice = sc.nextLine();
				switch (choice) {
				case "yes":
					System.out.println("Enter the Contract_ID to apply for a contract:");
					int cid = Integer.parseInt(sc.nextLine().trim());
					ps = con.prepareStatement("Insert into contract_request values(?,?,?)");
					ps.setInt(1, cid);
					ps.setInt(2, Labour_ID);
					ps.setInt(3, 0);
					ps.execute();
					System.out.println("Successfully applied to the contract");
					break;
				case "no":
					// Menu()
					System.out.println("Go To HEll");
					break;
				default:
					System.out.println("You're Gemini and you're indecisive");break;
				}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No contracts found");
			
		}
		db.closeConnection(con);
	}

//22204 SC VSKP
	@Override
	public String toString() {
		return "Labourer Details \nLabour Id=\t" + Labour_ID + "\nFirst name=\t" + LFname + "\nLast name=\t" + LLname +"\nGender=\t" + Gender+ "\nDate Of Birth=\t"
				+ LDOB + "\nPhone number =" + phone_num +"\nAddress=\t" + Street + "," + City + "," + State + "," + Postal_Code
				+"\nSkills=\t" + Skills;
	}
}
