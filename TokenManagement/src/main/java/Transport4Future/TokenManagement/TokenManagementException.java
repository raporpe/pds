package Transport4Future.TokenManagement;

public class TokenManagementException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String message;

	public TokenManagementException(String message) {

		this.message = message;
	}

	public String getMessage() {

		return this.message;
	}
}
