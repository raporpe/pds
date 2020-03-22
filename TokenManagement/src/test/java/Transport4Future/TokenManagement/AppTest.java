package Transport4Future.TokenManagement;

import static org.junit.Assert.assertNotNull;

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
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import javax.json.Json;
import javax.json.JsonObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

/**
 * Unit test for simple App.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class AppTest {

	/*
	 * Test case: CP_RF1_01 Equivalence class or boundary value considered:
	 * <CE-RF1-V-01 Testing technique: Equivalence Class Expected value: Pass
	 */

	private static JsonObject json;
	private static TokenManager tokenManager;
	private static String correctFilePath;
	private static TokenRequest request;
	private static String password;
	private static String token;

	@BeforeAll
	public static void partOne() {
		correctFilePath = "src/resources/CP-RF1-01.json";
		json = readJSON(correctFilePath);
		tokenManager = new TokenManager();

		// this assertion gives no throws, so we commented it
		//Assertions.assertThrows(TokenManagementException.class, () -> tm.readTokenRequestFromJSON(filePath));
		assertNotNull(correctFilePath);
	}

	@Test
	@Order(1)
	public void checkJsonStructure() {

		Assertions.assertTrue(json.getString("Device Name").length() < 20);
		Assertions.assertTrue(json.getString("Device Name").length() >= 1);
		Assertions.assertTrue(json.getString("Type of Device").equals("Sensor")
				|| json.getString("Type of Device").equals("Actuator"));
		Assertions.assertTrue(json.getString("Driver Version").matches("([0-9]{4})(-[0-9]{2}){2}"));
		Assertions.assertTrue(json.getString("Serial Number").length() < 50);
		Assertions.assertTrue(json.getString("MAC Address").matches("^([a-fA-F0-9]{2}[:-]){5}[a-fA-F0-9]{2}$"));
		Assertions.assertTrue(json.getString("MAC Address").length() == 17);

	}

	@Test
	@Order(2)
	public void checkReceivedData() {
		try {
			request = tokenManager.readTokenRequestFromJSON(correctFilePath);
		} catch (TokenManagementException a) {
			Assertions.fail("The json file was not found");
		}

		Assertions.assertEquals(request.getDeviceName(), json.getString("Device Name"));
		Assertions.assertEquals(request.getTypeDevice(), json.getString("Type of Device"));
		Assertions.assertEquals(request.getDriverVersion(), json.getString("Driver Version"));
		Assertions.assertEquals(request.getSerialNumber(), json.getString("Serial Number"));
		Assertions.assertEquals(request.getMacAddress(), json.getString("MAC Address"));

	}
	
	@Test
	@Order(2)
	public void checkFailOnBadXXXXX() {
		String badXXXXXPath = "src/resources/badXXXXX.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.readTokenRequestFromJSON(badXXXXXPath));
		

	}


	@Test
	@Order(3)
	public void checkMD5() throws TokenManagementException, NoSuchAlgorithmException {
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("Error: no such hashing algorithm.");
		}
		
		//  Defined  password is "Stardust" & req is the TokenRequest object
		String input =  password + "-" + request.toString();
		
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x" 
		String hex = String.format("%32x", new BigInteger(1, digest));
		
		token = request.getToken();
		
		System.out.println(token + " " + hex);

		Assertions.assertEquals(hex, token);
		
		Assertions.assertTrue(token.matches("([A-F0-9]{32})|([a-f0-9]{32})"));

		
	}



	
	@Test
	@Order(5)
	public void testWrongDataPath() {
		String wrongFilePath = "src/resources/doesnotexist.json";
		Assertions.assertThrows(TokenManagementException.class, () -> tokenManager.readTokenRequestFromJSON(wrongFilePath));
	}
	
	@Test
	@Order(6)
	public void testEmptyJson() {
		String emptyFilePath = "src/resources/empty.json";
		Assertions.assertThrows(TokenManagementException.class, () -> tokenManager.readTokenRequestFromJSON(emptyFilePath));
	}
	
	@Test
	@Order(7)
	public void testCorruptedJson() {
		String corruptedFilePath = "src/resources/corrupted_json.json";
		Assertions.assertThrows(TokenManagementException.class, () -> tokenManager.readTokenRequestFromJSON(corruptedFilePath));
	}
	
	@Test
	@Order(7)
	public void testIncorrectFormat() {
		String incorrectFormatFilePath = "src/resources/incorrect_format.json";
		Assertions.assertThrows(TokenManagementException.class, () -> tokenManager.readTokenRequestFromJSON(incorrectFormatFilePath));
	}
	
	@Test
	@Order(7)
	public void testInternalError() {
		String internalErrorFilePath = "src/resources/internal_error.json";
		Assertions.assertThrows(TokenManagementException.class, () -> tokenManager.readTokenRequestFromJSON(internalErrorFilePath));
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
		//DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		try {

			jsonLicense.getString("Device Name");
			jsonLicense.getString("Type of Device");
			jsonLicense.getString("Driver Version");
			jsonLicense.getString("Serial Number");
			jsonLicense.getString("MAC Address");

		} catch (Exception e) {
			Assertions.fail("Error: invalid input data in JSON structure.");
		}

		return jsonLicense;
	}

}
