CREATE TABLE personne
(
    id            SERIAL PRIMARY KEY,
    nom           varchar(255),
    prenome       varchar(255),
    dateNaissance date,
    telephone     varchar(255)
);
CREATE TABLE client
(
    code       varchar(255) not null unique,
    adresse    varchar(255),
    personne   int,
    FOREIGN KEY (personne) REFERENCES personne (id) ON DELETE CASCADE
);
CREATE TABLE employe
(
    matricule       varchar(255) not null unique,
    dateRecrutement date         not null,
    email           varchar(255),
    personneId      int,
    FOREIGN KEY (personneId) REFERENCES personne (id) ON DELETE CASCADE
);