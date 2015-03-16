package home.dgroup.servlet.asynchronous;

import home.dgroup.servlet.db.Comment;
import home.dgroup.servlet.db.DBStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static home.dgroup.util.ServletUtils.*;

/**
 * @author dgroup
 * @since 15.03.2015
 */

@MultipartConfig(
    location = "/attachments",
    fileSizeThreshold=1024*1024*50 , // file’s > 50 MB will be directly written to disk instead of saving in memory
    maxFileSize    = 1024*1024*1024, // 1 GB is maximum size for a single upload
    maxRequestSize = 1024*1024*200)  // 200 MB is maximum size allowed for multipart/form-data request.

@WebServlet(name = "SaveComment", value="/SaveComment", asyncSupported = true)

public class SaveComment extends HttpServlet{
    private static final Logger LOG = LoggerFactory.getLogger(SaveComment.class);


    @Override
    public void init() throws ServletException {
        LOG.debug("I've started.");
    }

    @Override
    public void destroy() {
        LOG.debug("I'm done.");
    }



    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String author = getParameterAsString(req, "author");
        String email  = getParameterAsString(req, "email");
        String text   = getParameterAsString(req, "comment");

        assertString(author, "Author can't be empty");
        assertString(email, "Email can't be empty");
        assertString(text, "Text can't be empty");

        copyAttachmentInAsyncMode( req );
        DBStub.add( new Comment(author, email, text) );

        forward("/Blog?action=toCommentsPage", req, resp);
    }



    private void copyAttachmentInAsyncMode(HttpServletRequest req) {
        AsyncContext acontext = req.startAsync();
        copyAttachment(req);
        acontext.complete();
    }
}