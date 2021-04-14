package lms;

public class Validators {
	
	public boolean validString(String s) {
		if (s.matches("[a-zA-Z]+")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validatePhone(String s) {
		if (s.matches("\\d{10}")) {
			return true;
		} else {
			return false;
		}
	}
}
