package password;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JPanel;

public class Utility {

	public static String generatePassword(int l , String generateString) {
		int i = 0;
		String[] generateArray = generateString.split(",");
		System.out.println(generateString+ " " + generateArray);
		
		StringBuilder sb = new StringBuilder(l);
		SecureRandom rnd = new SecureRandom();
		for(String s:generateArray) {
			if(i < l) {
				sb.append(s.charAt(rnd.nextInt(s.length())));
				i++;
			}
		}
		for(int j = 0; j<l-generateArray.length; j++) {
			int randIndex = rnd.nextInt(generateArray.length);
			
			sb.append(generateArray[randIndex].charAt(rnd.nextInt(generateArray[randIndex].length())));
			
		}
		return new String(sb);
	}
	
	public static PasswordStrength getPasswordStrength(String password) {
		
		if(password != null && !password.trim().isEmpty()) {
			if(password.length() < 8) {
				return PasswordStrength.Weak;
			}
			else {
				if(password.length() < 15) {
					
					if(checkString(password) && password.length() > 10) {
						return PasswordStrength.Strong;
					}
					else {
						return PasswordStrength.Average;
					}
					
				}
				else {
					if(checkString(password)) {
						return PasswordStrength.VeryStrong;
					}
					else {
						return PasswordStrength.Strong;
					}
					
				}
			}
		}
		else {
			return PasswordStrength.Weak;
		}
		
	}
	
	private static boolean checkString(String str) {
	    char ch;
	    boolean capitalFlag = false;
	    boolean lowerCaseFlag = false;
	    boolean numberFlag = false;
	    for(int i=0;i < str.length();i++) {
	        ch = str.charAt(i);
	        if( Character.isDigit(ch)) {
	            numberFlag = true;
	        }
	        else if (Character.isUpperCase(ch)) {
	            capitalFlag = true;
	        } else if (Character.isLowerCase(ch)) {
	            lowerCaseFlag = true;
	        }
	        
	    }
	    Pattern p = Pattern.compile("[^A-Za-z0-9]");
	     Matcher m = p.matcher(str);
	    // boolean b = m.matches();
	     boolean b = m.find();
	     if(numberFlag && capitalFlag && lowerCaseFlag && b)
	            return true;
	    return false;
	}
	
	public static void addComp(JPanel thePanel, Component comp, int xPos, int yPos, int compWidth, int compHeight, int place,
			int stretch) {

		GridBagConstraints gridConstraints = new GridBagConstraints();

		gridConstraints.gridx = xPos;

		gridConstraints.gridy = yPos;

		gridConstraints.gridwidth = compWidth;

		gridConstraints.gridheight = compHeight;

		gridConstraints.weightx = 100;

		gridConstraints.weighty = 100;

		gridConstraints.insets = new Insets(5, 5, 5, 5);

		gridConstraints.anchor = place;

		gridConstraints.fill = stretch;

		thePanel.add(comp, gridConstraints);

	}
}
