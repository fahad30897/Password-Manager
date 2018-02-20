package password;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserLoginDetailsData implements Serializable {
	
	private List<LoginDetails> loginList = new ArrayList<LoginDetails>();
	
	public boolean addNewLoginDetails(LoginDetails ld) {
		return loginList.add(ld);
	}
	
	public void loadUserLoginDetails(String filename) {
		
	}
	
	public void saveUserLoginDetails(String filename) {
		
	}
	
}
