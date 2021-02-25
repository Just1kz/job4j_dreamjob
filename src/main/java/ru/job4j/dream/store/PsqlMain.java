package ru.job4j.dream.store;

import ru.job4j.dream.model.*;

import java.sql.SQLException;

public class PsqlMain {
    public static void main(String[] args) throws SQLException {
        Store store = PsqlStore.instOf();
//        for (Post post : store.findAllPosts()) {
//            System.out.println(post.getId() + " " + post.getName() + " " + post.getDescription() + " " + post.getDate());
//        }
//        System.out.println(" ");
        for (Candidate candidate : store.findAllCandidates()) {
            System.out.println(candidate.getId() + " " + candidate.getName() + " " + candidate.getCity().getIdCity() + " " + candidate.getCity().getTown() + " " + candidate.getResume() + " " + candidate.getPhoto().getIdP() + " " + candidate.getPhoto().getTitle() + " " + candidate.getDate());
        }
//        store.savePost(new Post(0, "Java Job Anton", "test1"));
//        System.out.println("");
//        for (Post post : store.findAllPosts()) {
//            System.out.println(post.getId() + " " + post.getName() + " " + post.getDescription() + " " + post.getDate());
//        }
//        System.out.println("");
//        store.updatePost(new Post(4, "Java Job Anton", "test139"));
//        for (Post post : store.findAllPosts()) {
//            System.out.println(post.getId() + " " + post.getName() + " " + post.getDescription() + " " + post.getDate());
//        }
//        System.out.println("");
//        store.saveCandidate(new Candidate(0, "AntonJob4j", new City(1, "Novosibirsk"), "BlaX-BlaX", new Photo(1, "123")));
//        for (Candidate candidate : store.findAllCandidates()) {
//            System.out.println(candidate.getId() + " " + candidate.getName() + " " + candidate.getCity().getIdCity() + " " + candidate.getCity().getTown() + " " + candidate.getResume() + " " + candidate.getPhoto().getIdP() + " " + candidate.getPhoto().getTitle() + " " + candidate.getDate());
//        }
//        System.out.println("");
//        store.updateCandidate(new Candidate(6, "AntonJob4j", new City(1, "Novosibirsk"), "BlaZ-BlaZ", new Photo(1, "123")));
//        for (Candidate candidate : store.findAllCandidates()) {
//            System.out.println(candidate.getId() + " " + candidate.getName() + " " + candidate.getCity().getIdCity() + " " + candidate.getCity().getTown() + " " + candidate.getResume() + " " + candidate.getPhoto().getIdP() + " " + candidate.getPhoto().getTitle() + " " + candidate.getDate());
//        }
        System.out.println("");
        System.out.println(store.findByIdPost(4));

        System.out.println("");
        System.out.println(store.findByIdCandidate(3));
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

//        store.createUser(new User(1, "Anton", "Anton@mail.ru", "123"));
//        store.createUser(new User(2, "Anna", "Anna@mail.ru", "123"));
//        store.createUser(new User(3, "Petr", "Petr@mail.ru", "123"));
//
//        for (User user : store.findAllUsers()) {
//            System.out.println(user.getIdU() + " " + user.getName() + " " + user.getEmail() + " " + user.getPassword());
//        }
//
//        store.updateUser(new User(1, "Anton", "Anton@mail.ru", "job4j_4rever"));
//
//        for (User user : store.findAllUsers()) {
//            System.out.println(user.getIdU() + " " + user.getName() + " " + user.getEmail() + " " + user.getPassword());
//        }
//        System.out.println("");
//        System.out.println(store.findByIdUser(3));
//        System.out.println(store.findByEmailUser("Anton@mail.ru"));

        System.out.println(PsqlStore.instOf().findAllTownsName());

    }
}
