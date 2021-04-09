package lms;
import java.util.*;
public class LMS {
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		String choice;
		int c;
		boolean b=true;
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("\t\tWelcome to Labourer Management System");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		while(true) {
			do {
				System.out.println("\t1. Register as a new user\n\t2. Login\n\t3. Exit");
				choice=sc.nextLine();
				if(choice.equals("1") || choice.equals("2")) {
					b=false;
				}
				else if(choice.equals("3")) {
					System.exit(0);
				}
				else {
					System.out.println("Invalid choice");
				}
			}while(b==true);
			String uType;
			c=Integer.parseInt(choice);
			if(c==1) {
				//register
				b=true;
				do{
					System.out.println("---------------------------------------------------------------");
					System.out.println("Select user type to register :\n" + "\t1. Contractor\n" + "\t2. Labourer\n" + "\t3.Admin");
					
					uType = sc.nextLine();
					
					switch (uType) {
						case "1":
							User<Contractor> contractor = new User<>();
							contractor.register(1);
							b=false;
							break;
						case "2":
//							User<Labourer> labourer = new User<>();
//							labourer.register(2);
//							b=false;
//							break;
						case "3":
							System.out.println("Need to add classes of Labourer and Admin");
							b=false;
							break;
						default:
							System.out.println("Invalid choice");
							break;
					}
					
				}while(b==true);
				System.out.println("---------------------------------------------------------------");
			}
			else {
				//login
//				System.out.println("Login part to be implemented");
				
				b=true;
				do {
					System.out.println("---------------------------------------------------------------");
					System.out.println("Select user type to login as:\n\t1. Contractor\n\t2. Labourer\n\t3. Admin");
					uType=sc.nextLine();
					
					switch (uType) {
					case "1":
						User<Contractor> contractor=new User<>();
						Contractor cont=contractor.login(1);
						contractor=new User<>(cont);
						System.out.println(cont.toString());
						b=false;
						break;
					case "2":
//						User<Labourer> labourer = new User<>();
//						labourer.register(2);
//						b=false;
//						break;
					case "3":
						System.out.println("Need to add classes of Labourer and Admin");
						b=false;
						break;
					default:
						System.out.println("Invalid choice");
						break;
				}
				}while(b==true);
			}
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		}
	}
}
