package com.EasyBank.daoImpl;

import com.EasyBank.dao.MissionDao;
import com.EasyBank.entity.Mission;
import com.EasyBank.util.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
