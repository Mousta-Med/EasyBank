package com.EasyBank.dao;

import com.EasyBank.entity.Operation;

import java.util.Optional;

public interface OperationDao {
    public Optional<Operation> ajouterOperation(Operation operation);
    public Integer supprimerOperation(Integer integer);
    public Optional<Operation> chercherOperation(Integer integer);
}
