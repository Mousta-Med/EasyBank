package com.EasyBank.dao;

import com.EasyBank.entity.CompteCourant;

import java.util.Optional;

public interface CompteCourantDao {
    Optional<CompteCourant> creeCompte(CompteCourant compteCourant);
    Optional<CompteCourant> chercheCompte(String code);
    Integer supprimerCompte(String numero);

}
