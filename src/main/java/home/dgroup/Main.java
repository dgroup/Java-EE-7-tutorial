package home.dgroup;

import home.dgroup.db.DBStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * implements SingleThreadModel
 *      If a servlet implements this interface,
 *      no two threads will execute concurrently
 *      in the servlet's service method.
 */
@WebServlet(name = "Main", value="/Blog")
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

        RequestDispatcher dispatcher = req.getRequestDispatcher( resultURL );
        dispatcher.forward(req, resp);  // Forward vs Redirect. What? Why?
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
        req.getSession().setAttribute("comments", DBStub.comments());
        return commentsURL();
    }


    private static String saveComment(HttpServletRequest req) {
        String author = getParameterAsString(req, "author");
        String email  = getParameterAsString(req, "email");
        String text   = getParameterAsString(req, "comment");

        Comment comment = new Comment(author, email, text);
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

    private static String getParameterAsString(HttpServletRequest req, String parameterName){
        Object par = req.getParameter( parameterName );
        if (par == null)
            return "";
        LOG.debug("Extracted {} : {}", parameterName, par.toString());
        return par.toString();
    }
}