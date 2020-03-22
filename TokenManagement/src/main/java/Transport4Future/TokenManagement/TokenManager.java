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

	public TokenRequest readTokenRequestFromJSON(String path) throws TokenManagementException {
		
		TokenRequest req = null;
		String fileContents = "";
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(path));
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
}
