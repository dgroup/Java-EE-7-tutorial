Recall that your application can notify web context and session listener objects of servlet lifecycle events (Handling Servlet Lifecycle Events).
You can also notify objects of certain events related to their association with a session, such as the following.

* When the object is added to or removed from a session. To receive this notification, your object must implement
the `javax.servlet.http.HttpSessionBindingListener` interface.
* When the session to which the object is attached will be passivated or activated.
 A session will be passivated or activated when it is moved between virtual machines or saved to and restored from persistent storage.
 To receive this notification, your object must implement the `javax.servlet.http.HttpSessionActivationListener` interface.