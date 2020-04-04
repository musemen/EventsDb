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

INSERT INTO Organization (OrganizationID,Name) VALUES("1","Vancouver Canucks");
INSERT INTO Organization (OrganizationID,Name) VALUES("2","Rain Or Shine");
INSERT INTO Organization (OrganizationID,Name) VALUES("3","Dragonball Tea House");
INSERT INTO Organization (OrganizationID,Name) VALUES("4","Poke Guy");
INSERT INTO Organization (OrganizationID,Name) VALUES("5","Martin Garrix");
INSERT INTO Organization (OrganizationID,Name) VALUES("6","Vancouver WhiteCaps");
INSERT INTO Organization (OrganizationID,Name) VALUES("7","University of British Columbia");
INSERT INTO Organization (OrganizationID,Name) VALUES("8","Powell Street Festival Society");
INSERT INTO Organization (OrganizationID,Name) VALUES("9","Greater Vancouver Food Truck Festival");
INSERT INTO Organization (OrganizationID,Name) VALUES("10","Powell Street Festival Society");
INSERT INTO Organization (OrganizationID,Name) VALUES("11","Pacific National Exhibition");

INSERT INTO Event (EventId,VenueId,OrganizationID,Name,StartTime,EndTime,Url) VALUES("1","1","1","Vancouver Canucks vs. Pittsburgh Penguins",CONVERT(datetime, '2019-12-22T03:00:00Z'),CONVERT(datetime, '2019-13-22T03:00:00Z'),"https://www.ticketmaster.ca/vancouver-canucks-vs-pittsburgh-penguins-vancouver-british-columbia-12-21-2019/event/11005724CCF73B0D");
INSERT INTO Event (EventId,VenueId,OrganizationID,Name,StartTime,EndTime,Url) VALUES("2","2","2","Buy One Get One Free Icecream - Rain Or Shine",CONVERT(datetime, '2020-03-22T03:00:00Z'),CONVERT(datetime, '2020-03-31T03:00:00Z'), "http://rainorshineicecream.com/events/");
INSERT INTO Event (EventId,VenueId,OrganizationID,Name,StartTime,EndTime,Url) VALUES("4","2","2","Free Icecream - Rain Or Shine",CONVERT(datetime, '2020-05-01T03:00:00Z'),CONVERT(datetime, '2020-05-08T03:00:00Z'), "http://rainorshineicecream.com/events/");
INSERT INTO Event (EventId,VenueId,OrganizationID,Name,StartTime,EndTime,Url) VALUES("3","3","3","$5 Bubble Tea Promo - Limtied Time Offer",CONVERT(datetime, '2020-02-25T03:00:00Z'),CONVERT(datetime, '2020-03-25T03:00:00Z'), "https://www.yelp.ca/biz/dragon-ball-tea-house-vancouver");
INSERT INTO Event (EventId,VenueId,OrganizationID,Name,StartTime,EndTime,Url) VALUES("5","4","4","Soft Opening - First 100 People 50% Off",CONVERT(datetime, '2020-06-22T12:00:00Z'),CONVERT(datetime, '2020-06-22T16:00:00Z'),"https://thepokeguy.ca/");
INSERT INTO Event (EventId,VenueId,OrganizationID,Name,StartTime,EndTime,Url) VALUES("6","1","5","Martin Garrix World Tour",CONVERT(datetime, '2020-08-22T17:00:00Z'),CONVERT(datetime, '2020-08-24T22:00:00Z'), "https://martingarrix.com/");
INSERT INTO Event (EventId,VenueId,OrganizationID,Name,StartTime,EndTime,Url) VALUES("7","1","6","Vancouver WhiteCaps Exhibition Match", CONVERT(datetime, '2020-04-10T12:00:00Z'),CONVERT(datetime, '2020-0-22T16:00:00Z'), "https://whitecaps.ca"/);
INSERT INTO Event (EventId,VenueId,OrganizationID,Name,StartTime,EndTime,Url) VALUES("8","5","7","Poits After Party",CONVERT(datetime, '2020-06-22T19:00:00Z'),CONVERT(datetime, '2019-06-22T23:00:00Z'),"https://celebritiesnightclub.com/");
INSERT INTO Event (EventId,VenueId,OrganizationID,Name,StartTime,EndTime,Url) VALUES("9","6","8","Powell Street Festival",CONVERT(datetime, '2020-06-22T12:00:00Z'),CONVERT(datetime, '2019-06-22T16:00:00Z'),"http://www.powellstreetfestival.com/");
INSERT INTO Event (EventId,VenueId,OrganizationID,Name,StartTime,EndTime,Url) VALUES("10","7","9","Columbia StrEAT Food Truck Fest",CONVERT(datetime, '2020-07-14T12:00:00Z'),CONVERT(datetime, '2019-08-17T16:00:00Z'),"https://downtownnewwest.ca/");
INSERT INTO Event (EventId,VenueId,OrganizationID,Name,StartTime,EndTime,Url) VALUES("11","8","11","The Fair At the PNE",CONVERT(datetime, '2020-08-01T12:00:00Z'),CONVERT(datetime, '2020-09-02T16:00:00Z'),"https://www.pne.ca/");

INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("1","VIP",200.00,"1","alvinlo","1");
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("2","Regular",50.00,"1","musamohannad","1");
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("3","Free",0.00,"2","dorukesriyeli","2");
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("4","Free",0.00,"3","Jcena","3");
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("5","Free",0.00,"3","alvinlo","4");
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("6","Regular",5.00,"4","musamohannad","5");
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("7","Free",0.00,"5","Jcena","6");
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("8","Regular",150.00,"6","dorukesriyeli","7");
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("9","VIP",300.00,"6","musamohannad",8);
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("10","Regular",150.00,"6","alvinlo",9);
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("11","VIP",50.00,"7","Gkikzales",10);
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("12","Regular",10.00,"7","musamohannad",11);
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("13","Free",0.00,"9","alvinlo",12);
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("14","Free",0.00,"10","dorukresiyeli",13);
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("15","Free",0.00,"10","Jcena", 15);
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("16", "Free",0.00,"10","Gkikzales", 16);
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("17", "Regular",10.00,"11","alvinlo", 17);
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("18", "Regular",10.00,"11","dorukresiyeli", 18);
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("19", "VIP",30.00,"11","musamohannad", 19);
INSERT INTO Ticket (TicketID,TicketType,Price,EventID,Username,OrderNum) VALUES("20", "VIP",30.00,"11","Gkikzales", 20);

INSERT INTO Venue (VenueID,AgeRestriction,Name,Capacity) VALUES("1",0,"Rogers Arena",30000);
INSERT INTO Venue (VenueID,AgeRestriction,Name,Capacity) VALUES("2",0,"Rain Or Shine UBC",30);
INSERT INTO Venue (VenueID,AgeRestriction,Name,Capacity) VALUES("3",0,"Dragonball Tea House",50);
INSERT INTO Venue (VenueID,AgeRestriction,Name,Capacity) VALUES("4",0,"Poke Guy",35);
INSERT INTO Venue (VenueID,AgeRestriction,Name,Capacity) VALUES("5", 19,"Celebrities Night Club",350);
INSERT INTO Venue (VenueID,AgeRestriction,Name,Capacity) VALUES("6",0,"Powell St", 1000);
INSERT INTO Venue (VenueID,AgeRestriction,Name,Capacity) VALUES("7",0,"Columbia St", 2000);
INSERT INTO Venue (VenueID,AgeRestriction,Name,Capacity) VALUES("8",0,"PNE Amphitheatre",10000);

INSERT INTO Address (VenueID,City,Province,ZipCode,Street) VALUES("1","Vancouver","BC","V6B 6G1","800 Griffiths Way");
INSERT INTO Address (VenueID,City,Province,ZipCode,Street) VALUES("2","Vancouver","BC","V6T 0C5","6001 University Blvd");
INSERT INTO Address (VenueID,City,Province,ZipCode,Street) VALUES("3","Vancouver","BC","V6H 1Z3","1007 W King Edward Ave");
INSERT INTO Address (VenueID,City,Province,ZipCode,Street) VALUES("4","Vancouver","BC","V6B 2Z3","420 Richards St");
INSERT INTO Address (VenueID,City,Province,ZipCode,Street) VALUES("5","vancouver","BC","V6E 1M3","1022 Davie St");
INSERT INTO Address (VenueID,City,Province,ZipCode,Street) VALUES("6","Vancouver","BC","V6B 1H4","11 W Hastings St #410");
INSERT INTO Address (VenueID,City,Province,ZipCode,Street) VALUES("7","New Westminster","BC","V3L 1B1","8-552 Columbia St");
INSERT INTO Address (VenueID,City,Province,ZipCode,Street) VALUES("8","Vancouver","BC","V5K 5J1","2901 E Hastings St");

