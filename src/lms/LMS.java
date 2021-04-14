package lms;
import java.util.*;
import java.sql.*;
public class LMS {
	static Scanner sc = new Scanner(System.in);
	static Validators v=new Validators();
	static DBConnection db=new DBConnection(); 
	static Connection con=null;
	static void Register(String Phone_num, int userType) {
	
		if (userType == 1) {
			String FName,LName,DOB;
			int year,Month=1,Date=1;
			boolean flag = false;
			do {
				System.out.println("Enter First Name");
				FName = sc.nextLine().trim();
				flag=v.validString(FName);
				if(!flag) {
					System.out.println("Name Not Supported. Please Enter Name in Correct Format. FirstName Without Spaces");
				}
				if(FName.length()>25) {
					System.out.println("Name Too Long");
					flag = false;
				}
			} while (flag==false);
			flag=false;
			do {
				System.out.println("Enter Last Name");
				LName = sc.nextLine().trim();
				flag=v.validString(LName);
				if(!flag) {
					System.out.println("Name Not Supported. Please Enter Name in Correct Format. LastName Without Spaces");
				}
				if(LName.length()>25) {
					System.out.println("Name Too Long");
					flag = false;
				}
			} while(flag==false);
			flag = false;
			do {
				System.out.println("Enter DOB :");
				System.out.println("Enter Year");
				year = Integer.parseInt(sc.nextLine().trim());
				int y = Calendar.getInstance().get(Calendar.YEAR);
				if (y - year <= 18 || y - year >= 60) {
					System.out.println("Bache Budhe Dur Rahe");
					flag = false;
				} else {
					System.out.println("Enter Month");
					 Month = Integer.parseInt(sc.nextLine().trim());
					if (Month > 12 || Month < 0) {
						System.out.println("You don't belong to this planet");
						flag = false;
					} else {
						System.out.println("Enter Date");
						Date = Integer.parseInt(sc.nextLine().trim());
						if (Month == 1 || Month == 3 || Month == 5 || Month == 7 || Month == 8 || Month == 10) {
							if (Date < 0 || Date > 31) {
								System.out.println("You don't belong to this planet");
								flag = false;
							}
							else {
								flag=true;
							}
						} else if (Month == 2) {
							if (Date < 0 || Date > 28) {
								System.out.println("You don't belong to this planet");
								flag = false;
							}
							else {
								flag=true;
							}
						} else {
							if (Date < 0 || Date > 30) {
								System.out.println("You don't belong to this planet");
								flag = false;
							}
							else {
								flag=true;
							}
						}

					}
				}
			} while (flag==false);
			con=db.startConnection();
			try {
				PreparedStatement ps = con.prepareStatement("SELECT max(Contractor_ID) FROM contractor");
				ResultSet r=ps.executeQuery();
				r.next();
				int maxx=r.getInt(1);
				maxx++;
				ps=con.prepareStatement("Insert into contractor values(?,?,?,?,?)");
				DOB=year+"-";
				DOB+=Month+"-";
				DOB+=Date+"";
				ps.setInt(1, maxx);
				ps.setString(2, FName);
				ps.setString(3, LName);
				ps.setString(4, DOB);
				ps.setInt(5, 0);
				ps.execute();
				ps=con.prepareStatement("Insert into cphone_directory values(?,?)");
				ps.setInt(1, maxx);
				ps.setString(2, Phone_num);
				ps.execute();
				
				System.out.println("Registered Succesfully. Please Login Again");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (userType == 2) {
			String FName,LName,Gender,Street,City,State,Skills,DOB;
			int year,Postal_Code;
			int Month=1;
			int Date=1;
			boolean flag = false;
			do {
				System.out.println("Enter First Name");
				FName = sc.nextLine().trim();
				flag=v.validString(FName);
				if(!flag) {
					System.out.println("Name Not Supported. Please Enter Name in Correct Format. FirstName Without Spaces");
				}
				if(FName.length()>25) {
					System.out.println("Name Too Long");
					flag = false;
				}
			} while (flag==false);
			flag=false;
			do {
				System.out.println("Enter Last Name");
				LName = sc.nextLine().trim();
				flag=v.validString(LName);
				if(!flag) {
					System.out.println("Name Not Supported. Please Enter Name in Correct Format. LastName Without Spaces");
				}
				if(LName.length()>25) {
					System.out.println("Name Too Long");
					flag = false;
				}
			} while(flag==false);
			flag = false;
			do {
				System.out.println("Enter Gender :");
				Gender = sc.nextLine().trim();
				if ((Gender.equalsIgnoreCase("Male")) || (Gender.equalsIgnoreCase("Female"))
						|| (Gender.equalsIgnoreCase("Others"))) {
					flag=true;
				} else {
					System.out.println("Invalid Input");
					flag = false;
				}
			} while (flag==false);
			flag = false;
			do {
				System.out.println("Enter DOB :");
				System.out.println("Enter Year");
				year = Integer.parseInt(sc.nextLine().trim());
				int y = Calendar.getInstance().get(Calendar.YEAR);
				if (y - year <= 18 || y - year >= 60) {
					System.out.println("Bache Budhe Dur Rahe");
					flag = false;
				} else {
					System.out.println("Enter Month");
					 Month = Integer.parseInt(sc.nextLine().trim());
					if (Month > 12 || Month < 0) {
						System.out.println("You don't belong to this planet");
						flag = false;
					} else {
						System.out.println("Enter Date");
						Date = Integer.parseInt(sc.nextLine().trim());
						if (Month == 1 || Month == 3 || Month == 5 || Month == 7 || Month == 8 || Month == 10) {
							if (Date < 0 || Date > 31) {
								System.out.println("You don't belong to this planet");
								flag = false;
							}
							else {
								flag=true;
							}
						} else if (Month == 2) {
							if (Date < 0 || Date > 28) {
								System.out.println("You don't belong to this planet");
								flag = false;
							}
							else {
								flag=true;
							}
						} else {
							if (Date < 0 || Date > 30) {
								System.out.println("You don't belong to this planet");
								flag = false;
							}
							else {
								flag=true;
							}
						}

					}
				}
			} while (flag==false);
			flag=false;
			do {
				System.out.println("Enter Street :");
				Street = sc.nextLine().trim();
				flag=true;
				if (!Street.matches("[0-9\\sa-zA-Z]+")) {
					System.out.println("error aagya");
					flag = false;
				}
				if(Street.length()>100) {
					System.out.println("Address Too Long");
					flag=false;;
				}
			} while (flag==false);
			do {
				
				System.out.println("Enter City :");
				City = sc.nextLine().trim();
				flag=v.validString(City);
				if(!flag) {
					System.out.println("Invalid input ");
				}
				if(City.length()>25) {
					System.out.println("City Name Too Long");
					flag=false;
				}
			
			}while(!flag);
			do {
				
				System.out.println("Enter State :");
				State = sc.nextLine().trim();
				flag=v.validString(State);
				if(!flag) {
					System.out.println("Invalid input ");
				}
				
				if(State.length()>25) {
					System.out.println("State Name Too Long");
					flag=false;
				}
			
			}while(!flag);
			
			do {
				flag=false;
				System.out.println("Enter Postal Code :");
				Postal_Code = Integer.parseInt(sc.nextLine().trim());
				if(Postal_Code<100000||Postal_Code>999999) {
					System.out.println("Invalid Postal Code:");
					flag=true;
				}
			}while(flag);
			do {
				flag=false;
	
				System.out.println("Enter Skills Separated by Space");
				Skills = sc.nextLine();
				if(!Skills.matches("[a-zA-z]+(\\s[a-zA-z]+)*")) {
					System.out.println("Invalid Input");
					flag=true;
				}
			}
			while(flag);
			
			con=db.startConnection();
			try {
				PreparedStatement ps = con.prepareStatement("SELECT max(Labour_ID) FROM labourer");
				
				ResultSet r=ps.executeQuery();
				r.next();
				int maxx=r.getInt(1);
				maxx++;
				ps=con.prepareStatement("Insert into labourer values(?,?,?,?,?,?,?,?,?,?,?)");
				DOB=year+"-";
				DOB+=Month+"-";
				DOB+=Date+"";
				ps.setInt(1, maxx);
				ps.setString(2, FName);
				ps.setString(3, LName);
				ps.setString(4, Gender);
				ps.setString(5, DOB);
				ps.setString(6, Street);
				ps.setString(7, City);
				ps.setString(8, State);
				
				ps.setInt(9, Postal_Code);
				ps.setString(10, "No");
				ps.setInt(11, 0);
				ps.execute();
				ps=con.prepareStatement("Insert into lphone_directory values(?,?)");
				ps.setInt(1, maxx);
				ps.setString(2, Phone_num);
				ps.execute();
				String[] skill=Skills.split(" ");
				for(int i=0;i<skill.length;i++) {
					ps=con.prepareStatement("Insert into skill_directory values(?,?)");
					ps.setInt(1, maxx);
					ps.setString(2, skill[i]);
					ps.execute();
				}
				System.out.println("Registered Succesfully. Please Login Again");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Invalid UserType");
		}

	}

	public static void main(String[] args) {
		
		System.out.println("Welcome to Labourer Management System");
		int choice;
		do {
			System.out.println("\t1. Register as a new user\n\t2. Login\n\t3. Exit");
			choice = Integer.parseInt(sc.nextLine().trim());
			switch (choice) {
			case 1:
				System.out.println("Registration");
				System.out.println("Select user type to Register as:\n\t1. Contractor\n\t2. Labourer");
				int Rchoice=Integer.parseInt(sc.nextLine().trim());
				String Phone_num;
				switch(Rchoice) {
				case 1: 
					System.out.println("Enter Your Phone Number");
					Phone_num=sc.nextLine();
					
					con=db.startConnection();
					try {
						PreparedStatement ps=con.prepareStatement("call CheckRegisteredContractor(?, @res)");
						ps.setString(1, Phone_num);
						ps.execute();
						ps=con.prepareStatement("select @res");
						ResultSet rs=ps.executeQuery();
						rs.next();
						if(rs.getInt(1)==0) {
							Register(Phone_num, Rchoice);
						}
						else {
							System.out.println("You are a Registered User. Please Login IN");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
					
				case 2:
					System.out.println("Enter Your Phone Number");
					Phone_num=sc.nextLine();
					
					con=db.startConnection();
					try {
						PreparedStatement ps=con.prepareStatement("call CheckRegisteredLabourer(?, @res)");
						ps.setString(1, Phone_num);
						ps.execute();
						ps=con.prepareStatement("select @res");
						ResultSet rs=ps.executeQuery();
						rs.next();
						if(rs.getInt(1)==0) {
							Register(Phone_num, Rchoice);
						}
						else {
							System.out.println("You are a Registered User. Please Login IN");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				default:
					System.out.println("Invalid Input");
				}
				break;
			case 2:
				System.out.println("Select user type to login as:\n\t1. Contractor\n\t2. Labourer");
				int LChoice = Integer.parseInt(sc.nextLine().trim());
				switch (LChoice) {
				case 1:
					con=db.startConnection();
					System.out.println("Enter the Phone number of Contractor");
					
					Contractor cont = new Contractor();
					Phone_num = sc.nextLine();
					PreparedStatement ps;
					try {
						ps = con.prepareStatement("select c.*, Phone_no from contractor c join cphone_directory pd on c.Contractor_ID=pd.CID where Phone_no=?");
						ps.setString(1, Phone_num);
						ResultSet rs = ps.executeQuery();
						rs.next();
						if (rs.getInt(1) == 0) {
							System.out.println("Not a Registered User");
							System.exit(0);
						} else {
							cont = new Contractor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(6));
						}
						db.closeConnection(con);
						cont.CMenu();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					labourer.register(2);
					System.out.println("Entered");
					break;
				case 2:
					con=db.startConnection();
					System.out.println("Enter the Phone number of Labourer");
					
					Labourer l = new Labourer();
					Phone_num = sc.nextLine();
					
					try {
						ps = con.prepareStatement("select l.*, group_concat(distinct(skills)),group_concat(distinct(phone_no)) from labourer l join skill_directory sd on l.labour_id = sd.lid join lphone_directory pd on pd.lid=sd.lid where phone_no=?");
						ps.setString(1, Phone_num);
						ResultSet rs = ps.executeQuery();
						rs.next();
						if (rs.getInt(1) == 0) {
							System.out.println("Not a Registered User");
							System.exit(0);
						} else {
							l = new Labourer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
									rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9),
									rs.getString(10), rs.getString(12), rs.getString(13));
						}
						db.closeConnection(con);
						l.LMenu();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					labourer.register(2);
					System.out.println("Entered");
					break;
				default:
					System.out.println("Invalid Choice");
					break;
				}
				break;
			case 3:
				System.exit(0);
				break;
			}
		} while (choice != 3);
	}
}
