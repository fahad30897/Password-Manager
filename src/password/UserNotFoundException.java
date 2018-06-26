package password;

public class UserNotFoundException extends Exception {
	
	private String username;
	
	public UserNotFoundException(String msg, String username) {
		super(msg);
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
}
