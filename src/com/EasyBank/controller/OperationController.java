package com.EasyBank.controller;

import com.EasyBank.dao.CompteCourantDao;
import com.EasyBank.dao.EmployeDao;
import com.EasyBank.dao.OperationDao;
import com.EasyBank.daoImpl.CompteCourantDaoImpl;
import com.EasyBank.daoImpl.EmployeDaoImpl;
import com.EasyBank.daoImpl.OperationDaoImpl;
import com.EasyBank.entity.CompteCourant;
import com.EasyBank.entity.Operation;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class OperationController {
    static Scanner scanner = new Scanner(System.in);
    static OperationDao operationDao = new OperationDaoImpl();
    static CompteCourantDao compteCourantDao = new CompteCourantDaoImpl();
    static EmployeDao employeDao = new EmployeDaoImpl();
    static Operation operation = new Operation();

    public static void ajouterOperation() {
        LocalDate operationDate = LocalDate.now();
        System.out.println("Entrer numero de operation");
        String numero = scanner.next();
        while (true) {
            System.out.println("quel est ton choix");
            System.out.println("1.Retrait");
            System.out.println("2.Versement");
            String input = scanner.next();
            switch (input) {
                case "1":
                    operation.setType(Operation.Type.Retrait);
                    break;
                case "2":
                    operation.setType(Operation.Type.Versement);
                    break;
                default:
                    System.out.println("Invalid Choix");
                    continue;
            }
            break;
        }
        System.out.println("Entrer Montant de operation");
        Double montant = scanner.nextDouble();
        System.out.println("Entrer Numero de Compte");
        String compte = scanner.next();
        System.out.println("Entrer matricule de employe");
        String matricule = scanner.next();
        operation.setCompte(compteCourantDao.chercheCompte(compte).get());
        operation.setDateCreation(operationDate);
        operation.setMontant(montant);
        operation.setEmployé(employeDao.chercherEmploye(matricule).get());
        operation.setNumero(Integer.valueOf(numero));
        Optional<Operation> res = operationDao.ajouterOperation(operation);
        if (res.isPresent()) {
            System.out.println("bien ajouter");
        } else {
            System.out.println("NULL");
        }
    }
    public static void supprimerOperation(){
        System.out.println("Entrer le numero de operation");
        String numero = scanner.next();
        Integer res = operationDao.supprimerOperation(Integer.valueOf(numero));
        if (res == null){
            System.out.println("NULL");
        }else
            System.out.println("Operation bien supprimer");
    }
    public static void chercherOperation(){
        System.out.println("Entrer numero de Operation");
        String code = scanner.next();
        Optional<Operation> optionalOperation = operationDao.chercherOperation(Integer.valueOf(code));
        if (optionalOperation.isPresent()){
            System.out.println(optionalOperation.get());;
        }else
            System.out.println("NULL");
    }
}
