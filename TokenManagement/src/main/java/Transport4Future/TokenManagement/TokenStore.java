package Transport4Future.TokenManagement;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;


public class TokenStore {
	final private String STORE_PATH = System.getProperty("user.dir") + "/store/tokenStore.json";
	private List<Token> tokenList;

	
	public void add(Token newToken) throws TokenManagementException {
		this.load();
		if (find(newToken.toString()) == null) {
			tokenList.add(newToken);
			this.save();
		}
	}
	
	public Token find(String tokenToFind) throws TokenManagementException {
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
	
	private void save() throws TokenManagementException {
		
		Gson gson = new Gson();
		File file = new File(STORE_PATH);
		FileWriter fileWriter;
		File dir = new File(System.getProperty("user.dir") + "/store");

		//Create the directory if it does not exist
		if(!dir.exists()){
			boolean created = dir.mkdir();
			if(!created){
				throw new TokenManagementException(ErrorMessage.storeDirCreationError);
			}
		}

		//Create the file if it does not exist
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new TokenManagementException(ErrorMessage.jsonStoreFileCreationError);
			}
		}
		
		String jsonString = gson.toJson(this.tokenList);

		try {
			fileWriter = new FileWriter(STORE_PATH);
		} catch (IOException e) {
			throw new TokenManagementException(ErrorMessage.readStoreFileError);
		}
		
		//Write the file
		
		try {
			fileWriter.write(jsonString);
		} catch (IOException e) {
			
			//Closing the file
			try {
				fileWriter.close();
			} catch (IOException e2) {
				throw new TokenManagementException(ErrorMessage.closeStoreFileError);
			}
			
			throw new TokenManagementException(ErrorMessage.writeStoreFileError);
		}
		
		try {
			fileWriter.close();
		} catch (IOException e) {
			throw new TokenManagementException(ErrorMessage.closeStoreFileError);
		}
		
	}
	
	private void load() throws TokenManagementException {
		try {
			FileReader fr = new FileReader(STORE_PATH);
			JsonReader jsonReader = new JsonReader (fr);
			Gson gson = new Gson();

			Token[] loadedTokens = gson.fromJson(jsonReader, Token[].class);
			this.tokenList = new ArrayList<>();

			//Load the retrieved contents in
			this.tokenList.addAll(Arrays.asList(loadedTokens));
		} catch (Exception e) {
			this.tokenList = new ArrayList<>();
			throw new TokenManagementException(ErrorMessage.readStoreFileError);
		}
	}


}
