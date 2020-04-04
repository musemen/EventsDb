package ca.ubc.cs304.model;

import java.util.Date;

public class event {

    private String eventID;
    private String venueID;
    private String organizationID;
    private String name;
    private Date startTime;
    private Date endTime;
    private String url;

    public event(String e, String v, String o, String n, Date s, Date ed, String u){
        this.eventID = e;
        this.venueID = v;
        this.organizationID = o;
        this.name = n;
        this.startTime = s;
        this.endTime = ed;
        this.url = u;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getVenueID() {
        return venueID;
    }

    public void setVenueID(String venueID) {
        this.venueID = venueID;
    }

    public String getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(String organizationID) {
        this.organizationID = organizationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    

}