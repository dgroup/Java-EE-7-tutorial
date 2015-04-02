package home.dgroup.servlet.db;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
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

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("QA_schema");
    private EntityManager eManager;
    private EntityTransaction trx;


    @Before
    public void initEntityManager() {
        eManager = emf.createEntityManager();
        trx = eManager.getTransaction();
    }

    @After
    public void closeEntityManager() {
        if (eManager != null) {
            eManager.close();
        }
    }


    @Test
    public void shouldCreateStubComment() {
        Comment comment = new Comment("dgroup", "dgroup@home.ru", "This is a nice comment");
        LOG.debug("Initial {}", comment);

        persist( comment );
        assertTrue("ID should not be null", comment.id() > 0);

        Comment persistedComment = eManager.createNamedQuery("findCommentById", Comment.class)
            .setParameter("id", comment.id())
            .getSingleResult();

        LOG.debug("Persisted {}", persistedComment);

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
        persist( new Comment(null, "aaa@bbb.cc", "'author' should not be a null") );
    }

    @Test(expected = ConstraintViolationException.class)
    @Ignore // javax/el/PropertyNotFoundException
    public void longAuthor(){
        Comment comment = new Comment();
        comment.setEmail("bbb@ccc.dd");
        comment.setText("Field 'author' is more than 40");
        comment.setAuthor("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        persist( comment );
    }


    private void persist(Comment comment){
        trx.begin();
        eManager.persist(comment);
        trx.commit();
    }
}