package ru.job4j.dream.model;

import java.util.Objects;

public class Photo {
    private int idP;
    private String title;

    public Photo() {
    }

    public Photo(int id) {
        this.idP = id;
    }

    public Photo(int id, String title) {
        this.idP = id;
        this.title = title;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Photo photo = (Photo) o;
        return idP == photo.idP
                && Objects.equals(title, photo.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idP, title);
    }

    @Override
    public String toString() {
        return "Photo{"
                + "id="
                + idP
                + ", title='"
                + title
                + '\''
                + '}';
    }
}
