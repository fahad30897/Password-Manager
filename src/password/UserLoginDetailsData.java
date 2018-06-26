package password;

import java.io.*;
import java.util.*;

public class UserLoginDetailsData implements Serializable {
	
	private List<LoginDetails> loginList = new ArrayList<LoginDetails>();
	
	/**
	 * Method to add new login details to list of login details of current user
	 * @param ld - Login Details for the website for which user wants to store the password
	 * @return
	 * @throws IOException 
	 * @throws CryptoException 
	 */
	public boolean addNewLoginDetails(LoginDetails ld) throws IOException, CryptoException {
		System.out.println("UserLoginDetailsData" + loginList.size());
		if(loginList.size() == 0){
			ld.setIndex(1);
		}
		else{
			ld.setIndex(loginList.get(loginList.size()-1).getIndex() + 1);
		}
		boolean returnValue = loginList.add(ld);
		if(returnValue == true) {
			System.out.println("in userlogindetailsdata add");
//			User user = Authorization.getCurrentUser();
//			this.saveUserLoginDetails(user.getUsername(), user.getPassword(), user.getLoginDetailsFileName());
		}
		return returnValue;
	}
	
	/**
	 * Method to load the Login Details of the current User
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws CryptoException 
	 */
	public static UserLoginDetailsData loadUserLoginDetails(String username, String password ,String fileName) throws IOException, ClassNotFoundException, CryptoException {
		File file = new File("data\\" + fileName + ".pm");
		if(!file.exists()) {
			file.createNewFile();
		}
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
		
			UserLoginDetailsEncryptionDecryption ulded = new UserLoginDetailsEncryptionDecryption(Authorization.loadSecretKey(username, password));
			return (UserLoginDetailsData) ulded.decrypt(ois.readObject());
		}
		catch(EOFException e) {
			return new UserLoginDetailsData();
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
	 * Method to persist data of the user in the corresponding file
	 * @param filename
	 * @throws IOException
	 * @throws CryptoException 
	 */
	public void saveUserLoginDetails(String username, String password , String fileName) throws IOException, CryptoException  {
		File file = new File("data\\" + fileName + ".pm");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		try {
			UserLoginDetailsEncryptionDecryption ulded = new UserLoginDetailsEncryptionDecryption(Authorization.loadSecretKey(username, password)); // getting null
			System.out.println("In UserLoginDetailsData  "+ username + " " + password);
			
			oos.writeObject(ulded.encrypt(this));
		}
		finally {
			fos.close();
			oos.close();
		}
	}
	
	public boolean editLoginDetails(LoginDetails ld) throws IOException, CryptoException{
		
		boolean rvalue;
		if(loginList.indexOf(ld) == -1) {
			rvalue = false;
		}
		else {
			LoginDetails details = loginList.get(loginList.indexOf(ld));
			
			if(details == null) {
				rvalue = false;
			}
			else {
				details.setComments(ld.getComments());
				details.setLoginPassword(ld.getLoginPassword());
				details.setLoginURL(ld.getLoginURL());
				details.setLoginUsername(ld.getLoginUsername());
				details.setLoginWebsiteName(ld.getLoginWebsiteName());
//				User user = Authorization.getCurrentUser();
//				this.saveUserLoginDetails(user.getUsername(), user.getPassword(), user.getLoginDetailsFileName());
				rvalue = true;
				
			}
		}
		return rvalue;
	}
	
	public void deleteLoginDetails(LoginDetails ld) throws IOException, CryptoException {
		this.loginList.remove(ld);
//		User user = Authorization.getCurrentUser();
//		this.saveUserLoginDetails(user.getUsername(), user.getPassword(), user.getLoginDetailsFileName());
	}
	
	public void takeBackup(String username, String password , String fileName) throws IOException, CryptoException {
		File file = new File(fileName);
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		try {
			UserLoginDetailsEncryptionDecryption ulded = new UserLoginDetailsEncryptionDecryption(Authorization.loadSecretKey(username, password)); // getting null
			System.out.println("In UserLoginDetailsData  "+ username + " " + password);
			//System.out.println("In UserLoginDetailsData  "+ Base64.getEncoder().encodeToString(Authorization.loadSecretKey(username, password).getEncoded()));
			oos.writeObject(ulded.encrypt(this));
			ulded.decrypt(this);
		}
		finally {
			fos.close();
			oos.close();
		}
	}
	
	public UserLoginDetailsData loadFromBackup(String username, String password ,String fileName) throws IOException, ClassNotFoundException, CryptoException {
		File file = new File(fileName);
		if(!file.exists()) {
			file.createNewFile();
		}
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
		
			UserLoginDetailsEncryptionDecryption ulded = new UserLoginDetailsEncryptionDecryption(Authorization.loadSecretKey(username, password));
			return (UserLoginDetailsData) ulded.decrypt(ois.readObject());
		}
		catch(EOFException e) {
			return new UserLoginDetailsData();
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
	
	
	
	
	public List<LoginDetails> getLoginDetailsList(){
		return this.loginList;
	}

	//for testing only
	public static void main(String arg[]) {
		try {
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
