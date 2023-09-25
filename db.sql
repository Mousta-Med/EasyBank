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
    code     varchar(255) not null unique,
    adresse  varchar(255),
    personne int,
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
CREATE TYPE public.etat AS ENUM (
    'Bloqué',
    'Actif',
    'Suspendu',
    'Frauduleux'
    );
CREATE TABLE compteCourant
(
    nemuro       int          not null unique,
    dateCreation date,
    sold         real,
    decouvert    real,
    etat         etat DEFAULT 'Actif',
    client       varchar(255) not null,
    employe      varchar(255) not null,
    FOREIGN KEY (client) REFERENCES client (code) ON DELETE CASCADE,
    FOREIGN KEY (employe) REFERENCES employe (matricule) ON DELETE CASCADE
);
CREATE TABLE compteEpargne
(
    nemuro       int          not null unique,
    dateCreation date,
    sold         real,
    decouvert    real,
    etat         etat DEFAULT 'Actif',
    client       varchar(255) not null,
    employe      varchar(255) not null,
    FOREIGN KEY (client) REFERENCES client (code) ON DELETE CASCADE,
    FOREIGN KEY (employe) REFERENCES employe (matricule) ON DELETE CASCADE
);