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
 * @author dgroup on 11.03.2015.
 */
public class HttpSessionActivationListenerImpl implements HttpSessionActivationListener {

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        // I'm going to migrate into another JVM
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        // Hi JVM(cluster 2), I have already migrated from cluster 1,
        //  but session can't be used at this moment.
    }
}
