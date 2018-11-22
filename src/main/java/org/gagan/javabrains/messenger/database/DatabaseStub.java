package org.gagan.javabrains.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.gagan.javabrains.messenger.model.Message;
import org.gagan.javabrains.messenger.model.Profile;
//Connect to database to fetch messages using hibernates or jdbc.
public class DatabaseStub {

	private static Map<Long,Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	
	public static Map<Long,Message> getMessages(){
		return messages;
	}
	
	public static Map<String,Profile> getProfiles(){
		return profiles;
	}
}
