package ca.ubc.cs304.model;

public class Address {

    private String venueID;
    private String city;
    private String province;
    private String zipCode;
    private String street;

    public Address(String v, String c, String p, String z, String s){
        this.venueID = v;
        this.city = c;
        this.province = p;
        this.zipCode = z;
        this.street = s;
    }

    public String getVenueID() {
        return venueID;
    }

    public void setVenueID(String venueID) {
        this.venueID = venueID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}