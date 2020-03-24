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
		
		try {	
			String deviceName = jsonLicense.getString("Device Name");
			String typeDevice = jsonLicense.getString("Type of Device");
			String driverVersion = jsonLicense.getString("Driver Version");
			String email = jsonLicense.getString("Support e-mail");
			String serialNumber = jsonLicense.getString("Serial Number");
			String macAddress = jsonLicense.getString("MAC Address");
			
			req = new TokenRequest(deviceName, typeDevice, driverVersion, email, serialNumber, macAddress);
			
		} catch(Exception e) {
			throw new TokenManagementException("Error: invalid input data in JSON structure.");
		}
	
		return req;
	}
	
	public String RequestToken(String InputFile) throws TokenManagementException{
		Token myToken;		
		String fileContents = "";
		BufferedReader reader;
		String hashed, toReturn;
		
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

			
			myToken = new Token(tokenRequest, notificationEmail, requestDate);
			
		} catch(Exception e) {
			throw new TokenManagementException("Error: invalid input data in JSON structure.");
		}
	

		String header = "SHA-256" + "PDS";
		String payload = tokenRequest + requestDate + "17/06/2030 22:00:00";
		String noSignetureToken = header + payload;
		
		String hash = myToken.CodeHash256(myToken);
		toReturn = myToken.encodeString(hashed);
		
		return toReturn;
	}
	
	public boolean VerifyToken(String Token) throws TokenManagementException{
		TokenStore myStore = new TokenStore();
		boolean result = false;
		Token tokenFound = myStore.Find(Token);
		if (tokenFound !=null) {
			result = tokenFound.isValid(tokenFound);
		}
		return result;
	}

}
