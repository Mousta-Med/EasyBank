package com.EasyBank.controller;

import com.EasyBank.dao.ClientDao;
import com.EasyBank.dao.CompteEpargneDao;
import com.EasyBank.dao.EmployeDao;
import com.EasyBank.daoImpl.ClientDaoImpl;
import com.EasyBank.daoImpl.CompteEpargneDaoImpl;
import com.EasyBank.daoImpl.EmployeDaoImpl;
import com.EasyBank.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CompteEpargneController {
    static CompteEpargneDao compteEpargneDao = new CompteEpargneDaoImpl();
    static ClientDao clientDao = new ClientDaoImpl();
    static EmployeDao employeDao = new EmployeDaoImpl();
    static Scanner scanner = new Scanner(System.in);
    static CompteEpargne compteEpargne = new CompteEpargne();

    public static void ajouterCompte() {
        LocalDate creationDate = LocalDate.now();
        System.out.println("Entrer numero de Compte");
        String numero = scanner.next();
        System.out.println("Entrer Solde de compte");
        Double solde = scanner.nextDouble();
        System.out.println("Entrer tauxInteret de compte");
        Double tauxInteret = scanner.nextDouble();
        System.out.println("Entrer code de client");
        String code = scanner.next();
        System.out.println("Entrer matricule de employe");
        String matricule = scanner.next();
        Optional<Client> optionalClient = clientDao.chercherClient(code);
        Optional<Employe> employeOptional = employeDao.chercherEmploye(matricule);
        compteEpargne.setTauxInteret(tauxInteret);
        compteEpargne.setNemuro(numero);
        compteEpargne.setDateCreation(creationDate);
        compteEpargne.setSold(solde);
        compteEpargne.setClient(optionalClient.get());
        compteEpargne.setEmployé(employeOptional.get());
        Optional<CompteEpargne> compteEpargneOptional = compteEpargneDao.creeCompte(compteEpargne);
        if (compteEpargneOptional.isPresent()) {
            System.out.println("compteEpargne a ajouter ");
        } else
            System.out.println("NULL");
    }

    public static void chercherCompte() {
        System.out.println("Entrer Code client");
        String code = scanner.next();
        Optional<CompteEpargne> compteEpargneOptional = compteEpargneDao.chercheCompte(code);
        if (compteEpargneOptional.isPresent()) {
            System.out.println(compteEpargneOptional.get());
        } else
            System.out.println("NULL");
    }

    public static void supprimerCompte() {
        System.out.println("Entrer Code client");
        String code = scanner.next();
        System.out.println("Entrer matricule de employe");
        String matricule = scanner.next();
        System.out.println("Entrer numero de compte");
        String numero = scanner.next();
        Optional<Client> optionalClient = clientDao.chercherClient(code);
        Optional<Employe> employeOptional = employeDao.chercherEmploye(matricule);
        if (optionalClient.isPresent() && employeOptional.isPresent()) {
            Integer res = compteEpargneDao.supprimerCompte(numero);
            if (res != 0) {
                System.out.println("Compte supprimer");
            } else
                System.out.println("null");
        } else {
            System.out.println("client or employe pas trouvé ");
        }
    }

    public static void updateCompteEtat() {
        System.out.println("Entrer numero de compte");
        String numero = scanner.next();
        Compte.statut statut;
        while (true) {
            System.out.println("entrer une choix");
            System.out.println("1.Actif");
            System.out.println("2.Bloqué");
            System.out.println("3.Suspendu");
            System.out.println("4.Frauduleux");
            String input = scanner.next();
            switch (input) {
                case "1":
                    statut = Compte.statut.Actif;
                    break;
                case "2":
                    statut = Compte.statut.Bloqué;
                    break;
                case "3":
                    statut = Compte.statut.Suspendu;
                    break;
                case "4":
                    statut = Compte.statut.Frauduleux;
                    break;
                default:
                    System.out.println("Invalid Choix");
                    continue;
            }
            break;
        }
        Integer res = compteEpargneDao.updateCompteEtat(statut, numero);
        if (res != 0) {
            System.out.println("Bien Mis a jouré");
        } else {
            System.out.println("NULL");
        }
    }
    public static void afficheComptesParStatut() {
        Compte.statut statut;
        while (true) {
            System.out.println("entrer une choix");
            System.out.println("1.Actif");
            System.out.println("2.Bloqué");
            System.out.println("3.Suspendu");
            System.out.println("4.Frauduleux");
            String input = scanner.next();
            switch (input) {
                case "1":
                    statut = Compte.statut.Actif;
                    break;
                case "2":
                    statut = Compte.statut.Bloqué;
                    break;
                case "3":
                    statut = Compte.statut.Suspendu;
                    break;
                case "4":
                    statut = Compte.statut.Frauduleux;
                    break;
                default:
                    System.out.println("Invalid Choix");
                    continue;
            }
            break;
        }
        Optional<ArrayList<CompteEpargne>> optionalCompteEpargnes = compteEpargneDao.afficherComptes();
        if (optionalCompteEpargnes.isPresent()) {
            ArrayList<CompteEpargne> compteEpargnes = optionalCompteEpargnes.get();
            List<CompteEpargne> filteredCompteEpargnes = compteEpargnes.stream()
                    .filter(compteEpargne -> statut.equals(compteEpargne.getEtat()))
                    .collect(Collectors.toList());
            if (!filteredCompteEpargnes.isEmpty()) {
                System.out.println(filteredCompteEpargnes);
            } else {
                System.out.println("il n'y a pas compteEpargne avec staut "+ statut);
            }
        } else {
            System.out.println("NULL");
        }
    }

    public static void afficheComptesParDate() {
        System.out.println("Entrer la date");
        String date = scanner.next();
        Optional<ArrayList<CompteEpargne>> optionalCompteEpargne = compteEpargneDao.afficherComptes();
        if (optionalCompteEpargne.isPresent()) {
            ArrayList<CompteEpargne> compteEpargnes = optionalCompteEpargne.get();
            List<CompteEpargne> filteredCompteEpargnes = compteEpargnes.stream()
                    .filter(compteEpargne -> LocalDate.parse(date).equals(compteEpargne.getDateCreation()))
                    .collect(Collectors.toList());
            if (!filteredCompteEpargnes.isEmpty()) {
                System.out.println(filteredCompteEpargnes);
            } else {
                System.out.println("il n'y a pas compteEpargne avec cette date" + date);
            }
        } else {
            System.out.println("NULL");
        }
    }

}
