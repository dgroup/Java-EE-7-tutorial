## 17.6 Filtering Requests and Responses

  A filter is an object that can transform the header and content (or both) of a request or response.
Filters differ from web components in that filters usually do not themselves create a response.
Instead, a filter provides functionality that can be "attached" to any kind of web resource.
Consequently, a filter should not have any dependencies on a web resource for which it is acting as a filter;
this way, it can be composed with more than one type of web resource.

The main tasks that a filter can perform are as follows:
1. Query the request and act accordingly;
2. Block the request-and-response pair from passing any further;
3. Modify the request headers and data. You do this by providing a customized version of the request;
4. Modify the response headers and data. You do this by providing a customized version of the response;
5. Interact with external resources.

Applications of filters include authentication, logging, image conversion, data compression, encryption,
tokenizing streams, XML transformations, and so on.

You can configure a web resource to be filtered by a chain of zero, one, or more filters in a specific order.
This chain is specified when the web application containing the component is deployed and is instantiated
when a web container loads the component.

Read [more](https://docs.oracle.com/javaee/7/tutorial/servlets006.htm)