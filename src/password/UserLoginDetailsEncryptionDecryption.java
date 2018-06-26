package password;

import java.util.*;

import javax.crypto.*;

public class UserLoginDetailsEncryptionDecryption extends EncryptionDecryption{

	private SecretKey secretKey;
	
	public UserLoginDetailsEncryptionDecryption (SecretKey sk) {
		this.secretKey = sk;
	}
	
	@Override
	public Object encrypt(Object o) throws CryptoException {
		UserLoginDetailsData userLoginDetailsData = (UserLoginDetailsData) o;
		
		StringEncryptionDecryption sed = new StringEncryptionDecryption(secretKey);
		
		Iterator<LoginDetails> i = userLoginDetailsData.getLoginDetailsList().iterator();
		while(i.hasNext()){
			LoginDetails loginDetail = i.next();
			
			String encryptedLoginUsername = (String) sed.encrypt(loginDetail.getLoginUsername());
			String encryptedLoginPassword = (String) sed.encrypt(loginDetail.getLoginPassword());
			
			loginDetail.setLoginUsername(encryptedLoginUsername);
			loginDetail.setLoginPassword(encryptedLoginPassword);
			
		}
		return userLoginDetailsData;
	}

	@Override
	public Object decrypt(Object o) throws CryptoException {
		
		UserLoginDetailsData userLoginDetailsData = (UserLoginDetailsData) o;
		
		StringEncryptionDecryption sed = new StringEncryptionDecryption(secretKey);
		
		Iterator<LoginDetails> i = userLoginDetailsData.getLoginDetailsList().iterator();
		while(i.hasNext()){
			LoginDetails loginDetail = i.next();
			
			String decryptedLoginUsername = (String) sed.decrypt(loginDetail.getLoginUsername());
			String decryptedLoginPassword = (String) sed.decrypt(loginDetail.getLoginPassword());
			
			loginDetail.setLoginUsername(decryptedLoginUsername);
			loginDetail.setLoginPassword(decryptedLoginPassword);
			
		}
		return userLoginDetailsData;
	}
	
	//for testing only
	public static void main(String arg[]) {
		try {
			Authorization.loginUser("fahad","fms");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
