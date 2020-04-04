package ca.ubc.cs304.model;

public class Ticket {

    private String ticketID;
    private String ticketType;
    private Double price;
    private String eventID;
    private String username;
    private String orderNum;

    public Ticket(String t, String tt, Double p, String e, String u, String o){
        this.ticketID = t;
        this.ticketType = tt;
        this.price = p;
        this.eventID = e;
        this.username = u;
        this.orderNum = o;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}