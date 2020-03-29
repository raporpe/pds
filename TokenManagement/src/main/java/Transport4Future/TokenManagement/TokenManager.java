package Transport4Future.TokenManagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.json.Json;
import javax.json.JsonObject;


public class TokenManager {

	private TokenStore store;
	
	public TokenManager() {
		this.store = new TokenStore();
	}
	
	
	/**
	 * Creates a token request given a Json file with the required fields.
	 * @param InputFile a json file
	 * @return a TokenRequest object with all the json fields integrated
	 * @throws TokenManagementException if any error related to json parsing and access is found
	 */
	public TokenRequest TokenRequestGeneration(String InputFile) throws TokenManagementException {
		
		TokenRequest req = null;
		String fileContents = "";
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(InputFile));
		} catch (FileNotFoundException e) {
			System.out.println(e);
			throw new TokenManagementException(ErrorMessage.inputFileNotFoundError);
		}
		
		String line;

		try {
			while ((line = reader.readLine()) != null) {
				fileContents += line;
			}
		} catch (IOException e) {
			throw new TokenManagementException(ErrorMessage.readFileError);
		}

		//Check if the file is totally empty
		if (fileContents == "") {
			throw new TokenManagementException(ErrorMessage.emptyFileError);
		}
		
		try {
			reader.close();
		} catch (IOException e) {
			throw new TokenManagementException(ErrorMessage.closeFileError);
		}
		
		JsonObject jsonLicense;
		
		try {
			jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();
		} catch(Exception e){
			throw new TokenManagementException(ErrorMessage.jsonParsingError);
		}

		//Check for empty json
		if(jsonLicense.size() == 0){
			throw new TokenManagementException(ErrorMessage.emptyFileError);
		}

		//Check for json with too many tags
		if(jsonLicense.size() > 6) {
			throw new TokenManagementException(ErrorMessage.jsonExtraTagError);
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
			throw new TokenManagementException(ErrorMessage.jsonMissingTagError);
		}
		
		req = new TokenRequest(deviceName, typeDevice, driverVersion, email, serialNumber, macAddress);

	
		return req;
	}
	
	/**
	 * Generates a authorisation token if given a Json file with the required fields
	 * @param InputFile a json file with the three indicated fields
	 * @return a string encoded in base64 that contains the specified information in the
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
			throw new TokenManagementException(ErrorMessage.inputFileNotFoundError);
		}
		
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				fileContents += line;
			}
		} catch (IOException e) {
			throw new TokenManagementException(ErrorMessage.readFileError);
		}
		
		try {
			reader.close();
		} catch (IOException e) {
			throw new TokenManagementException(ErrorMessage.closeFileError);
		}
		
		JsonObject jsonLicense;
		
		try {
			jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();
		} catch(Exception e){
			
			throw new TokenManagementException(ErrorMessage.jsonParsingError);
		}
		
		if(jsonLicense.size() > 3) {
			throw new TokenManagementException(ErrorMessage.jsonExtraTagError);
		}
		
		String tokenRequest;
		String notificationEmail;
		String requestDate;
		
		try {	
			tokenRequest = jsonLicense.getString("Token Request");
			notificationEmail = jsonLicense.getString("Notification e-mail");
			requestDate = jsonLicense.getString("Request Date");
			
		} catch(Exception e) {
			throw new TokenManagementException(ErrorMessage.jsonMissingTagError);
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:MM:SS");  
		
		long unixDate;
		try {
			unixDate = dateFormat.parse(requestDate).getTime();
		} catch (ParseException e) {
			throw new TokenManagementException(ErrorMessage.invalidDateFormat);
		}
		
		myToken = new Token(tokenRequest, notificationEmail, unixDate);
	

		return myToken.getBase64Token();
	}
	
	
	/**
	 * Checks if base64 encoded token is valid
	 * @param Token a token object
	 * @return boolean with validity
	 * @throws TokenManagementException
	 */
	public boolean VerifyToken(String Token) throws TokenManagementException{
		boolean result = false;
		Token tokenFound = this.store.find(Token);
		if (tokenFound != null) {
			result = tokenFound.isValid();
		}
		return result;
	}

}
