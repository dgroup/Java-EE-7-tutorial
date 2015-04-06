package home.dgroup.servlet.listener.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author dgroup
 * @since 01.03.2015
 */
@WebListener
public class HttpSessionAttributeListenerImpl implements HttpSessionAttributeListener {
    private static final Logger LOG = LoggerFactory.getLogger(HttpSessionAttributeListenerImpl.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        LOG.debug("{} - {} added from {}", event.getName(), event.getValue(), event.getSource());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        LOG.debug("{} - {} removed from {}", event.getName(), event.getValue(), event.getSource());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        LOG.debug("{} - {} replaced from {}", event.getName(), event.getValue(), event.getSource());
    }
}