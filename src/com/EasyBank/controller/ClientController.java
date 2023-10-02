package com.EasyBank.controller;

import com.EasyBank.dao.ClientDao;
import com.EasyBank.daoImpl.ClientDaoImpl;
import com.EasyBank.entity.Client;
import com.EasyBank.entity.Employe;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class ClientController {
    Scanner scanner = new Scanner(System.in);
    Client client = new Client();
    ClientDao clientDao = new ClientDaoImpl();

    public void ajouterClient() {
        System.out.println("Entrer nom de Client");
        String nom = scanner.nextLine();
        System.out.println("Entrer prénom de client");
        String prenom = scanner.nextLine();
        System.out.println("Entrer code de client");
        String code = scanner.nextLine();
        System.out.println("Entrer date de naissance de client en form YYYY-MM-DD");
        String naissance = scanner.nextLine();
        System.out.println("Entrer telephone de client");
        String tele = scanner.nextLine();
        System.out.println("Entrer adresse de client");
        String adresse = scanner.nextLine();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setDateNaissance(LocalDate.parse(naissance));
        client.setTelephone(tele);
        client.setCode(code);
        client.setAdresse(adresse);
        Optional<Client> optional = clientDao.ajouterClient(client);
        if (optional.isPresent()) {
            System.out.println("client bien Ajouter");
        } else
            System.out.println("NULL");
    }

    public void miseajourClient(){
        System.out.println("Entrer code de client");
        String code = scanner.nextLine();
        Optional<Client> optionalClient = clientDao.chercherClient(code);
        if (optionalClient.isPresent()){
        System.out.println("Entrer nom de Client");
        String nom = scanner.nextLine();
        System.out.println("Entrer prénom de client");
        String prenom = scanner.nextLine();
        System.out.println("Entrer date de naissance de client en form YYYY-MM-DD");
        String naissance = scanner.nextLine();
        System.out.println("Entrer telephone de client");
        String tele = scanner.nextLine();
        System.out.println("Entrer adresse de client");
        String adresse = scanner.nextLine();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setDateNaissance(LocalDate.parse(naissance));
        client.setTelephone(tele);
        client.setCode(code);
        client.setAdresse(adresse);
        Optional<Client> optional = clientDao.miseajourClient(client);
        if (optional.isPresent()) {
            System.out.println("client bien mise a jouré");
        } else
            System.out.println("NULL");
        }else
            System.out.println("il n'y a pas un client avec cette code");
    }

    public void supprimerClient() {
        System.out.println("Entrer le code de client");
        String code = scanner.nextLine();
        Integer res = clientDao.supprimerClient(code);
        if (res == null) {
            System.out.println("NULL");
        } else
            System.out.println("Client bien supprimer");
    }

    public void chercherClient() {
        System.out.println("Entre Code de client");
        String code = scanner.next();
        Optional<Client> optional = clientDao.chercherClient(code);
        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else
            System.out.println("NULL");
    }public void chercherClientParAttribute() {
        System.out.println("Entre nom de client");
        String code = scanner.next();
        Optional<Client> optional = clientDao.chercherClientParAttribute(code);
        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else
            System.out.println("NULL");
    }
    public void afficherClients() {
        Optional<ArrayList<Client>> optional = clientDao.afficherClients();
        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else
            System.out.println("NULL");
    }
}
