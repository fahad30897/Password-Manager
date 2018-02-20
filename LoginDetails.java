package password;

import java.io.Serializable;

public class LoginDetails implements Serializable {

	private String loginWebsiteName , loginUsername , loginPassword , loginURL;
	/**
	 * Empty constructor to initialize null object
	 */
	public LoginDetails() {}
	
	/**
	 * Parameterized Constructor to initialize Login Details Object with parameters
	 * @param webName - Name of the website for which login details are provided
	 * @param username - User name on that website
	 * @param pass - Password to login to the website
	 * @param URL - URL to navigate to the Login page of the website
	 */
	public LoginDetails(String webName , String username , String pass ,String URL) {
		this.setLoginWebsiteName(webName);
		this.setLoginUsername(username);
		this.setLoginPassword(pass);
		this.setLoginURL(URL);
	}
	
	/**
	 * Method to get the website name for which Login details are provided
	 * @return
	 */
	public String getLoginWebsiteName() {
		return this.loginWebsiteName;
	}
	
	/**
	 * Method to set the name of the website
	 * @param webName
	 */
	public void setLoginWebsiteName(String webName) {
		this.loginWebsiteName = webName;
	}
	
	/**
	 * Method to get the Login User name for the website for which Login details are provided
	 * @return
	 */
	public String getLoginUsername() {
		return this.loginUsername;
	}
	
	/**
	 * Method to set the User name of the user on the corresponding website
	 * @param username
	 */
	public void setLoginUsername(String username) {
		this.loginUsername = username;
	}
	
	/**
	 * Method to get the Login Password for the website for which Login details are provided
	 * @return
	 */
	public String getLoginPassword() {
		return this.loginPassword;
	}
	
	/**
	 * Method to set the password of the user on the corresponding website
	 * @param pass
	 */
	public void setLoginPassword(String pass) {
		this.loginPassword = pass;
	}
	
	/**
	 * Method to get the URL for the Login page of the website 
	 * @return
	 */
	public String getLoginURL() {
		return this.loginURL;
	}
	
	/**
	 * Method to set the URL of the Login page on the corresponding website
	 * @param URL
	 */
	public void setLoginURL(String URL) {
		this.loginURL = URL;
	}
	
}
