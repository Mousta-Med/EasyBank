package com.EasyBank.daoImpl;

import com.EasyBank.dao.CompteCourantDao;
import com.EasyBank.entity.CompteCourant;
import com.EasyBank.util.DbConnection;

import java.sql.*;
import java.util.Optional;

public class CompteCourantDaoImpl implements CompteCourantDao {
    Connection connection = DbConnection.createConnection();
    @Override
    public Optional<CompteCourant> creeCompte(CompteCourant compteCourant) {
        String query = "INSERT INTO compteCourant(nemuro, dateCreation, sold, decouvert, client, employe) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, compteCourant.getNemuro());
            preparedStatement.setDate(2, Date.valueOf(compteCourant.getDateCreation()));
            preparedStatement.setDouble(3, compteCourant.getSold());
            preparedStatement.setDouble(4, compteCourant.getDecouvert());
//            preparedStatement.setObject(5, compteCourant.getEtat(), Types.OTHER);
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
        return Optional.empty();
    }

    @Override
    public Integer supprimerCompte(String numero) {
        return null;
    }
}
