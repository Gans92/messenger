package org.gagan.javabrains.messenger.resources;



import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.gagan.javabrains.messenger.model.Message;
import org.gagan.javabrains.messenger.resources.beans.MessageFilterBean;
import org.gagan.javabrains.messenger.service.MessageService;

@Path("/messages")
@Consumes(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML})
@Produces(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML})
public class MessageResource {

	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean messageFilters){
		if(messageFilters.getYear() > 0){
			return messageService.getMessageForYear(messageFilters.getYear());
		}
		if(messageFilters.getStart() >= 0 && messageFilters.getSize() > 0){
			return messageService.getPaginatedMessage(messageFilters.getStart(), messageFilters.getSize());
		}
		return messageService.getAllMessages();	
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo){
		Message message = messageService.getMessage(id);
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profiles");
		message.addLink(getUriForComments(uriInfo, message), "comments");
		return message;
	}

	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build().toString();
		return uri;
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(String.valueOf(message.getAuthor())).build().toString();
		return uri;
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class).path(String.valueOf(message.getId())).build().toString();
		return uri;
	}
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo ){
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build(); 
		return Response.created(uri).entity(newMessage).build();	
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id,Message message){
		message.setId(id);
		return messageService.updateMessage(message);	
	}
	
	@DELETE
	@Path("/{messageId}")
	public void removeMessage(@PathParam("messageId") long id){
		messageService.removeMessage(id);	
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
	
}
