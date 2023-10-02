package com.EasyBank.dao;

import com.EasyBank.entity.Compte;
import com.EasyBank.entity.CompteCourant;
import com.EasyBank.entity.CompteEpargne;

import java.util.ArrayList;
import java.util.Optional;

public interface CompteCourantDao {
    Optional<CompteCourant> creeCompte(CompteCourant compteCourant);
    Integer updateCompteEtat(Compte.statut statut, String numero);
    Optional<CompteCourant> chercheCompte(String code);
    Optional<CompteCourant> chercheCompteParNum(String code);
    Integer supprimerCompte(String numero);
    Optional<ArrayList<CompteCourant>> afficherComptes();
}
