package com.EasyBank.controller;

import com.EasyBank.dao.MissionDao;
import com.EasyBank.daoImpl.MissionDaoImpl;
import com.EasyBank.entity.Mission;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class MissionController {
    static Mission mission = new Mission();
    static MissionDao missionDao = new MissionDaoImpl();
    static Scanner scanner = new Scanner(System.in);

    public static void ajouterMission() {
        System.out.println("Entrer le code demission");
        String code = scanner.next();
        System.out.println("Entre le nom de mission");
        scanner.next();
        String nom = scanner.nextLine();
        System.out.println("Entre le desc de mission");
        String desc = scanner.nextLine();
        mission.setCode(code);
        mission.setNom(nom);
        mission.setDescription(desc);
        Optional<Mission> optionalMission = missionDao.ajouterMision(mission);
        if (optionalMission.isPresent()){
            System.out.println(optionalMission.get());
        }else {
            System.out.println("NULL");
        }
    }
    public static void supprimerMission(){
        System.out.println("Entrer le code demission");
        String code = scanner.next();
        Integer integer = missionDao.supprimerMision(code);
        if (integer != 0){
            System.out.println("Bien supprimer");
        }else {
            System.out.println("NULL");
        }
    }
    public static void afficherMissions(){
        Optional<ArrayList<Mission>> optionalMission = missionDao.afficherMissions();
        if (optionalMission.isPresent()){
            System.out.println(optionalMission.get());
        }else {
            System.out.println("NULL");
        }
    }
}
