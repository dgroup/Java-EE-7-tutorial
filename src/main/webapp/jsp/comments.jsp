<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Demo Project</title>
  <meta   charset = "utf-8">
  <meta   name    = "viewport"
          content = "width=device-width, initial-scale=1">
  <link   rel     = "stylesheet"
          href    = "css/bootstrap.min.css">
  <script src     = "js/jquery-1.11.1.min.js"></script>
</head>
<body>

<div class="container">
  <div class="jumbotron">
    <h2>Are the Servlet & JSP simple technologies?</h2>

    <div    class = "progress">
      <div  class = "progress-bar progress-bar-success"
            role  = "progressbar"
            style = "width: 60%">
        Agree
      </div>

      <div  class = "progress-bar progress-bar-danger"
            role  = "progressbar"
            style = "width:  15%">
        Disagree
      </div>

      <div  class = "progress-bar progress-bar-warning"
            role  = "progressbar"
            style = "width: 25%">
        Tentative
      </div>
    </div>
  </div>

  <div class="row">
    <div            class     = "col-sm-3 col-md-6 col-lg-4">
      <form         role      = "form"
                    action    = "Blog"
                    enctype   = "multipart/form-data"
                    method    = "post">

        <div        class     = "form-group">
          <label    for       = "author">Author:</label>
          <input    type      = "text"
                    class     = "form-control"
                    id        = "author"
                    name      = "author"
                    required  = "true">
        </div>

        <div        class     = "form-group">
          <label    for       = "email">Email:</label>
          <input    type      = "text"
                    class     = "form-control"
                    id        = "email"
                    name      = "email"
                    required  = "true"
                    pattern   = "[^@]+@[^@]+\.[a-zA-Z]{2,6}"
                    title     = "xxx@xxx.xx">
        </div>

        <div        class     = "form-group">
          <label    for       = "comment">Comment:</label>
          <textarea class     = "form-control"
                    rows      = "5"
                    id        = "comment"
                    name      = "comment"
                    required  = "required"
                    maxlength = "3000"></textarea>
        </div>

        <div        class     = "form-group">
          <label    for       = "attachment">Attachment:</label>
          <input    type      = "file"
                    id        = "attachment"
                    name      = "attachment"/>
        </div>

        <input      type      = "hidden"
                    name      = "action"
                    value     = "saveComment">

        <button     type      = "submit"
                    class     = "btn btn-success">Send</button>
      </form>
    </div>
  </div>

  <div class="row">
    <div class="col-sm-12">
        <c:forEach var="comm" items="${sessionScope['comments']}">
            <h3><c:out value="${comm.author}"/></h3>
            <p> <c:out value="${comm.text}"/></p>
            <br/>
        </c:forEach>
    </div>
  </div>

</div>

</body>
</html>