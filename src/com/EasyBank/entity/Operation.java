package com.EasyBank.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Operation {
    private Integer numero;
    private LocalDate dateCreation;
    private Double montant;
    private Employe employé;
    private CompteCourant compteCourant;
    private CompteEpargne compteEpargne;
    private Type type;

    public enum Type {
        Retrait,
        Versement
    }

    public Operation() {
    }

    public Operation(Integer numero, LocalDate dateCreation, Double montant, Employe employé, CompteCourant compteCourant, CompteEpargne compteEpargne, Type type) {
        this.numero = numero;
        this.dateCreation = dateCreation;
        this.montant = montant;
        this.employé = employé;
        this.compteCourant = compteCourant;
        this.compteEpargne = compteEpargne;
        this.type = type;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Employe getEmployé() {
        return employé;
    }

    public void setEmployé(Employe employé) {
        this.employé = employé;
    }

    public CompteCourant getCompteCourant() {
        return compteCourant;
    }

    public void setCompteCourant(CompteCourant compteCourant) {
        this.compteCourant = compteCourant;
    }

    public CompteEpargne getCompteEpargne() {
        return compteEpargne;
    }

    public void setCompteEpargne(CompteEpargne compteEpargne) {
        this.compteEpargne = compteEpargne;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(numero, operation.numero) && Objects.equals(dateCreation, operation.dateCreation) && Objects.equals(montant, operation.montant) && Objects.equals(employé, operation.employé) && Objects.equals(compteCourant, operation.compteCourant) && type == operation.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, dateCreation, montant, employé, compteCourant, type);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "numero=" + numero +
                ", dateCreation=" + dateCreation +
                ", montant=" + montant +
                ", employé=" + employé +
                ", compte=" + compteCourant +
                ", type=" + type +
                '}';
    }
}