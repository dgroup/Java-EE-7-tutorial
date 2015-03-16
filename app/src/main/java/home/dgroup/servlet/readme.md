### Finalizing a Servlet
The web container may determine that a servlet should be removed from service
(for example, when a container wants to reclaim memory resources or when it is being shut down).
In such a case, the container calls the destroy method of the Servlet interface.
In this method, you release any resources the servlet is using and save any persistent state.
The destroy method releases the database object created in the init method.

A servlet's service methods should all be complete when a servlet is removed.
The server tries to ensure this by calling the destroy method only after:

1. all service requests have returned
2. after a server-specific `grace period`, whichever comes first.

If your servlet has operations that may run `longer` than the server's `grace period`,
the `operations` could still `be running` when `destroy is called`.
You must make sure that any threads still handling client requests complete.

The remainder of this section explains how to do the following.

* Keep track of how many threads are currently running the service method.
* Provide a clean shutdown by having the destroy method notify long-running threads of the shutdown
 and wait for them to complete.
* Have the long-running methods poll periodically to check for shutdown and,
if necessary, stop working, clean up, and return.

Read more:

* [17.10 Finalizing a Servlet](https://docs.oracle.com/javaee/7/tutorial/servlets010.htm)


### @Deprecated javax.servlet.SingleThreadModel
Ensures that servlets handle only one request at a time. This interface has no methods.

If a servlet implements this interface, you are guaranteed that no two threads
will execute concurrently in the servlet's service method.
The servlet container can make this guarantee by synchronizing access to a single instance of the servlet,
 or by maintaining a pool of servlet instances and dispatching each new request to a free servlet.

Note that SingleThreadModel does not solve all thread safety issues.
For example, session attributes and static variables can still be accessed by multiple requests
on multiple threads at the same time, even when SingleThreadModel servlets are used.
It is recommended that a developer take other means to resolve those issues
instead of implementing this interface, such as avoiding the usage of an instance variable
or synchronizing the block of the code accessing those resources.
This interface is deprecated in Servlet API version 2.4.

Read more:

* [Interface SingleThreadModel](http://docs.oracle.com/cd/E17802_01/products/products/servlet/2.5/docs/servlet-2_5-mr2/javax/servlet/SingleThreadModel.html)
* [Why SingleThreadModel deprecated?](http://stackoverflow.com/questions/2551999/why-is-javax-servlet-singlethreadmodel-deprecated)


### Include vs Forward
The main difference is that when you use forward the control is transferred to the next servlet/jsp you are calling, while include retains the control with the current servlet, it just includes the processing done by the calling servlet/jsp(like doing any out.println or other processing).

* [Include vs Forward](http://stackoverflow.com/questions/9432912/difference-between-include-and-forward-mechanism-for-request-dispatching-concept)
* [Include vs Forward vs Redirect](http://www.coderanch.com/t/366059/Servlets/java/encodeURL-purpose-place)
* [Forward](http://docs.oracle.com/javaee/7/api/javax/servlet/RequestDispatcher.html#forward%28javax.servlet.ServletRequest,%20javax.servlet.ServletResponse%29)
* [Include](http://docs.oracle.com/javaee/6/api/javax/servlet/RequestDispatcher.html#include)