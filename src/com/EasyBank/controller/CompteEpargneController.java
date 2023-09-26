package com.EasyBank.controller;

import com.EasyBank.dao.ClientDao;
import com.EasyBank.dao.CompteEpargneDao;
import com.EasyBank.dao.EmployeDao;
import com.EasyBank.daoImpl.ClientDaoImpl;
import com.EasyBank.daoImpl.CompteEpargneDaoImpl;
import com.EasyBank.daoImpl.EmployeDaoImpl;
import com.EasyBank.entity.Client;
import com.EasyBank.entity.CompteEpargne;
import com.EasyBank.entity.Employe;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

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

    public static void chercherCompte(){
        System.out.println("Entrer Code client");
        String code = scanner.next();
        Optional<CompteEpargne> compteEpargneOptional = compteEpargneDao.chercheCompte(code);
        if (compteEpargneOptional.isPresent()){
            System.out.println(compteEpargneOptional.get());;
        }else
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
            }else
                System.out.println("null");
        }
    }
}
