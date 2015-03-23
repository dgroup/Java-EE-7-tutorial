package home.dgroup.servlet;

import home.dgroup.servlet.db.DBStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static home.dgroup.servlet.util.ServletUtils.*;

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

        String what = getParameterAsString(req, "action");

        if ("toCommentsPage".equals(what) ) {
            addToSession(req, "comments", DBStub.comments());
            return "/jsp/comments.jsp";
        }

        return "/jsp/index.jsp";
    }
}