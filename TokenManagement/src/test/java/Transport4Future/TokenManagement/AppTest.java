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

public class AppTest {

	/*
	 * Test case: CP_RF1_01 Equivalence class or boundary value considered:
	 * <CE-RF1-V-01 Testing technique: Equivalence Class Expected value: Pass
	 */

	private static JsonObject json;
	private static TokenManager tokenManager;
	private static String deviceDataFilePath;
	private static String licenseFilePath;
	private static TokenRequest request;
	private static String password;
	private static String token;

	@BeforeAll
	public static void TM_RF_01() {
		deviceDataFilePath = "src/resources/CP-RF1-01.json";
		json = readJSON(deviceDataFilePath);
		tokenManager = new TokenManager();

		// this assertion gives no throws, so we commented it
		//Assertions.assertThrows(TokenManagementException.class, () -> tm.readTokenRequestFromJSON(filePath));
		assertNotNull(deviceDataFilePath);
	}

	@Test
	public void checkFailOnBadJsonTag_01() {
		
				
		//Check error on extra tag
		String extraTagPath = "src/resources/extraTag.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(extraTagPath));
		
		
		//Check error on missing tags
		
		String missingDeviceNamePath = "src/resources/missingDeviceName.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingDeviceNamePath));
		
		String missingTypeOfDevicePath = "src/resources/missingTypeOfDevice.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingTypeOfDevicePath));
		
		String missingDriverVersionPath = "src/resources/missingDriverVersion.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingDriverVersionPath));
		
		String missingSerialNumberPath = "src/resources/missingSerialNumber.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingSerialNumberPath));
		
		String missingEmailPath = "src/resources/missingEmail.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingEmailPath));
		
		String missingMacAddressPath = "src/resources/missingMacAddress.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(missingMacAddressPath));

	}
	
	@Test
	public void checkFailOnBadJsonSyntax_01() {

		String badSyntaxPath = "src/resources/badSyntax.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badSyntaxPath));

	}
	
	

	@Test
	public void checkReceivedData_01() {
		try {
			request = tokenManager.TokenRequestGeneration(deviceDataFilePath);
		} catch (TokenManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void checkFailOnBadDataRegex_01() {
		
		String badDeviceNamePath = "src/resources/badDeviceName.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badDeviceNamePath));
		
		String badTypeOfDevicePath = "src/resources/badTypeOfDevice.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badTypeOfDevicePath));
		
		String badDriverVersionPath = "src/resources/badDriverVersion.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badDriverVersionPath));
		
		String badSerialNumberPath = "src/resources/badSerialNumber.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badSerialNumberPath));
		
		String badEmailPath = "src/resources/badEmail.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badEmailPath));
		
		String badMacAddressPath = "src/resources/badMacAddress.json";
		Assertions.assertThrows(TokenManagementException.class,
				() -> tokenManager.TokenRequestGeneration(badMacAddressPath));
	}



	@Test
	public void checkMD5_01() throws TokenManagementException, NoSuchAlgorithmException {
		
		request = tokenManager.TokenRequestGeneration(deviceDataFilePath);
		
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
		
		token = request.getHash();
		
		System.out.println(token + " " + hex);

		Assertions.assertEquals(hex, token);
		
		//Check the md5 is a valid string
		Assertions.assertTrue(token.matches("([A-F0-9]{32})|([a-f0-9]{32})"));

		
	}



	
	@Test
	public void testFailOnWrongDataPath_01() {
		String wrongFilePath = "src/resources/doesnotexist.json";
		Assertions.assertThrows(TokenManagementException.class, () -> tokenManager.TokenRequestGeneration(wrongFilePath));
	}
	
	@Test
	public void testFailOnEmptyJson_01() {
		String emptyFilePath = "src/resources/empty.json";
		Assertions.assertThrows(TokenManagementException.class, () -> tokenManager.TokenRequestGeneration(emptyFilePath));
	}
	
	
//	@Test
//	public void testInternalError() {
//		String internalErrorFilePath = "src/resources/internal_error.json";
//		Assertions.assertThrows(TokenManagementException.class, () -> tokenManager.readTokenRequestFromJSON(internalErrorFilePath));
//	}
	

	
	@Test
	public void testJsonCorrectRead_02() throws TokenManagementException{
	
		JsonObject test = readJSON(licenseFilePath);
		
		
		String requestToken = test.getString("Token Request");
		String notificationEmail = test.getString("Notification e-mail");
		String resquestDate = test.getString("Request Date");
		
		
		String header = "SHA-256";
		String payload = requestToken + resquestDate + "";
		String noSignetureToken = header + payload;
		
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("Error: no such hashing algorithm.");
		}
		
		//  Defined  password is "Stardust" & req is the TokenRequest object
		String input = noSignetureToken;
		
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x" 
		String signature = String.format("%64x", new BigInteger(1, digest));
		
		
		String token = noSignetureToken + signature;
		
		String tokenManagerRequest;
		
		try {
			tokenManagerRequest = tokenManager.RequestToken(licenseFilePath);
		} catch (TokenManagementException e) {
			throw e;
		}
		
		Assertions.assertEquals(token, tokenManagerRequest);
		
		
		
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


		return jsonLicense;
	}
	


}
