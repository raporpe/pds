package Transport4Future.TokenManagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.*;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Base64;


/**
 * Unit test for simple App.
 */

public class AppTest {

	private static JsonObject json;
	private static TokenManager tokenManager;
	private static String deviceDataFilePath;
	private static String licenseFilePath;
	private static TokenRequest request;
	private static String password;
	private static String token;

	/* Test case: <Test path is valid>
	* Equivalence class or boundary value considered: deviceDataFilePath
	* Testing technique: Equivalence Classes Analysis | Boundary Values Analysis
	* Expected value: No throws given, this test is always passed.
	*/
	@BeforeAll
	static void TM_RF_01() {
		deviceDataFilePath = "src/resources/01/CP-RF1-01.json";
		json = readJSON(deviceDataFilePath);
		tokenManager = new TokenManager();

		// this assertion gives no throws, so we commented it
		//Assertions.assertThrows(TokenManagementException.class, () -> tm.readTokenRequestFromJSON(filePath));
		Assertions.assertNotNull(deviceDataFilePath);
	}

	
	/* Test case: <Test number of data is correct>
	* Equivalence class or boundary value considered: correct number of data
	* Testing technique: Equivalence Classes Analysis | Boundary Values Analysis
	* Expected value: No throws
	*/
	@Test
	void checkFailOnBadJsonTag_01() {
					
		//Check error on extra tag
		String extraTagPath = "src/resources/01/extraTag.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(extraTagPath));
		
		//Check error on missing tags
		
		String missingDeviceNamePath = "src/resources/01/missingDeviceName.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingDeviceNamePath));
		
		String missingTypeOfDevicePath = "src/resources/01/missingTypeOfDevice.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingTypeOfDevicePath));
		
		String missingDriverVersionPath = "src/resources/01/missingDriverVersion.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingDriverVersionPath));
		
		String missingSerialNumberPath = "src/resources/01/missingSerialNumber.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingSerialNumberPath));
		
		String missingEmailPath = "src/resources/01/missingEmail.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingEmailPath));
		
		String missingMacAddressPath = "src/resources/01/missingMacAddress.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingMacAddressPath));

	}
	
	/* Test case: <Test path is well written>
	* Equivalence class or boundary value considered: well written path
	* Testing technique: Equivalence Classes Analysis | Boundary Values Analysis
	* Expected value: No throws
	*/
	@Test
	void checkFailOnBadJsonSyntax_01() {

		String badSyntaxPath = "src/resources/01/badSyntax.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badSyntaxPath));

	}
	
	
	/* Test case: <Test data is received correctly>
	* Equivalence class or boundary value considered: supposed data to receive
	* Testing technique: Equivalence Classes Analysis | Boundary Values Analysis
	* Expected value: Everything corresponds correctly
	*/
	@Test
	void checkReceivedData_01() throws TokenManagementException {

		String checkReceivedData = "src/resources/01/CP-RF1-01.json";
		try {
			request = tokenManager.TokenRequestGeneration(checkReceivedData);
		} catch (TokenManagementException e) {
			throw e;
		}


		Assertions.assertEquals(request.getDeviceName(), json.getString("Device Name"));
		Assertions.assertEquals(request.getTypeDevice(), json.getString("Type of Device"));
		Assertions.assertEquals(request.getDriverVersion(), json.getString("Driver Version"));
		Assertions.assertEquals(request.getSerialNumber(), json.getString("Serial Number"));
		Assertions.assertEquals(request.getMacAddress(), json.getString("MAC Address"));
		Assertions.assertEquals(request.getEmail(), json.getString("Support e-mail"));


	}
	
	/* Test case: <Test regex>
	* Equivalence class or boundary value considered: Valid data regex
	* Testing technique: Equivalence Classes Analysis | Boundary Values Analysis
	* Expected value: No throws
	*/
	@Test
	void checkFailOnBadDataRegex_01() {
		
		String badDeviceNamePath = "src/resources/01/badDeviceName.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badDeviceNamePath));
		
		String badTypeOfDevicePath = "src/resources/01/badTypeOfDevice.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badTypeOfDevicePath));
		
		String badDriverVersionPath = "src/resources/01/badDriverVersion.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badDriverVersionPath));
		
		String badSerialNumberPath = "src/resources/01/badSerialNumber.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badSerialNumberPath));
		
		String badEmailPath = "src/resources/01/badEmail.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badEmailPath));
		
		String badMacAddressPath = "src/resources/01/badMacAddress.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badMacAddressPath));
	}


