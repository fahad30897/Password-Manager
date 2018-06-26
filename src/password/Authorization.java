package password;

import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.util.*;

import javax.crypto.*;
import javax.crypto.spec.*;

public class Authorization {
	
	private static User user;
	private static UserData userData;
	private static SecretKey userSecretKey;
	private static KeyStore keyStore;
	private static String keyStorePassword = "PasswordManagerDesktopApp";
	private static SecretKey masterKey = new SecretKeySpec("UserDataMasterKe".getBytes() , "AES");
	
	/**
	 * Method used to login user
	 * @param username name of the user
	 * @param password password of the user
	 * @return logged in or not
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static boolean loginUser(String username, String password) throws UserNotFoundException , CryptoException , ClassNotFoundException, IOException{
		
	    Authorization.userData = UserData.loadUserData(); // requires Master key
	    
		User loggedInUser = userData.getLoggedInUser(username);
		
		if(loggedInUser.getPassword().equals(password)) {
			Authorization.user = loggedInUser;
			Authorization.userSecretKey = loadSecretKey(username, password);
			Authorization.user.loadUserLoginDetailsData();
			return true;
		}
		return false;
	}
	
	public static User getCurrentUser() {
		return Authorization.user;
	}
	
	public static UserData getUserData() {
		return Authorization.userData;
	}
	
	public static SecretKey getMasterKey() {
		return Authorization.masterKey;
	}
	
	public static SecretKey getUserSecretKey() {
		return userSecretKey;
	}
	
	public static SecretKey loadSecretKey(String username , String password) throws CryptoException{
	
		loadOrCreateKeyStore();
		
		try {
			
			Key key = keyStore.getKey(username, password.toCharArray() );
			
			return (SecretKey)key;
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
			throw new CryptoException(e.getMessage()  , e);
		}
		
	}
	
	public static void storeKeyToKeyStore(SecretKey sk ,String username , String password) throws CryptoException {
		Authorization.loadOrCreateKeyStore();
		
		try {
			keyStore.setKeyEntry(username, sk , password.toCharArray() , null);
			keyStore.store(new FileOutputStream( new File("data\\KeyStoreFile.keystore")), keyStorePassword.toCharArray());
			
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void loadOrCreateKeyStore() throws CryptoException {
		File file = new File("data\\KeyStoreFile.keystore");
		
		try {
			keyStore = KeyStore.getInstance("JCEKS");
		
		    if (file.exists()) {
		    
				keyStore.load(new FileInputStream(file), keyStorePassword.toCharArray());
				
		    } else {
		        // if not exists, create
		    
		        keyStore.load(null, null);
		        keyStore.store(new FileOutputStream(file), keyStorePassword.toCharArray());
		    }
		    
		} 
		catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			throw new CryptoException(e.getMessage() , e);
		}
	}
	
	
	public static boolean createNewUser(User user) throws ClassNotFoundException, IOException, UserAlreadyExistException, CryptoException, UserNotFoundException {
		Authorization.userData = UserData.loadUserData(); // requires secret key
		String username = new String(user.getUsername());
		
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
	        SecretKey secretKey = keyGen.generateKey();
	     
	        storeKeyToKeyStore(secretKey , user.getUsername() , user.getPassword()); // Users secretKey added
	        System.out.println("In createNewUser "+ Base64.getEncoder().encodeToString(loadSecretKey(user.getUsername(),user.getPassword()).getEncoded()));
			Authorization.userData.addNewUser(user);// New User added
			
			Authorization.userData = UserData.loadUserData(); // requires secret key
			user = Authorization.userData.getUserFromUsername(username);
	        Authorization.loginUser(user.getUsername(), user.getPassword());
	        
		} catch (NoSuchAlgorithmException e) {
			throw new CryptoException(e.getMessage() , e);
		}
        
		
		
		
		return false;
	}
	
	
	public static void logoutUser(){
		Authorization.user = null;
	}
	
	//Only for testing
	public static void main(String arg[]) {
		try {
			Authorization.loginUser("abhi", "fahad");
			//Authorization.createNewUser(new User("abhi", "fahad"));
			//String keyString = "anos asd asd asd";
			//byte[] key1 = keyString.getBytes();
			//SecretKey sk = new SecretKeySpec(key1 , "AES");
			//keyStore.setKeyEntry("faizal", sk , "faizal".toCharArray(), null);
			//System.out.println(Base64.getEncoder().encodeToString(keyStore.getKey("faizal", "fahad".toCharArray()).getEncoded()));
			
			//Authorization.getCurrentUser().addNewLoginDetails(new  LoginDetails(1,"yahoo" , "chapat" ,"abhi"));
			System.out.println("Added 1");
			//Authorization.getCurrentUser().addNewLoginDetails(new  LoginDetails(1,"Google" , "chapat" ,"abhi"));
	        Authorization.getCurrentUser().editLoginDetials(new LoginDetails(1, "google", "chapat", "abhi" , "github" , "something"));
			
			Authorization.getCurrentUser().DisplayData();
			for(LoginDetails details : Authorization.getCurrentUser().getUserLoginDetailsData().getLoginDetailsList()) {
				details.displayLoginDetails();
			}
			/*
			for(User user : UserData.loadUserData().getAllUsers()) {
				user.DisplayData();
			}*/
			//System.out.println(Base64.getEncoder().encodeToString(Authorization.getUserSecretKey().getEncoded()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
