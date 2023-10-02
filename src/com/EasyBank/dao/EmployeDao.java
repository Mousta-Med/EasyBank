package com.EasyBank.dao;

import com.EasyBank.entity.Employe;

import java.util.ArrayList;
import java.util.Optional;

public interface EmployeDao {
    Optional<Employe> ajouterEmploye(Employe employe);
    Integer supprimerEmploye(String matricule);
    Optional<Employe> chercherEmploye(String matricule);
    Optional<Employe> chercherEmployeParAttribut(String string);
    Optional<ArrayList<Employe>> afficherEmployes();
}
