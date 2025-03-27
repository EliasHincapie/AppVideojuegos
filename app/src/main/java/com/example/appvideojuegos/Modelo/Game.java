package com.example.appvideojuegos.Modelo;

public class Game {
    private long id;
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

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getThumbnail() { return thumbnail; }
    public void setThumbnail(String value) { this.thumbnail = value; }

    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String value) { this.shortDescription = value; }

    public String getGameURL() { return gameURL; }
    public void setGameURL(String value) { this.gameURL = value; }

    public Genero getGenre() { return genre; }
    public void setGenre(Genero value) { this.genre = value; }

    public Platform getPlatform() { return platform; }
    public void setPlatform(Platform value) { this.platform = value; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String value) { this.publisher = value; }

    public String getDeveloper() { return developer; }
    public void setDeveloper(String value) { this.developer = value; }

    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String value) { this.releaseDate = value; }

    public String getFreetogameProfileURL() { return freetogameProfileURL; }
    public void setFreetogameProfileURL(String value) { this.freetogameProfileURL = value; }
}





