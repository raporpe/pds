package Transport4Future.TokenManagement;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
	private static TokenManager tm;
	private static String filePath;
	private static TokenRequest rq;
	private static String password;

	@BeforeAll
	public static void TM_RF1_01() {
		filePath = "src/resources/CP-RF1-01.json";
		json = readJSON(filePath);
		tm = new TokenManager();

		// this assertion gives no throws, so we commented it
		//Assertions.assertThrows(TokenManagementException.class, () -> tm.readTokenRequestFromJSON(filePath));
		assertNotNull(filePath);
	}

	@Test
	public void TM_RF1_02_I1() {

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
	public void TM_RF1_01_P1() {
		try {
			rq = tm.readTokenRequestFromJSON(filePath);
		} catch (TokenManagementException a) {
			Assertions.fail("The json file was not found");
		}

		Assertions.assertEquals(rq.getDeviceName(), json.getString("Device Name"));
		Assertions.assertEquals(rq.getTypeDevice(), json.getString("Type of Device"));
		Assertions.assertEquals(rq.getDriverVersion(), json.getString("Driver Version"));
		Assertions.assertEquals(rq.getSerialNumber(), json.getString("Serial Number"));
		Assertions.assertEquals(rq.getMacAddress(), json.getString("MAC Address"));

	}

	@Test
	public void TM_RF_01_P2() throws TokenManagementException, NoSuchAlgorithmException {
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("Error: no such hashing algorithm.");
		}
		
		//  Defined  password is "Stardust" & req is the TokenRequest object
		String input =  password + "-" + rq.toString();
		
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x" 
		String hex = String.format("%32x", new BigInteger(1, digest));

		try {
			Assertions.assertEquals(hex, rq.getToken());
		} catch (NoSuchAlgorithmException e) {
			throw e;
		}
		

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
		DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
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
	
	public void TM_RF_01_O1() throws NoSuchAlgorithmException {
		try {
			rq = tm.readTokenRequestFromJSON(filePath);
		}catch(TokenManagementException a) {
			Assertions.assertNotEquals(rq.getToken(), null);
		}
	}

}
