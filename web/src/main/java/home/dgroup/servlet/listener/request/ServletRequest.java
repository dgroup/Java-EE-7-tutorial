package home.dgroup.servlet.listener.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dgroup
 * @since 01.03.2015
 */
@WebListener
public class ServletRequest implements ServletRequestListener {
    private static final Logger LOG = LoggerFactory.getLogger(ServletRequest.class);
    private static AtomicInteger requestCounter = new AtomicInteger(0);

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        LOG.debug("Request #{} destroyed. {}", requestCounter, event);
    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        LOG.debug("Request #{} initialized.", requestCounter.incrementAndGet());
    }
}