package ru.job4j.dream.store;

import ru.job4j.dream.model.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemStore implements Store {
    private static final MemStore INST = new MemStore();
    private static final AtomicInteger POST_ID = new AtomicInteger(4);
    private static final AtomicInteger CANDIDATE_ID = new AtomicInteger(4);

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private MemStore() {
//        posts.put(1, new Post(1, "Junior Java Job", "test1"));
//        posts.put(2, new Post(2, "Middle Java Job", "test1"));
//        posts.put(3, new Post(3, "Senior Java Job", "test1"));
        candidates.put(1, new Candidate(1, "Junior Java", new City(1, "Novosibirsk"), "bla-bla", new Photo(0, "DSC_1827.jpg")));
        candidates.put(2, new Candidate(2, "Middle Java",  new City(1, "Novosibirsk"), "bla-bla", new Photo(0, "DSC_1827.jpg")));
        candidates.put(3, new Candidate(3, "Senior Java",  new City(1, "Novosibirsk"), "bla-bla", new Photo(0, "DSC_1827.jpg")));
    }

    public static MemStore instOf() {
        return INST;
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    public void savePost(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    public int size() {
        return posts.size();
    }

    @Override
    public Collection<City> findAllTowns() {
        return null;
    }

    @Override
    public Collection<String> findAllTownsName() {
        return null;
    }

    @Override
    public Post createPost(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
        return post;
    }

    @Override
    public Post updatePost(Post post) {
        posts.put(post.getId(), post);
        return post;
    }

    public Post findByIdPost(int id) {
        return posts.get(id);
    }

    public void saveCandidate(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(CANDIDATE_ID.incrementAndGet());
        }
        candidates.put(candidate.getId(), candidate);
    }

    @Override
    public Candidate createCandidate(Candidate candidate) {
        return null;
    }

    @Override
    public Candidate updateCandidate(Candidate candidate) {
        return null;
    }

    public Candidate findByIdCandidate(int id) {
        return candidates.get(id);
    }

    @Override
    public boolean deletePost(int id) {
        return false;
    }

    @Override
    public boolean deleteCandidate(int id) {
        return false;
    }

    @Override
    public Photo findPhotoById(int id) {
        return null;
    }

    @Override
    public Photo createPhoto(Photo photo) {
        return null;
    }

    @Override
    public List<String> findAllNamePhoto() {
        return null;
    }

    @Override
    public boolean deletePhoto(int id) {
        return false;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public User findByIdUser(int id) {
        return null;
    }

    @Override
    public User findByEmailUser(String email) {
        return null;
    }

    @Override
    public Collection<User> findAllUsers() {
        return null;
    }
}
