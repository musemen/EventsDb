CREATE TABLE Organization (
OrganizationID VARCHAR(100) PRIMARY KEY,
Name VARCHAR(100),
);

CREATE TABLE Event (
EventId VARCHAR(100) PRIMARY KEY,
VenueId VARCHAR(100) NOT NULL,
OrganizationID VARCHAR(100) NOT NULL,
Name VARCHAR(100),
StartTime DATE,
EndTime DATE,
Url VARCHAR(100),
FOREIGN KEY (VenueID) references Venue ON DELETE CASCADE,
FOREIGN KEY (OrganizationID) references Organization ON DELETE CASCADE
);

CREATE TABLE Ticket (
TicketID VARCHAR(100) PRIMARY KEY,
TicketType VARCHAR(100),
Price DOUBLE(100, 2),
EventID VARCHAR(100) NOT NULL,
Username VARCHAR(100) NOT NULL,
OrderNum VARCHAR(100),
FOREIGN KEY (EventID) references Event ON DELETE CASCADE,
FOREIGN KEY (Username) references Attendee ON DELETE CASCADE
);

CREATE TABLE Venue (
VenueID VARCHAR(100) PRIMARY KEY,
AgeRestriction INTEGER,
Name VARCHAR(100),
Capacity INTEGER
);

CREATE TABLE Address (
VenueID VARCHAR(100),
City VARCHAR(100),
Province VARCHAR(100),
ZipCode VARCHAR(100),
Street VARCHAR(100),
FOREIGN KEY (VenueID) references Venue ON DELETE CASCADE,
PRIMARY KEY (VenueID, ZipCode, Street) ON DELETE CASCADE
);

CREATE TABLE GiveRating(
Username VARCHAR(100),
RatingID VARCHAR(100),
PRIMARY KEY (Username, RatingID),
FOREIGN KEY (Username) references Attendee ON DELETE CASCADE,
FOREIGN KEY (RatingID) references Rating ON DELETE CASCADE
);

CREATE TABLE ReceiveRating(
EventID VARCHAR(100),
RatingID VARCHAR(100),
PRIMARY KEY (EventID, RatingID),
FOREIGN KEY (EventID) references Event ON DELETE CASCADE,
FOREIGN KEY (RatingID) references Rating ON DELETE CASCADE
);

CREATE TABLE Rating (
RatingID VARCHAR(100) PRIMARY KEY,
Value INTEGER,
Description VARCHAR(100)
);

CREATE TABLE Attendee(
Username VARCHAR(100) PRIMARY KEY,
Password VARCHAR(100)
);

CREATE TABLE Volunteer(
Username VARCHAR(100) PRIMARY KEY,
TimeVolunteered INTEGER,
Password VARCHAR(100)
);

CREATE TABLE VolunteerClassification(
TimeVolunteered INTEGER PRIMARY KEY,
VolunteerType VARCHAR(100),
FOREIGN KEY (TimeVolunteered) references Volunteer ON DELETE CASCADE
);

CREATE TABLE VolunteersAt(
Username VARCHAR(100) PRIMARY KEY,
EventID VARCHAR(100),
FOREIGN KEY (Username) references Attendee ON DELETE CASCADE,
FOREIGN KEY (EventID) references Event ON DELETE CASCADE
);

CREATE TABLE Performer(
PerformerID VARCHAR(100) PRIMARY KEY,
Genre VARCHAR(100),
Name VARCHAR(100)
);

CREATE TABLE PerformsAt(
PerformerId VARCHAR(100),
EventId VARCHAR(100),
PRIMARY KEY (PerformerID, EventID),
FOREIGN KEY (PerformerID) references Performer ON DELETE CASCADE,
FOREIGN KEY (EventID) references Event ON DELETE CASCADE
);