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

import org.gagan.javabrains.messenger.model.Message;
import org.gagan.javabrains.messenger.model.Profile;
import org.gagan.javabrains.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML})
@Produces(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML})
public class ProfileResource {

	ProfileService profilesService = new ProfileService();
	
	@GET
	public List<Profile> getProfiles(){
		return profilesService.getAllProfiles();	
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String pName){
		return profilesService.getProfile(pName);
	}
	
	@POST
	public Profile addProfile(Profile profile){
		return profilesService.addProfile(profile);	
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateMessage(@PathParam("profileName") String pName,Profile profile){
		profile.setProfileName(pName);
		return profilesService.updateProfile(profile);	
	}
	
	@DELETE
	@Path("/{profileName}")
	public void removeMessage(@PathParam("profileName") String pName){
		profilesService.removeProfile(pName);	
	}
	
}