INSERT INTO GiveRating(Username,RatingID) VALUES("alvinlo","R1");
INSERT INTO GiveRating(Username,RatingID) VALUES("alvinlo","R2");
INSERT INTO GiveRating(Username,RatingID) VALUES("alvinlo","R3");
INSERT INTO GiveRating(Username,RatingID) VALUES("alvinlo","R4");
INSERT INTO GiveRating(Username,RatingID) VALUES("alvinlo","R5");
INSERT INTO GiveRating(Username,RatingID) VALUES("musamohannad","R6");
INSERT INTO GiveRating(Username,RatingID) VALUES("musamohannad","R7");
INSERT INTO GiveRating(Username,RatingID) VALUES("musamohannad","R8");
INSERT INTO GiveRating(Username,RatingID) VALUES("musamohannad","R9");
INSERT INTO GiveRating(Username,RatingID) VALUES("musamohannad","R10");
INSERT INTO GiveRating(Username,RatingID) VALUES("dorukesriyeli","R11");
INSERT INTO GiveRating(Username,RatingID) VALUES("dorukesriyeli","R12");
INSERT INTO GiveRating(Username,RatingID) VALUES("dorukesriyeli","R13");
INSERT INTO GiveRating(Username,RatingID) VALUES("dorukesriyeli","R14");
INSERT INTO GiveRating(Username,RatingID) VALUES("Jcena","R16");
INSERT INTO GiveRating(Username,RatingID) VALUES("Jcena","R17");
INSERT INTO GiveRating(Username,RatingID) VALUES("Jcena","R18");
INSERT INTO GiveRating(Username,RatingID) VALUES("Gkikzales","R21");
INSERT INTO GiveRating(Username,RatingID) VALUES("Gkikzales","R22");
INSERT INTO GiveRating(Username,RatingID) VALUES("Gkikzales","R23");

INSERT INTO ReceiveRating(EventID,RatingID) VALUES("1","R1");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("1","R6");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("2","R11");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("3","R16");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("3","R2");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("4","R7");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("5","R17");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("6","R12");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("6","R8");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("6","R3");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("7","R21");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("7","R9");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("9","R4");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("10","R13");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("10","R18");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("10","R22");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("11","R5");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("11","R14");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("11","R10");
INSERT INTO ReceiveRating(EventID,RatingID) VALUES("11","R23");

INSERT INTO Rating (RatingID,Value,Description) VALUES("R1",1,"Terrible");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R6",2,"Pretty Bad");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R11",3,"OK");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R16",4,"Good");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R2",5,"I Loved it");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R7",5,"Phenomenal");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R17",3,"very Average");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R12",4,"Very Good, I enjoyed it");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R8",5,"Awesome");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R3",4,"I Really enjoyed this");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R21",2,"Not my cup of tea");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R9",1,"Pretty meh");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R4",3,"OK");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R13",4,"Better than expected");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R18",4,"Pretty ok");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R22",3,"Pretty average");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R5",5,"Absolutely amazing");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R14",4,"Great");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R10",5,"Perfect");
INSERT INTO Rating (RatingID,Value,Description) VALUES("R23",5,"Best thing ever");

INSERT INTO Attendee(Username,Password) VALUES("alvinlo","alvinlopsw");
INSERT INTO Attendee(Username,Password) VALUES("musamohannad","musamohannadpsw");
INSERT INTO Attendee(Username,Password) VALUES("dorukesriyeli", "dorukesriyelipsw");
INSERT INTO Attendee(Username,Password) VALUES("Jcena","Jcena1234");
INSERT INTO Attendee(Username,Password) VALUES("Gkikzales","TrustthenaturalRecursion1");

