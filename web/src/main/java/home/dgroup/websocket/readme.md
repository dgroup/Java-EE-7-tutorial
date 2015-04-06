### WebSocket Applications in the Java EE Platform
The Java API for WebSocket consists of the following packages.

* The `javax.websocket.server` package contains annotations, classes, and interfaces to create and configure server endpoints.
* The `javax.websocket package` contains annotations, classes, interfaces, and exceptions that are common to client and server endpoints.

The Java API for WebSocket enables you to create two kinds of endpoints:

* [Programmatic endpoints](https://docs.oracle.com/javaee/7/tutorial/websocket003.htm);
* [Annotated endpoints](https://docs.oracle.com/javaee/7/tutorial/websocket004.htm).

To create a `programmatic endpoint`, you extend the Endpoint class and override its lifecycle methods.
To create an `annotated endpoint`, you decorate a Java class and some of its methods
with the annotations provided by the packages mentioned previously.
After you have created an endpoint, you deploy it to an specific URI in the application
so that remote clients can connect to it

As opposed to servlets, WebSocket endpoints are _instantiated multiple times_.
The container creates an instance of an endpoint per connection to its deployment URI.
_Each instance_ is associated with one and _only one connection_.
This facilitates keeping user state for each connection and makes development easier,
because there is only one thread executing the code of an endpoint instance at any given time.

Read more:

* [18.2 Creating WebSocket Applications in the Java EE Platform](https://docs.oracle.com/javaee/7/tutorial/websocket002.htm)