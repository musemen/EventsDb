package ca.ubc.cs304.model;

public class Performer {

    private String performerID;
    private String genre;
    private String name;
    
    public Performer(String p, String g, String n){
        this.performerID = p;
        this.genre = g;
        this.name = n;
    }

    public String getPerformerID() {
        return performerID;
    }

    public void setPerformerID(String performerID) {
        this.performerID = performerID;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}