INSERT INTO Volunteer(Username,TimeVolunteered,Password) VALUES("Alvinl",30,"Alvin123");
INSERT INTO Volunteer(Username,TimeVolunteered,Password) VALUES("Mmohannad",10,"Musa123");
INSERT INTO Volunteer(Username,TimeVolunteered,Password) VALUES("Doruk",20,"Doru123");
INSERT INTO Volunteer(Username,TimeVolunteered,Password) VALUES("Jcena",430,"Jcena123");
INSERT INTO Volunteer(Username,TimeVolunteered,Password) VALUES("Gkikzales",25,"Gregor123");

INSERT INTO VolunteerClassification(TimeVolunteered,VolunteerType) VALUES();
INSERT INTO VolunteerClassification(TimeVolunteered,VolunteerType) VALUES(10,"New");
INSERT INTO VolunteerClassification(TimeVolunteered,VolunteerType) VALUES(20,"Starting");
INSERT INTO VolunteerClassification(TimeVolunteered,VolunteerType) VALUES(30,"Intermediate");
INSERT INTO VolunteerClassification(TimeVolunteered,VolunteerType) VALUES(50,"Veteran");
INSERT INTO VolunteerClassification(TimeVolunteered,VolunteerType) VALUES(100,"Expert");
INSERT INTO VolunteerClassification(TimeVolunteered,VolunteerType) VALUES(200,"Professional");

INSERT INTO VolunteersAt(Username,EventID) VALUES("Alvinl","1");
INSERT INTO VolunteersAt(Username,EventID) VALUES("Mmohannad","1");
INSERT INTO VolunteersAt(Username,EventID) VALUES("Mmohannad","2");
INSERT INTO VolunteersAt(Username,EventID) VALUES("Mmohannad","3");
INSERT INTO VolunteersAt(Username,EventID) VALUES("Alvinl","5");
INSERT INTO VolunteersAt(Username,EventID) VALUES("Doruk","6");
INSERT INTO VolunteersAt(Username,EventID) VALUES("Doruk","2");
INSERT INTO VolunteersAt(Username,EventID) VALUES("Alvinl","7");
INSERT INTO VolunteersAt(Username,EventID) VALUES("Jcena","4");
INSERT INTO VolunteersAt(Username,EventID) VALUES("Jcena","5");
INSERT INTO VolunteersAt(Username,EventID) VALUES("Gkikzales","6");
INSERT INTO VolunteersAt(Username,EventID) VALUES("Gkikzales","7");

INSERT INTO Performer(PerformerID,Genre,Name) VALUES("1","Music","The Weeknd");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("2","Music","Miley Cyrus");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("3","Music","Martin Garrix");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("4","Music","ACDC");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("5","Sports","Vancouver WhiteCaps");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("6","Food","The Poke Guy");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("7","Food","Rain Or Shine");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("8","Food","The Ramen Guy");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("9","Sports","Fusion Football Club");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("10","Sports","Vancouver Canucks");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("11","Sports", "Pittsburgh Penguins");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("12","Food","Miss Siam Food Truck");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("13","Food","Japadog Food Truck");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("14","Food","Mini Donut Food Truck");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("15","Music", "Drake");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("16"), "Music","Eminem");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("17","Entertainment","Face Paint Station");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("18","Entertainment","Traditional Dance Performance");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("19","Entertainment","Cirque Du Soleil");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("20","Entertainment","The Amazing Race");
INSERT INTO Performer(PerformerID,Genre,Name) VALUES("21","Food","Dragonball Tea House");

INSERT INTO PerformsAt(PerformerId,EventId) VALUES("10","1");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("11","1");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("7","2");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("7","3");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("21","3");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("6","5");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("3","6");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("9","7");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("5","7");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("1","8");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("2","8");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("12","9");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("13","9");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("17","9");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("13","10");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("14","10");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("8","10");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("15","11");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("16","11");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("19","11");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("20","11");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("21","3");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("14","11");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("4","11");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("18","9");
INSERT INTO PerformsAt(PerformerId,EventId) VALUES("12","11");