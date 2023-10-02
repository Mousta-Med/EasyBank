package com.EasyBank.dao;

import com.EasyBank.entity.Affectation;

import java.util.Optional;

public interface AffectationDao {
    public Optional<Affectation> ajouterAffectation(Affectation affectation);
    public Integer supprimerAffectation(String employe, String mission);

}
