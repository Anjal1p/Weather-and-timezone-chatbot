/*
 * Name: Anjali Prabhala
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
import org.jibble.pircbot.*;

public class ChatMain {
	public static void main(String[] args) throws Exception{
		//connecting to the server
		ChatBot bot = new ChatBot();
		bot.setVerbose(true);
		bot.connect("irc.freenode.net");
		bot.joinChannel("#pircbot");  

	}
}

