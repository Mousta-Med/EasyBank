package com.EasyBank.dao;

import com.EasyBank.entity.Mission;

import java.util.Optional;

public interface MissionDao {
    public Optional<Mission> ajouterMision(Mission mission);
    public Integer supprimerMision(String code);
}
