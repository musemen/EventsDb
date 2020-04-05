CREATE TABLE Organization (
OrganizationID varchar2(100),
Name varchar2(100),
PRIMARY KEY (OrganizationID)
);

CREATE TABLE Venue (
VenueID varchar2(100),
AgeRestriction INTEGER,
Name varchar2(100),
Capacity INTEGER,
PRIMARY KEY (VenueID)
);

CREATE TABLE Address (
VenueID varchar2(100),
City varchar2(100),
Province varchar2(100),
ZipCode varchar2(100),
Street varchar2(100),
PRIMARY KEY (VenueID, ZipCode, Street),
FOREIGN KEY (VenueID) references Venue ON DELETE CASCADE
);

CREATE TABLE Attendee(
Username varchar2(100) PRIMARY KEY,
Password varchar2(100)
);

CREATE TABLE Event (
EventId varchar2(100),
VenueId varchar2(100) NOT NULL,
OrganizationID varchar2(100) NOT NULL,
Name varchar2(100),
StartTime DATE,
EndTime DATE,
Url varchar2(100),
PRIMARY KEY (EventId),
FOREIGN KEY (VenueID) references Venue ON DELETE CASCADE,
FOREIGN KEY (OrganizationID) references Organization ON DELETE CASCADE
);

CREATE TABLE Ticket (
TicketID varchar2(100),
TicketType varchar2(100),
Price FLOAT(100),
EventID varchar2(100) NOT NULL,
Username varchar2(100) NOT NULL,
OrderNum varchar2(100),
PRIMARY KEY (TicketID),
FOREIGN KEY (EventID) references Event ON DELETE CASCADE,
FOREIGN KEY (Username) references Attendee ON DELETE CASCADE
);

CREATE TABLE Rating (
RatingID varchar2(100) PRIMARY KEY,
Value INT,
Description varchar2(100)
);

CREATE TABLE GiveRating(
Username varchar2(100),
RatingID varchar2(100),
PRIMARY KEY (Username, RatingID),
FOREIGN KEY (Username) references Attendee ON DELETE CASCADE,
FOREIGN KEY (RatingID) references Rating ON DELETE CASCADE
);

CREATE TABLE ReceiveRating(
EventID varchar2(100),
RatingID varchar2(100),
PRIMARY KEY (EventID, RatingID),
FOREIGN KEY (EventID) references Event ON DELETE CASCADE,
FOREIGN KEY (RatingID) references Rating ON DELETE CASCADE
);

CREATE TABLE Volunteer(
Username varchar2(100),
TimeVolunteered INT,
Password varchar2(100),
PRIMARY KEY (Username)
);

CREATE TABLE VolunteerClassification(
Username varchar2(100) PRIMARY KEY,
VolunteerType varchar2(100),
FOREIGN KEY (Username) references Volunteer ON DELETE CASCADE
);

CREATE TABLE VolunteersAt(
Username varchar2(100) PRIMARY KEY,
EventID varchar2(100),
FOREIGN KEY (Username) references Attendee ON DELETE CASCADE,
FOREIGN KEY (EventID) references Event ON DELETE CASCADE
);

CREATE TABLE Performer(
PerformerID varchar2(100) PRIMARY KEY,
Genre varchar2(100),
Name varchar2(100)
);

CREATE TABLE PerformsAt(
PerformerId varchar2(100),
EventId varchar2(100),
PRIMARY KEY (PerformerID, EventID),
FOREIGN KEY (PerformerID) references Performer ON DELETE CASCADE,
FOREIGN KEY (EventID) references Event ON DELETE CASCADE
);