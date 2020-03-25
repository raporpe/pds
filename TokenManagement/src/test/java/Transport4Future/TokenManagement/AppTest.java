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

	private static TokenManager tokenManager;
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
		password = "Stardust";
		tokenManager = new TokenManager();

		// this assertion gives no throws, so we commented it
		//Assertions.assertThrows(TokenManagementException.class, () -> tm.readTokenRequestFromJSON(filePath));
	}


	/**
	 *   .--.      .-'.      .--.      .--.      .--.      .--.      .`-.      .--.
	 * :::::.\::::::::.\::::::::TESTS FOR JSON FIELDS:::::::::.\::::::::.\::::::::.\
	 * '      `--'      `.-'      `--'      `--'      `--'      `-.'      `--'      `
	 */

	//Device name

	@Test
	/** Test case: TM-RF-01-P1 - Device Name
	 * Equivalence class or boundary value considered: LaCeramicaDeTalaveraN
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
	/** Test case: TM-RF-01-P1 - Device Name
	 * Equivalence class or boundary value considered: EsElVecinoElQueElige
	 * Testing technique: Boundary Values Analysis
	 * Expected value: No exception thrown and value matches
	 */
	public void testDeviceName_BV20() throws TokenManagementException {
		String deviceNamePath = "src/resources/01/deviceName_BV20.json";
		assertTrue(tokenManager.TokenRequestGeneration(deviceNamePath)
				.getDeviceName() == "EsElVecinoElQueElige");
	}


	@Test
	/** Test case: TM-RF-01-P1 - Device Name
	 * Equivalence class or boundary value considered: AlAlcaldeYEsElAlcal
	 * Testing technique: Boundary Values Analysis
	 * Expected value: No exception is thrown and the value mathces
	 */
	public void testDeviceName_BV19() throws TokenManagementException {
		String deviceNamePath = "src/resources/01/deviceName_BV19.json";
		assertTrue(tokenManager.TokenRequestGeneration(deviceNamePath)
				.getDeviceName() == "AlAlcaldeYEsElAlcal");
	}

	@Test
	/** Test case: TM-RF-01-P1 - Device Name
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
	/** Test case: TM-RF-01-P1 - Device Name
	 * Equivalence class or boundary value considered: 1
	 * Testing technique: Boundary Values Analysis
	 * Expected value: No exception thrown
	 */
	public void testDeviceName_BV1() throws TokenManagementException {
		String deviceNamePath = "src/resources/01/deviceName_BV1.json";
		assertTrue(tokenManager.TokenRequestGeneration(deviceNamePath).getDeviceName()
				.length() == 1);

	}

	//Type of device

	@Test
	/** Test case: TM-RF-01-P1 - Type of Device
	 * Equivalence class or boundary value considered: Sensor
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: The result equals sensor
	 */
	void testTypeOfDevice_EC_Sensor() throws TokenManagementException {
		String typeOfDevicePath = "src/resources/01/typeOfDevice_EC_Sensor.json";
		assertTrue(tokenManager.TokenRequestGeneration(typeOfDevicePath).getTypeDevice() == "Sensor");
	}

	@Test
	/** Test case: TM-RF-01-P1 - Type of Device
	 * Equivalence class or boundary value considered: Actuator
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: The result equals sensor
	 */
	void testTypeOfDevice_EC_Actuator() throws TokenManagementException {
		String typeOfDevicePath = "src/resources/01/typeOfDevice_EC_Actuator.json";
		assertTrue(tokenManager.TokenRequestGeneration(typeOfDevicePath).getTypeDevice() == "Actuator");
	}

	@Test
	/** Test case: TM-RF-01-P1 - Type of Device
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
	/** Test case: TM-RF-01-P1 - Type of Device
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

	//Driver version

	@Test
	/** Test case: TM-RF-01-P1 - Driver Version
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
	/** Test case: TM-RF-01-P1 - Driver Version
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

	//Support email

	@Test
	/** Test case: TM-RF-01-P1 - Support Email
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
	/** Test case: TM-RF-01-P1 - Support Email
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

	//Serial number

	@Test
	/** Test case: TM-RF-01-P1 - Serial Number
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
	/** Test case: TM-RF-01-P1 - Serial Number
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

	@Test
	/** Test case: TM-RF-01-P1 - Serial Number
	 * Equivalence class or boundary value considered: (50)"wf4sdrf4q4wef134sdfbdzxbmeqm8t2hh45htehef2gerg3523"
	 * Testing technique: Boundary Values Analysis
	 * Expected value: No thrown exceptions and correct serial number value.
	 */
	void testSerialNumber_BV_50() throws TokenManagementException {
		String serialNumberPath = "src/resources/01/serialNumber_BV_50";
		assertTrue(tokenManager.TokenRequestGeneration(serialNumberPath)
				.getEmail() == "wf4sdrf4q4wef134sdfbdzxbmeqm8t2hh45htehef2gerg3523");
	}

	@Test
	/** Test case: TM-RF-01-P1 - Serial Number
	 * Equivalence class or boundary value considered: (51)"wf4sdrf4q4wef134sdfbdzxbmeqm8t2hh45htehef2gerg3523f"
	 * Testing technique: Boundary Values Analysis
	 * Expected value: A exception thrown indication that the serial number is invalid.
	 */
	void testSerialNumber_BV_51() {
		String serialNumberPath = "src/resources/01/serialNumber_BV_51.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(serialNumberPath));
		assertEquals(e.getMessage(), ErrorMessage.serialNumberInvalidFormat);
	}

	@Test
	/** Test case: TM-RF-01-P1 - Serial Number
	 * Equivalence class or boundary value considered: (49)"wf4sdrf4q4wef134sdfbdzxbmeqm8t2hh45htehef2gerg352"
	 * Testing technique: Boundary Values Analysis
	 * Expected value: The serial number is valid and equal to the given one. No exception is thrown.
	 */
	void testSerialNumber_BV_49() throws TokenManagementException {
		String serialNumberPath = "src/resources/01/serialNumber_BV_49.json";
		assertTrue(tokenManager.TokenRequestGeneration(serialNumberPath)
				.getEmail() == "wf4sdrf4q4wef134sdfbdzxbmeqm8t2hh45htehef2gerg352");

	}

	@Test
	/** Test case: TM-RF-01-P1 - Serial Number
	 * Equivalence class or boundary value considered: (0)""
	 * Testing technique: Boundary Values Analysis
	 * Expected value: A exception thrown indication that the serial number is invalid.
	 */
	void testSerialNumber_BV_0() {
		String serialNumberPath = "src/resources/01/serialNumber_BV_0.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(serialNumberPath));
		assertEquals(e.getMessage(), ErrorMessage.serialNumberInvalidFormat);
	}

	@Test
	/** Test case: TM-RF-01-P1 - Serial Number
	 * Equivalence class or boundary value considered: (1)"a"
	 * Testing technique: Boundary Values Analysis
	 * Expected value: The serial number is valid and is equal to the given one. No exception is thrown.
	 */
	void testSerialNumber_BV_1() throws TokenManagementException {
		String serialNumberPath = "src/resources/01/serialNumber_BV_1.json";
		assertTrue(tokenManager.TokenRequestGeneration(serialNumberPath)
				.getEmail() == "a");
	}

	//Mac address

	@Test
	/** Test case: TM-RF-01-P1 - Mac Address
	 * Equivalence class or boundary value considered: 2F:3A:5B:9C:4A
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: No thrown exceptions and correct mac address value.
	 */
	void testMacAddress_EC_Valid() throws TokenManagementException {
		String macAddressPath = "src/resources/01/macAddress_EC_Valid.json";
		assertTrue(tokenManager.TokenRequestGeneration(macAddressPath)
				.getEmail() == "2F:3A:5B:9C:4A");
	}

	@Test
	/** Test case: TM-RF-01-P1 - Mac Address
	 * Equivalence class or boundary value considered: 2F:3A:5B:9C:4U
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: A exception thrown indication that the mac address is invalid.
	 */
	void testMacAddress_EC_Invalid() {
		String macAddressPath = "src/resources/01/macAddress_EC_Invalid.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(macAddressPath));
		assertEquals(e.getMessage(), ErrorMessage.macAddressInvalidFormat);
	}



	/**
	 *   .--.      .-'.      .--.      .--.      .--.      .--.      .`-.      .--.
	 * :::::.\::::::::.\::::::::TESTS FOR JSON FORMAT:::::::::.\::::::::.\::::::::.\
	 * '      `--'      `.-'      `--'      `--'      `--'      `-.'      `--'      `
	 */

	@Test
	/** Test case: TM-RF-01-I1 - Test extra json tag
	* Equivalence class or boundary value considered: extra tag
	* Testing technique: Equivalence Classes Analysis
	* Expected value: An exception thrown with a the specified message for detecting an extra tag
	*/
	void testExtraJsonTag() {

		String extraTagPath = "src/resources/01/extraTag.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(extraTagPath));
		assertEquals(e.getMessage(), ErrorMessage.jsonExtraTagError);

	}

	@Test
	/** Test case: TM-RF-01-O2 - Test empty file
	 * Equivalence class or boundary value considered: empty file
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: An exception thrown with a the specified message for detecting an empty file
	 */
	void testEmptyFile() {
		String emptyFilePath = "src/resources/01/emptyFile.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(emptyFilePath));
		assertEquals(e.getMessage(), ErrorMessage.emptyFileError);
	}

	@Test
	/** Test case: TM-RF-01-O2 - Test empty json
	 * Equivalence class or boundary value considered: empty json
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: An exception thrown with a the specified message for detecting an empty json
	 * Note: This is different than an empty file: in this case, the file has an empty json structure.
	 * It should return the same error as the previous test.
	 */
	void testEmptyJson() {
		String emptyJsonPath = "src/resources/01/emptyJson.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(emptyJsonPath));
		assertEquals(e.getMessage(), ErrorMessage.emptyFileError);
	}

	@Test
	/** Test case: TM-RF-01-O2 - Test malformed json
	 * Equivalence class or boundary value considered: malformed json
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: An exception thrown with a the specified message for detecting a malformed json
	 */
	void testMalformedJsonSyntax() {

		String malformedJsonPath = "src/resources/01/malformedJson.json";
	 	TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(malformedJsonPath));
	 	assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}

	@Test
	/** Test case: TM-RF-01-O2 - Test wrong file path
	 * Equivalence class or boundary value considered: wrong file path
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: An exception thrown with a the specified message for a invalid path
	 */
	void testWrongFilePath() {
		String wrongFilePath = "src/resources/01/doesNotExist.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(wrongFilePath));
		assertEquals(e.getMessage(), ErrorMessage.inputFileNotFoundError);
	}

	@Test
	/** Test case: TM-RF-01-O2 - Test missing json tag
	 * Equivalence class or boundary value considered: missing tag case
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: An exception thrown with a the specified message for missing tag error
	 */
	void testMissingTag(){

		String missingDeviceNamePath = "src/resources/01/missingDeviceName.json";
		TokenManagementException e1 =  assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingDeviceNamePath));
		assertEquals(e1.getMessage(), ErrorMessage.jsonExtraTagError);

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

	@Test
	/** Test case: TM-RF-01-P2 - Test md5 hash is correctly calculated
	 * Equivalence class or boundary value considered: missing tag case
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: An exception thrown with a the specified message for missing tag error
	 */
	void testMD5() throws TokenManagementException {

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












































	/**
	 *   .--.      .-'.      .--.      .--.      .--.      .--.      .`-.      .--.
	 * :::::.\::::::::.\:::::::::::HELPER FUNCTIONS:::::::::::.\::::::::.\::::::::.\
	 * '      `--'      `.-'      `--'      `--'      `--'      `-.'      `--'      `
	 */


	/**
	 * This function generates the SHA-256 hash
	 * @param input the string to hash
	 * @return the hash of the input string
	 * @throws TokenManagementException when the hash algorithm is not found
	 */
	private static String hashSHA256(String input) throws TokenManagementException {
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException(ErrorMessage.sha256AlgorithmNotFound);
		}
		
		
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x" 
		String result = String.format("%64x", new BigInteger(1, digest));
		
		return result;
		
	}


	/**
	 * Helper function to handle json reading.
	 * @param path path of the json file to read.
	 * @return a JsonObject ready to get fields from.
	 */
	private static JsonObject readJSON(String path) {

		String fileContents = "";
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			fail(ErrorMessage.inputFileNotFoundError);
		}

		String line;

		try {
			while ((line = reader.readLine()) != null) {
				fileContents += line;
			}
		} catch (IOException e) {
			fail(ErrorMessage.readFileError);
		}

		try {
			reader.close();
		} catch (IOException e) {
			fail(ErrorMessage.closeFileError);
		}

		JsonObject jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();

		return jsonLicense;
	}
	


}
