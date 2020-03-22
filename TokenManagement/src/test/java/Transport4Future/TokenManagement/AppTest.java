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
	public static void partOneInitialization() {
		correctFilePath = "src/resources/CP-RF1-01.json";
		json = readJSON(correctFilePath);
		tokenManager = new TokenManager();

		// this assertion gives no throws, so we commented it
		//Assertions.assertThrows(TokenManagementException.class, () -> tm.readTokenRequestFromJSON(filePath));
		assertNotNull(correctFilePath);
	}

	@Test
	@Order(1)
	public void checkFailOnBadJsonTag() {
		
		//Check error on extra tag
		String extraTagPath = "src/resources/extraTag.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.readTokenRequestFromJSON(extraTagPath));
		
		
		//Check error on missing tags
		
		String missingDeviceNamePath = "src/resources/missingDeviceName.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.readTokenRequestFromJSON(missingDeviceNamePath));
		
		String missingTypeOfDevicePath = "src/resources/missingTypeOfDevice.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.readTokenRequestFromJSON(missingTypeOfDevicePath));
		
		String missingDriverVersionPath = "src/resources/missingDriverVersion.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.readTokenRequestFromJSON(missingDriverVersionPath));
		
		String missingSerialNumberPath = "src/resources/missingSerialNumber.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.readTokenRequestFromJSON(missingSerialNumberPath));
		
		String missingEmailPath = "src/resources/missingEmail.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.readTokenRequestFromJSON(missingEmailPath));
		
		String missingMacAddressPath = "src/resources/missingMacAddress.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.readTokenRequestFromJSON(missingMacAddressPath));

	}
	
	
	public void checkFailOnBadJsonSyntax() {

		String badSyntaxPath = "src/resources/badSyntax.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.readTokenRequestFromJSON(badSyntaxPath));

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
		Assertions.assertEquals(request.getEmail(), json.getString("Support e-mail"));


	}
	
	//Test bad regex case
	
	@Test
	@Order(2)
	public void checkFailOnBadDataRegex() {
		
		String badDeviceNamePath = "src/resources/badDeviceName.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.readTokenRequestFromJSON(badDeviceNamePath));
		
		String badTypeOfDevicePath = "src/resources/badTypeOfDevice.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.readTokenRequestFromJSON(badTypeOfDevicePath));
		
		String badDriverVersionPath = "src/resources/badDriverVersion.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.readTokenRequestFromJSON(badDriverVersionPath));
		
		String badSerialNumberPath = "src/resources/badSerialNumber.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.readTokenRequestFromJSON(badSerialNumberPath));
		
		String badEmailPath = "src/resources/badEmail.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.readTokenRequestFromJSON(badEmailPath));
		
		String badMacAddressPath = "src/resources/badMacAddress.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.readTokenRequestFromJSON(badMacAddressPath));
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
		
		//Check the md5 is a valid string
		Assertions.assertTrue(token.matches("([A-F0-9]{32})|([a-f0-9]{32})"));

		
	}



	
	@Test
	@Order(5)
	public void testFailOnWrongDataPath() {
		String wrongFilePath = "src/resources/doesnotexist.json";
		Assertions.assertThrows(TokenManagementException.class, () -> tokenManager.readTokenRequestFromJSON(wrongFilePath));
	}
	
	@Test
	@Order(6)
	public void testFailOnEmptyJson() {
		String emptyFilePath = "src/resources/empty.json";
		Assertions.assertThrows(TokenManagementException.class, () -> tokenManager.readTokenRequestFromJSON(emptyFilePath));
	}
	
	
//	@Test
//	@Order(7)
//	public void testInternalError() {
//		String internalErrorFilePath = "src/resources/internal_error.json";
//		Assertions.assertThrows(TokenManagementException.class, () -> tokenManager.readTokenRequestFromJSON(internalErrorFilePath));
//	}
	
	
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
