package com.EasyBank.daoImpl;

import com.EasyBank.dao.EmployeDao;
import com.EasyBank.entity.Employe;
import com.EasyBank.util.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class EmployeDaoImpl implements EmployeDao {
    Connection connection = DbConnection.createConnection();
    Employe employe = new Employe();

    @Override
    public Optional<Employe> ajouterEmploye(Employe employe) {
        String query = "INSERT INTO personne(nom, prenome, dateNaissance, telephone) VALUES (?,?,?,?) RETURNING id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employe.getNom());
            preparedStatement.setString(2, employe.getPrenom());
            preparedStatement.setDate(3, Date.valueOf(employe.getDateNaissance()));
            preparedStatement.setString(4, employe.getTelephone());
            ResultSet result = preparedStatement.executeQuery();
            Integer personneId = 0;
            if (result.next()) {
                personneId = result.getInt(1);
            }
            String stmt = "INSERT INTO employe VALUES (?,?,?,?)";
            PreparedStatement statment = connection.prepareStatement(stmt);
            statment.setString(1, employe.getMatricul());
            statment.setDate(2, Date.valueOf(employe.getDateRecrutement()));
            statment.setString(3, employe.getEmail());
            statment.setInt(4, personneId);
            Integer integer = statment.executeUpdate();
            if (integer != 0) {
                return Optional.of(employe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Integer supprimerEmploye(String matricul) {
        String query = "DELETE FROM personne WHERE id = (SELECT personneId FROM employe WHERE matricule = ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, matricul);
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
    public Optional<Employe> chercherEmploye(String matricule) {
        String query = "SELECT * FROM employe WHERE matricule = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, matricule);
            ResultSet result = preparedStatement.executeQuery();
            Integer personneId = 0;
            if (result.next()) {
                personneId = result.getInt("personneId");
            }
            String stmt = "SELECT * FROM personne WHERE id = ?";
            PreparedStatement statment = connection.prepareStatement(stmt);
            statment.setInt(1, personneId);
            ResultSet resultSet = statment.executeQuery();
            if (resultSet.next()) {
                employe.setMatricul(result.getString("matricule"));
                employe.setNom(resultSet.getString("nom"));
                employe.setPrenom(resultSet.getString("prenome"));
                employe.setDateNaissance(resultSet.getDate("datenaissance").toLocalDate());
                employe.setTelephone(resultSet.getString("telephone"));
                employe.setEmail(result.getString("email"));
                employe.setDateRecrutement(result.getDate("daterecrutement").toLocalDate());
                return Optional.of(employe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
