package com.EasyBank.service;

import com.EasyBank.dao.EmployeDao;
import com.EasyBank.daoImpl.EmployeDaoImpl;
import com.EasyBank.entity.Employe;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class EmployeService {
    EmployeDao employeDao = new EmployeDaoImpl();
    Employe employe = new Employe();
    Scanner scanner = new Scanner(System.in);

    public void ajouterEmploye() {
        System.out.println("Entrer nom de employe");
        String nom = scanner.nextLine();
        System.out.println("Entrer prénom de employe");
        String prenom = scanner.nextLine();
        System.out.println("Entrer matricule de employe");
        String matricul = scanner.nextLine();
        System.out.println("Entrer date de naissance de employe en form 1999-12-30");
        String naissance = scanner.nextLine();
        System.out.println("Entrer telephone de employe");
        String tele = scanner.nextLine();
        LocalDate recruteDate = LocalDate.now();
        System.out.println("Entrer email de employe");
        String email = scanner.nextLine();
        employe.setNom(nom);
        employe.setPrenom(prenom);
        employe.setMatricul(matricul);
        employe.setEmail(email);
        employe.setTelephone(tele);
        employe.setDateRecrutement(recruteDate);
        employe.setDateNaissance(LocalDate.parse(naissance));
        Optional<Employe> optional = employeDao.ajouterEmploye(employe);
        if (optional.isPresent()) {
            System.out.println("Employe bien Ajouter");
        } else
            System.out.println("NULL");
    }

    public void miseajourEmploye() {
        System.out.println("Entrer matricule de employe");
        String matricul = scanner.nextLine();
        Optional<Employe> optionalEmploye = employeDao.chercherEmploye(matricul);
        if (optionalEmploye.isPresent()){
        System.out.println("Entrer nom de employe");
        String nom = scanner.nextLine();
        System.out.println("Entrer prénom de employe");
        String prenom = scanner.nextLine();
        System.out.println("Entrer date de naissance de employe en form 1999-12-30");
        String naissance = scanner.nextLine();
        System.out.println("Entrer telephone de employe");
        String tele = scanner.nextLine();
        LocalDate recruteDate = LocalDate.now();
        System.out.println("Entrer email de employe");
        String email = scanner.nextLine();
        employe.setNom(nom);
        employe.setPrenom(prenom);
        employe.setMatricul(matricul);
        employe.setEmail(email);
        employe.setTelephone(tele);
        employe.setDateRecrutement(recruteDate);
        employe.setDateNaissance(LocalDate.parse(naissance));
        Optional<Employe> optional = employeDao.miseajourEmploye(employe);
        if (optional.isPresent()) {
            System.out.println("Employe bien mise a jouré");
        } else
            System.out.println("NULL");
        }else {
            System.out.println("il n'y a pas un employe avec cette matricule");
        }

    }

    public void recherchEmploye() {
        System.out.println("Entre Matricule de l'employe");
        String matricule = scanner.next();
        Optional<Employe> optional = employeDao.chercherEmploye(matricule);
        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else
            System.out.println("NULL");
    }

    public void recherchEmployeParAttribut() {
        System.out.println("Entre nom de l'employe");
        String string = scanner.next();
        Optional<Employe> optional = employeDao.chercherEmployeParAttribut(string);
        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else
            System.out.println("NULL");
    }

    public void deleteEmploye() {
        System.out.println("Entrer le matricule de employe");
        String matricule = scanner.nextLine();
        Integer res = employeDao.supprimerEmploye(matricule);
        if (res == null) {
            System.out.println("NULL");
        } else
            System.out.println("Employe bien supprimer");
    }

    public void afficherEmlpoyes() {
        Optional<ArrayList<Employe>> optional = employeDao.afficherEmployes();
        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else
            System.out.println("NULL");
    }
}
