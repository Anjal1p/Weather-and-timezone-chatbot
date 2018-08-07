
import java.text.*; 
import org.jibble.pircbot.*;

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
public class ChatBot extends PircBot{
	public String city, zipCode;
	public ChatBot()
	{
		this.setName("MyChatB");
	}
	 
	//checks message and then does the following
	public void onMessage(String channel, String sender, String login, String hostname, String message){
		/*
		 * For Weather:
		 * The user can enter the following formats/keywords to interact with the chatbot and weather API
		 * 					Format 1: Weather <space> zipcode (i.e., 75022)
		 * 					Format 2: Weather <space> city (i.e., Dallas)
		 * The chatbot will reply with the current temperature, the minimum temperature and the maximum.
		 */
		if(message.equalsIgnoreCase("weather") || message.endsWith("weather") 
				|| message.contains("weather") || message.matches("weather") ||
				message.startsWith("weather")){
			// Look for state/zipcode  
            String[] words = message.split(" ");
            //finds the zipcode or city and stores it
			WeatherAPI.uCity = words[1]; 
			//calls WeatherAPI's main method
			WeatherAPI.main(null);
			//to only display two decimal points in the output
			DecimalFormat df = new DecimalFormat("#.##");
			//current temp
			String cur = df.format(WeatherAPI.parseTemp(WeatherAPI.result.toString()));
			//minimum temp
			String min = df.format(WeatherAPI.parseMinTemp(WeatherAPI.result.toString()));
			//maximum temp
			String max = df.format(WeatherAPI.parseMaxTemp(WeatherAPI.result.toString()));
			//sends a message or replies in the chatbot with the information fetched form the weather api
			sendMessage(channel, ": Weather:" + " Cur Temp: "+ cur + "°F" + "  |  Min Temp: " + min + "°F  |  Max Temp: " + max + "°F");
		}
		/*
		 * For local time and time zone:
		 * The user can enter the following keywords to interact with the chatbot and TimeZonebd api
		 * 					Format1: Local <space> time <space> Zone/Zone
		 * 					Format2: time <space> zone/zone (OR) local <space> zone/zone
		 * A few examples for location zones are:
		 * 					America/Panama
		 * 					Africa/Kigali
		 * 					Europe/San_Marino
		 * 					Pacific/Apia
		 * 					Asia/Dubai
		 * The chatbot will reply with the current local time in that location and the time zone that location follows.
		 */
		if(message.equalsIgnoreCase("Local Time") || message.endsWith("time") 
				|| message.contains("time") ||message.contains("local")|| message.matches("local time") ||
				message.startsWith("time") || message.startsWith("local")){
				String [] arr = message.split(" ");
				if(arr.length == 3) {
					TimeZoneApi.location = arr[2];
				}
				else if(arr.length == 2) {
					TimeZoneApi.location = arr[1];
				}
				
				TimeZoneApi.main(null);
     			String localTime = TimeZoneApi.getLocalTime(TimeZoneApi.result.toString());
     			String timeZone = TimeZoneApi.getTimeZone(TimeZoneApi.result.toString());
    			//sends a message or replies in the chatbot with the information fetched form the timezonedb api
				sendMessage(channel, "The local time in " + TimeZoneApi.location + " is: " + localTime );
				sendMessage(channel, "Time zone is: " + timeZone);
		}
		/*
		 * CHANGE THIS !!!
		 * Convert time zone:
		 * The user can enter the following keywords to interact with the chatbot and TimeZonebd api
		 * 					Format1: Local <space> time <space> Zone/Zone
		 * 					Format2: time <space> zone/zone (OR) local <space> zone/zone
		 * A few examples for location zones are:
		 * 					America/Panama
		 * 					Africa/Kigali
		 * 					Europe/San_Marino
		 * 					Pacific/Apia
		 * 					Asia/Dubai
		 * The chatbot will reply with the current local time in that location and the time zone that location follows.
		 */
		if(message.equalsIgnoreCase("Convert Time") || message.endsWith("time") 
				|| message.contains("time") ||message.contains("convert")|| message.matches("convert time") ||
				message.startsWith("convert") || message.startsWith("convert")){
				String [] arr = message.split(" ");
				if(arr.length == 5) {
					ConvertTime.from = arr[3];
					ConvertTime.to = arr[5];
				}
				
				ConvertTime.main(null);
     			String converted = ConvertTime.convertTime(ConvertTime.result.toString());
    			//sends a message or replies in the chatbot with the information fetched form the timezonedb api
				sendMessage(channel, "The converted time from " + ConvertTime.from + " to " + ConvertTime.to + "is: " + converted);
		}
	}
}