	/* Test case: <Test MD5>
	* Equivalence class or boundary value considered: Correct defined string 
	* Testing technique: Equivalence Classes Analysis | Boundary Values Analysis
	* Expected value: String matching regex
	*/
	@Test
	void checkMD5_01() throws TokenManagementException {

		String checkMD5 = "src/resources/01/CP-RF1-01.json";
		request = tokenManager.TokenRequestGeneration(checkMD5);
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException(ErrorMessage.md5AlgorithmNotFound);
		}
		
		//  Defined  password is "Stardust" & req is the TokenRequest object
		String input =  password + "-" + request.toString();
		
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x" 
		String hex = String.format("%32x", new BigInteger(1, digest));
		
		token = request.getHash();
		
		System.out.println(token + " " + hex);

		Assertions.assertEquals(hex, token);
		
		//Check the md5 is a valid string
		Assertions.assertTrue(token.matches("([A-F0-9]{32})|([a-f0-9]{32})"));

		
	}

	/* Test case: <test json path exists>
	* Equivalence class or boundary value considered: Path syntaxis
	* Testing technique: Equivalence Classes Analysis | Boundary Values Analysis
	* Expected value: No throws
	*/
	@Test
	void testFailOnWrongDataPath_01() {
		String wrongFilePath = "src/resources/01/doesnotexist.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(wrongFilePath));

	}
	
	/* Test case: <test json path is not empty>
	* Equivalence class or boundary value considered: valid json file
	* Testing technique: Equivalence Classes Analysis | Boundary Values Analysis
	* Expected value: No throws
	*/
	@Test
	void testFailOnEmptyJson_01() {
		String emptyFilePath = "src/resources/01/empty.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(emptyFilePath));
	}
	
