package id.io.portal.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ping")
@Produces(MediaType.APPLICATION_JSON)
public class SampleService extends BaseService{
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post() {
        return getSuccessResponse();
    }

    @GET
    public Response get() {
        return getSuccessResponse();
    }
    
}
