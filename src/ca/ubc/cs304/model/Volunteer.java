package ca.ubc.cs304.model;

public class Volunteer {

    private String username;
    private int timeVolunteered;
    private String password;

    public Volunteer(String u, int t, String p){
        this.username = u;
        this.timeVolunteered = t;
        this.password = p;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTimeVolunteered() {
        return timeVolunteered;
    }

    public void setTimeVolunteered(int timeVolunteered) {
        this.timeVolunteered = timeVolunteered;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}