package com.EasyBank.dao;

import com.EasyBank.entity.Mission;

import java.util.ArrayList;
import java.util.Optional;

public interface MissionDao {
    public Optional<Mission> ajouterMision(Mission mission);
    public Integer supprimerMision(String code);
    public Optional<Mission> chercherMission(String code);
    public  Optional<ArrayList<Mission>>afficherMissions();
}
