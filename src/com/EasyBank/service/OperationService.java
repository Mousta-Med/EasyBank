package com.EasyBank.service;

import com.EasyBank.dao.CompteCourantDao;
import com.EasyBank.dao.CompteEpargneDao;
import com.EasyBank.dao.EmployeDao;
import com.EasyBank.dao.OperationDao;
import com.EasyBank.daoImpl.CompteCourantDaoImpl;
import com.EasyBank.daoImpl.CompteEpargneDaoImpl;
import com.EasyBank.daoImpl.EmployeDaoImpl;
import com.EasyBank.daoImpl.OperationDaoImpl;
import com.EasyBank.entity.CompteCourant;
import com.EasyBank.entity.CompteEpargne;
import com.EasyBank.entity.Operation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class OperationService {
    static Scanner scanner = new Scanner(System.in);
    static OperationDao operationDao = new OperationDaoImpl();
    static CompteCourantDao compteCourantDao = new CompteCourantDaoImpl();
    static CompteEpargneDao compteEpargneDao = new CompteEpargneDaoImpl();
    static EmployeDao employeDao = new EmployeDaoImpl();
    static Operation operation = new Operation();

    public static void ajouterOperation() {
        LocalDate operationDate = LocalDate.now();
        System.out.println("Entrer numero de operation");
        String numero = scanner.next();
        Optional<Operation> res = Optional.empty();
        System.out.println("Entrer Numero de Compte");
        String compte = scanner.next();
        Optional<CompteCourant> optionalCompteCourant = compteCourantDao.chercheCompteParNum(compte);
        Optional<CompteEpargne> optionalCompteEpargne = compteEpargneDao.chercheCompteParNum(compte);
        Double solde;
        while (true) {
            System.out.println("Compte est Compte Courant ou Epargne");
            System.out.println("1.Compte Courant");
            System.out.println("2.Compte Epargne");
            String input = scanner.next();
            switch (input) {
                case "1":
                    operation.setCompteCourant(optionalCompteCourant.get());
                    solde = optionalCompteCourant.get().getSold();
                    break;
                case "2":
                    operation.setCompteEpargne(optionalCompteEpargne.get());
                    solde = optionalCompteEpargne.get().getSold();
                    break;
                default:
                    System.out.println("Invalid Choix");
                    continue;
            }
            break;
        }
        System.out.println("Entrer Montant de operation");
        Double montant = scanner.nextDouble();
        System.out.println("Entrer matricule de employe");
        String matricule = scanner.next();
        while (true) {
            System.out.println("quel est ton choix");
            System.out.println("1.Retrait");
            System.out.println("2.Versement");
            String input = scanner.next();
            switch (input) {
                case "1":
                    operation.setType(Operation.Type.Retrait);
                    if (optionalCompteCourant.get().getSold() > montant) {
                        operation.setDateCreation(operationDate);
                        operation.setMontant(montant);
                        operation.setEmployé(employeDao.chercherEmploye(matricule).get());
                        operation.setNumero(Integer.valueOf(numero));
                        res = operationDao.ajouterOperation(operation);
                    }else {
                        System.out.println("Votre solde est " + solde);
                    }
                    break;
                case "2":
                    operation.setType(Operation.Type.Versement);
                    operation.setDateCreation(operationDate);
                    operation.setMontant(montant);
                    operation.setEmployé(employeDao.chercherEmploye(matricule).get());
                    operation.setNumero(Integer.valueOf(numero));
                    res = operationDao.ajouterOperation(operation);
                    break;
                default:
                    System.out.println("Invalid Choix");
                    continue;
            }
            break;
        }
        if (res.isPresent()) {
            System.out.println("bien ajouter");
        } else {
            System.out.println("NULL");
        }
    }


    public static void supprimerOperation() {
        System.out.println("Entrer le numero de operation");
        String numero = scanner.next();
        Integer res = operationDao.supprimerOperation(Integer.valueOf(numero));
        if (res == null) {
            System.out.println("NULL");
        } else
            System.out.println("Operation bien supprimer");
    }

    public static void chercherOperation() {
        System.out.println("Entrer numero de Operation");
        String code = scanner.next();
        Optional<Operation> optionalOperation = operationDao.chercherOperation(Integer.valueOf(code));
        if (optionalOperation.isPresent()) {
            System.out.println(optionalOperation.get());
        } else
            System.out.println("NULL");
    }

    public static void afficherOperations() {
        Optional<ArrayList<Operation>> optionalOperation = operationDao.afficherOperations();
        if (optionalOperation.isPresent()) {
            System.out.println(optionalOperation.get());
        } else
            System.out.println("NULL");
    }
}
