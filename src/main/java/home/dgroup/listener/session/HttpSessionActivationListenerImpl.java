package home.dgroup.listener.session;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * When the session to which the object is attached will be passivated or activated.
 * A session will be passivated or activated when it is moved between virtual machines
 *  or saved to and restored from persistent storage.
 *
 *  To receive this notification, your object must implement the
 *  {{@link javax.servlet.http.HttpSessionActivationListener}} interface.
 *
 * <a href="http://www.xyzws.com/servletfaq/when-do-i-use-httpsessionactivationlistener/4">When do I use HttpSessionActivationListener?</a>
 * <a href="http://memorynotfound.com/httpsessionactivationlistener-example-use-case/">Use Case</a>
 *
 * @author dgroup on 11.03.2015.
 */
public class HttpSessionActivationListenerImpl implements HttpSessionActivationListener {

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {

    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {

    }
}
