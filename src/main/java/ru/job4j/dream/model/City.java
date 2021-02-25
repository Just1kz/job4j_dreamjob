package ru.job4j.dream.model;

import java.util.Objects;

public class City {
    private int idCity;
    private String town;

    public City() {
    }

    public City(int idCity) {
        this.idCity = idCity;
    }

    public City(int idCity, String town) {
        this.idCity = idCity;
        this.town = town;
    }

    public City(String town) {
        this.town = town;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city = (City) o;
        return idCity == city.idCity
                && Objects.equals(town, city.town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCity, town);
    }

    @Override
    public String toString() {
        return "City{"
                +  "idCity="
                + idCity
                + ", town='"
                + town
                + '\''
                + '}';
    }
}
