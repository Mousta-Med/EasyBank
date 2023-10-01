package com.EasyBank.daoImpl;

import com.EasyBank.dao.OperationDao;
import com.EasyBank.entity.Operation;
import com.EasyBank.util.DbConnection;

import java.sql.*;
import java.util.Optional;

public class OperationDaoImpl implements OperationDao {
    Connection connection = DbConnection.createConnection();
    @Override
    public Optional<Operation> ajouterOperation(Operation operation) {
        String query = "INSERT INTO Operation(numero, dateOperation, montant, type, compteCourant, employe) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, operation.getNumero());
            preparedStatement.setDate(2, Date.valueOf(operation.getDateCreation()));
            preparedStatement.setDouble(3, operation.getMontant());
            preparedStatement.setObject(4, operation.getType(), Types.OTHER);
            preparedStatement.setString(5, operation.getCompte().getNemuro());
            preparedStatement.setString(6, operation.getEmployé().getMatricul());
            Integer result = preparedStatement.executeUpdate();
            if (result != 0) {
                return Optional.of(operation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Integer supprimerOperation(Integer numero) {
        String query = "DELETE FROM operation WHERE numero = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, numero);
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
    public Optional<Operation> chercherOperation(Integer integer) {
        return null;
    }
}
