package password;

import java.awt.Color;

public enum PasswordStrength {

	Weak("Weak" , Color.RED),
	Average("Average" , Color.BLUE),
	Strong("Strong" , Color.GREEN),
	VeryStrong("Very Strong", Color.GREEN),
	;
	
	private String displayString;
	private Color color;
	private PasswordStrength(String displayString, Color color) {
		this.displayString = displayString;
		this.color= color;
	}
	
	public String getDisplayString() {
		return this.displayString;
	}
	
	public Color getColor() {
		return this.color;
	}
	
}
