package Transport4Future.TokenManagement;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Token {

	private String tokenRequest;
	private String notificationEmail;

	private long requestDate;
	private long expirationDate;

	//Set by default to a year
	final private long timeToExpireAfterIssue = 31536000;
	final private String algorithm;
	final private String password;
	final private String type;

	public Token(String tokenRequest, String notificationEmail, long requestDate) {

		this.tokenRequest = tokenRequest;

		this.notificationEmail = notificationEmail;

		// Using unix time
		this.requestDate = requestDate;

		// Expires one year later
		this.expirationDate = this.requestDate + this.timeToExpireAfterIssue;

		this.algorithm = "SHA-256";
		this.type = "PDS";
		this.password = "Stardust";
	}

	public boolean isValid() {
		return this.expirationDate <= System.currentTimeMillis()/1000;

	}
	
	public String getTokenRequest() {
		return this.tokenRequest;
	}



	public String getBase64Token() throws TokenManagementException {

		String result = this.toString() + this.generateSignatureSHA256();
		return this.encodeBase64(result);

	}

	@Override
	public String toString() {
		String header = this.algorithm + this.type;
		String payload = this.tokenRequest + this.requestDate + this.expirationDate;
		return header + payload;
	}

	private String encodeBase64(String stringToEncode) throws TokenManagementException {
		String encodedURL;
		try {
			encodedURL = Base64.getUrlEncoder().encodeToString(stringToEncode.getBytes());
		} catch (Exception e) {
			throw new TokenManagementException(ErrorMessage.base64EncodingError);
		}
		return encodedURL;
	}

	private String generateSignatureSHA256() throws TokenManagementException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException(ErrorMessage.sha256AlgorithmNotFound);
		}

		String input = this.password + "-" + this.toString();
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		String hex = String.format("%64x", new BigInteger(1, digest));
		System.out.println(hex);

		return hex;
		
	}

}
