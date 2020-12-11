drop database if exists SuperHeroSightingsTest;

create database SuperHeroSightingsTest;

use SuperHeroSightingsTest;

create table SuperPower (
SuperPowerID int unsigned not null auto_increment,
Description varchar(45) not null,
primary key (SuperPowerID)
);

create table Hero (
HeroID int unsigned not null auto_increment,
HeroName varchar(45) not null,
Description varchar(300),
primary key (HeroID)
);

create table HeroSuperPower (
HeroID int unsigned not null,
SuperPowerID int unsigned not null,
primary key (HeroID, SuperPowerID),
foreign key (HeroID)
references Hero (HeroID),
foreign key (SuperPowerID)
references SuperPower (SuperPowerID)
);

create table Location (
LocationID int unsigned not null auto_increment,
LocationName varchar(45) not null,
Description varchar(300),
StreetAddress varchar(45),
City varchar(45),
State char(2),
Zipcode varchar(10),
Latitude decimal(8,6),
Longitude decimal(9,6),
primary key (LocationID)
);

create table Sighting (
SightingID int unsigned not null auto_increment,
LocationID int unsigned not null,
SightingDate date not null,
HeroID int unsigned not null,
primary key (SightingID),
foreign key (LocationID)
references Location (LocationID),
foreign key (HeroID)
references Hero (HeroID)
);

create table Organization (
OrganizationID int unsigned not null auto_increment,
OrgName varchar(45) not null,
Description varchar(300),
StreetAddress varchar(45),
City varchar(45),
State char(2),
Zipcode varchar(10),
primary key (OrganizationID)
);

create table HeroOrganization (
HeroID int unsigned not null,
OrganizationID int unsigned not null,
primary key (HeroID, OrganizationID),
foreign key (HeroID)
references Hero (HeroID),
foreign key (OrganizationID)
references Organization (OrganizationID)
);
