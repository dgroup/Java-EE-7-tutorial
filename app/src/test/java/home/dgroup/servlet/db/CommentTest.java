package home.dgroup.servlet.db;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author dgroup
 * @since 30.03.2015
 */
public class CommentTest {
    private static final Logger LOG = LoggerFactory.getLogger(CommentTest.class);
    private static final long FEW_SECONDS = 2000;

    private static EntityManagerFactory emf;
    private EntityManager eManager;
    private EntityTransaction trx;
    private Comment comment;

    @BeforeClass
    public static void initEntityFactory(){
        emf = Persistence.createEntityManagerFactory("QA_schema");
    }
    @AfterClass
    public static void closeEntityFactory(){
        emf.close();
    }


    @Before
    public void initEntityManager() {
        eManager = emf.createEntityManager();
        trx = eManager.getTransaction();
        comment = new Comment("dgroup", "dgroup@home.com", "This is a nice comment");
    }

    @After
    public void closeEntityManager() {
        if (eManager != null) {
            eManager.close();
        }
    }


    @Test
    public void newComment() {
        persist( comment );
        assertTrue("ID should not be null", comment.id() > 0);

        Comment persistedComment = eManager.createNamedQuery("findById", Comment.class)
            .setParameter("id", comment.id())
            .getSingleResult();

        // can be replaced by `assertEquals`, but i guess it more readable
        assertThat( persistedComment.author(), is( comment.author() ));
        assertThat( persistedComment.email(), is( comment.email() ));
        assertThat( persistedComment.text(), is( comment.text() ));
    }


    @Test(timeout = FEW_SECONDS)
    public void selectAllRows(){
        List<Comment> comments = eManager.createQuery("select c from Comment c", Comment.class)
            .getResultList();

        LOG.debug("Amount is {} and data {}", comments.size(), comments);
        assertTrue("Comment's amount should be more 3", comments.size() >= 3);
    }


    @Test(expected = ConstraintViolationException.class)
    public void nullAuthor(){
        comment.setAuthor(null);
        persist(comment);
    }

    @Test(expected = ConstraintViolationException.class)
    public void longAuthor(){
        comment.setAuthor("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        persist( comment );
    }


    @Test(expected = ConstraintViolationException.class)
    public void incorrectEmail(){
        comment.setEmail("wrong @email format.ru");
        persist( comment );
    }



    private void persist(Object entity){
        trx.begin();
        eManager.persist(entity);
        trx.commit();
    }
}