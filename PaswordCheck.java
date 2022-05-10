import java.util.Scanner;

public class PaswordCheck {
	static Scanner x = new Scanner(System.in);

	

	public boolean Password(String password) {
		if (password.length() >= 8) {
			for (int i = 0; i < password.length(); i++) {
				if (Character.isUpperCase(password.charAt(i))) {
					
					return this.PassNextStep(password);
					
				}
			}
		} else
			return false;
		return false;
	}

	protected boolean PassNextStep(String password) {
		int counter=0;
		for (int i = 0; i < password.length(); i++) {
			if(counter==2)return this.PassNextStep2(password);
			else if (Character.isDigit(password.charAt(i))) {
				counter++;
			}
		}
		return false;
		
	}
	protected boolean PassNextStep2(String password) {
		int counter=0;
		for(int i=0;i<password.length();i++) {
			if(counter==2)return this.PassNextStep3(password);
			else if(Character.isLetter(password.charAt(i))) {
				counter++; 
			}
		}
		return false;
		
		
	}
	protected boolean PassNextStep3(String password) {
		for(int i=0;i<password.length();i++) {
			if(Character.isLowerCase(password.charAt(i))) {
				return this.PassNextStep4(password);
			}
		}
		return false;
	}
	protected boolean PassNextStep4(String password) {
		for(Character c:password.toCharArray()) {
			
			if(!(c>='a'&&c<'z')||(c=='?')||(c=='_'))return true;
		}
		return false;
	}
}
