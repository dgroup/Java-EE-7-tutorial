package home.dgroup.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by dgroup on 01.03.2015.
 */
@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.debug("Context initialized. {}", sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.debug("Context destroyed. {}", sce);
    }
}