package Transport4Future.TokenManagement;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

public class Token {

	private String alg;
	private String typ;
	private String device;
	private String requestDate;
	private String notificationEmail;
	private long iat;
	private long exp;
	private String signature;
	private String tokenValue;
	
	public Token(String Device, String RequestDate, String NotificationEmail) {
		this.alg = "SHA-256";
		this.typ = "PDS";
		this.device = Device;
		this.requestDate = RequestDate;
		this.notificationEmail = NotificationEmail;
		//this.iat = System.currentTimeMillis();
		this.iat = 123456789;
		this.exp = this.iat + 987654321;
		this.signature = null;
		this.tokenValue = null;
	}
	
	public String getTokenValue() {
		return this.tokenValue;
	}
	public String CodeHash256(Token myToken) throws TokenManagementException{
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		}catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("Error: no such hashing algorithm");
		}
		
		String input = "Stardust" + "-" + myToken.toString();
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte [] digest = md.digest();
		
		String hex = String.format("%64x", new BigInteger(1,digest));
		return hex;
	}
	
	public String encodeString(String stringToEncode) throws TokenManagementException{
		String encodedURL;
		try {
			encodedURL = Base64.getUrlEncoder().encodeToString(stringToEncode.getBytes());
		}catch (Exception ex) {
			throw new TokenManagementException("Error enconding 64URL.");
		}
		return encodedURL;
	}
	
	public boolean isValid (Token tokenFound) {
		if (!tokenFound.isExpired() && tokenFound.isGranted()) {
			return true;
		}
		return false;
	}
	
	public boolean isGranted() {
		return true;
	}
	
	public boolean isExpired() {
		if(this.exp > System.currentTimeMillis()) {
			return false;
		}
		return true;
	}
	
	public String toString() {
		return this.alg + this.typ;
	}
	
}
