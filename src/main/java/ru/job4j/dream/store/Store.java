package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Photo;
import ru.job4j.dream.model.Post;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    void savePost(Post post);

    Post createPost(Post post);

    Post updatePost(Post post);

    void saveCandidate(Candidate candidate);

    Candidate createCandidate(Candidate candidate);

    Candidate updateCandidate(Candidate candidate);

    Post findByIdPost(int id) throws SQLException;

    Candidate findByIdCandidate(int id) throws SQLException;

    boolean deletePost(int id);

    boolean deleteCandidate(int id);

    Photo findPhotoById(int id);

    Photo createPhoto(Photo photo);

    List<String> findAllNamePhoto();

    boolean deletePhoto(int id);
}
