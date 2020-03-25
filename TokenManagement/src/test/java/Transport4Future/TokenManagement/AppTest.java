package Transport4Future.TokenManagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test for simple App.
 */

public class AppTest {

	private static JsonObject json;
	private static TokenManager tokenManager;
	private static String deviceDataFilePath;
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
		password = "Stardust";
		json = readJSON(deviceDataFilePath);
		tokenManager = new TokenManager();

		// this assertion gives no throws, so we commented it
		//Assertions.assertThrows(TokenManagementException.class, () -> tm.readTokenRequestFromJSON(filePath));
		assertNotNull(deviceDataFilePath);
	}


	@Test
	/** Test case: TM-RF-01-I1 - Device Name
	 * Equivalence class or boundary value considered: 21
	 * Testing technique: Boundary Values Analysis
	 * Expected value: Exception thrown stating that the Device Name date is not in the format.
	 */
	public void testDeviceName_BV21(){
		String deviceNamePath = "src/resources/01/deviceName_BV21.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(deviceNamePath));
		assertEquals(e.getMessage(), ErrorMessage.deviceNameInvalidFormat);
	}

	@Test
	/** Test case: TM-RF-01-I1 - Device Name
	 * Equivalence class or boundary value considered: 20
	 * Testing technique: Boundary Values Analysis
	 * Expected value: No exception thrown
	 */
	public void testDeviceName_BV20() throws TokenManagementException {
		String deviceNamePath = "src/resources/01/deviceName_BV20.json";
		assertTrue(tokenManager.TokenRequestGeneration(deviceNamePath).getDeviceName()
				.length() == 20);
	}


	@Test
	/** Test case: TM-RF-01-I1 - Device Name
	 * Equivalence class or boundary value considered: 19
	 * Testing technique: Boundary Values Analysis
	 * Expected value: the length after execution is 19
	 */
	public void testDeviceName_BV19() throws TokenManagementException {
		String deviceNamePath = "src/resources/01/deviceName_BV19.json";
		assertTrue(tokenManager.TokenRequestGeneration(deviceNamePath).getDeviceName()
				.length() == 19);
	}

	@Test
	/** Test case: TM-RF-01-I1 - Device Name
	 * Equivalence class or boundary value considered: 0
	 * Testing technique: Boundary Values Analysis
	 * Expected value: Exception thrown stating that the Device Name date is not in the format.
	 */
	public void testDeviceName_BV0(){
		String deviceNamePath = "src/resources/01/deviceName_BV0.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(deviceNamePath));
		assertEquals(e.getMessage(), ErrorMessage.deviceNameInvalidFormat);
	}

	@Test
	/** Test case: TM-RF-01-I1 - Device Name
	 * Equivalence class or boundary value considered: 1
	 * Testing technique: Boundary Values Analysis
	 * Expected value: No exception thrown
	 */
	public void testDeviceName_BV1() throws TokenManagementException {
		String deviceNamePath = "src/resources/01/deviceName_BV1.json";
		assertTrue(tokenManager.TokenRequestGeneration(deviceNamePath).getDeviceName()
				.length() == 1);

	}






	@Test
	/** Test case: TM-RF-01-I1 - Type of Device
	 * Equivalence class or boundary value considered: Sensor
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: The result equals sensor
	 */
	void testTypeOfDevice_EC_Sensor() throws TokenManagementException {
		String typeOfDevicePath = "src/resources/01/typeOfDevice_EC_Sensor.json";
		assertTrue(tokenManager.TokenRequestGeneration(typeOfDevicePath).getTypeDevice() == "Sensor");
	}

	@Test
	/** Test case: TM-RF-01-I1 - Type of Device
	 * Equivalence class or boundary value considered: Actuator
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: The result equals sensor
	 */
	void testTypeOfDevice_EC_Actuator() throws TokenManagementException {
		String typeOfDevicePath = "src/resources/01/typeOfDevice_EC_Actuator.json";
		assertTrue(tokenManager.TokenRequestGeneration(typeOfDevicePath).getTypeDevice() == "Actuator");
	}

	@Test
	/** Test case: TM-RF-01-I1 - Type of Device
	 * Equivalence class or boundary value considered: empty value
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: A exception thrown indication that the type of device is invalid.
	 */
	void testTypeOfDevice_EC_Empty() {
		String typeOfDevicePath = "src/resources/01/typeOfDevice_EC_Empty.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(typeOfDevicePath));
		assertEquals(e.getMessage(), ErrorMessage.typeDeviceInvalidFormat);
	}

	@Test
	/** Test case: TM-RF-01-I1 - Type of Device
	 * Equivalence class or boundary value considered: Actuators
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: A exception thrown indication that the type of device is invalid.
	 */
	void testTypeOfDevice_EC_Actuators() {
		String typeOfDevicePath = "src/resources/01/typeOfDevice_EC_Actuators.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(typeOfDevicePath));
		assertEquals(e.getMessage(), ErrorMessage.typeDeviceInvalidFormat);
	}







	@Test
	/** Test case: TM-RF-01-I1 - Driver Version
	 * Equivalence class or boundary value considered: 123.123.123.123.456.456.456.0
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value:
	 */
	void testDriverVersion_EC_Valid() throws TokenManagementException {
		String driverVersionPath = "src/resources/01/driverVersion_EC_Valid.json";
		assertTrue(tokenManager.TokenRequestGeneration(driverVersionPath)
				.getTypeDevice() == "123.123.123.123.456.456.456.0");
	}

	@Test
	/** Test case: TM-RF-01-I1 - Driver Version
	 * Equivalence class or boundary value considered: 32123.2342..234
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: A exception thrown indication that the type of device is invalid.
	 */
	void testDriverVersion_EC_Invalid() {
		String driverVersionPath = "src/resources/01/driverVersion_EC_Invalid.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(driverVersionPath));
		assertEquals(e.getMessage(), ErrorMessage.driverVersionInvalidFormat);
	}





	@Test
	/** Test case: TM-RF-01-I1 - Support Email
	 * Equivalence class or boundary value considered: example@example.com
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: No thrown exceptions and correct email value.
	 */
	void testEmail_EC_Valid() throws TokenManagementException {
		String emailPath = "src/resources/01/email_EC_Valid.json";
		assertTrue(tokenManager.TokenRequestGeneration(emailPath)
				.getEmail() == "example@example.com");
	}

	@Test
	/** Test case: TM-RF-01-I1 - Support Email
	 * Equivalence class or boundary value considered: example@example..com
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: A exception thrown indication that the email is invalid.
	 */
	void testEmail_EC_Invalid() {
		String emailPath = "src/resources/01/email_EC_Invalid.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(emailPath));
		assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);
	}



	@Test
	/** Test case: TM-RF-01-I1 - Serial Number
	 * Equivalence class or boundary value considered: serq2efwqr234
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: No thrown exceptions and correct serial number value.
	 */
	void testSerialNumber_EC_Valid() throws TokenManagementException {
		String serialNumberPath = "src/resources/01/serialNumber_EC_Valid.json";
		assertTrue(tokenManager.TokenRequestGeneration(serialNumberPath)
				.getEmail() == "serq2efwqr234");
	}

	@Test
	/** Test case: TM-RF-01-I1 - Serial Number
	 * Equivalence class or boundary value considered: 234rerfqsf qw3rr2
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: A exception thrown indication that the serial number is invalid.
	 */
	void testSerialNumber_EC_Invalid() {
		String serialNumberPath = "src/resources/01/serialNumber_EC_Invalid.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(serialNumberPath));
		assertEquals(e.getMessage(), ErrorMessage.serialNumberInvalidFormat);
	}












	/** Test case: TM-RF-01-I1 - Test extra json tag
	* Equivalence class or boundary value considered: correct number of data
	* Testing technique: Equivalence Classes Analysis
	* Expected value: An exception thrown with a the specified message for that error
	*/
	@Test
	void checkFailOnBadJsonTag_01() {
					
		//Check error on extra tag
		String extraTagPath = "src/resources/01/extraTag.json";
		TokenManagementException e1 = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(extraTagPath));
		assertEquals(e1.getMessage(), ErrorMessage.jsonExtraTagError);
		
		//Check error on missing tags
		
		String missingDeviceNamePath = "src/resources/01/missingDeviceName.json";
		TokenManagementException e2 =  assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingDeviceNamePath));
		assertEquals(e2.getMessage(), ErrorMessage.deviceNameInvalidFormat);
		
		String missingTypeOfDevicePath = "src/resources/01/missingTypeOfDevice.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingTypeOfDevicePath));
		
		String missingDriverVersionPath = "src/resources/01/missingDriverVersion.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingDriverVersionPath));
		
		String missingSerialNumberPath = "src/resources/01/missingSerialNumber.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingSerialNumberPath));
		
		String missingEmailPath = "src/resources/01/missingEmail.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingEmailPath));
		
		String missingMacAddressPath = "src/resources/01/missingMacAddress.json";
		assertThrows(TokenManagementException.class,
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
		assertThrows(TokenManagementException.class,
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


		assertEquals(request.getDeviceName(), json.getString("Device Name"));
		assertEquals(request.getTypeDevice(), json.getString("Type of Device"));
		assertEquals(request.getDriverVersion(), json.getString("Driver Version"));
		assertEquals(request.getSerialNumber(), json.getString("Serial Number"));
		assertEquals(request.getMacAddress(), json.getString("MAC Address"));
		assertEquals(request.getEmail(), json.getString("Support e-mail"));


	}
	
	/* Test case: <Test regex>
	* Equivalence class or boundary value considered: Valid data regex
	* Testing technique: Equivalence Classes Analysis | Boundary Values Analysis
	* Expected value: No throws
	*/
	@Test
	void checkFailOnBadDataRegex_01() {
		

		
		String badTypeOfDevicePath = "src/resources/01/badTypeOfDevice.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badTypeOfDevicePath));
		
		String badDriverVersionPath = "src/resources/01/badDriverVersion.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badDriverVersionPath));
		
		String badSerialNumberPath = "src/resources/01/badSerialNumber.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badSerialNumberPath));
		
		String badEmailPath = "src/resources/01/badEmail.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badEmailPath));
		
		String badMacAddressPath = "src/resources/01/badMacAddress.json";
		assertThrows(TokenManagementException.class,
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
		
		String input =  password + "-" + request.toString();
		
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x" 
		String hex = String.format("%32x", new BigInteger(1, digest));
		
		token = request.getHash();
		
		System.out.println(token + " " + hex);

		assertEquals(hex, token);
		
		//Check the md5 is a valid string
		assertTrue(token.matches("([A-F0-9]{32})|([a-f0-9]{32})"));

		
	}

	/* Test case: <test json path exists>
	* Equivalence class or boundary value considered: Path syntaxis
	* Testing technique: Equivalence Classes Analysis | Boundary Values Analysis
	* Expected value: No throws
	*/
	@Test
	void testFailOnWrongDataPath_01() {
		String wrongFilePath = "src/resources/01/doesnotexist.json";
		assertThrows(TokenManagementException.class,
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
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(emptyFilePath));
	}
	
//	@Test
//	public void testInternalError() {
//		String internalErrorFilePath = "src/resources/internal_error.json";
//		assertThrows(TokenManagementException.class,
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
		
		String token = noSignetureToken + hashSHA256(password + "-" + noSignetureToken);
		
		token = Base64.getUrlEncoder().encodeToString(token.getBytes());

		String tokenManagerRequest;
		
		try {
			tokenManagerRequest = tokenManager.RequestToken(normalPath);
		} catch (TokenManagementException e) {
			throw e;
		}

		
		assertEquals(token, tokenManagerRequest);
		
		
	}
	
	
	@Test
	 void testFailOnBadDataRegx_02(){
		
		String badTokenRequestPath = "src/resources/02/badTokenRequest.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badTokenRequestPath));
		
		String badNotificationEmailPath = "src/resources/02/badNotificationEmail.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badNotificationEmailPath));
		
		String badRequestDatePath = "src/resources/02/badRequestDate.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badRequestDatePath));
		
	}
	
	
	@Test
	 void testFailOnMissingTag_02() {
		
		
		//Check error on extra tag
		String extraTagPath = "src/resources/02/extraTag.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(extraTagPath));
		
		
		//Check error on missing tags
		
		String missingTokenRequestPath = "src/resources/02/missingTokenRequest.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingTokenRequestPath));
		
		String missingNotificationEmailPath = "src/resources/02/missingNotificationEmail.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingNotificationEmailPath));
		
		String missingRequestDatePath = "src/resources/02/missingRequestDate.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingRequestDatePath));
	
		
	}

	
	
	@Test
	 void testFailOnEmptyJson_02() {
		
		String emptyPath = "src/resources/02/empty.json";
		assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(emptyPath));
		
	}
	
	@Test
	 void testFailOnMalformedJson_02() {
		
		String malformedJsonPath = "src/resources/02/malformed.json";
		assertThrows(TokenManagementException.class,
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
		
		String token = noSignetureToken + hashSHA256(password + "-" + noSignetureToken);

		String encodedToken = Base64.getUrlEncoder().encodeToString(token.getBytes());
		
		assertEquals(encodedToken, tokenManager.RequestToken(inputFile));
		
	}
	
	
	 void testFailOnExpiredDate_03() {
		
		String inputFile = "src/resources/03/expired.json";

		assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(inputFile));
			
	}
	
	
	public void testFailOnUnexistentToken_03() {
		
		String inputFile = "src/resources/03/unexistent.json";

		assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(inputFile));
			
	}
	
	public void testRegisteredToken_03() throws TokenManagementException {
		
		String inputFile = "src/resources/03/register.json";
		
		String base64 = tokenManager.RequestToken(inputFile);
		

		assertTrue(tokenManager.VerifyToken(base64));
		

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
		
		assertFalse(tokenManager.VerifyToken(unexistingToken));


	}
	
	public void testInvalidBase64() {
		String invalidBase64 = "Es el vecino el que elige el alcalde y"
				+ " es el alcalde el que quiere que sean los vecinos el alcalde\n";
		
		assertThrows(TokenManagementException.class,
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
			fail("The json file was not found");
		}

		String line;
		try {
			while ((line = reader.readLine()) != null) {
				fileContents += line;
			}
		} catch (IOException e) {
			fail("Error: input file could not be accessed.");
		}

		try {
			reader.close();
		} catch (IOException e) {
			fail("Error: input file could not be closed.");
		}

		JsonObject jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();
	

		return jsonLicense;
	}
	


}
