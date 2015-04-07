package home.dgroup.servlet.db;

import org.junit.Ignore;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * @author dgroup
 * @since  06.04.2015.
 */
public class DBServiceTest {

    @Test @Ignore
    public void lookup() throws NamingException {
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("build/classes"));

        try (EJBContainer container = EJBContainer.createEJBContainer( properties )) {
            Context ctx = container.getContext();

            DBService jdbc = (DBService) ctx.lookup("java:global/classes/DBService");

            jdbc.add(new Comment("Tom",  "Tom@edu.com",  "Comment #1"));
            jdbc.add(new Comment("Dan",  "Dan@edu.com",  "Comment #2"));
            jdbc.add(new Comment("Mike", "Mike@edu.com", "Comment #3"));

            Collection<Comment> comments = jdbc.comments();
            assertEquals("Amount of comments is wrong", 3, comments.size());
        }
    }

}