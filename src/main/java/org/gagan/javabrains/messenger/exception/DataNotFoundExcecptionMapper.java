package org.gagan.javabrains.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.gagan.javabrains.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExcecptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exc) {
		ErrorMessage error = new ErrorMessage(exc.getMessage(),404,"https://about.me/gsolur");
		return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).entity(error).build();
	}

}
