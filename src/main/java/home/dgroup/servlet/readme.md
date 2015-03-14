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