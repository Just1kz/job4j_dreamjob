package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Photo;
import ru.job4j.dream.model.Post;

import java.sql.SQLException;

public class PsqlMain {
    public static void main(String[] args) throws SQLException {
        Store store = PsqlStore.instOf();
//
//        for (Post post : store.findAllPosts()) {
//            System.out.println(post.getId() + " " + post.getName() + " " + post.getDescription());
//        }
//        for (Candidate candidate : store.findAllCandidates()) {
//            System.out.println(candidate.getId() + " " + candidate.getName());
//        }
//        store.savePost(new Post(0, "Java Job Anton", "test1"));
//        System.out.println("");
//        for (Post post : store.findAllPosts()) {
//            System.out.println(post.getId() + " " + post.getName() + " " + post.getDescription());
//        }
//        System.out.println("");
//        store.updatePost(new Post(5, "Java Job Anton", "test139"));
//        for (Post post : store.findAllPosts()) {
//            System.out.println(post.getId() + " " + post.getName() + " " + post.getDescription());
//        }
//        System.out.println("");
//        store.createCandidate(new Candidate(4, "AntonJob4j", new Photo(1, "123")));
//        for (Candidate candidate : store.findAllCandidates()) {
//            System.out.println(candidate.getId() + " " + candidate.getName() + " "+ candidate.getPhoto().getTitle());
//        }
        System.out.println("");
        store.updateCandidate(new Candidate(6, "La-La", new Photo(1)));
        for (Candidate candidate : store.findAllCandidates()) {
            System.out.println(candidate.getId() + " " + candidate.getName() + " " + candidate.getPhoto().getTitle());
        }
//        System.out.println("");
//        System.out.println(store.findByIdPost(5));

//        System.out.println("");
//        System.out.println(store.findByIdCandidate(2));
//        System.out.println("");

//        for (Post post : store.findAllPosts()) {
//            System.out.println(post.getId() + " " + post.getName() + " " + post.getDescription());
//        }
//        System.out.println("");
//        store.deletePost(4);
//        System.out.println("");
//        for (Post post : store.findAllPosts()) {
//            System.out.println(post.getId() + " " + post.getName() + " " + post.getDescription());
//        }

//        for (Candidate candidate : store.findAllCandidates()) {
//            System.out.println(candidate.getId() + " " + candidate.getName());
//        }
//        System.out.println("");
////        store.deleteCandidate(3);
////        System.out.println("");
//        for (Candidate candidate : store.findAllCandidates()) {
//            System.out.println(candidate.getId() + " " + candidate.getName());
//        }
//
//        System.out.println(store.findPhotoById(2));

    }
}
