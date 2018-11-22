package org.gagan.javabrains.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.gagan.javabrains.messenger.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exc) {
		ErrorMessage error = new ErrorMessage(exc.getMessage(),500,"https://about.me/gsolur");
		return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
	}

}
