CREATE TABLE Organization (
OrganizationID char(100),
Name char(100),
PRIMARY KEY (OrganizationID)
);

CREATE TABLE Venue (
VenueID char(100),
AgeRestriction INTEGER,
Name char(100),
Capacity INTEGER,
PRIMARY KEY (VenueID)
);

CREATE TABLE Address (
VenueID char(100),
City char(100),
Province char(100),
ZipCode char(100),
Street char(100),
PRIMARY KEY (VenueID, ZipCode, Street),
FOREIGN KEY (VenueID) references Venue ON DELETE CASCADE
);

CREATE TABLE Attendee(
Username char(100) PRIMARY KEY,
Password char(100)
);

CREATE TABLE Event (
EventId char(100),
VenueId char(100) NOT NULL,
OrganizationID char(100) NOT NULL,
Name char(100),
StartTime DATE,
EndTime DATE,
Url char(100),
PRIMARY KEY (EventId),
FOREIGN KEY (VenueID) references Venue ON DELETE CASCADE,
FOREIGN KEY (OrganizationID) references Organization ON DELETE CASCADE
);

CREATE TABLE Ticket (
TicketID char(100),
TicketType char(100),
Price FLOAT(100),
EventID char(100) NOT NULL,
Username char(100) NOT NULL,
OrderNum char(100),
PRIMARY KEY (TicketID),
FOREIGN KEY (EventID) references Event ON DELETE CASCADE,
FOREIGN KEY (Username) references Attendee ON DELETE CASCADE
);

CREATE TABLE Rating (
RatingID char(100) PRIMARY KEY,
Value INT,
Description char(100)
);

CREATE TABLE GiveRating(
Username char(100),
RatingID char(100),
PRIMARY KEY (Username, RatingID),
FOREIGN KEY (Username) references Attendee ON DELETE CASCADE,
FOREIGN KEY (RatingID) references Rating ON DELETE CASCADE
);

CREATE TABLE ReceiveRating(
EventID char(100),
RatingID char(100),
PRIMARY KEY (EventID, RatingID),
FOREIGN KEY (EventID) references Event ON DELETE CASCADE,
FOREIGN KEY (RatingID) references Rating ON DELETE CASCADE
);

CREATE TABLE Volunteer(
Username char(100),
TimeVolunteered INT,
Password char(100),
PRIMARY KEY (Username)
);

CREATE TABLE VolunteerClassification(
Username char(100) PRIMARY KEY,
VolunteerType char(100),
FOREIGN KEY (Username) references Volunteer ON DELETE CASCADE
);

CREATE TABLE VolunteersAt(
Username char(100) PRIMARY KEY,
EventID char(100),
FOREIGN KEY (Username) references Attendee ON DELETE CASCADE,
FOREIGN KEY (EventID) references Event ON DELETE CASCADE
);

CREATE TABLE Performer(
PerformerID char(100) PRIMARY KEY,
Genre char(100),
Name char(100)
);

CREATE TABLE PerformsAt(
PerformerId char(100),
EventId char(100),
PRIMARY KEY (PerformerID, EventID),
FOREIGN KEY (PerformerID) references Performer ON DELETE CASCADE,
FOREIGN KEY (EventID) references Event ON DELETE CASCADE
);