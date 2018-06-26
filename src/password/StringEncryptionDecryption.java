package password;

import java.security.*;
import java.util.*;

import javax.crypto.*;
import javax.crypto.spec.*;

public class StringEncryptionDecryption extends EncryptionDecryption {

	private SecretKey secretKey;
	public StringEncryptionDecryption(SecretKey sk) {
		secretKey = sk;
	}
	
	@Override
	public Object encrypt(Object o) throws CryptoException {
		try {
			String data = (String) o ;
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE ,  this.secretKey);
			byte[] encryptedString = cipher.doFinal(data.getBytes());
			return Base64.getEncoder().encodeToString(encryptedString);			
		}
		catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException  e ) {
			throw new CryptoException(e.getMessage(), e);
		}
		
	}

	@Override
	public Object decrypt(Object o) throws CryptoException {
		try {
			String data = (String) o ;
			Cipher cipher = Cipher.getInstance("AES");
			
			cipher.init(Cipher.DECRYPT_MODE ,  this.secretKey);
			
			byte[] decryptedString = cipher.doFinal(Base64.getDecoder().decode(data));
			return new String(decryptedString);			
		}
		catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException  e ) {
			throw new CryptoException(e.getMessage(), e);
		}
		
	}

	
	//For testing only
	public static void main(String arg[]) {
		try {
			String data = "faha asd sdd asdd";
			String keyString = "anon asd asd asd";
			byte[] key = keyString.getBytes();
			SecretKey sk = new SecretKeySpec(key , "AES");
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.ENCRYPT_MODE , sk);
			byte[] encrypted = c.doFinal(data.getBytes());
			String eString = Base64.getEncoder().encodeToString(encrypted);
			System.out.println("Encrypted " + eString);
			
			c = Cipher.getInstance("AES");
			c.init(Cipher.DECRYPT_MODE,  sk);
			byte[] decrypted = c.doFinal(Base64.getDecoder().decode(eString));
			String dString = new String(decrypted);
			System.out.println("Decrypted " + dString);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
