package org.gagan.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.gagan.javabrains.messenger.database.DatabaseStub;
import org.gagan.javabrains.messenger.exception.DataNotFoundException;
import org.gagan.javabrains.messenger.model.Message;

public class MessageService {

	private Map<Long,Message> messages = DatabaseStub.getMessages();
	
	public MessageService(){
		messages.put(1L, new Message(1,"Hello","Gagan"));
		messages.put(2L, new Message(2,"Hello","Solur"));
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(Long id){
		Message message =  messages.get(id);
		if(message == null){
			throw new DataNotFoundException("Message Not Found For ID = "+id);
		}
		return message;
	}
	
	public List<Message> getMessageForYear(int year){
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year){
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}
	
	public List<Message> getPaginatedMessage(int start, int size){
		List<Message> messagePerPage = new ArrayList<Message>(messages.values());
		if(start+size > messagePerPage.size()) return new ArrayList<Message>();
		return messagePerPage.subList(start, start+size);
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		message.setCreated(new Date());
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId() <= 0){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(Long id){
		return messages.remove(id);
	}
}
