package com.EasyBank.controller;

import com.EasyBank.dao.ClientDao;
import com.EasyBank.dao.CompteCourantDao;
import com.EasyBank.dao.EmployeDao;
import com.EasyBank.daoImpl.ClientDaoImpl;
import com.EasyBank.daoImpl.CompteCourantDaoImpl;
import com.EasyBank.daoImpl.EmployéDaoImpl;
import com.EasyBank.entity.Client;
import com.EasyBank.entity.CompteCourant;
import com.EasyBank.entity.Employe;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class CompteCourantController {
    static CompteCourantDao compteCourantDao = new CompteCourantDaoImpl();
    static ClientDao clientDao = new ClientDaoImpl();
    static EmployeDao employeDao = new EmployéDaoImpl();
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
            }else
                System.out.println("null");
        }
    }
}
