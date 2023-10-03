package com.EasyBank.daoImpl;

import com.EasyBank.dao.CompteCourantDao;
import com.EasyBank.dao.EmployeDao;
import com.EasyBank.dao.OperationDao;
import com.EasyBank.entity.CompteCourant;
import com.EasyBank.entity.Operation;
import com.EasyBank.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OperationDaoImpl implements OperationDao {
    Operation operation = new Operation();
    CompteCourantDao compteCourantDao = new CompteCourantDaoImpl();
    EmployeDao employeDao = new EmployeDaoImpl();
    Connection connection = DbConnection.createConnection();

    @Override
    public Optional<Operation> ajouterOperation(Operation operation) {
        String query;
        if (operation.getCompteCourant() != null) {
            query = "INSERT INTO Operation(numero, dateOperation, montant, type, compteCourant, employe) VALUES (?,?,?,?,?,?)";
        } else {
            query = "INSERT INTO Operation(numero, dateOperation, montant, type, compteEpargne, employe) VALUES (?,?,?,?,?,?)";
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, operation.getNumero());
            preparedStatement.setDate(2, Date.valueOf(operation.getDateCreation()));
            preparedStatement.setDouble(3, operation.getMontant());
            preparedStatement.setObject(4, operation.getType(), Types.OTHER);
            if (operation.getCompteCourant() != null) {
                preparedStatement.setString(5, operation.getCompteCourant().getNemuro());
            } else {
                preparedStatement.setString(5, operation.getCompteEpargne().getNemuro());
            }
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Operation> chercherOperation(Integer numero) {
        String query = "SELECT * FROM operation WHERE numero = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, numero);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                operation.setNumero(result.getInt("numero"));
                operation.setMontant(result.getDouble("montant"));
                operation.setType(Operation.Type.valueOf(result.getObject("type").toString()));
                operation.setDateCreation(result.getDate("dateOperation").toLocalDate());
                operation.setEmployé(employeDao.chercherEmploye(result.getString("employe")).get());
                operation.setCompteCourant(compteCourantDao.chercheCompteParNum(result.getString("compteCourant")).get());
                return Optional.of(operation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<ArrayList<Operation>> afficherOperations() {
        String query = "SELECT * FROM operation";
        ArrayList<Operation> operations = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                operation.setNumero(result.getInt("numero"));
                operation.setMontant(result.getDouble("montant"));
                operation.setType(Operation.Type.valueOf(result.getObject("type").toString()));
                operation.setDateCreation(result.getDate("dateOperation").toLocalDate());
                operation.setEmployé(employeDao.chercherEmploye(result.getString("employe")).get());
                operation.setCompteCourant(compteCourantDao.chercheCompteParNum(result.getString("compteCourant")).get());
                operations.add(operation);
            }
            return Optional.of(operations);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
