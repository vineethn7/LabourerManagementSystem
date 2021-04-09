package lms;

public class Labourer {
	int Labour_ID;
	String LFname, LLname, LDOB;
	String phone_num;
	int userType=2;
	Labourer() {
		
	}

	

	@Override
	public String toString() {
		return "Labourer Details \nLabour Id=" + Labour_ID + "\nFirst name=" + LFname + "\nLast name=" + LLname + "\nDOB="
				+ LDOB + "\nPhone number =" + phone_num;
	}
	
}
