package com.EasyBank.daoImpl;

import com.EasyBank.dao.AffectationDao;
import com.EasyBank.entity.Affectation;
import com.EasyBank.util.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class AffectationDaoImpl implements AffectationDao {
    Connection connection = DbConnection.createConnection();
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
        if(res != 0){
            return Optional.of(affectation);
        }
        }catch (SQLException e){
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
            if(res != 0){
                return res;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
