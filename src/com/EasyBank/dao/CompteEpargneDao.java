package com.EasyBank.dao;

import com.EasyBank.entity.CompteEpargne;

import java.util.Optional;

public interface CompteEpargneDao {
    Optional<CompteEpargne> creeCompte(CompteEpargne compteEpargne);
    Optional<CompteEpargne> chercheCompte(String code);
    Integer supprimerCompte(String numero);
}
