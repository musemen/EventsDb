package ca.ubc.cs304.model;

public class Organization {

    private String organizationID;
    private String name;
    
    public Organization(String o, String n){
        this.organizationID = o;
        this.name = n;
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

    
}