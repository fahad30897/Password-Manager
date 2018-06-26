package password;

import java.util.*;

public class UserDataEncryptionDecryption extends EncryptionDecryption{

	public UserDataEncryptionDecryption() {}
	
	@Override
	public Object encrypt(Object o) throws CryptoException {
		
		UserData userData = (UserData) o;
	
		StringEncryptionDecryption sed = new StringEncryptionDecryption(Authorization.getMasterKey());
		Iterator<User> i = userData.getAllUsers().iterator();
		while(i.hasNext()){
			User user = i.next();
			
			String encryptedPassword = (String)sed.encrypt(user.getPassword());
			String encryptedUsername = (String)sed.encrypt(user.getUsername());
			String encryptedPrivateKey = (String) sed.encrypt(user.getPrivateKey());
			System.out.println("In UserDataEncryptionDecrpytion Encrypt");
					
			user.setPassword(encryptedPassword);
			user.setUsername(encryptedUsername);
			user.setPrivateKey(encryptedPrivateKey);
		}
		return userData;
	}

	@Override
	public Object decrypt(Object o) throws CryptoException {
		
		UserData userData = (UserData) o;
		StringEncryptionDecryption sed = new StringEncryptionDecryption(Authorization.getMasterKey());
		Iterator<User> i = userData.getAllUsers().iterator();
		while(i.hasNext()){
			User user = i.next();
			String decryptedPassword = (String)sed.decrypt(user.getPassword());
			String decryptedUsername = (String)sed.decrypt(user.getUsername());
			String decryptedPrivateKey = (String) sed.decrypt(user.getPrivateKey());
			System.out.println("In UserDataEncryptionDecrpytion");
			
			user.setPassword(decryptedPassword);
			user.setUsername(decryptedUsername);
			user.setPrivateKey(decryptedPrivateKey);
		}
		
		return userData;
	}
	
	//For testing only
	public static void main(String args[]) {
		try {
			//UserData userData = UserData.loadUserData();
			Authorization.loginUser("fahad" , "fms");
			UserDataEncryptionDecryption uded = new UserDataEncryptionDecryption();
			UserData userData = (UserData) uded.encrypt(Authorization.getUserData());
			//System.out.println("Encrypted data");
			for(User user : userData.getAllUsers()) {
				user.DisplayData();
			}
			
			System.out.println("decrypted data");
			userData = (UserData) uded.decrypt(Authorization.getUserData());
			for(User user : userData.getAllUsers()) {
				user.DisplayData();
			}
			
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
	}

}
