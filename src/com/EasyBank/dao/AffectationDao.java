package com.EasyBank.dao;

import com.EasyBank.entity.Affectation;

import java.util.ArrayList;
import java.util.Optional;

public interface AffectationDao {
    Optional<Affectation> ajouterAffectation(Affectation affectation);
    Integer supprimerAffectation(String employe, String mission);
    Optional<ArrayList<Affectation>> afficherAffectations();
    Optional<ArrayList<Affectation>> afficherAffectationParEmploye(String employe);
}
