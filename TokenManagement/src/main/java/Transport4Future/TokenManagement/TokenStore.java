package Transport4Future.TokenManagement;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import java.awt.*;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class TokenStore {
	private List<Token> tokenList;
	
	private void Load() {
		try {
			JsonReader reader = new JsonReader(new FileReader(System.getProperty("user.dir")+"/Store/tokenStore.json"));
			Gson gson = new Gson();
			Token [] myArray = gson.fromJson(reader, Token[].class);
			this.tokenList = new ArrayList<Token>();
			for(Token token: myArray) {
				this.tokenList.add(token);
			}
		}catch (Exception ex) {
			this.tokenList = new ArrayList<Token>();
		}
	}
	
	public void Add (Token newToken) throws TokenManagementException{
		this.Load();
		if(Find(newToken.toString())==null) {
			tokenList.add(newToken);
			this.Save();
		}
	}
	
	private void Save () throws TokenManagementException{
		Gson gson = new Gson();
		String jsonString = gson.toJson(this.tokenList);
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(System.getProperty("user.dir") + "/Store/tokenStore.json");
		}catch(IOException e) {
			throw new TokenManagementException("Error: Unable to save a new token in the internal licenses store");
		}
	}
	
	public Token Find (String tokenToFind) {
		Token result = null;
		this.Load();
		for(Token token : this.tokenList) {
			if(token.getTokenValue().equals(tokenToFind)) {
				result = token;
			}
		}
		return result;
	}
}
