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

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test for simple App.
 */

public class AppTest_1 {

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
	 * :::::.\::::::::.\::::::::TESTS FOR JSON TAGS:::::::::::.\::::::::.\::::::::.\
	 * '      `--'      `.-'      `--'      `--'      `--'      `-.'      `--'      `
	 */

	//Device name

	@Test
	/** Test case: TM-RF-01-P1 - Device Name
	 * Equivalence class or boundary value considered: (21) -> "LaCeramicaDeTalaveraN"
	 * Testing technique: Boundary Values Analysis
	 * Expected value: Exception thrown stating that the Device Name date is not in the format.
	 */
	public void testDeviceName_BV21_01(){
		String path = "src/resources/01/deviceName_BV21_01.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
		assertEquals(e.getMessage(), ErrorMessage.deviceNameInvalidFormat);
	}

	@Test
	/** Test case: TM-RF-01-P1 - Device Name
	 * Equivalence class or boundary value considered: (20) -> "EsElVecinoElQueElige"
	 * Testing technique: Boundary Values Analysis
	 * Expected value: No exception thrown and value matches
	 */
	public void testDeviceName_BV20_01() throws TokenManagementException {
		String path = "src/resources/01/deviceName_BV20_01.json";
		assertTrue(tokenManager.TokenRequestGeneration(path)
				.getDeviceName().equals("EsElVecinoElQueElige"));
	}


	@Test
	/** Test case: TM-RF-01-P1 - Device Name
	 * Equivalence class or boundary value considered: (19) -> "AlAlcaldeYEsElAlcal"
	 * Testing technique: Boundary Values Analysis
	 * Expected value: No exception is thrown and the value mathces
	 */
	public void testDeviceName_BV19_01() throws TokenManagementException {
		String path = "src/resources/01/deviceName_BV19_01.json";
		assertTrue(tokenManager.TokenRequestGeneration(path)
				.getDeviceName().equals("AlAlcaldeYEsElAlcal"));
	}

	@Test
	/** Test case: TM-RF-01-P1 - Device Name
	 * Equivalence class or boundary value considered: (0) -> ""
	 * Testing technique: Boundary Values Analysis
	 * Expected value: Exception thrown stating that the Device Name date is not in the format.
	 */
	public void testDeviceName_BV0_01(){
		String path = "src/resources/01/deviceName_BV0_01.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
		assertEquals(e.getMessage(), ErrorMessage.deviceNameInvalidFormat);
	}

	@Test
	/** Test case: TM-RF-01-P1 - Device Name
	 * Equivalence class or boundary value considered: (1) -> "a"
	 * Testing technique: Boundary Values Analysis
	 * Expected value: The value is equal to "a". No exception thrown
	 */
	public void testDeviceName_BV1_01() throws TokenManagementException {
		String path = "src/resources/01/deviceName_BV1_01.json";
		assertTrue(tokenManager.TokenRequestGeneration(path)
				.getDeviceName().equals("a"));

	}

	//Type of device

	@Test
	/** Test case: TM-RF-01-P1 - Type of Device
	 * Equivalence class or boundary value considered: Sensor
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: The result equals sensor
	 */
	void testTypeOfDevice_EC_Sensor_01() throws TokenManagementException {
		String path = "src/resources/01/typeOfDevice_EC_Sensor_01.json";
		assertTrue(tokenManager.TokenRequestGeneration(path).getTypeDevice()
				.equals("Sensor"));
	}

	@Test
	/** Test case: TM-RF-01-P1 - Type of Device
	 * Equivalence class or boundary value considered: Actuator
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: The result equals sensor
	 */
	void testTypeOfDevice_EC_Actuator_01() throws TokenManagementException {
		String path = "src/resources/01/typeOfDevice_EC_Actuator_01.json";
		assertTrue(tokenManager.TokenRequestGeneration(path).getTypeDevice()
				.equals("Actuator"));
	}

	@Test
	/** Test case: TM-RF-01-P1 - Type of Device
	 * Equivalence class or boundary value considered: empty value
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: A exception thrown indication that the type of device is invalid.
	 */
	void testTypeOfDevice_EC_Empty_01() {
		String path = "src/resources/01/typeOfDevice_EC_Empty_01.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
		assertEquals(e.getMessage(), ErrorMessage.typeDeviceInvalidFormat);
	}

	@Test
	/** Test case: TM-RF-01-P1 - Type of Device
	 * Equivalence class or boundary value considered: Actuators
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: A exception thrown indication that the type of device is invalid.
	 */
	void testTypeOfDevice_EC_Actuators_01() {
		String path = "src/resources/01/typeOfDevice_EC_Actuators_01.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
		assertEquals(e.getMessage(), ErrorMessage.typeDeviceInvalidFormat);
	}

	//Driver version

	@Test
	/** Test case: TM-RF-01-P1 - Driver Version
	 * Equivalence class or boundary value considered: 123.123.123.123.456.456.456.0
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: No thrown exceptions and correct Driver version value.
	 */
	void testDriverVersion_EC_Valid_01() throws TokenManagementException {
		String path = "src/resources/01/driverVersion_EC_Valid_01.json";
		assertTrue(tokenManager.TokenRequestGeneration(path)
				.getDriverVersion().equals("123.123.123.123.456.456.456.0"));
	}

	@Test
	/** Test case: TM-RF-01-P1 - Driver Version
	 * Equivalence class or boundary value considered: 32123.2342..234
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: A exception thrown indication that the Driver version is invalid.
	 */
	void testDriverVersion_EC_Invalid_01() {
		String path = "src/resources/01/driverVersion_EC_Invalid_01.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
		assertEquals(e.getMessage(), ErrorMessage.driverVersionInvalidFormat);
	}

	//Support email

	@Test
	/** Test case: TM-RF-01-P1 - Support Email
	 * Equivalence class or boundary value considered: autonomous@vehicle.com
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: No thrown exceptions and correct email value.
	 */
	void testEmail_EC_Valid_01() throws TokenManagementException {
		String path = "src/resources/01/email_EC_Valid_01.json";
		assertTrue(tokenManager.TokenRequestGeneration(path)
				.getEmail().equals("autonomous@vehicle.com"));
	}

	@Test
	/** Test case: TM-RF-01-P1 - Support Email
	 * Equivalence class or boundary value considered: autonomous@vehicle..com
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: A exception thrown indication that the email is invalid.
	 */
	void testEmail_EC_Invalid_01() {
		String path = "src/resources/01/email_EC_Invalid_01.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
		assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);
	}

	//Serial number

	@Test
	/** Test case: TM-RF-01-P1 - Serial Number
	 * Equivalence class or boundary value considered: serq2efwqr234
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: No thrown exceptions and correct serial number value.
	 */
	void testSerialNumber_EC_Valid_01() throws TokenManagementException {
		String path = "src/resources/01/serialNumber_EC_Valid_01.json";
		assertTrue(tokenManager.TokenRequestGeneration(path)
				.getSerialNumber().equals("serq2efwqr234"));
	}

	@Test
	/** Test case: TM-RF-01-P1 - Serial Number
	 * Equivalence class or boundary value considered: 234rerfqsf qw3rr2
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: A exception thrown indication that the serial number is invalid.
	 */
	void testSerialNumber_EC_Invalid_01() {
		String path = "src/resources/01/serialNumber_EC_Invalid_01.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
		assertEquals(e.getMessage(), ErrorMessage.serialNumberInvalidFormat);
	}

	@Test
	/** Test case: TM-RF-01-P1 - Serial Number
	 * Equivalence class or boundary value considered: (50) -> "wf4sdrf4q4wef134sdfbdzxbmeqm8t2hh45htehef2gerg3523"
	 * Testing technique: Boundary Values Analysis
	 * Expected value: No thrown exceptions and correct serial number value.
	 */
	void testSerialNumber_BV_50_01() throws TokenManagementException {
		String path = "src/resources/01/serialNumber_BV_50_01.json";
		assertTrue(tokenManager.TokenRequestGeneration(path)
				.getSerialNumber().equals("wf4sdrf4q4wef134sdfbdzxbmeqm8t2hh45htehef2gerg3523"));
	}

	@Test
	/** Test case: TM-RF-01-P1 - Serial Number
	 * Equivalence class or boundary value considered: (51) -> "wf4sdrf4q4wef134sdfbdzxbmeqm8t2hh45htehef2gerg3523f"
	 * Testing technique: Boundary Values Analysis
	 * Expected value: A exception thrown indication that the serial number is invalid.
	 */
	void testSerialNumber_BV_51_01() {
		String path = "src/resources/01/serialNumber_BV_51_01.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
		assertEquals(e.getMessage(), ErrorMessage.serialNumberInvalidFormat);
	}

	@Test
	/** Test case: TM-RF-01-P1 - Serial Number
	 * Equivalence class or boundary value considered: (49) -> "wf4sdrf4q4wef134sdfbdzxbmeqm8t2hh45htehef2gerg352"
	 * Testing technique: Boundary Values Analysis
	 * Expected value: The serial number is valid and equal to the given one. No exception is thrown.
	 */
	void testSerialNumber_BV_49_01() throws TokenManagementException {
		String path = "src/resources/01/serialNumber_BV_49_01.json";
		assertTrue(tokenManager.TokenRequestGeneration(path)
				.getSerialNumber().equals("wf4sdrf4q4wef134sdfbdzxbmeqm8t2hh45htehef2gerg352"));

	}

	@Test
	/** Test case: TM-RF-01-P1 - Serial Number
	 * Equivalence class or boundary value considered: (0) -> ""
	 * Testing technique: Boundary Values Analysis
	 * Expected value: A exception thrown indication that the serial number is invalid.
	 */
	void testSerialNumber_BV_0_01() {
		String path = "src/resources/01/serialNumber_BV_0_01.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
		assertEquals(e.getMessage(), ErrorMessage.serialNumberInvalidFormat);
	}

	@Test
	/** Test case: TM-RF-01-P1 - Serial Number
	 * Equivalence class or boundary value considered: (1) -> "a"
	 * Testing technique: Boundary Values Analysis
	 * Expected value: The serial number is valid and is equal to the given one. No exception is thrown.
	 */
	void testSerialNumber_BV_1_01() throws TokenManagementException {
		String path = "src/resources/01/serialNumber_BV_1_01.json";
		assertTrue(tokenManager.TokenRequestGeneration(path)
				.getSerialNumber().equals("a"));

	}


	//Mac address

	@Test
	/** Test case: TM-RF-01-P1 - Mac Address
	 * Equivalence class or boundary value considered: 3D:F2:C9:A6:B3:4F
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: No thrown exceptions and correct mac address value.
	 */
	void testMacAddress_EC_Valid_01() throws TokenManagementException {
		String path = "src/resources/01/macAddress_EC_Valid_01.json";
		assertTrue(tokenManager.TokenRequestGeneration(path)
				.getMacAddress().equals("3D:F2:C9:A6:B3:4F"));
	}

	@Test
	/** Test case: TM-RF-01-P1 - Mac Address
	 * Equivalence class or boundary value considered: 3D:F2:C9:A6:B3:4U
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: A exception thrown indication that the mac address is invalid.
	 */
	void testMacAddress_EC_Invalid_01() {
		String path = "src/resources/01/macAddress_EC_Invalid_01.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
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
	void testExtraJsonTag_01() {

		String path = "src/resources/01/extraTag_01.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
		assertEquals(e.getMessage(), ErrorMessage.jsonExtraTagError);

	}

	@Test
	/** Test case: TM-RF-01-O2 - Test empty file
	 * Equivalence class or boundary value considered: empty file
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: An exception thrown with a the specified message for detecting an empty file
	 */
	void testEmptyFile_01() {
		String path = "src/resources/01/emptyFile_01.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
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
	void testEmptyJson_01() {
		String path = "src/resources/01/emptyJson_01.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
		assertEquals(e.getMessage(), ErrorMessage.emptyFileError);
	}

	@Test
	/** Test case: TM-RF-01-O2 - Test malformed json
	 * Equivalence class or boundary value considered: malformed json
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: An exception thrown with a the specified message for detecting a malformed json
	 */
	void testMalformedJsonSyntax_01() {

		String path = "src/resources/01/malformedJson_01.json";
	 	TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
	 	assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}

	@Test
	/** Test case: TM-RF-01-O2 - Test wrong file path
	 * Equivalence class or boundary value considered: wrong file path
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: An exception thrown with a the specified message for a invalid path
	 */
	void testWrongFilePath_01() {
		String path = "src/resources/01/doesNotExist_01.json";
		TokenManagementException e = assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
		assertEquals(e.getMessage(), ErrorMessage.inputFileNotFoundError);
	}

	@Test
	/** Test case: TM-RF-01-O2 - Test missing json tag
	 * Equivalence class or boundary value considered: missing tag case
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: An exception thrown with a the specified message for missing tag error
	 */
	void testMissingTag_01(){

		String path = "src/resources/01/missingTag_01.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(path));
		assertEquals(e.getMessage(), ErrorMessage.jsonTagMismatchError);

	}

	@Test
	/** Test case: TM-RF-01-P2 - Test md5 hash is correctly calculated
	 * Equivalence class or boundary value considered: missing tag case
	 * Testing technique: Equivalence Classes Analysis
	 * Expected value: An exception thrown with a the specified message for missing tag error
	 */
	void testMD5_01() throws TokenManagementException {

		String path = "src/resources/01/CP-RF1-01_01.json";
		request = tokenManager.TokenRequestGeneration(path);

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
		String hex = String.format("%032x", new BigInteger(1, digest));

		token = request.getHash();

		System.out.println(token + " " + hex);

		assertEquals(hex, token);

		//Check the md5 is a valid string
		assertTrue(token.matches("([A-F0-9]{32})|([a-f0-9]{32})"));

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
	 * @return a JsonObject ready to get tags from it.
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
