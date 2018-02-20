package password;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class UserData implements Serializable {
	
	private Map<String , User> userMap = new HashMap<String , User>();
	
	/**
	 * Method to add new User to the existing map
	 * @param user
	 */
	public void addNewUser(User user) throws UserAlreadyExistException {
		if(userMap.containsKey(user.getUsername())) {
			throw new UserAlreadyExistException( user.getUsername() + " - this username already exists" , user.getUsername());
		}
		else {
			userMap.put(user.getUsername() , user);
		}
	}
	
	/**
	 * Method used to get the User object given the user name. It may return null if such user name does not exist.
	 * @param username
	 * @return User object
	 */
	public User getUserFromUsername(String username) {
		return this.userMap.get(username);
	}
	
	public void loadUserData() {
		
	}
	
	public void saveUserData() {
		
	}
	
}
