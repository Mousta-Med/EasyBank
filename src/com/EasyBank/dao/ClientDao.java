package com.EasyBank.dao;

import com.EasyBank.entity.Client;

import java.util.ArrayList;
import java.util.Optional;

public interface ClientDao {
    Optional<Client> ajouterClient(Client client);
    Integer supprimerClient(String code);
    Optional<Client> chercherClient(String code);
    Optional<ArrayList<Client>> afficherClients();
}
