package lms;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validators {
	
	public boolean validString(String s) {
		if (s.matches("[a-zA-Z]+(\\s[a-zA-Z]*){0,2}")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean validDate(String s) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setLenient(false);
		try {
			df.parse(s);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	public boolean validatePhone(String s) {
		if (s.matches("\\d{10}")) {
			return true;
		} else {
			return false;
		}
	}
}
