package Transport4Future.TokenManagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.json.Json;
import javax.json.JsonObject;


public class TokenManager {

	private TokenStore store;
	
	public TokenManager() {
		this.store = new TokenStore();
	}
	
	
	/**
	 * Creates a token request from a json file (type 1)
	 * @param InputFile
	 * @return the json parameters in an object
	 * @throws TokenManagementException
	 */
	public TokenRequest TokenRequestGeneration(String InputFile) throws TokenManagementException {
		
		TokenRequest req = null;
		String fileContents = "";
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(InputFile));
		} catch (FileNotFoundException e) {
			throw new TokenManagementException("Error: input file not found.");
		}
		
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				fileContents += line;
			}
		} catch (IOException e) {
			throw new TokenManagementException("Error: input file could not be accessed.");
		}
		
		try {
			reader.close();
		} catch (IOException e) {
			throw new TokenManagementException("Error: input file could not be closed.");
		}
		
		JsonObject jsonLicense;
		
		try {
			jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();
		} catch(Exception e){
			
			throw new TokenManagementException("Error: error reading the json file.");
		}
		
		if(jsonLicense.size()!= 6) {
			throw new TokenManagementException("Error: not number of properties requested.");
		}
		
		String deviceName;
		String typeDevice;
		String driverVersion;
		String email;
		String serialNumber;
		String macAddress;
		
		try {	
			deviceName = jsonLicense.getString("Device Name");
			typeDevice = jsonLicense.getString("Type of Device");
			driverVersion = jsonLicense.getString("Driver Version");
			email = jsonLicense.getString("Support e-mail");
			serialNumber = jsonLicense.getString("Serial Number");
			macAddress = jsonLicense.getString("MAC Address");
						
		} catch(Exception e) {
			throw new TokenManagementException("Error: invalid input data in JSON structure.");
		}
		
		req = new TokenRequest(deviceName, typeDevice, driverVersion, email, serialNumber, macAddress);

	
		return req;
	}
	
	/**
	 * 
	 * @param InputFile
	 * @return a base64 token auth (type 2)
	 * @throws TokenManagementException
	 */
	
	public String RequestToken(String InputFile) throws TokenManagementException{
		Token myToken;		
		String fileContents = "";
		BufferedReader reader;
		String hashed;
		String toReturn = null;
		
		try {
			reader = new BufferedReader(new FileReader(InputFile));
		} catch (FileNotFoundException e) {
			throw new TokenManagementException("Error: input file not found.");
		}
		
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				fileContents += line;
			}
		} catch (IOException e) {
			throw new TokenManagementException("Error: input file could not be accessed.");
		}
		
		try {
			reader.close();
		} catch (IOException e) {
			throw new TokenManagementException("Error: input file could not be closed.");
		}
		
		JsonObject jsonLicense;
		
		try {
			jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();
		} catch(Exception e){
			
			throw new TokenManagementException("Error: error reading the json file.");
		}
		
		if(jsonLicense.size()!= 3) {
			throw new TokenManagementException("Error: not number of properties requested.");
		}
		
		String tokenRequest;
		String notificationEmail;
		String requestDate;
		
		try {	
			tokenRequest = jsonLicense.getString("Token Request");
			notificationEmail = jsonLicense.getString("Notification e-mail");
			requestDate = jsonLicense.getString("Request Date");
			
		} catch(Exception e) {
			throw new TokenManagementException("Error: invalid input data in JSON structure.");
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:MM:SS");  
		
		long unixDate;
		try {
			unixDate = dateFormat.parse(requestDate).getTime();
		} catch (ParseException e) {
			throw new TokenManagementException("The date is not in the correct format");
		}
		
		myToken = new Token(tokenRequest, notificationEmail, unixDate);
	

		return myToken.getBase64Token();
	}
	
	
	/**
	 * Checks if base64 encoded token is valid
	 * @param Token base64 token
	 * @return boolean with validity
	 * @throws TokenManagementException
	 */
	public boolean VerifyToken(String Token) throws TokenManagementException{
		boolean result = false;
		Token tokenFound = this.store.find(Token);
		if (tokenFound !=null) {
			result = tokenFound.isValid(tokenFound);
		}
		return result;
	}

}
