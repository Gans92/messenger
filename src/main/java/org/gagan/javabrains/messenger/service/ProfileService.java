package org.gagan.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.gagan.javabrains.messenger.database.DatabaseStub;
import org.gagan.javabrains.messenger.model.Profile;

public class ProfileService {

private Map<String, Profile> profiles = DatabaseStub.getProfiles();
	
	public ProfileService(){
		profiles.put("gsolur", new Profile(1,"gsolur","Gagan","Solur Venkatesh"));
		profiles.put("kite", new Profile(2,"kite","Jagan","Kolar"));
	}
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profile.setCreated(new Date());
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}
}
