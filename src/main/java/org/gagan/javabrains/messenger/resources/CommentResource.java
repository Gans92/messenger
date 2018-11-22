package org.gagan.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.gagan.javabrains.messenger.model.Comment;
import org.gagan.javabrains.messenger.service.CommentService;

@Path("/")
@Consumes(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML})
@Produces(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML})
public class CommentResource {

	CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getComments(@PathParam("messageId") Long messageId){
		return commentService.getAllComments(messageId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") Long messageId, @PathParam("commentId") Long commentId){
		return commentService.getComment(messageId,commentId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") Long messageId, Comment comment){
		return commentService.addComment(messageId,comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") Long messageId, @PathParam("commentId") Long commentId, Comment comment){
		comment.setId(commentId);
		return commentService.updateComment(messageId,comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public Comment removeComment(@PathParam("messageId") Long messageId, @PathParam("commentId") Long commentId){
		return commentService.removeComment(messageId,commentId);
	}
	
	
}
