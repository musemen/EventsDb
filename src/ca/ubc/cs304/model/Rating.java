package ca.ubc.cs304.model;

public class Rating {

    private String ratingID;
    private int value;
    private String description;

    public Rating(String r, int v, String d){
        this.ratingID = r;
        this.value = v;
        this.description = d;
    }

    public String getRatingID() {
        return ratingID;
    }

    public void setRatingID(String ratingID) {
        this.ratingID = ratingID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}