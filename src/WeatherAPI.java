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

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
//weather api
public class WeatherAPI {
	//static variables
		static String uCity;
		static StringBuilder result;
		//main method
	    public static void main(String[] args)
	    {
	        try {
	        	//calls to the api
	         String weatherURL = "http://api.openweathermap.org/data/2.5/weather?q=" + uCity + "&APPID=983e534f6e2a187378743870f52bd56e";             
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
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    //get minimum Temperature
	    static double parseMinTemp(String json){
	    	JsonElement jelement = new JsonParser().parse(json);
	    	JsonObject MasterWeatherObject = jelement.getAsJsonObject();
	    	JsonObject mainObject = MasterWeatherObject.getAsJsonObject("main");
	    	double minTemp = mainObject.get("temp_min").getAsDouble();
	    	double minTempF = minTemp * 9/5 - 459.67;
	    	Double minT = minTempF;
	    	return minTempF;
	    }
	    //get maximum Temperature
	    static double parseMaxTemp(String json){
	    	JsonElement jelement = new JsonParser().parse(json);
	    	JsonObject MasterWeatherObject = jelement.getAsJsonObject();
	    	JsonObject mainObject = MasterWeatherObject.getAsJsonObject("main");
	    	double maxTemp = mainObject.get("temp_max").getAsDouble();
	    	double maxTempF = maxTemp * 9/5 - 459.67;
	        Double minT = maxTempF;
	    	return maxTempF;
	    }
	    //get current Temperature
	    static double parseTemp(String json){
	    	JsonElement jelement = new JsonParser().parse(json);
	    	JsonObject MasterWeatherObject = jelement.getAsJsonObject();
	    	JsonObject mainObject = MasterWeatherObject.getAsJsonObject("main");
	    	double temp = mainObject.get("temp").getAsDouble();
	    	double tempF = temp * 9/5 - 459.67;
	    	Double minT = tempF;
	    	return tempF;
	    }
	       
	}