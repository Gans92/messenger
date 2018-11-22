package org.gagan.javabrains.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResourcce {

	@GET
	@Path("/annotations")
	//@MatrixParam Example -  http://localhost:8080/messenger/webapi/injectdemo/annotations;path=value
	//@FormParam are used to fetch form parameter submitted in html.
	public String getParamUsingAnnotation(@MatrixParam("param") String matrixParam,
											@HeaderParam("apikey") String key,
											@CookieParam("name") String cookie){
		return "MatrixParam: "+matrixParam+", HeaderParam: "+key+", CookieParam: "+cookie;
	}
	
	//To overcome multiple parameters and hard coded cookie parameters we can use @Context annotations as below.
	@GET
	@Path("/context")
	public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders header){
		String uriPath = uriInfo.getAbsolutePath().toString();
		String cookies = header.getCookies().toString();
		return "URI: "+uriPath+", Cookies: "+cookies;
	}
}
