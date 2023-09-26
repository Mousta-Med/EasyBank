package com.EasyBank.daoImpl;

import com.EasyBank.dao.ClientDao;
import com.EasyBank.dao.CompteCourantDao;
import com.EasyBank.dao.EmployeDao;
import com.EasyBank.entity.Compte;
import com.EasyBank.entity.CompteCourant;
import com.EasyBank.util.DbConnection;

import java.sql.*;
import java.util.Optional;

public class CompteCourantDaoImpl implements CompteCourantDao {
    Connection connection = DbConnection.createConnection();
    EmployeDao employeDao = new EmployeDaoImpl();
    ClientDao clientDao = new ClientDaoImpl();
    CompteCourant compteCourant = new CompteCourant();
    @Override
    public Optional<CompteCourant> creeCompte(CompteCourant compteCourant) {
        String query = "INSERT INTO compteCourant(numero, dateCreation, sold, decouvert, client, employe) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, compteCourant.getNemuro());
            preparedStatement.setDate(2, Date.valueOf(compteCourant.getDateCreation()));
            preparedStatement.setDouble(3, compteCourant.getSold());
            preparedStatement.setDouble(4, compteCourant.getDecouvert());
            preparedStatement.setString(5, compteCourant.getClient().getCode());
            preparedStatement.setString(6, compteCourant.getEmployé().getMatricul());
            Integer result = preparedStatement.executeUpdate();
            if (result != 0) {
                return Optional.of(compteCourant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<CompteCourant> chercheCompte(String code) {
        String query = "SELECT * FROM compteCourant WHERE client = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                compteCourant.setSold(result.getDouble("sold"));
                compteCourant.setEmployé(employeDao.chercherEmploye(result.getString( "employe")).get());
                compteCourant.setDecouvert(result.getDouble("decouvert"));
                compteCourant.setClient(clientDao.chercherClient(result.getString("client")).get());
                compteCourant.setEtat(Compte.statut.valueOf(result.getString("etat")));
                compteCourant.setNemuro(result.getString("numero"));
                compteCourant.setDateCreation(result.getDate("datecreation").toLocalDate());
                return Optional.of(compteCourant);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Integer supprimerCompte(String numero) {
        String query = "DELETE FROM compteCourant WHERE numero = ?";
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
}
