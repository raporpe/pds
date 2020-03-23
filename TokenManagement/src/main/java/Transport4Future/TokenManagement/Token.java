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
	private Date requestDate;
	private String notificationEmail;
	private long iat;
	private long exp;
	
	public Token(String alg, String typ, String Device, Date RequestDate, String NotificationEmail, long iat, long exp) {
		this.alg = "HS256";
		this.typ = "PDS";
		this.device = Device;
		this.requestDate = RequestDate;
		this.notificationEmail = NotificationEmail;
		//this.iat = System.currentTimeMillis();
		this.iat = 123456789;
		this.exp = this.iat + 987654321;
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
	
	private String encodeString(String stringToEncode) throws TokenManagementException{
		String encodedURL;
		try {
			encodedURL =Base64.getUrlEncoder().encodeToString(stringToEncode.getBytes());
		}catch (Exception ex) {
			throw new TokenManagementException("Error enconding 64URL.");
		}
		return encodedURL;
	}
}
