package home.dgroup.servlet;

import home.dgroup.servlet.db.Comment;
import home.dgroup.servlet.db.DBStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static home.dgroup.servlet.util.ServletUtils.*;

@WebServlet(name = "Main", value="/Blog")
@MultipartConfig(
    location = "/attachments",
    fileSizeThreshold=1024*1024, // file’s > 5 MB will be directly written to disk instead of saving in memory
    maxFileSize=1024*1024*2,     // 10 MB is maximum size for a single upload
    maxRequestSize=1024*1024*20) // 5 MB is maximum size allowed for multipart/form-data request.
public class Main extends HttpServlet  {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);


    @Override
    public void init() throws ServletException {
        DBStub.initDatabase();
        LOG.info("Initialization of database finished..");
    }

    @Override
    public void destroy() {
        LOG.info("I'm going to sleep.");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String resultURL = performYourLogic(req);
        LOG.debug("Result of your operation is {}", resultURL);

        forward(resultURL, req, resp); // Forward vs Redirect. What? Why?
    }



    /**
     * Warning. Bottleneck! Please do not use this approach in your labs/applications.
     * For labs please use "Action servlet" paradigm instead of it.
     *      http://reflection-note.blogspot.com/2008/06/blog-post_10.html
     *
     * Spring MWC/JSF/etc frameworks are deprecated for education process.
     */
    private static String performYourLogic(HttpServletRequest req) {
        switch (getParameterAsString(req, "action")){
            case "toCommentsPage"   : return showCommentsPage(req);
            case "saveComment"      : return saveComment(req);
            default                 : return indexURL();
        }
    }

    private static String showCommentsPage(HttpServletRequest req) {
        addToSession(req, "comments", DBStub.comments());
        return commentsURL();
    }


    private static String saveComment(HttpServletRequest req) {
        String author = getParameterAsString(req, "author");
        String email  = getParameterAsString(req, "email");
        String text   = getParameterAsString(req, "comment");

        Comment comment = new Comment(author, email, text);
        copyAttachment(req);
        DBStub.add(comment);
        LOG.debug("Comment added: {}", comment);

        return showCommentsPage(req);
    }


    private static String indexURL(){
        return "/jsp/index.jsp";
    }
    private static String commentsURL(){
        return "/jsp/comments.jsp";
    }
}