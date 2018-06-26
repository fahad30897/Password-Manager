package password;

import java.io.Serializable;

public class LoginDetails implements Serializable {
	private int index;
	private String loginWebsiteName , loginUsername , loginPassword , loginURL, comments;
	/**
	 * Empty constructor to initialize null object
	 */
	public LoginDetails() {
		this(0 ,null, null, null, null, null);
	}
	
	
	public LoginDetails(int index ,String webName ,String username, String password) {
		this(index, webName, username, password, "", "");
	}
	
	public LoginDetails(String webName ,String username, String password , String URL) {
		this( 0 ,webName, username, password, URL, "");
	}
	
	/**
	 * Parameterized Constructor to initialize Login Details Object with parameters
	 * @param webName - Name of the website for which login details are provided
	 * @param username - User name on that website
	 * @param pass - Password to login to the website
	 * @param URL - URL to navigate to the Login page of the website
	 */
	public LoginDetails(int index ,String webName , String username , String pass ,String URL, String comments) {
		this.setIndex(index);
		this.setLoginWebsiteName(webName);
		this.setLoginUsername(username);
		this.setLoginPassword(pass);
		this.setLoginURL(URL);
		this.setComments(comments);
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public int getIndex(){
		return this.index;
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
	
	/**
	 * Getter method for comments
	 * @return comments given by user
	 */
	public String getComments() {
		return this.comments;
	}
	
	/**
	 * Setter method for comments
	 * @param com
	 */
	public void setComments(String com) {
		this.comments = com;
	}
	
	public void displayLoginDetails() {
		System.out.println("Index: " + this.index);
		System.out.println("Website: " + this.loginWebsiteName);
		System.out.println("Username: " + this.loginUsername);
		System.out.println("Password: " + this.loginPassword);
		System.out.println("URL: " + this.loginURL);
		System.out.println("Comments: " + this.comments);
		
	}
	
	public boolean equals(Object o) {
		if(o instanceof LoginDetails) {
			if(((LoginDetails)o).getIndex() == this.getIndex()) {
				return true;
			}
		}
		return false;
	}
}
