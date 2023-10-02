package com.EasyBank.daoImpl;


import com.EasyBank.dao.ClientDao;
import com.EasyBank.dao.CompteEpargneDao;
import com.EasyBank.dao.EmployeDao;
import com.EasyBank.entity.Compte;
import com.EasyBank.entity.CompteCourant;
import com.EasyBank.entity.CompteEpargne;
import com.EasyBank.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class CompteEpargneDaoImpl implements CompteEpargneDao {
    Connection connection = DbConnection.createConnection();
    EmployeDao employeDao = new EmployeDaoImpl();
    ClientDao clientDao = new ClientDaoImpl();
    CompteEpargne compteEpargne = new CompteEpargne();
    @Override
    public Optional<CompteEpargne> creeCompte(CompteEpargne compteEpargne) {
        String query = "INSERT INTO compteEpargne(numero, dateCreation, sold, tauxInteret, client, employe) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, compteEpargne.getNemuro());
            preparedStatement.setDate(2, Date.valueOf(compteEpargne.getDateCreation()));
            preparedStatement.setDouble(3, compteEpargne.getSold());
            preparedStatement.setDouble(4, compteEpargne.getTauxInteret());
            preparedStatement.setString(5, compteEpargne.getClient().getCode());
            preparedStatement.setString(6, compteEpargne.getEmployé().getMatricul());
            Integer result = preparedStatement.executeUpdate();
            if (result != 0) {
                return Optional.of(compteEpargne);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<CompteEpargne> chercheCompte(String code) {
        String query = "SELECT * FROM compteEpargne WHERE client = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                compteEpargne.setSold(result.getDouble("sold"));
                compteEpargne.setEmployé(employeDao.chercherEmploye(result.getString( "employe")).get());
                compteEpargne.setTauxInteret(result.getDouble("tauxInteret"));
                compteEpargne.setClient(clientDao.chercherClient(result.getString("client")).get());
                compteEpargne.setEtat(Compte.statut.valueOf(result.getString("etat")));
                compteEpargne.setNemuro(result.getString("numero"));
                compteEpargne.setDateCreation(result.getDate("datecreation").toLocalDate());
                return Optional.of(compteEpargne);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Integer updateCompteEtat(Compte.statut statut, String numero) {
        String query = "UPDATE compteEpargne SET etat = ? WHERE numero = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, statut, Types.OTHER);
            preparedStatement.setString(2, numero);
            Integer result = preparedStatement.executeUpdate();
            if (result != 0) {
                return result;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<CompteEpargne> miseajourCompte(CompteEpargne compteEpargne) {
        String query = "UPDATE compteCourant SET sold = ?, tauxInteret = ? WHERE numero = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, compteEpargne.getSold());
            preparedStatement.setDouble(2, compteEpargne.getTauxInteret());
            preparedStatement.setString(3, compteEpargne.getNemuro());
            Integer result = preparedStatement.executeUpdate();
            if (result != 0) {
                return Optional.of(compteEpargne);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<CompteEpargne> chercheCompteParNum(String code) {
        String query = "SELECT * FROM compteEpargne WHERE numero = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                compteEpargne.setSold(result.getDouble("sold"));
                compteEpargne.setEmployé(employeDao.chercherEmploye(result.getString( "employe")).get());
                compteEpargne.setTauxInteret(result.getDouble("tauxInteret"));
                compteEpargne.setClient(clientDao.chercherClient(result.getString("client")).get());
                compteEpargne.setEtat(Compte.statut.valueOf(result.getString("etat")));
                compteEpargne.setNemuro(result.getString("numero"));
                compteEpargne.setDateCreation(result.getDate("datecreation").toLocalDate());
                return Optional.of(compteEpargne);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Integer supprimerCompte(String numero) {
        String query = "DELETE FROM compteEpargne WHERE numero = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, numero);
            Integer result = preparedStatement.executeUpdate();
            if (result != 0) {
                return result;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<ArrayList<CompteEpargne>> afficherComptes() {
        String query = "SELECT * FROM compteEpargne";
        ArrayList<CompteEpargne> compteEpargnes = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                compteEpargne.setSold(result.getDouble("sold"));
                compteEpargne.setEmployé(employeDao.chercherEmploye(result.getString( "employe")).get());
                compteEpargne.setTauxInteret(result.getDouble("tauxInteret"));
                compteEpargne.setClient(clientDao.chercherClient(result.getString("client")).get());
                compteEpargne.setEtat(Compte.statut.valueOf(result.getString("etat")));
                compteEpargne.setNemuro(result.getString("numero"));
                compteEpargne.setDateCreation(result.getDate("datecreation").toLocalDate());
                compteEpargnes.add(compteEpargne);
            }
                return Optional.of(compteEpargnes);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
