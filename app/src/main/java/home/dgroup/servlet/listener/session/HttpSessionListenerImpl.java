package home.dgroup.servlet.listener.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by dgroup on 01.03.2015.
 */
@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {
    private static final Logger LOG = LoggerFactory.getLogger(HttpSessionListenerImpl.class);

    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        LOG.debug("Session created. {}", hse);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        LOG.debug("Session destroyed. {}", hse);
    }
}
