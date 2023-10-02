package com.EasyBank.dao;

import com.EasyBank.entity.Compte;
import com.EasyBank.entity.CompteCourant;
import com.EasyBank.entity.CompteEpargne;

import java.util.ArrayList;
import java.util.Optional;

public interface CompteEpargneDao {
    Optional<CompteEpargne> creeCompte(CompteEpargne compteEpargne);
    Optional<CompteEpargne> chercheCompte(String code);
    Integer updateCompteEtat(Compte.statut statut, String numero);
    Optional<CompteEpargne> miseajourCompte(CompteEpargne compteEpargne);
    Optional<CompteEpargne> chercheCompteParNum(String code);
    Integer supprimerCompte(String numero);
    Optional<ArrayList<CompteEpargne>> afficherComptes();
}
