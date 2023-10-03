package com.EasyBank.daoImpl;

import com.EasyBank.dao.AffectationDao;
import com.EasyBank.dao.EmployeDao;
import com.EasyBank.dao.MissionDao;
import com.EasyBank.entity.Affectation;
import com.EasyBank.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class AffectationDaoImpl implements AffectationDao {
    Connection connection = DbConnection.createConnection();
    Affectation affectation = new Affectation();
    EmployeDao employeDao = new EmployeDaoImpl();
    MissionDao missionDao = new MissionDaoImpl();

    @Override
    public Optional<Affectation> ajouterAffectation(Affectation affectation) {
        String query = "INSERT INTO affectation values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(affectation.getDateAffectation()));
            preparedStatement.setDate(2, Date.valueOf(affectation.getDateFinAffectation()));
            preparedStatement.setString(3, affectation.getEmploye().getMatricul());
            preparedStatement.setString(4, affectation.getMission().getCode());
            int res = preparedStatement.executeUpdate();
            if (res != 0) {
                return Optional.of(affectation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Integer supprimerAffectation(String employe, String mission) {
        String query = "DELETE FROM affectation WHERE employe = ? AND mission = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employe);
            preparedStatement.setString(2, mission);
            int res = preparedStatement.executeUpdate();
            if (res != 0) {
                return res;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<ArrayList<Affectation>> afficherAffectations() {
        String query = "SELECT * FROM affectation";
        ArrayList<Affectation> affectations = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                affectation.setEmploye(employeDao.chercherEmploye(res.getString("employe")).get());
                affectation.setDateAffectation(res.getDate("dateAffectation").toLocalDate());
                affectation.setDateFinAffectation(res.getDate("dateFinAffectation").toLocalDate());
                affectation.setMission(missionDao.chercherMission(res.getString("mission")).get());
                affectations.add(affectation);
            }
            return Optional.of(affectations);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<ArrayList<Affectation>> afficherAffectationParEmploye(String employe) {
        String query = "SELECT * FROM affectation WHERE employe = ?";
        ArrayList<Affectation> affectations = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employe);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                affectation.setEmploye(employeDao.chercherEmploye(employe).get());
                affectation.setDateAffectation(res.getDate("dateAffectation").toLocalDate());
                affectation.setDateFinAffectation(res.getDate("dateFinAffectation").toLocalDate());
                affectation.setMission(missionDao.chercherMission(res.getString("mission")).get());
                affectations.add(affectation);
            }
                return Optional.of(affectations);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
