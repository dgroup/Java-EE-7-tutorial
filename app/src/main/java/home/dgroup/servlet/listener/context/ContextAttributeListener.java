package home.dgroup.servlet.listener.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * @author dgroup
 * @since 01.03.2015
 */
@WebListener
public class ContextAttributeListener implements ServletContextAttributeListener {
    private static final Logger LOG = LoggerFactory.getLogger(ContextAttributeListener.class);

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        LOG.debug("{} - {} added.", event.getName(), event.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        LOG.debug("{} - {} removed from {}", event.getName(), event.getValue(), event.getSource());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        LOG.debug("{} - {} replaced from {}", event.getName(), event.getValue(), event.getSource());
    }
}