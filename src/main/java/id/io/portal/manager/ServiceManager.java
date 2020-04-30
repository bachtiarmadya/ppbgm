package id.io.portal.manager;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

@ApplicationPath("/system")
public class ServiceManager extends ResourceConfig {

    public ServiceManager() {
        packages("id.io.portal.service", "id.io.portal.util.rest");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }

}
