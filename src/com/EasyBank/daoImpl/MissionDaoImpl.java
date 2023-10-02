package com.EasyBank.daoImpl;

import com.EasyBank.dao.MissionDao;
import com.EasyBank.entity.Mission;
import com.EasyBank.util.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

public class MissionDaoImpl implements MissionDao {
    Connection connection = DbConnection.createConnection();
    Mission mission = new Mission();
    @Override
    public Optional<Mission> ajouterMision(Mission mission) {
        String query = "INSERT INTO Mission(code, nom, description) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, mission.getCode());
            preparedStatement.setString(2, mission.getNom());
            preparedStatement.setString(3, mission.getDescription());
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return Optional.of(mission);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public Integer supprimerMision(String code) {
        String query = "DELETE FROM Mission WHERE code = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,code);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Mission> chercherMission(String code) {
        String query = "SELECT * FROM Mission WHERE code = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,code);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                mission.setCode(result.getString("code"));
                mission.setNom(result.getString("nom"));
                mission.setDescription(result.getString("description"));
                return Optional.of(mission);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<ArrayList<Mission>> afficherMissions() {
        String query = "SELECT * FROM Mission";
        ArrayList<Mission> missions = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                mission.setNom(result.getString("nom"));
                mission.setDescription(result.getString("description"));
                mission.setCode(result.getString("code"));
                missions.add(mission);
            }
            return Optional.of(missions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
