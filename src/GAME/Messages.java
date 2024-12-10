package GAME;

import java.util.ArrayList;

public class Messages {
	public static ArrayList<String> messages = new ArrayList<>();

	public static void addMessage(String message) {
		Messages.messages.add(message);
	}

	public static void showAllMessagesStacked(){
		for(String message : Messages.messages){
			System.out.println(message);
		}
		Messages.messages.clear();
	}


}
