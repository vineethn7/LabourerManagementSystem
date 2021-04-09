package lms;

public class Contractor {
	int Contractor_Id;
	String CFname, CLname, CDOB;
	String phone_num;
	int userType=1;
	Contractor() {
		
	}


	public Contractor(int contractor_Id, String cFname, String cLname, String cDOB,String phone_num) {
		super();
		Contractor_Id = contractor_Id;
		CFname = cFname;
		CLname = cLname;
		CDOB = cDOB;
		this.phone_num=phone_num;
	}

	public int getContractor_Id() {
		return Contractor_Id;
	}

	public void setContractor_Id(int contractor_Id) {
		Contractor_Id = contractor_Id;
	}

	public String getCFname() {
		return CFname;
	}

	public void setCFname(String cFname) {
		CFname = cFname;
	}

	public String getCLname() {
		return CLname;
	}

	public void setCLname(String cLname) {
		CLname = cLname;
	}

	public String getCDOB() {
		return CDOB;
	}

	public void setCDOB(String cDOB) {
		CDOB = cDOB;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	@Override
	public String toString() {
		return "Contractor Details \nContractor Id=" + Contractor_Id + "\nFirst name : " + CFname + "\nLast name : " + CLname + "\nCDOB="
				+ CDOB + "\nphone_num=" + phone_num;
	}
	
}
