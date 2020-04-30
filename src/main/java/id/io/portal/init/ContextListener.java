package id.io.portal.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import id.io.portal.manager.ConnectionManager;
import id.io.portal.manager.EncryptionManager;
import id.io.portal.util.log.AppLogger;

@WebListener
public class ContextListener implements ServletContextListener {

    public final AppLogger log;

    public ContextListener() {
        log = new AppLogger(this.getClass());
    }

    @Override
    public void contextInitialized(ServletContextEvent evt) {
        log.info("Application Init Started");
        EncryptionManager.init();
        ConnectionManager.init();
        log.info("Application Init Completed");
    }

    @Override
    public void contextDestroyed(ServletContextEvent evt) {
        log.info("Application Shutdown Started");
        ConnectionManager.shutdown();
        EncryptionManager.shutdown();
        log.info("Application Shutdown Completed");
    }

}
