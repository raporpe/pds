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
	 * :::::.\::::::::.\:::::::::\::::PART 2 TESTS:::\::::::::.\::::::::.\::::::::.\
	 * '      `--'      `.-'      `--'      `--'      `--'      `-.'      `--'      `
	 */

	//Standard
	/** Test case: TM-RF-02-<id> - <Name>
	 * Derivation Tree Node: <Value/s>
	 * Type of case: <Regular Value | Omission | Repetition | Modification>
	 * Testing technique: Syntax Analysis
	 * Expected value: <Description>
	 */

	@Test
	/** Test case: TM-RF-02-I1 - Test many days in date part
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that an invalid date was detected.
	 */
	void testDateManyDayCharacters_02(){

		String path = "src/resources/02/dateManyDayCharacters_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test many hours in date part
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that an invalid date was detected.
	 */
	void testDateManyHourCharacters_02(){

		String path = "src/resources/02/dateManyHourCharacters_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test many minutes in date part
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that an invalid date was detected.
	 */
	void testDateManyMinuteCharacters_02(){

		String path = "src/resources/02/dateManyMinuteCharacters_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test many month numbers in date part
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that an invalid date was detected.
	 */
	void testDateManyMonthCharacters_02(){

		String path = "src/resources/02/dateManyMonthCharacters_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test many second numbers in date part
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that an invalid date was detected.
	 */
	void testDateManySecondCharacters_02(){

		String path = "src/resources/02/dateManySecondCharacters_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test Missing days in date part
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that an invalid date was detected.
	 */
	void testDateMissingDayCharacters_02(){

		String path = "src/resources/02/dateMissingDayCharacters_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test Missing hours in date part
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that an invalid date was detected.
	 */
	void testDateMissingHourCharacters_02(){

		String path = "src/resources/02/dateMissingHourCharacters_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test Missing minutes in date part
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that an invalid date was detected.
	 */
	void testDateMissingMinuteCharacters_02(){

		String path = "src/resources/02/dateMissingMinuteCharacters_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test Missing month numbers in date part
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that an invalid date was detected.
	 */
	void testDateMissingMonthCharacters_02(){

		String path = "src/resources/02/dateMissingMonthCharacters_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test Missing second numbers in date part
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that an invalid date was detected.
	 */
	void testDateMissingSecondCharacters_02(){

		String path = "src/resources/02/dateMissingSecondCharacters_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test json that has no tags on it
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that no information in in the file.
	 */
	void testEmptyJSON_02(){

		String path = "src/resources/02/emptyFILE_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.emptyFileError);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a empty file
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that no information in in the file.
	 */
	void testEmptyFile_02(){

		String path = "src/resources/02/emptyFILE_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.emptyFileError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a date with invalid days
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the date is not in a valid format.
	 */
	void testErrorDateInvalidDay_02(){

		String path = "src/resources/02/errorDateInvalidDay_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a date with missing day bar
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the date is not in a valid format.
	 */
	void testErrorDateInvalidDayBar_02(){

		String path = "src/resources/02/errorDateInvalidDayBar_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a date with an invalid hour value
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the date is not in a valid format.
	 */
	void testErrorDateInvalidHour_02(){

		String path = "src/resources/02/errorDateInvalidHour_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a date with an invalid minute value
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the date is not in a valid format.
	 */
	void testErrorDateInvalidMinute_02(){

		String path = "src/resources/02/errorDateInvalidMinute_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a date with an invalid month value
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the date is not in a valid format.
	 */
	void testErrorDateInvalidMonth_02(){

		String path = "src/resources/02/errorDateInvalidMonth_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a date with an invalid second value
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the date is not in a valid format.
	 */
	void testErrorDateInvalidSecond_02(){

		String path = "src/resources/02/errorDateInvalidSecond_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test email with an invalid domain value
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the email is not in a valid format.
	 */
	void testErrorIncorrectWrittenDomain_02(){

		String path = "src/resources/02/errorIncorrectWrittenDomain_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test email with an invalid first part before @ value
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the email is not in a valid format.
	 */
	void testErrorIncorrectWrittenEmail_02(){

		String path = "src/resources/02/errorIncorrectWrittenEmail_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a badly written token.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the token is not in a valid format.
	 */
	void testErrorIncorrectWrittenToken_02(){

		String path = "src/resources/02/errorIncorrectWrittenToken_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidTokenRequest);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a token request with missing characters.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the token is not in a valid format.
	 */
	void testErrorMissingTokenCharacters_02(){

		String path = "src/resources/02/errorMissingTokenCharacters_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidTokenRequest);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a email with a too large domain.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the email is not in a valid format.
	 */
	void testErrorTooLargeEmail_02(){

		String path = "src/resources/02/errorTooLargeEmail_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a token with a too many characters.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the token is not in a valid format.
	 */
	void testErrorTooLargeToken_02(){

		String path = "src/resources/02/errorTooLargeToken_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidTokenRequest);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a email with a repeated '@' symbol.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the email is not in a valid format.
	 */
	void testExtraAt_02(){

		String path = "src/resources/02/extra@_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a json with a extra comma in a random tag.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is not in a valid format.
	 */
	void testExtraComma_02(){

		String path = "src/resources/02/extraComma_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a date with a extra day separator.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the date is not in a valid format.
	 */
	void testExtraDayBar_02(){

		String path = "src/resources/02/extraDayBar_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json with a extra ':' separator.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is not in a valid format.
	 */
	void testExtraEqual_02(){

		String path = "src/resources/02/extraEqual_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json with a extra '{' at the beginning.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is not in a valid format.
	 */
	void testExtraFirstBrace_02(){

		String path = "src/resources/02/extraFirstBrace_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a date with a extra ':' in the date between the hours and minutes part.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the date is not in a valid format.
	 */
	void testExtraHourEqual_02(){

		String path = "src/resources/02/extraHourEqual_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json with an extra '}' at the end.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is not in a valid format.
	 */
	void testExtraLastBrace_02(){

		String path = "src/resources/02/extraLastBrace_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a json where a tag text is duplicated.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is not in a valid format.
	 */
	void testDuplicatedInnerTagName_02(){

		String path = "src/resources/02/duplicatedInnerTagName_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonTagMismatchError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json where left quotations are duplicated.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is not in a valid format.
	 */
	void testExtraTagLeftQuotes_02(){

		String path = "src/resources/02/extraTagLeftQuotes_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json where right quotations are duplicated.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is not in a valid format.
	 */
	void testExtraTagRightQuotes_02(){

		String path = "src/resources/02/extraTagRightQuotes_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a json where a tag right value is duplicated.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is not in a valid format.
	 */
	void testExtraValue_02(){

		String path = "src/resources/02/extraValue_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidTokenRequest);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a json where a tag right value has double quotations on the left.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is not in a valid format.
	 */
	void testExtraValueLeftQuotes_02(){

		String path = "src/resources/02/extraValueLeftQuotes_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a json where a tag right value has double quotations on the right.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is not in a valid format.
	 */
	void testExtraValueRightQuotes_02(){

		String path = "src/resources/02/extraValueRightQuotes_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}

	//amorcito
	@Test
	/** Test case: TM-RF-02-I1 - Test a json where a tag right value has double quotations on the right.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is not in a valid format.
	 */
	void testHasComma_02(){

		String path = "src/resources/02/hasComma_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a json where a tag right value has double quotations on the right.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is not in a valid format.
	 */
	void testHasEqual_02(){

		String path = "src/resources/02/hasEqual_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a json where a tag right value has double quotations on the right.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is not in a valid format.
	 */
	void testHasExtraIncorrectComma_02(){

		String path = "src/resources/02/hasExtraIncorrectComma_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}

	//hasta aqui amorcito

	@Test
	/** Test case: TM-RF-02-I1 - Test a date where there is a '.' instead of '/' as a separator.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the date in not in a valid format.
	 */
	void testIncorrectDateDayBar_02(){

		String path = "src/resources/02/incorrectDateDayBar_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a date where there is a '.' instead of ':' as a separator for the hour.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the date in not in a valid format.
	 */
	void testIncorrectDateHourEqual_02(){

		String path = "src/resources/02/incorrectDateHourEqual_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a json where the date tag is incorrectly written.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json tags do not match.
	 */
	void testIncorrectDateTag_02(){

		String path = "src/resources/02/incorrectDateTag_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonTagMismatchError);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a email where the domain separation point is duplicated.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Repetition
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the email is not in the valid format.
	 */
	void testIncorrectDot_02(){

		String path = "src/resources/02/incorrectDot_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a json where the email tag name wrongly written.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating a json tag mismatch.
	 */
	void testIncorrectEmailTag_02(){

		String path = "src/resources/02/incorrectEmailTag_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonTagMismatchError);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a email where the domain part has an invalid char.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the email is not in the valid format.
	 */
	void incorrectExtension_02(){

		String path = "src/resources/02/incorrectExtension_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json where the first brace is '\' instead of '{'.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the json is not in the valid format.
	 */
	void testIncorrectFirstBrace_02(){

		String path = "src/resources/02/incorrectFirstBrace_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json where the first brace is '/' instead of '}'.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the json is not in the valid format.
	 */
	void testIncorrectLastBrace_02(){

		String path = "src/resources/02/incorrectLastBrace_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a json where the first tag left part left quotes are '/' instead of '"'.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the json is not in the valid format.
	 */
	void testIncorrectTagLeftQuotes_02(){

		String path = "src/resources/02/incorrectTagLeftQuotes_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json where the first tag name is not valid.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating there is a json tag mismatch.
	 */
	void testIncorrectTokenTag_02(){

		String path = "src/resources/02/incorrectTokenTag_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonTagMismatchError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json where the value left quotes are replaced by another symbol.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Modification
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the json is invalid.
	 */
	void testIncorrectValueLeftQuotes_02(){

		String path = "src/resources/02/incorrectValueLeftQuotes_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a email with missing '@'.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the email is invalid.
	 */
	void noAt_02(){

		String path = "src/resources/02/no@_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json with missing comma.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the json is invalid.
	 */
	void testNoComma_02(){

		String path = "src/resources/02/noComma_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a date with missing day.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the date is invalid.
	 */
	void testNoDay_02(){

		String path = "src/resources/02/noDay_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a email with missing domain part.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the email is invalid.
	 */
	void testNoDomain_02(){

		String path = "src/resources/02/noDomain_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a email with missing body part.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the email is invalid.
	 */
	void testNoEmailBody_02(){

		String path = "src/resources/02/noEmailBody_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a email with missing '.' separating domain from tld.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the email is invalid.
	 */
	void testNoEmailDot_02(){

		String path = "src/resources/02/noEmailDot_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json with missing ending quotes in the right part of the tag.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the json is invalid.
	 */
	void testNoEndingTagRightQuotes_02(){

		String path = "src/resources/02/noEndingTagRightQuotes_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json with missing ':' symbol separator.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the json is invalid.
	 */
	void testNoEqual_02(){

		String path = "src/resources/02/noEqual_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a email with missing top level domain part.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the email is invalid.
	 */
	void testNoExtension_02(){

		String path = "src/resources/02/noExtension_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.emailInvalidFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json with missing starting brace.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the json is invalid.
	 */
	void testNoFirstBrace_02(){

		String path = "src/resources/02/noFirstBrace_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a date with missing hour.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the date is invalid.
	 */
	void testNoHour_02(){

		String path = "src/resources/02/noHour_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json with missing last brace.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the json is invalid.
	 */
	void testNoLastBrace_02(){

		String path = "src/resources/02/noLastBrace_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a date with missing minutes part.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the date is invalid.
	 */
	void testNoMinute_02(){

		String path = "src/resources/02/noMinute_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a date with missing month part.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the date is invalid.
	 */
	void testNoMonth_02(){

		String path = "src/resources/02/noMonth_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a date with missing second part.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating the date is invalid.
	 */
	void testNoSecond_02(){

		String path = "src/resources/02/noSecond_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidDateFormat);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json with missing tag text content.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating a json tag mismatch
	 */
	void testNoTag_02(){

		String path = "src/resources/02/noTag_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonTagMismatchError);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a json with missing left tag quotes.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is invalid
	 */
	void testNoTagLeftQuotes_02(){

		String path = "src/resources/02/noTagLeftQuotes_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a json whose Token Request value that is empty.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the token request is invalid
	 */
	void testNoValue_02(){

		String path = "src/resources/02/noValue_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.invalidTokenRequest);

	}

	@Test
	/** Test case: TM-RF-02-I1 - Test a json with missing value left quotes in Token Request.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is invalid
	 */
	void testNoValueLeftQuotes_02(){

		String path = "src/resources/02/noValueLeftQuotes_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a json with missing value right quotes in Token Request.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the json is invalid
	 */
	void testNoValueRightQuotes_02(){

		String path = "src/resources/02/noValueRightQuotes_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a date with missing year.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Omission
	 * Testing technique: Syntax Analysis
	 * Expected value: Exception thrown stating that the date is invalid
	 */
	void testNoYear_02(){

		String path = "src/resources/02/noYear_02.json";
		TokenManagementException e =  assertThrows(TokenManagementException.class,
				() -> tokenManager.RequestToken(path));

		assertEquals(e.getMessage(), ErrorMessage.jsonParsingError);

	}


	@Test
	/** Test case: TM-RF-02-I1 - Test a normal case.
	 * Derivation Tree Node: <Value/s>
	 * Type of case: Regular Value
	 * Testing technique: Syntax Analysis
	 * Expected value: The operation work without exceptions.
	 */
	void validCase_02(){

		String path = "src/resources/02/validCase_02.json";
		String result;
		try{
			result = tokenManager.RequestToken(path);
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}

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
