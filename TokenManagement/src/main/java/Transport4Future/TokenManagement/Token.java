package Transport4Future.TokenManagement;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

public class Token {

	private String tokenRequest;
	private String requestDate;
	private String notificationEmail;

	private long issueDate;
	private long expirationDate;

	//Set by default to a year
	final private long timeToExpireAfterIssue = 31536000;
	final private String algorithm;
	final private String password;
	final private String type;

	public Token(String tokenRequest, String requestDate, String notificationEmail) {

		this.tokenRequest = tokenRequest;
		this.requestDate = requestDate;

		this.notificationEmail = notificationEmail;

		// Using unix time
		this.issueDate = Instant.now().getEpochSecond();

		// Expires one year later
		this.expirationDate = this.issueDate + this.timeToExpireAfterIssue;

		this.algorithm = "SHA-256";
		this.type = "PDS";
		this.password = "Stardust";
	}

	public boolean isValid(Token tokenFound) {
		return !tokenFound.isExpired();

	}

	public boolean isExpired() {
		return this.expirationDate > Instant.now().getEpochSecond();
	}
	
	public String getTokenRequest() {
		return this.tokenRequest;
	}

	public String toString() {
		String header = this.algorithm + this.type;
		String payload = this.tokenRequest + issueDate + expirationDate;
		return header + payload;
	}

	public String getBase64Token() throws TokenManagementException {
		String header = this.algorithm + this.type;
		String payload = this.tokenRequest;

		String result = header + payload + this.generateSignatureSHA256();

		return this.encodeBase64(result);
	}

	private String encodeBase64(String stringToEncode) throws TokenManagementException {
		String encodedURL;
		try {
			encodedURL = Base64.getUrlEncoder().encodeToString(stringToEncode.getBytes());
		} catch (Exception ex) {
			throw new TokenManagementException("Error enconding 64URL.");
		}
		return encodedURL;
	}

	private String generateSignatureSHA256() throws TokenManagementException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("Error: the hashing algorithm SHA-256 is not available");
		}

		String input = this.password + "-" + this.toString();
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		String hex = String.format("%64x", new BigInteger(1, digest));
		return hex;
	}

}
