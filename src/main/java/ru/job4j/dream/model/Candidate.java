package ru.job4j.dream.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Candidate {
    private int id;
    private String name;
    private City city;
    private String resume;
    private Photo photo;
    private final Date created = new Date();
    private String date = changeFormatDate();

    public Candidate(int id, String name, City city, String resume, Photo photo) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.resume = resume;
        this.photo = photo;
    }

    public Candidate(int id, String name, City city, String resume, Photo photo, String date) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.resume = resume;
        this.photo = photo;
        this.date = date;
    }

    public Candidate(String name, City city, String resume, Photo photo, String date) {
        this.name = name;
        this.city = city;
        this.resume = resume;
        this.photo = photo;
        this.date = date;
    }

    public String changeFormatDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss  dd.MM.yyyy");
        return dateFormat.format(created);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getResume() {
        return resume;
    }

    public Photo getPhoto() {
        return photo;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Candidate{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", city="
                + city
                + ", resume='"
                + resume
                + '\''
                + ", photo="
                + photo
                + ", created="
                + created
                + ", date='"
                + date
                + '\''
                + '}';
    }
}
