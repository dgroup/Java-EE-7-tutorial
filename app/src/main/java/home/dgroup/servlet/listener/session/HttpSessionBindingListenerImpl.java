package home.dgroup.servlet.listener.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Just for test. There are no any business logic in this class :)
 *
 *
 * @author dgroup
 * @since 11.03.2015
 */
public class HttpSessionBindingListenerImpl implements HttpSessionBindingListener {
    private static final Logger LOG = LoggerFactory.getLogger(HttpSessionAttributeListenerImpl.class);

    private final String msg;

    public HttpSessionBindingListenerImpl(String msg) {
        this.msg = msg;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        LOG.debug("I'm added into Http Session :). Message: " + msg);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        LOG.debug("I have been removed from Http Session. Message: " + msg);
    }
}

