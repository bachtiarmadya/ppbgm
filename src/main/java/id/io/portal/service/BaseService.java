package id.io.portal.service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import id.io.portal.manager.PropertyManager;
import id.io.portal.util.log.AppLogger;
import id.io.portal.util.rest.model.ServiceResponse;

public class BaseService {

    protected AppLogger log;

    @Context
    protected HttpServletRequest request;

    protected Response getErrorResponse() {
        return buildResponse(Status.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    protected Response getResourceNotFoundResponse() {
        return buildResponse(Status.NOT_FOUND, "Resource Not Found");
    }

    protected Response getAcceptedResponse(String message) {
        return buildResponse(Status.ACCEPTED, message);
    }

    protected Response getSuccessResponse() {
        return buildResponse(Status.OK, "Success");
    }

    protected Response getSuccessResponse(Object obj) {
        return Response.status(Status.OK).entity(obj).build();
    }

    protected Response getInvalidRequestResponse() {
        return buildResponse(Status.BAD_REQUEST, "Invalid Request");
    }

    protected Response getConflictedResponse(String message) {
        return buildResponse(Status.CONFLICT, message);
    }

    private Response buildResponse(Status status, String message) {
        return Response.status(status).entity(new ServiceResponse(status, message)).build();
    }

    protected AppLogger getLogger(Class<?> clazz) {
        return new AppLogger(clazz);
    }

    protected Executor initThreadPool() {
        return Executors.newFixedThreadPool(30);
    }

    protected void start(String methodName) {
        log.debug(methodName, "start");
    }

    protected void completed(String methodName) {
        log.debug(methodName, "completed");
    }

    protected String getProperty(String key) {
        return PropertyManager.getInstance().getProperty(key);
    }


}
