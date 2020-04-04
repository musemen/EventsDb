package ca.ubc.cs304.model;

public class Venue {

    private String venueID;
    private int ageRestriction;
    private String name;
    private int capacity;

    public Venue(String v, int a, String n, int c){
        this.venueID = v;
        this.ageRestriction = a;
        this.name = n;
        this.capacity = c;
    }

	public String getVenueID() {
		return venueID;
	}

	public void setVenueID(String venueID) {
		this.venueID = venueID;
	}

	public int getAgeRestriction() {
		return ageRestriction;
	}

	public void setAgeRestriction(int ageRestriction) {
		this.ageRestriction = ageRestriction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
    }
}

    
