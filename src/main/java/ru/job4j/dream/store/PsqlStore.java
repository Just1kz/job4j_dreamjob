package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Photo;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;


public class PsqlStore implements Store {
    private final BasicDataSource pool = new BasicDataSource();

    private static final Logger LOG = LoggerFactory.getLogger(PsqlStore.class.getName());

    private PsqlStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("db.properties")
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Collection<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM post")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(new Post(it.getInt("id"), it.getString("name"), it.getString("description")));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return posts;
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM candidate t1 left join photo t2 on t2.idP = t1.photo_id")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(new Candidate(it.getInt("idC"), it.getString("name"),
                            new Photo(it.getInt("idP"), it.getString("title"))));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return candidates;
    }

    @Override
    public void savePost(Post post) {
        if (post.getId() == 0) {
            createPost(post);
        } else {
            updatePost(post);
        }
    }

    public Post createPost(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO post(name, description) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return post;
    }

    public Post updatePost(Post post) {
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "update post set name = ?, description = ? where id = ?;", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setInt(3, post.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return post;
    }

    @Override
    public void saveCandidate(Candidate candidate) {
        if (candidate.getId() == 0) {
            createCandidate(candidate);
        } else {
            updateCandidate(candidate);
        }
    }

    @Override
    public Candidate createCandidate(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO candidate(name, photo_id) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getPhoto().getIdP());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return candidate;
    }

    @Override
    public Candidate updateCandidate(Candidate candidate) {
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "update candidate set name = ?, photo_id = ? where idC = ?;", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getPhoto().getIdP());
            ps.setInt(3, candidate.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return candidate;
    }

    @Override
    public Post findByIdPost(int id) throws SQLException {
        Post post = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "select * from post where id = ?;", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    post = new Post(
                            Integer.parseInt(rs.getString("id")),
                            rs.getString("name"),
                            rs.getString("description"));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return post;
    }

    @Override
    public Candidate findByIdCandidate(int id) throws SQLException {
        Candidate candidate = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "SELECT * FROM candidate t1 left join photo t2 on t2.idP = t1.photo_id where t1.idC = ?;", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    candidate = new Candidate(rs.getInt("idC"), rs.getString("name"),
                            new Photo(rs.getInt("idP"), rs.getString("title")));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return candidate;
    }

    @Override
    public boolean deletePost(int id) {
        boolean rsl = false;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "delete from post where id = ?;", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            ps.execute();
            rsl = true;
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rsl;
    }

    @Override
    public boolean deleteCandidate(int id) {
        boolean rsl = false;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "delete from candidate where id = ?;", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            ps.execute();
            rsl = true;
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rsl;
    }

    @Override
    public Photo findPhotoById(int id) {
        Photo photo = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("select * from photo where idP = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                photo = new Photo(
                        rs.getInt(1),
                        rs.getString("title")
                );
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return photo;
    }

    @Override
    public Photo createPhoto(Photo photo) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO photo(title) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, photo.getTitle());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    photo.setIdP(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return photo;
    }

    @Override
    public List<String> findAllNamePhoto() {
        List<String> photo = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("select title from photo;")) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    photo.add(it.getString("title"));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return photo;
    }

    @Override
    public boolean deletePhoto(int id) {
        boolean rsl = false;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "delete from photo where idP = ?;", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            ps.execute();
            rsl = true;
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rsl;
    }

    @Override
    public User createUser(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO users(name, email, password) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setIdU(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        boolean rsl = false;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "update users set name = ?, email = ?, password = ? where idu = ?;", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getIdU());
            ps.executeUpdate();
            rsl = true;
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rsl;
    }

    @Override
    public User findByIdUser(int id) {
        User user = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "select * from users where idu = ?;", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            Integer.parseInt(rs.getString("idu")),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return user;
    }

    @Override
    public Collection<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM users")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    users.add(new User(it.getInt("idu"),
                            it.getString("name"),
                            it.getString("email"),
                            it.getString("password")));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return users;
    }
}
