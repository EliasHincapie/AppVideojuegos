package com.example.appvideojuegos.Modelo;
import java.io.Serializable;
public class Game implements  Serializable{
    private static final long serialVersionUID = 1L; // Añadir esto para serialización

    private int id;
    private String title;
    private String thumbnail;
    private String shortDescription;
    private String gameURL;
    private Genero genre;
    private Platform platform;
    private String publisher;
    private String developer;
    private String releaseDate;
    private String freetogameProfileURL;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getGameURL() {
        return gameURL;
    }

    public void setGameURL(String gameURL) {
        this.gameURL = gameURL;
    }

    public Genero getGenre() {
        return genre;
    }

    public void setGenre(Genero genre) {
        this.genre = genre;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getFreetogameProfileURL() {
        return freetogameProfileURL;
    }

    public void setFreetogameProfileURL(String freetogameProfileURL) {
        this.freetogameProfileURL = freetogameProfileURL;
    }
}







