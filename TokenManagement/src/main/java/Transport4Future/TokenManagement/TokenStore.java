package Transport4Future.TokenManagement;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;


public class TokenStore {
	final private String STORE_PATH = System.getProperty("user.dir") + "store/tokenStore.json";
	private List<Token> tokenList;

	
	public void add(Token newToken) throws TokenManagementException {
		this.load();
		if (find(newToken.toString()) == null) {
			tokenList.add(newToken);
			this.save();
		}
	}
	
	public Token find(String tokenToFind) {
		Token result = null;
		this.load();
		for (Token token : this.tokenList) {
			if (token.getTokenRequest().equals(tokenToFind)) {
				result = token;
			}
		}
		return result;
	}

	//Private functions
	
	//TODO:REALLY WRITE THINGS
	private void save() throws TokenManagementException {
		
		Gson gson = new Gson();
		File file = new File(STORE_PATH);
		FileWriter fileWriter;
		
		//Create a file if it does not exist
		if(!file.exists()) {
			throw new TokenManagementException("The tokenStore.json file does not exist");
		}
		
		String jsonString = gson.toJson(this.tokenList);

		System.out.println(jsonString);
		
		try {
			fileWriter = new FileWriter(System.getProperty("user.dir") + "store/tokenStore.json");
		} catch (IOException e) {
			throw new TokenManagementException("Error: Unable to open internal licenses store file");
		}
		
		//Write the file
		
		try {
			fileWriter.write(jsonString);
		} catch (IOException e) {
			
			//Closing the file
			try {
				fileWriter.close();
			} catch (IOException e2) {
				throw new TokenManagementException("Could not close the license store file");
			}
			
			throw new TokenManagementException("Error: Unable to write to license store file");
			
		}
		
		try {
			fileWriter.close();
		} catch (IOException e) {
			throw new TokenManagementException("Error: Unable to close file");
		}
		
	}
	
	private void load() {
		try {
			JsonReader reader = new JsonReader(
					new FileReader(System.getProperty("user.dir") + "/Store/tokenStore.json"));
			Gson gson = new Gson();
			Token[] myArray = gson.fromJson(reader, Token[].class);
			this.tokenList = new ArrayList<Token>();
			for (Token token : myArray) {
				this.tokenList.add(token);
			}
		} catch (Exception e) {
			this.tokenList = new ArrayList<Token>();
		}
	}


}
