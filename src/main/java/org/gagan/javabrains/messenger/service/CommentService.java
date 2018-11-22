package org.gagan.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.gagan.javabrains.messenger.database.DatabaseStub;
import org.gagan.javabrains.messenger.model.Comment;
import org.gagan.javabrains.messenger.model.Message;

public class CommentService {

	private Map<Long,Message> messages = DatabaseStub.getMessages();
	
	/*public CommentService(){
		for (Message message : messages.values()) {
			message.setComments(comments);
		}
	}*/
	
	public List<Comment> getAllComments(Long messageId) {
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(Long messageId, Long commentId) {
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		return comments.get(commentId);
	}

	public Comment addComment(Long messageId, Comment comment) {
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		comment.setId((long) (comments.size()+1));
		comment.setCreated(new Date());
		comments.put(comment.getId(), comment);
		//messages.get(messageId).setComments(comments);
		return comment;
	}

	public Comment updateComment(Long messageId, Comment comment) {
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		if(comment.getId() <= 0){
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment removeComment(Long messageId, Long commentId) {
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}

	

}
