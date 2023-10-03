package com.EasyBank.service;

import com.EasyBank.dao.ClientDao;
import com.EasyBank.dao.CompteCourantDao;
import com.EasyBank.dao.EmployeDao;
import com.EasyBank.daoImpl.ClientDaoImpl;
import com.EasyBank.daoImpl.CompteCourantDaoImpl;
import com.EasyBank.daoImpl.EmployeDaoImpl;
import com.EasyBank.entity.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CompteCourantService {
    static CompteCourantDao compteCourantDao = new CompteCourantDaoImpl();
    static ClientDao clientDao = new ClientDaoImpl();
    static EmployeDao employeDao = new EmployeDaoImpl();
    static Scanner scanner = new Scanner(System.in);
    static CompteCourant compteCourant = new CompteCourant();

    public static void ajouterCompte() {
        LocalDate creationDate = LocalDate.now();
        System.out.println("Entrer numero de Compte");
        String numero = scanner.next();
        System.out.println("Entrer Solde de compte");
        Double solde = scanner.nextDouble();
        System.out.println("Entrer decouvert de compte");
        Double decouvert = scanner.nextDouble();
        System.out.println("Entrer code de client");
        String code = scanner.next();
        System.out.println("Entrer matricule de employe");
        String matricule = scanner.next();
        Optional<Client> optionalClient = clientDao.chercherClient(code);
        Optional<Employe> employeOptional = employeDao.chercherEmploye(matricule);
        compteCourant.setDecouvert(decouvert);
        compteCourant.setNemuro(numero);
        compteCourant.setDateCreation(creationDate);
        compteCourant.setSold(solde);
        compteCourant.setClient(optionalClient.get());
        compteCourant.setEmployé(employeOptional.get());
        Optional<CompteCourant> compteCourantOptional = compteCourantDao.creeCompte(compteCourant);
        if (compteCourantOptional.isPresent()) {
            System.out.println("compteCourant a ajouter ");
        } else
            System.out.println("NULL");
    }

    public static void chercherCompte() {
        System.out.println("Entrer Code client");
        String code = scanner.next();
        Optional<CompteCourant> optionalCompteCourant = compteCourantDao.chercheCompte(code);
        if (optionalCompteCourant.isPresent()) {
            System.out.println(optionalCompteCourant.get());
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
            Integer res = compteCourantDao.supprimerCompte(numero);
            if (res != 0) {
                System.out.println("Compte supprimer");
            } else
                System.out.println("null");
        }
    }

    public static void afficherComptes() {
        Optional<ArrayList<CompteCourant>> optionalCompteCourant = compteCourantDao.afficherComptes();
        if (optionalCompteCourant.isPresent()) {
            System.out.println(optionalCompteCourant.get());
        } else
            System.out.println("NULL");
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
        Integer res = compteCourantDao.updateCompteEtat(statut, numero);
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
        Optional<ArrayList<CompteCourant>> optionalCompteCourant = compteCourantDao.afficherComptes();
        if (optionalCompteCourant.isPresent()) {
            ArrayList<CompteCourant> compteCourants = optionalCompteCourant.get();
            List<CompteCourant> filteredCompteCourants = compteCourants.stream()
                    .filter(compteCourant -> statut.equals(compteCourant.getEtat()))
                    .collect(Collectors.toList());
            if (!filteredCompteCourants.isEmpty()) {
                System.out.println(filteredCompteCourants);
            } else {
                System.out.println("il n'y a pas CompteCourant avec staut " + statut);
            }
        } else {
            System.out.println("NULL");
        }
    }

    public static void afficheComptesParDate() {
        System.out.println("Entrer la date");
        String date = scanner.next();
        Optional<ArrayList<CompteCourant>> optionalCompteCourant = compteCourantDao.afficherComptes();
        if (optionalCompteCourant.isPresent()) {
            ArrayList<CompteCourant> compteCourants = optionalCompteCourant.get();
            List<CompteCourant> filteredCompteCourants = compteCourants.stream()
                    .filter(compteCourant -> LocalDate.parse(date).equals(compteCourant.getDateCreation()))
                    .collect(Collectors.toList());
            if (!filteredCompteCourants.isEmpty()) {
                System.out.println(filteredCompteCourants);
            } else {
                System.out.println("il n'y a pas CompteCourant avec cette date" + date);
            }
        } else {
            System.out.println("NULL");
        }
    }

    public static void miseajourCompte() {
        System.out.println("Entrer numero de Compte");
        String numero = scanner.next();
        Optional<CompteCourant> optionalCompteCourant = compteCourantDao.chercheCompteParNum(numero);
        if (optionalCompteCourant.isPresent()) {
            System.out.println("Entrer Solde de compte");
            Double solde = scanner.nextDouble();
            System.out.println("Entrer decouvert de compte");
            Double decouvert = scanner.nextDouble();
            compteCourant.setDecouvert(decouvert);
            compteCourant.setNemuro(numero);
            compteCourant.setSold(solde);
            Optional<CompteCourant> compteCourantOptional = compteCourantDao.miseajourCompte(compteCourant);
            if (compteCourantOptional.isPresent()) {
                System.out.println("compteCourant a misea jouré ");
            } else
                System.out.println("NULL");
        }
    }

    public static void afficherCompteParOperation() {
        System.out.println("Entrer numero de Operation");
        String numero = scanner.next();
        Optional<CompteCourant> compteCourantOptional = compteCourantDao.chercherCompteParOperation(Integer.valueOf(numero));
        if (compteCourantOptional.isPresent()) {
            System.out.println(compteCourantOptional.get());
        } else
            System.out.println("NULL");
    }
}
