### HttpSessionBindingListener vs HttpSessionAttributeListener
The first one is a generic listener to the session. It's called whenever an attribute of any kind is being added or removed to/from a session.
It's used when you want to be informed of any session attribute addition/removal.

The second one is a callback interface that can be implemented by a specific class.
The callback method is called on an object implementing this interface when this object is being bound/unbound to/from the session.
It's used when you want an object to be informed of its own addition/removal to/from the session.

Read more:

* [Oracle Docs: Associating Objects with a Session](https://docs.oracle.com/javaee/7/tutorial/servlets009.htm)
* [HttpSessionAttributeListener and HttpSessionBindingListener](http://stackoverflow.com/questions/11490166/httpsessionattributelistener-and-httpsessionbindinglistener)
* [Difference between HttpSessionAttributeListener and HttpSessionBindingListener](http://www.quickprogrammingtips.com/java-ee/difference-between-httpsessionattributelistener-and-httpsessionbindinglistener.html)

### HttpSessionActivationListener
* [When do I use HttpSessionActivationListener?](http://www.xyzws.com/servletfaq/when-do-i-use-httpsessionactivationlistener/4)
* [Use Case](http://memorynotfound.com/httpsessionactivationlistener-example-use-case/)