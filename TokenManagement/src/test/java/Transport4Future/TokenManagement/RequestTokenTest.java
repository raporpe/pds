package Transport4Future.TokenManagement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RequestTokenTest {

	private TokenManager myManager;
	private String jsonFilesFolder;
	
	public RequestTokenTest() {
		jsonFilesFolder = System.getProperty("user.dir") + "/JSONFiles/RequestToken/";
		myManager = new TokenManager();
	}
	
	void CorrectRequestTokenTest() throws TokenManagementException {
		String FilePath = this.jsonFilesFolder + "Correct.json";
		String expectedToken = "put here expected token";
		String obtainedToken = myManager.RequestToken(FilePath);
		assertEquals(expectedToken, obtainedToken);
	}

}
