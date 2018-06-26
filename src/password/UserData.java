package password;

import java.io.*;
import java.util.*;



public class UserData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID =6293340134098810241L;
	private Map<String , User> userMap = new HashMap<String , User>();
	
	/**
	 * Method to add new User to the existing map
	 * @param user
	 * @throws IOException 
	 * @throws CryptoException 
	 * @throws ClassNotFoundException 
	 */
	public void addNewUser(User user) throws UserAlreadyExistException, IOException, CryptoException, ClassNotFoundException {
		if(userMap.containsKey(user.getUsername())) {
			throw new UserAlreadyExistException( user.getUsername() + " - this username already exists" , user.getUsername());
		}
		else {
			userMap.put(user.getUsername() , user);
			this.saveUserData();
		}
	}
	
	/**
	 * Method used to get the User object given the user name. It may return null if such user name does not exist.
	 * @param username
	 * @return User object
	 */
	public User getUserFromUsername(String username)  {
		return this.userMap.get(username);
	}
	
	public User getLoggedInUser(String username) throws UserNotFoundException {
		if(this.userMap.containsKey(username)) {
			return this.userMap.get(username);
		}
		else {
			throw new UserNotFoundException(username + " not found" , username);
		}
	}
	
	/**
	 * Get all users
	 * @return collection of users
	 */
	public Collection<User> getAllUsers(){
		return this.userMap.values();
	}
	
	/**
	 * Load Data of Users from file
	 * @return returns the retrived user data
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws CryptoException 
	 */
	public static UserData loadUserData() throws IOException, ClassNotFoundException, CryptoException {
		File file = new File("data\\UserData.pm");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		if(!file.exists()) {
			file.createNewFile();
		}
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			UserDataEncryptionDecryption uded = new UserDataEncryptionDecryption();
			
			UserData userData = (UserData)uded.decrypt(ois.readObject());
			
			return  userData;
		}
		catch(EOFException e) {
			return new UserData();
		}
		finally {
			if(fis != null) {
				fis.close();
			}
			if(ois != null) {
				ois.close();
			}
		}
		
	}
	
	/**
	 * Save user data to file
	 * @throws IOException
	 * @throws CryptoException 
	 * @throws ClassNotFoundException 
	 */
	public void saveUserData() throws IOException, CryptoException, ClassNotFoundException{
		File file = new File("data\\UserData.pm");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		try {
			
			UserDataEncryptionDecryption uded  = new UserDataEncryptionDecryption();
			
			UserData userData = (UserData)uded.encrypt(this);
			
			oos.writeObject(userData);
		}
		finally {
			fos.close();
			oos.close();
		}
	}
	
}
