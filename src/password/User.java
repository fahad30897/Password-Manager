package password;

import java.io.*;

import javax.crypto.*;

/**
 * A class containing details about the user.
 */
public class User implements Serializable{
	
	//private static final long serialVersionUID = 1L;
	
	private String  username, password, privateKey, loginDetailsFileName;
	transient private UserLoginDetailsData userLoginDetailsData;
	/**
	 * Default Constructor to initialize empty object 
	 */
	public User() {
		this(null, null, null);
	}
	
	public User(String u , String pass) {
		this(u , pass , pass);
	}
	/**
	 * Parameterized Constructor to initialize User object
	 * @param u - user name of  the user
	 * @param pass - password of the user
	 * @param pk - private key of the user 
	 */
	public User(String u , String pass ,String pk) {
		this.setUsername(u);
		this.setPassword(pass);
		this.setPrivateKey(pk);
		this.loginDetailsFileName = u + "LoginDetails";
		this.userLoginDetailsData = new UserLoginDetailsData();
	}
	
	/**
	 * Parameterized Constructor to provide filename alongwith other details
	 * @param u - user name of  the user
	 * @param pass - password of the user
	 * @param pk - private key of the user 
	 * @param filename - Name of file in which login Details of this user will be stored
	 */
	public User(String u, String pass, String pk, String filename) {
		this(u , pass, pk);
		this.loginDetailsFileName = filename;
	}
	
	/**
	 * Method to return the user name of the User
	 * @return String user name
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * Method to set the user name of the User
	 * @param u - user name of the user
	 */
	public void setUsername(String u) {
		this.username = u;
	}
	/**
	 * Method to return the password of the User
	 * @return String password
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Method to set the password of the User
	 * @param pass - password of the user
	 */
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	/**
	 * Method to return the Private Key of the User
	 * @return String private key
	 */
	public String getPrivateKey() {
		return this.privateKey;
	}
	
	/**
	 * Method to set the private key of the User
	 * @param pk - private key of the user
	 */
	public void setPrivateKey(String pk) {
		this.privateKey = pk;
	}
	
	/**
	 * Method to return the filename in which user login details are stored
	 * @return String file name
	 */
	public String getLoginDetailsFileName () {
		return this.loginDetailsFileName ;
	}
	
	/**
	 * Method to set the filename in which user login details are stored
	 * @param file  - file name for the user
	 */
	public void setLoginDetailsFileName(String file) {
		this.loginDetailsFileName  = file;
	}
	
	/**
	 * Display data of user
	 */
	public void DisplayData() {
		System.out.println("Username: " + this.username+"\n Password: "+this.password+"\nPrivate Key: "+ this.privateKey);
	}
	
	public UserLoginDetailsData getUserLoginDetailsData() {
		return this.userLoginDetailsData;
	}
	
	public UserLoginDetailsData loadUserLoginDetailsData() throws ClassNotFoundException, IOException, CryptoException {
		
		this.userLoginDetailsData = UserLoginDetailsData.loadUserLoginDetails(this.username, this.password, this.loginDetailsFileName);
		
		return this.userLoginDetailsData;
	}
	
	public void saveUserLoginDetailsData() throws IOException, CryptoException {
		this.userLoginDetailsData.saveUserLoginDetails(this.username, this.password, this.loginDetailsFileName);
	}
	
	public void setUserLoginDetailsData (UserLoginDetailsData uldd) {
		this.userLoginDetailsData = uldd;
	}
	
	public boolean addNewLoginDetails(LoginDetails ld) throws IOException, CryptoException, ClassNotFoundException {
		boolean returnValue = this.userLoginDetailsData.addNewLoginDetails(ld);
//		this.userLoginDetailsData = UserLoginDetailsData.loadUserLoginDetails(this.username, this.password, this.loginDetailsFileName);
		return returnValue;
	}
	
	public boolean editLoginDetials(LoginDetails ld) throws IOException, CryptoException, ClassNotFoundException{
		boolean rvalue = this.userLoginDetailsData.editLoginDetails(ld);
//		this.userLoginDetailsData = UserLoginDetailsData.loadUserLoginDetails(this.username, this.password, this.loginDetailsFileName);
		return rvalue;
	}
	
	public void deleteLoginDetails(LoginDetails ld) throws IOException, CryptoException, ClassNotFoundException {
		this.userLoginDetailsData.deleteLoginDetails(ld);
//		this.userLoginDetailsData = UserLoginDetailsData.loadUserLoginDetails(this.username, this.password, this.loginDetailsFileName);
		
	}
	
	
	public void takeBackup(String filename) throws IOException, CryptoException, ClassNotFoundException {
		this.userLoginDetailsData.takeBackup(this.username, this.password, filename);
//		this.userLoginDetailsData = this.userLoginDetailsData.loadUserLoginDetails(this.username, this.password, this.loginDetailsFileName);
	}

	public void loadFromBackup(String filename) throws IOException, CryptoException, ClassNotFoundException {
		this.userLoginDetailsData = this.userLoginDetailsData.loadFromBackup(this.username, this.password,filename);
	}
	
	/**
	 * Method of the object class to check equality of the object
	 * @return boolean equal or not
	 */
	@Override
	public boolean equals(Object u) {
		if(u instanceof User) {
			if(((User)u).getUsername().equals(this.username) && ((User)u).getPassword().equals(this.password) && ((User)u).getPrivateKey().equals(this.privateKey)) {
				return true;
			}
			return false;
		}
		return false;
	}
	
}
