package password;

public abstract class EncryptionDecryption {
	public abstract Object encrypt(Object o) throws CryptoException;
	public abstract Object decrypt(Object o) throws CryptoException;
	
}
