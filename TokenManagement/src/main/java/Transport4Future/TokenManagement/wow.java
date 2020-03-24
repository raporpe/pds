package Transport4Future.TokenManagement;

public class wow {
	
	public static void main(String args[]) throws TokenManagementException {

		TokenStore tk = new TokenStore();
		Token token = new Token("asdfasdfasd", "asfasdfasfd", System.currentTimeMillis()/1000);
		tk.add(token);
		
	}

}
