package com.EasyBank.dao;

import com.EasyBank.entity.Employe;
import com.EasyBank.entity.Personne;

import java.util.Optional;

public interface EmployeDao {
    Optional<Employe> ajouterEmploye(Employe employe);
    Integer supprimerEmploye(String matricule);
    Optional<Employe> chercherEmploye(String matricule);
}
