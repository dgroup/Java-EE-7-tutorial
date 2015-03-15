### 17.12 Asynchronous Processing
Web containers in application servers normally use a server thread per client request.

There are two common scenarios in which a thread associated with a request can be sitting idle.

* The thread needs to wait for a resource to become available or process data before building the response. For example, an application may need to query a database or access data from a remote web service before generating the response.
* The thread needs to wait for an event before generating the response. For example, an application may have to wait for a JMS message, new information from another client, or new data available in a queue before generating the response.

The `javax.servlet.AsyncContext` class provides the functionality that you need
to perform asynchronous processing inside service methods.
To obtain an instance of AsyncContext, call the `startAsync()` method
on the request object of your service method

Read more:

* [17.12 Asynchronous Processing](https://docs.oracle.com/javaee/7/tutorial/servlets012.htm)
* [Asynchronous servlet best practices](http://www-01.ibm.com/support/knowledgecenter/SSAW57_8.0.0/com.ibm.websphere.nd.doc/info/ae/ae/cweb_asyncservlet.html)
* [How to use Asynchronous Servlets to improve performance](https://plumbr.eu/blog/how-to-use-asynchronous-servlets-to-improve-performance)
* [Asynchronous servlets in Servlet Spec 3.0](http://www.softwareengineeringsolutions.com/blogs/2010/08/13/asynchronous-servlets-in-servlet-spec-3-0/)
* [Asynchronous Servlet – A new feature in Servlet 3.0 and Java EE 6](http://www.javabeat.net/asynchronous-servlet-servlet-3-0/)
* [Async Servlets](http://www.jayway.com/2014/05/16/async-servlets/)


### Uploading Files
A new annotation, `javax.servlet.annotation.MultipartConfig`,
is used to indicate that the servlet on which it is declared expects requests
to be made using the multipart/form-data MIME type.
Servlets that are annotated with `@MultipartConfig` can retrieve
the Part components of a given multipart/form-data request by calling the request.getPart(String name)
or request.getParts() method.

The `@MultipartConfig` annotation supports the following optional attributes:

* `location`: An absolute path to a directory on the file system. The location attribute does not support a path relative to the application context. This location is used to store files temporarily while the parts are processed or when the size of the file exceeds the specified fileSizeThreshold setting. `The default location is ""`.
* `fileSizeThreshold`: The file size in bytes after which the file will be temporarily stored on disk. `The default size is 0 bytes`.
* `MaxFileSize`: The maximum size allowed for uploaded files, in bytes. If the size of any uploaded file is greater than this size, the web container will throw an exception (IllegalStateException). `The default size is unlimited`.
* `maxRequestSize`: The maximum size allowed for a multipart/form-data request, in bytes. The web container will throw an exception if the overall size of all uploaded files exceeds this threshold. `The default size is unlimited`.

For, example, the `@MultipartConfig` annotation could be constructed as follows:

````Java
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024,
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
````
Instead of using the `@MultipartConfig` annotation to hard-code these attributes in your file upload servlet, you could add the following as a child element of the servlet configuration element in the web.xml file:

````XML
<multipart-config>
    <location>/tmp</location>
    <max-file-size>20848820</max-file-size>
    <max-request-size>418018841</max-request-size>
    <file-size-threshold>1048576</file-size-threshold>
</multipart-config>
````

Read more:

* [Uploading Files with Java Servlet Technology](https://docs.oracle.com/javaee/7/tutorial/servlets011.htm)