package Transport4Future.TokenManagement;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Pattern;

public class Token {

	private String tokenRequest;
	private String notificationEmail;

	private long requestDate;
	private long expirationDate;

	final private String algorithm;
	final private String password;
	final private String type;


	public Token(String tokenRequest, String notificationEmail, long requestDate) throws TokenManagementException {

		if (tokenRequest.matches("^([A-F0-9]{32})|([a-f0-9]{32})$")) {
			this.tokenRequest = tokenRequest;
		} else {
			throw new TokenManagementException(ErrorMessage.invalidTokenRequest);
		}

		Pattern mailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]{1,64}@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+){0,254}\\.[a-zA-Z]{2,63}$");
		if (mailPattern.matcher(notificationEmail).matches()) {
			this.notificationEmail = notificationEmail;
		} else {
			throw new TokenManagementException(ErrorMessage.emailInvalidFormat);
		}

		// Using unix time
		if (requestDate > 0) {
			this.notificationEmail = notificationEmail;
		} else {
			throw new TokenManagementException(ErrorMessage.invalidUnixTime);
		}

		// Expires one year later
		long timeToExpireAfterIssue = 31536000;
		this.expirationDate = this.requestDate + timeToExpireAfterIssue;

		this.algorithm = "SHA-256";
		this.type = "PDS";
		this.password = "Stardust";
	}

	public boolean isValid() {
		return this.expirationDate <= System.currentTimeMillis()/1000;

	}

	/**
	 * Gets the token request associated with the token
	 * @return the md5 value of the request for the token
	 */
	public String getTokenRequest() {
		return this.tokenRequest;
	}

	/**
	 * Get the encoded authorisation token of the object encoded in base64
	 * @return the encoded base64 token
	 * @throws TokenManagementException if the sha256 hashing algorithm is not found
	 */
	public String getBase64Token() throws TokenManagementException {

		String result = this.toString() + this.generateSignatureSHA256();
		return this.encodeBase64(result);

	}

	/**
	 * Internal helper function to encode strings with base 64
	 * @param stringToEncode string to encode
	 * @return the encoded string
	 * @throws TokenManagementException if the base 64 encoding fails
	 */
	private String encodeBase64(String stringToEncode) throws TokenManagementException {
		String encodedURL;
		try {
			encodedURL = Base64.getUrlEncoder().encodeToString(stringToEncode.getBytes());
		} catch (Exception e) {
			throw new TokenManagementException(ErrorMessage.base64EncodingError);
		}
		return encodedURL;
	}

	/**
	 * Internal helper function that returns the sha256 hash of the current object
	 * Notice that a password is used and the hash is calculated based on the string representation of the
	 * Token object.
	 * @return the sha-256 hash of the token
	 * @throws TokenManagementException if the sha256 hashing algorithm is not found
	 */
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

		String hex = String.format("%064x", new BigInteger(1, digest));

		return hex;
		
	}

	/**
	 * Helper function that returns the first two parts of the token specification
	 * @return the header + payload without signature
	 */
	@Override
	public String toString() {
		String header = this.algorithm + this.type;
		String payload = this.tokenRequest + this.requestDate + this.expirationDate;
		return header + payload;
	}


}
