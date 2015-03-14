package home.dgroup.servlet.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * @author dgroup on 14.03.2015.
 */
public final class ServletUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ServletUtils.class);

    private ServletUtils(){
        throw new IllegalStateException("Not supported");
    }

    public static String getParameterAsString(HttpServletRequest req, String parameterName){
        notNull( req, "Request can't be a null");
        notNull( parameterName, "Key can't be a null");
        Object par = req.getParameter( parameterName );
        if (par == null)
            return "";
        LOG.debug("Extracted {} : {}", parameterName, par.toString());
        return par.toString();
    }



    public static void addToSession(HttpServletRequest req, String key, Object value){
        addToSession(req, key, value, true);
    }
    public static void addToSession(HttpServletRequest req, String key, Object value, boolean create){
        notNull(req,   "Request can't be a null");
        add(key, value, req.getSession(create));
    }
    public static void add(String key, Object value, HttpSession session){
        notNull( key,     "Key can't be a null" );
        notNull( value,   "Value can't be a null" );
        notNull( session, "Session can't be a null" );
        session.setAttribute(key, value);
    }


    public static void forward(String url, HttpServletRequest req, HttpServletResponse resp){
        assertUrl(url);
        forward (req.getRequestDispatcher(url), req, resp);
    }
    public static void forward(RequestDispatcher dispatcher, HttpServletRequest req, HttpServletResponse resp){
        notNull(dispatcher, "Dispatcher can't be a null");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException|IOException e){
            throw new ForwardException(e);
        }
    }


    public static void include(String url, HttpServletRequest req, HttpServletResponse resp){
        assertUrl(url);
        forward (req.getRequestDispatcher(url), req, resp);
    }
    public static void include(RequestDispatcher dispatcher, HttpServletRequest req, HttpServletResponse resp){
        notNull(dispatcher, "Dispatcher can't be a null");
        try {
            dispatcher.include(req, resp);
        } catch (ServletException|IOException e){
            throw new IncludeException(e);
        }
    }

    private static void assertUrl(String url){
        assertString(url, "URL can't be a blank");
    }
    private static void assertString(String text, String message){
        notBlank(text, message);
    }





    public static void copyAttachment(HttpServletRequest request){
        copyFile(request, "attachment");
    }

    /**
     * @param parameter it's a name in form
     * */
    public static void copyFile(HttpServletRequest request, String parameter){
        notNull(request, "Request can't be a null");
        assertString(parameter, "Parameter can't be a null");
        try {
            for(Part p : request.getParts()) {
                if (parameter.equalsIgnoreCase(p.getName()))
                    p.write( getFileName(p) );
            }
        } catch (IOException|ServletException e) {
            throw new CopyFileException(e);
        }
    }

    private static String getFileName(Part part) {
        String headerContent = part.getHeader("content-disposition");
        for (String token : headerContent.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 2, token.length() - 1);
            }
        }
        return "";
    }
}
