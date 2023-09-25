package com.EasyBank.dao;

import com.EasyBank.entity.Personne;

import java.util.Optional;

public interface PersonneDao {
   Optional<Personne> ajouterPersonne(Personne personne);
}
