package home.dgroup.servlet;

import home.dgroup.servlet.db.DBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
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

    @Inject
    private DBService jdbc;


    @Override
    public void init() throws ServletException {
        LOG.info("Initialization of database finished..");
    }

    @Override
    public void destroy() {
        LOG.info("I'm going to sleep.");
    }


    /**
     * Warning. Bottleneck! Please do not use this approach in your labs/applications.
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        String resultURL = "/jsp/index.jsp";

        String what = getParameterAsString(req, "action");
        if ("toCommentsPage".equals(what) ) {
            addToSession(req, "comments", jdbc.comments());
            resultURL = "/jsp/comments.jsp";
        }
        LOG.debug("Result of your operation is {}", resultURL);

        forward(resultURL, req, resp); // Forward vs Redirect. What? Why?
    }
}