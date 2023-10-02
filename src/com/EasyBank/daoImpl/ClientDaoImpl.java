package com.EasyBank.daoImpl;

import com.EasyBank.dao.ClientDao;
import com.EasyBank.entity.Client;
import com.EasyBank.util.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

public class ClientDaoImpl implements ClientDao {
    Connection connection = DbConnection.createConnection();
    Client client = new Client();

    @Override
    public Optional<Client> ajouterClient(Client client) {
        String query = "INSERT INTO personne(nom, prenome, dateNaissance, telephone) VALUES (?,?,?,?) RETURNING id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            preparedStatement.setDate(3, Date.valueOf(client.getDateNaissance()));
            preparedStatement.setString(4, client.getTelephone());
            ResultSet result = preparedStatement.executeQuery();
            Integer personneId = 0;
            if (result.next()) {
                personneId = result.getInt(1);
            }
            String stmt = "INSERT INTO client VALUES (?,?,?)";
            PreparedStatement statment = connection.prepareStatement(stmt);
            statment.setString(1, client.getCode());
            statment.setString(2, client.getAdresse());
            statment.setInt(3, personneId);
            Integer integer = statment.executeUpdate();
            if (integer != 0) {
                return Optional.of(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Integer supprimerClient(String code) {
        String query = "DELETE FROM personne WHERE id = (SELECT personne FROM client WHERE code = ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            Integer result = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (result != 0) {
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Client> chercherClient(String code) {
        String query = "SELECT * FROM client WHERE code = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet result = preparedStatement.executeQuery();
            Integer personneId = 0;
            if (result.next()) {
                personneId = result.getInt("personne");
            }
            String stmt = "SELECT * FROM personne WHERE id = ?";
            PreparedStatement statment = connection.prepareStatement(stmt);
            statment.setInt(1, personneId);
            ResultSet resultSet = statment.executeQuery();
            if (resultSet.next()) {
                client.setCode(result.getString("code"));
                client.setNom(resultSet.getString("nom"));
                client.setPrenom(resultSet.getString("prenome"));
                client.setDateNaissance(resultSet.getDate("datenaissance").toLocalDate());
                client.setTelephone(resultSet.getString("telephone"));
                client.setAdresse(result.getString("adresse"));
                return Optional.of(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> chercherClientParAttribute(String string) {
        String query = "SELECT * FROM personne WHERE nom LIKE ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, string);
            ResultSet result = preparedStatement.executeQuery();
            Integer personneId = 0;
            if (result.next()) {
                personneId = result.getInt("id");
            }
            String stmt = "SELECT * FROM client WHERE personne = ?";
            PreparedStatement statment = connection.prepareStatement(stmt);
            statment.setInt(1, personneId);
            ResultSet resultSet = statment.executeQuery();
            if (resultSet.next()) {
                client.setCode(resultSet.getString("code"));
                client.setNom(result.getString("nom"));
                client.setPrenom(result.getString("prenome"));
                client.setDateNaissance(result.getDate("datenaissance").toLocalDate());
                client.setTelephone(result.getString("telephone"));
                client.setAdresse(resultSet.getString("adresse"));
                return Optional.of(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<ArrayList<Client>> afficherClients() {
        String query = "SELECT * FROM personne INNER JOIN client ON personne.id = client.personne";
        ArrayList<Client> clients = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                client.setCode(result.getString("code"));
                client.setNom(result.getString("nom"));
                client.setPrenom(result.getString("prenome"));
                client.setDateNaissance(result.getDate("datenaissance").toLocalDate());
                client.setTelephone(result.getString("telephone"));
                client.setAdresse(result.getString("adresse"));
                clients.add(client);
            }
            return Optional.of(clients);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
