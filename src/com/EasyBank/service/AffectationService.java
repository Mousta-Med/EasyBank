package com.EasyBank.service;

import com.EasyBank.dao.AffectationDao;
import com.EasyBank.dao.EmployeDao;
import com.EasyBank.dao.MissionDao;
import com.EasyBank.daoImpl.AffectationDaoImpl;
import com.EasyBank.daoImpl.EmployeDaoImpl;
import com.EasyBank.daoImpl.MissionDaoImpl;
import com.EasyBank.entity.Affectation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class AffectationService {
    static Scanner scanner = new Scanner(System.in);
    static Affectation affectation = new Affectation();
    static AffectationDao affectationDao = new AffectationDaoImpl();
    static EmployeDao employeDao = new EmployeDaoImpl();
    static MissionDao missionDao = new MissionDaoImpl();

    public static void ajouterAffectation(){
        System.out.println("Entrer La date de fin Affectation");
        String datefinaffectaion = scanner.next();
        LocalDate localDate = LocalDate.now();
        System.out.println("Entrer Matricule de employe");
        String matricule = scanner.next();
        System.out.println("Entrer Code de mission");
        String code = scanner.next();
        affectation.setDateAffectation(localDate);
        affectation.setDateFinAffectation(LocalDate.parse(datefinaffectaion));
        affectation.setEmploye(employeDao.chercherEmploye(matricule).get());
        affectation.setMission(missionDao.chercherMission(code).get());
        Optional<Affectation> optionalAffectation = affectationDao.ajouterAffectation(affectation);
        if (optionalAffectation.isPresent()){
            System.out.println(optionalAffectation.get());
        }else {
            System.out.println("NULL");
        }
    }

    public static void supprimerAffectation(){
        System.out.println("Entrer Matricule de employe");
        String matricule = scanner.next();
        System.out.println("Entrer Code de mission");
        String code = scanner.next();
        Integer res = affectationDao.supprimerAffectation(matricule, code);
        if (res != 0){
            System.out.println("Bien supprimer");
        }else {
            System.out.println("NULL");
        }
    }

    public static void afficherAffectationEmploye(){
        System.out.println("Entrer Matricule de employe");
        String matricule = scanner.next();
        Optional<ArrayList<Affectation>> optionalAffectation = affectationDao.afficherAffectationParEmploye(matricule);
        if (optionalAffectation.isPresent()){
            System.out.println(optionalAffectation.get());
        }else {
            System.out.println("NULL");
        }
    }

    public static void afficherAffectation(){
        Optional<ArrayList<Affectation>> optionalAffectation = affectationDao.afficherAffectations();
        if (optionalAffectation.isPresent()){
            System.out.println((long) optionalAffectation.get().stream().count());
        }else {
            System.out.println("NULL");
        }
    }
}
