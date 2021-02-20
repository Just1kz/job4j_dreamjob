package ru.job4j.dream.model;

import java.util.Objects;

public class User {
    private int idU;
    private String name;
    private String email;
    private String password;

    public User(int idU, String name, String email, String password) {
        this.idU = idU;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return idU == user.idU;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idU);
    }

    @Override
    public String toString() {
        return "User{"
                + "idU="
                + idU
                + ", name='"
                + name
                + '\''
                + ", email='"
                + email
                + '\''
                + ", password='"
                + password
                + '\''
                + '}';
    }
}
