package home.dgroup.servlet.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;


/**
 * Warning. Do not use such approach. Just for database stub.
 */
@Stateless
public class DBService {
    private static final Logger LOG = LoggerFactory.getLogger( DBService.class );

    @PersistenceContext(unitName = "APP_schema")
    private EntityManager manager;


    @Transactional
    public <T> T add(T entity) {
        manager.persist(entity);
        LOG.debug("Comment have added: {}", entity);
        return entity;
    }


    public Collection<Comment> comments() {
        return manager.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }
}