//	@Test
//	public void testInternalError() {
//		String internalErrorFilePath = "src/resources/internal_error.json";
//		Assertions.assertThrows(TokenManagementException.class,
//		() -> tokenManager.readTokenRequestFromJSON(internalErrorFilePath));
//	}





















	///// SECTION 1 PART 2 TESTS
	
	@Test
	void testJsonCorrectReadSHA256_02() throws TokenManagementException{
	
		String normalPath = "src/resources/02/TM-RF-02-I1.json";
		JsonObject test = readJSON(normalPath);
		
		
		String requestToken = test.getString("Token Request");
		String notificationEmail = test.getString("Notification e-mail");		
		String requestDate = test.getString("Request Date");
		

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:MM:SS");  
		long unixDate;
		try {
			unixDate = dateFormat.parse(requestDate).getTime();
		} catch (ParseException e) {
			throw new TokenManagementException(ErrorMessage.invalidDateFormat);
		}

		String header = "SHA-256" + "PDS";
		String payload = requestToken + unixDate + (unixDate+31536000);
		String noSignetureToken = header + payload;
		
		String token = noSignetureToken + hashSHA256("Stardust" + "-" + noSignetureToken);
		
		token = Base64.getUrlEncoder().encodeToString(token.getBytes());

		String tokenManagerRequest;
		
		try {
			tokenManagerRequest = tokenManager.RequestToken(normalPath);
		} catch (TokenManagementException e) {
			throw e;
		}

		
		Assertions.assertEquals(token, tokenManagerRequest);
		
		
	}
	
	
	@Test
	 void testFailOnBadDataRegx_02(){
		
		String badTokenRequestPath = "src/resources/02/badTokenRequest.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badTokenRequestPath));
		
		String badNotificationEmailPath = "src/resources/02/badNotificationEmail.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badNotificationEmailPath));
		
		String badRequestDatePath = "src/resources/02/badRequestDate.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badRequestDatePath));
		
	}
	
	
	@Test
	 void testFailOnMissingTag_02() {
		
		
		//Check error on extra tag
		String extraTagPath = "src/resources/02/extraTag.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(extraTagPath));
		
		
		//Check error on missing tags
		
		String missingTokenRequestPath = "src/resources/02/missingTokenRequest.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingTokenRequestPath));
		
		String missingNotificationEmailPath = "src/resources/02/missingNotificationEmail.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingNotificationEmailPath));
		
		String missingRequestDatePath = "src/resources/02/missingRequestDate.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingRequestDatePath));
	
		
	}

	
	
	@Test
	 void testFailOnEmptyJson_02() {
		
		String emptyPath = "src/resources/02/empty.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(emptyPath));
		
	}
	
	@Test
	 void testFailOnMalformedJson_02() {
		
		String malformedJsonPath = "src/resources/02/malformed.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(malformedJsonPath));
		
	}
	
	
	// Part three
	

	 void testBase64Encoding_03() throws TokenManagementException {
		
		String inputFile = "src/resources/03/normal.json";
		
		JsonObject test = readJSON(inputFile);
		
		String requestToken = test.getString("Token Request");
		String notificationEmail = test.getString("Notification e-mail");		
		String requestDate = test.getString("Request Date");
		

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:MM:SS");  
		long unixDate;
		try {
			unixDate = dateFormat.parse(requestDate).getTime();
		} catch (ParseException e) {
			throw new TokenManagementException(ErrorMessage.invalidDateFormat);
		}

		String header = "SHA-256" + "PDS";
		String payload = requestToken + unixDate + (unixDate+31536000);
		String noSignetureToken = header + payload;
		
		String token = noSignetureToken + hashSHA256("Stardust" + "-" + noSignetureToken);

		String encodedToken = Base64.getUrlEncoder().encodeToString(token.getBytes());
		
		Assertions.assertEquals(encodedToken, tokenManager.RequestToken(inputFile));	
		
	}
	
	
	 void testFailOnExpiredDate_03() {
		
		String inputFile = "src/resources/03/expired.json";

		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(inputFile));
			
	}
	
	
	public void testFailOnUnexistentToken_03() {
		
		String inputFile = "src/resources/03/unexistent.json";

		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(inputFile));
			
	}
	
	public void testRegisteredToken_03() throws TokenManagementException {
		
		String inputFile = "src/resources/03/register.json";
		
		String base64 = tokenManager.RequestToken(inputFile);
		

		Assertions.assertTrue(tokenManager.VerifyToken(base64));
		

	}
	
	public void testNotRegisteredToken_03() throws TokenManagementException {
		
		String unregisteredTokenPath = "src/resources/03/unregistered.json";
		

		JsonObject test = readJSON(unregisteredTokenPath);
		
		
		String requestToken = test.getString("Token Request");
		
		//Useless?
		//String notificationEmail = test.getString("Notification e-mail");		
		String resquestDate = test.getString("Request Date");
		

		String header = "SHA-256";
		String payload = requestToken + resquestDate + "17/06/2030 22:00:00";
		String noSignatureToken = header + payload;
		
		
		String unexistingToken = noSignatureToken + hashSHA256(noSignatureToken);
		
		Assertions.assertFalse(tokenManager.VerifyToken(unexistingToken));


	}
	
	public void testInvalidBase64() {
		String invalidBase64 = "Es el vecino el que elige el alcalde y"
				+ " es el alcalde el que quiere que sean los vecinos el alcalde\n";
		
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.VerifyToken(invalidBase64));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static String hashSHA256(String input) throws TokenManagementException {
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("Error: no such hashing algorithm.");
		}
		
		
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x" 
		String result = String.format("%64x", new BigInteger(1, digest));
		
		return result;
		
	}
	
	
	
	private static JsonObject readJSON(String path) {

		String fileContents = "";
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			Assertions.fail("The json file was not found");
		}

		String line;
		try {
			while ((line = reader.readLine()) != null) {
				fileContents += line;
			}
		} catch (IOException e) {
			Assertions.fail("Error: input file could not be accessed.");
		}

		try {
			reader.close();
		} catch (IOException e) {
			Assertions.fail("Error: input file could not be closed.");
		}

		JsonObject jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();
	

		return jsonLicense;
	}
	


}
