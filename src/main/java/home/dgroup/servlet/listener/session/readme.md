### Events, related to object association with a session
You can also notify objects of certain events related to their association with a session, such as the following.

* When the object is added to or removed from a session. To receive this notification, your object must implement
the `javax.servlet.http.HttpSessionBindingListener` interface.
* When the session to which the object is attached will be passivated or activated.
 A session will be passivated or activated when it is moved between virtual machines or saved to and restored from persistent storage.
 To receive this notification, your object must implement the `javax.servlet.http.HttpSessionActivationListener` interface.

 Read [Oracle Doc: Associating Objects with a Session](https://docs.oracle.com/javaee/7/tutorial/servlets009.htm)


### HttpSessionBindingListener vs HttpSessionAttributeListener
The first one is a generic listener to the session. It's called whenever an attribute of any kind is being added or removed to/from a session.
It's used when you want to be informed of any session attribute addition/removal.

The second one is a callback interface that can be implemented by a specific class.
The callback method is called on an object implementing this interface when this object is being bound/unbound to/from the session.
It's used when you want an object to be informed of its own addition/removal to/from the session.

Read more:

* [HttpSessionAttributeListener and HttpSessionBindingListener](http://stackoverflow.com/questions/11490166/httpsessionattributelistener-and-httpsessionbindinglistener)
* [Difference between HttpSessionAttributeListener and HttpSessionBindingListener](http://www.quickprogrammingtips.com/java-ee/difference-between-httpsessionattributelistener-and-httpsessionbindinglistener.html)

### HttpSessionActivationListener
The `HttpSessionActivationListener` is used for responding to events when a sessions object migrates from one VM to another.
The `HttpSessionActivationListener` only has any relevance when a session is part of a web application in a distributed environment.
When a session is activated, notification that the session has just been activated will be received
by the `sessionDidActivate` method and when the session is about to be destroyed, the `sessionWillPassivate` method is invoked.

Objects, that are bound (stored) to the session and its class implements `HttpSessionActivationListener` interface,
can be initialised or destroyed based upon the listener. Also, when the session migrates between servers,
the `sessionWillPassivate` is called and once it is successfully moved, the `sessionDidActivate` will be called.
It is interesting to note that the session is not yet ready for service at the time the `sessionDidActivate` method is called.

Details:

* [When do I use HttpSessionActivationListener?](http://www.xyzws.com/servletfaq/when-do-i-use-httpsessionactivationlistener/4)
* [Use Case](http://memorynotfound.com/httpsessionactivationlistener-example-use-case/)

### Session Tracking
To associate a session with a user, a web container can use several methods, all of which involve passing an identifier
between the client and the server. The identifier can be maintained on the client as a cookie,
or the web component can include the identifier in every URL that is returned to the client.

If your application uses session objects, you must ensure that session tracking is enabled
by having the application rewrite URLs whenever the client turns off cookies.
You do this by calling the response's `encodeURL(URL)` method on all URLs returned by a servlet.
This method includes the session ID in the URL only if cookies are disabled;
otherwise, the method returns the URL unchanged.

Read more:

* [17.9.4 Session Tracking](https://docs.oracle.com/javaee/7/tutorial/servlets009.htm)
* [encodeURL() purpose and best place to use](http://www.coderanch.com/t/366059/Servlets/java/encodeURL-purpose-place)

    It also contains interesting overview of `sendRedirect` and `forward`, `include` of  `javax.servlet.RequestDispatcher`.

* [HTML form encoding and URL re-writing](http://stackoverflow.com/questions/10276473/url-encoding-in-java)
* [Difference between URL Rewriting and URL Encoding in JSP Servlet](http://javarevisited.blogspot.com/2012/01/url-rewriting-url-encoding-in-servlet.html)
