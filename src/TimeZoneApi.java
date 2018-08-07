/*
 * Name: Anjali Prabhala
 * NetID: axp171330
 * Class: CS 2336 
 * Section: 2
 * Description: This program uses APIs and the freenode.net webchat services to 
 * implement a chatbot. This chatbot uses two main APIS: 1. weather api 2. TimeZonedb api.
 * it enables other users to join the chat server and the channel for this program. The two
 * main things the chat bots returns are the weather and the local time and time zone. To use
 * this program, refer to the documentation in the program.
 * 
 * APIs used
 * Weather API: https://openweathermap.org/api
 * Timezone API: https://timezonedb.com/references/get-time-zone
 *  
 */
import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class TimeZoneApi {
	static StringBuilder result;
	static String location, timestamp;
	
	public static void main(String[] args) {
		try {
			 String weatherURL = "http://api.timezonedb.com/v2/get-time-zone?key=9LMFW2TCDKLM&format=json&by=zone&zone="+location;     
			 //Json Response
		        result = new StringBuilder(); 
		        URL url = new URL(weatherURL);
		    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    	conn.setRequestMethod("GET");
		    	BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    	String line;
		    	//reads the output
		    	while ((line = rd.readLine()) != null) {
		    		result.append(line);
		    	}
		    	rd.close();
		}catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
	//return current local time of a location zone
    static String getLocalTime(String jsonOutput){
    	JsonObject jsonObject = new JsonParser().parse(jsonOutput).getAsJsonObject();
    	String result = jsonObject.get("formatted").getAsString();
    	return result;
    }
    
    //return the current timezone of a location zone
    static String getTimeZone(String jsonOutput){
    	JsonObject jsonObject = new JsonParser().parse(jsonOutput).getAsJsonObject();
    	String result = jsonObject.get("abbreviation").getAsString();
    	return result;
    }
    
	//return current local time of a City
    static String getLocalTimeZone(String jsonOutput){
    	JsonObject jsonObject = new JsonParser().parse(jsonOutput).getAsJsonObject();
    	String result = jsonObject.get("formatted").getAsString();
    	return result;
    }
    
}
	