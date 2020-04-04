CREATE TABLE Organization (
OrganizationID CHAR(100),
Name CHAR(100),
PRIMARY KEY (OrganizationID)
);

CREATE TABLE Venue (
VenueID CHAR(100),
AgeRestriction INTEGER,
Name CHAR(100),
Capacity INTEGER,
PRIMARY KEY (VenueID)
);

CREATE TABLE Address (
VenueID CHAR(100),
City CHAR(100),
Province CHAR(100),
ZipCode CHAR(100),
Street CHAR(100),
PRIMARY KEY (VenueID, ZipCode, Street),
FOREIGN KEY (VenueID) references Venue ON DELETE CASCADE
);

CREATE TABLE Attendee(
Username CHAR(100) PRIMARY KEY,
Password CHAR(100)
);

CREATE TABLE Event (
EventId CHAR(100),
VenueId CHAR(100) NOT NULL,
OrganizationID CHAR(100) NOT NULL,
Name CHAR(100),
StartTime DATE,
EndTime DATE,
Url CHAR(100),
PRIMARY KEY (EventId),
FOREIGN KEY (VenueID) references Venue ON DELETE CASCADE,
FOREIGN KEY (OrganizationID) references Organization ON DELETE CASCADE
);

CREATE TABLE Ticket (
TicketID CHAR(100),
TicketType CHAR(100),
Price FLOAT(100),
EventID CHAR(100) NOT NULL,
Username CHAR(100) NOT NULL,
OrderNum CHAR(100),
PRIMARY KEY (TicketID),
FOREIGN KEY (EventID) references Event ON DELETE CASCADE,
FOREIGN KEY (Username) references Attendee ON DELETE CASCADE
);

CREATE TABLE Rating (
RatingID CHAR(100) PRIMARY KEY,
Value INT,
Description CHAR(100)
);

CREATE TABLE GiveRating(
Username CHAR(100),
RatingID CHAR(100),
PRIMARY KEY (Username, RatingID),
FOREIGN KEY (Username) references Attendee ON DELETE CASCADE,
FOREIGN KEY (RatingID) references Rating ON DELETE CASCADE
);

CREATE TABLE ReceiveRating(
EventID CHAR(100),
RatingID CHAR(100),
PRIMARY KEY (EventID, RatingID),
FOREIGN KEY (EventID) references Event ON DELETE CASCADE,
FOREIGN KEY (RatingID) references Rating ON DELETE CASCADE
);

CREATE TABLE Volunteer(
Username CHAR(100),
TimeVolunteered INT,
Password CHAR(100),
PRIMARY KEY (Username)
);

CREATE TABLE VolunteerClassification(
Username CHAR(100) PRIMARY KEY,
VolunteerType CHAR(100),
FOREIGN KEY (Username) references Volunteer ON DELETE CASCADE
);

CREATE TABLE VolunteersAt(
Username CHAR(100) PRIMARY KEY,
EventID CHAR(100),
FOREIGN KEY (Username) references Attendee ON DELETE CASCADE,
FOREIGN KEY (EventID) references Event ON DELETE CASCADE
);

CREATE TABLE Performer(
PerformerID CHAR(100) PRIMARY KEY,
Genre CHAR(100),
Name CHAR(100)
);

CREATE TABLE PerformsAt(
PerformerId CHAR(100),
EventId CHAR(100),
PRIMARY KEY (PerformerID, EventID),
FOREIGN KEY (PerformerID) references Performer ON DELETE CASCADE,
FOREIGN KEY (EventID) references Event ON DELETE CASCADE
);