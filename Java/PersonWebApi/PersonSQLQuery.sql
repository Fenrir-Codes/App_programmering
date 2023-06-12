create database PersonDB
go

use PersonDB
go

create table Person
(

	Id int PRIMARY KEY IDENTITY(1000, 1),
	Name nvarchar(30),
	Job nvarchar(30),
	Tlf int,
	HairColor int,
	Favorit bit,
	Pet int,
)

insert into Person (Name, Job, Tlf, HairColor, Favorit, Pet) VALUES ('Jozsef', 'Datatekniker', 32658754, 1, 1, 1)
insert into Person (Name, Job, Tlf, HairColor, Favorit, Pet) VALUES ('Kristian', 'Instructor', 12215487, 2, 0, 2)
insert into Person (Name, Job, Tlf, HairColor, Favorit, Pet) VALUES ('Lars', 'Medarbejder', 32986545, 3, 0, 3)
insert into Person (Name, Job, Tlf, HairColor, Favorit, Pet) VALUES ('Harry', 'Psykolog', 55558871, 1, 1, 1)
insert into Person (Name, Job, Tlf, HairColor, Favorit, Pet) VALUES ('Bettina', 'Politibetjent', 12458975, 2, 0, 2)
insert into Person (Name, Job, Tlf, HairColor, Favorit, Pet) VALUES ('Marko', 'Student', 94578415, 2, 1, 2)
insert into Person (Name, Job, Tlf, HairColor, Favorit, Pet) VALUES ('Marc', 'Automekaniker', 12854678, 3, 1, 3)
insert into Person (Name, Job, Tlf, HairColor, Favorit, Pet) VALUES ('Ole', 'Lastvognchaffeur', 12457895, 3, 1, 3)
insert into Person (Name, Job, Tlf, HairColor, Favorit, Pet) VALUES ('Pie', 'Kok', 42997541, 3, 1, 3)
insert into Person (Name, Job, Tlf, HairColor, Favorit, Pet) VALUES ('Robert', 'Skraldemand', 44455789, 3, 1, 3)

select * from Person