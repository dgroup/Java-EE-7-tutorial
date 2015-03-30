package home.dgroup.servlet.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;


/**
 * Warning. Do not use such approach. Just for database stub.
 *
 * TODO: replace it via CDI
 */
@Deprecated
public final class DBStub {
    private static final Logger LOG = LoggerFactory.getLogger( DBStub.class );
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("APP_schema");

    private DBStub() {}


    public static void add(Comment comment) {
        EntityManager eManager = EMF.createEntityManager();
        try {
            EntityTransaction trx = eManager.getTransaction();
            trx.begin();
            eManager.persist(comment);
            trx.commit();
        } finally {
            eManager.close();
        }
        LOG.debug("Comment have added: {}", comment);
    }


    public static Collection<Comment> comments() {
        EntityManager eManager = EMF.createEntityManager();
        try {
            return eManager.createQuery("select c from Comment c", Comment.class)
                    .getResultList();
        } finally {
            eManager.close();
        }
    }


    public static void close(){
        if (EMF != null) {
            EMF.close();
        }
    }
}