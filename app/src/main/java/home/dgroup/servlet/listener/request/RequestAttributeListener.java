package home.dgroup.servlet.listener.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * @author dgroup
 * @since 01.03.2015
 */
@WebListener
public class RequestAttributeListener implements ServletRequestAttributeListener {
    private static final Logger LOG = LoggerFactory.getLogger(RequestAttributeListener.class);

    @Override
    public void attributeAdded(ServletRequestAttributeEvent event) {
        LOG.debug("{} - {} added from {}", event.getName(), event.getValue(), event.getSource());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {
        LOG.debug("{} - {} removed from {}", event.getName(), event.getValue(), event.getSource());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {
        LOG.debug("{} - {} replaced from {}", event.getName(), event.getValue(), event.getSource());
    }
}