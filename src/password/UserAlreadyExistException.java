package password;

public class UserAlreadyExistException extends Exception {
	
	private String username;
	
	public UserAlreadyExistException(String msg, String username) {
		super(msg);
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
}
