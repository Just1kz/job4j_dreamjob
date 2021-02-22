package ru.job4j.dream.model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Post {
    private int id;
    private String name;
    private String description;
    private final Date created = new Date();
    private final String date = changeFormatDate();

    public Post(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Post() {
    }

    public String changeFormatDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss  dd.MM.yyyy");
        return dateFormat.format(created);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return name.equals(post.name)
                && Objects.equals(description, post.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", description='"
                + description
                + '\''
                + ", created="
                + created
                + ", date='"
                + date
                + '\''
                + '}';
    }
}
