package com.example.appvideojuegos.Modelo;
import java.io.Serializable;
public class Game implements  Serializable{
    private static final long serialVersionUID = 1L; // Añadir esto para serialización

    private int id;
    private String title;
    private String thumbnail;
    private String short_description;
    private String game_url;
    private Genre genre;
    private Platform platform;
    private String publisher;
    private String developer;
    private String release_date;
    private String freetogame_Profile_url;

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
        return short_description;
    }

    public void setShortDescription(String shortDescription) {
        this.short_description = shortDescription;
    }

    public String getGameURL() {
        return game_url;
    }

    public void setGameURL(String gameURL) {
        this.game_url = gameURL;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
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
        return release_date;
    }

    public void setReleaseDate(String releaseDate) {
        this.release_date = releaseDate;
    }

    public String getFreetogameProfileURL() {
        return freetogame_Profile_url;
    }

    public void setFreetogameProfileURL(String freetogameProfileURL) {
        this.freetogame_Profile_url = freetogameProfileURL;
    }
}







