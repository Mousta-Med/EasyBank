DROP TABLE IF EXISTS personne;
CREATE TABLE personne
(
    id            SERIAL PRIMARY KEY,
    nom           varchar(255),
    prenome       varchar(255),
    dateNaissance date,
    telephone     varchar(255)
);
DROP TABLE IF EXISTS client;
CREATE TABLE client
(
    code     varchar(255) not null unique,
    adresse  varchar(255),
    personne int,
    FOREIGN KEY (personne) REFERENCES personne (id) ON DELETE CASCADE
);
DROP TABLE IF EXISTS employe;
CREATE TABLE employe
(
    matricule       varchar(255) not null unique,
    dateRecrutement date         not null,
    email           varchar(255),
    personneId      int,
    FOREIGN KEY (personneId) REFERENCES personne (id) ON DELETE CASCADE
);
CREATE TYPE etat AS ENUM (
    'Bloqué',
    'Actif',
    'Suspendu',
    'Frauduleux'
    );
DROP TABLE IF EXISTS compteCourant;
CREATE TABLE compteCourant
(
    numero       varchar(255) not null unique,
    dateCreation date,
    sold         real,
    decouvert    real,
    etat         etat DEFAULT 'Actif',
    client       varchar(255) not null,
    employe      varchar(255) not null,
    FOREIGN KEY (client) REFERENCES client (code) ON DELETE CASCADE,
    FOREIGN KEY (employe) REFERENCES employe (matricule) ON DELETE CASCADE
);
DROP TABLE IF EXISTS compteEpargne;
CREATE TABLE compteEpargne
(
    numero       varchar(255) not null unique,
    dateCreation date,
    sold         real,
    tauxInteret  real,
    etat         etat DEFAULT 'Actif',
    client       varchar(255) not null,
    employe      varchar(255) not null,
    FOREIGN KEY (client) REFERENCES client (code) ON DELETE CASCADE,
    FOREIGN KEY (employe) REFERENCES employe (matricule) ON DELETE CASCADE
);
CREATE TYPE operationType AS ENUM (
    'Retrait',
    'Versement'
    );
DROP TABLE IF EXISTS operation;
CREATE TABLE operation
(
    numero        int          NOT NULL UNIQUE,
    dateOperation timestamp    not null,
    montant       real         not null,
    type          operationType,
    compteCourant varchar(255),
    compteEpargne varchar(255),
    employe       varchar(255) not null,
    FOREIGN KEY (compteCourant) REFERENCES compteCourant (numero) ON DELETE CASCADE,
    FOREIGN KEY (compteEpargne) REFERENCES compteEpargne (numero) ON DELETE CASCADE,
    FOREIGN KEY (employe) REFERENCES employe (matricule) ON DELETE CASCADE
);
Create or replace function operation()
    returns trigger as
$$
BEGIN
    IF NEW.compteCourant IS NOT NULL THEN
        IF NEW.type = 'Retrait' THEN
            UPDATE compteCourant
            SET sold = sold - NEW.montant
            WHERE numero = NEW.compteCourant;
        ELSE
            UPDATE compteCourant
            SET sold = sold + NEW.montant
            WHERE numero = NEW.compteCourant;
        END IF;
    ELSE
        IF NEW.type = 'Retrait' THEN
            UPDATE compteEpargne
            SET sold = sold - NEW.montant
            WHERE numero = NEW.compteEpargne;
        ELSE
            UPDATE compteEpargne
            SET sold = sold + NEW.montant
            WHERE numero = NEW.compteEpargne;
        END IF;
    END IF;

    RETURN NEW;
END;
$$ language plpgsql;
create trigger update_compteCourant_sold
    after insert
    on operation
    for each row
execute function operation();
DROP TABLE IF EXISTS Mission;
CREATE TABLE Mission
(
    code        varchar(255) NOT NULL UNIQUE,
    nom         varchar(255),
    description varchar(255)
);
Drop table if exists affectation;
CREATE TABLE affectation
(
    dateAffectation    timestamp,
    dateFinAffectation timestamp,
    employe varchar(255) not null ,
    mission varchar(255) not null ,
    FOREIGN KEY (employe) REFERENCES employe (matricule) ON DELETE CASCADE,
    FOREIGN KEY (mission) REFERENCES Mission (code) ON DELETE CASCADE
);
