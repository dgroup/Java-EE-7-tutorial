package home.dgroup.servlet.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Warning. Do not use such approach. Just for database stub.
 */
@Deprecated
public final class DBStub {
    private static final Logger LOG = LoggerFactory.getLogger(DBStub.class);

    private static final String URL  = "jdbc:h2:~/demo";
    private static final String USER = "sa";
    private static final String PASS = "";

    private static final String DROP_COMMENTS =
        "DROP TABLE if exists comments";

    private static final String SELECT_ALL =
        "select author, comment from comments order by created_date";

    private static final String INSERT_COMMENT =
        "INSERT into comments (author, email, comment, created_date) VALUES (?, ?, ?, ?)";

    private static final String CREATE_COMMENTS =
        "create table comments(" +
        "  author varchar2(255)," +
        "  email varchar2(255),"  +
        "  comment varchar2(3000)," +
        "  created_date date)";


    private DBStub() {}


    @Deprecated
    public static void initDatabase() {
        try(Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            try(PreparedStatement prep = conn.prepareStatement(DROP_COMMENTS)){
                prep.execute();
            }

            try(PreparedStatement prep = conn.prepareStatement(CREATE_COMMENTS)) {
                prep.execute();
            }

            try(PreparedStatement prep = conn.prepareStatement(INSERT_COMMENT)) {
                prep.setString(1, "Tolian");
                prep.setString(2, "Tolian@mail.ru");
                prep.setString(3, "What? Everybody must die...");
                prep.setDate(4, new Date(System.currentTimeMillis()));
                prep.execute();
            }

            try(PreparedStatement prep = conn.prepareStatement(INSERT_COMMENT)) {
                prep.setString(1, "Hank");
                prep.setString(2, "hacker@google.com");
                prep.setString(3, "From my point of view it's very simple.");
                prep.setDate(4, new Date(System.currentTimeMillis()));
                prep.execute();
            }

            try(PreparedStatement prep = conn.prepareStatement(INSERT_COMMENT)) {
                prep.setString(1, "xxxx");
                prep.setString(2, "xxxx@xxxxx.ua");
                prep.setString(3, "Stupid question at all... O_o");
                prep.setDate(4, new Date(System.currentTimeMillis()));
                prep.execute();
            }
            conn.commit();

        } catch (SQLException e) {
            LOG.error("Unable to initialize database", e);
            throw new DatabaseException(e);
        }
    }


    @Deprecated
    public static void add(Comment comment) {
        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement prep = conn.prepareStatement(INSERT_COMMENT)) {
            prep.setString(1, comment.getAuthor());
            prep.setString(2, comment.getEmail());
            prep.setString(3, comment.getText());
            prep.setDate(4, new Date(System.currentTimeMillis()));
            prep.execute();
            conn.commit();

        } catch (SQLException e) {
            LOG.error("Unable to add comment.", e);
            throw new DatabaseException(e);
        }
    }


    @Deprecated
    public static Collection<Comment> comments() {
        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement prep = conn.prepareStatement(SELECT_ALL);
            ResultSet res = prep.executeQuery()) {

            List<Comment> comments = new ArrayList<>();
            while (res.next()) {
                Comment comm = new Comment();
                comm.setAuthor  ( res.getString("author" ) );
                comm.setText    ( res.getString("comment") );
                comments.add(comm);
            }
            return comments;

        } catch (SQLException e){
            LOG.error("Unable to load comments.", e);
            throw new DatabaseException(e);
        }
    }
}