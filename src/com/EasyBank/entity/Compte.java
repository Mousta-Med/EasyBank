package com.EasyBank.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Compte {

    private String nemuro;
    private Double sold;
    private LocalDate dateCreation;
    private statut etat;
    private Client client;
    private Employe employé;
    public enum statut {
        Bloqué,
        Actif,
        Suspendu,
        Frauduleux
    }

    public Compte(String nemuro, Double sold, LocalDate dateCreation, statut etat, Client client, Employe employé) {
        this.nemuro = nemuro;
        this.sold = sold;
        this.dateCreation = dateCreation;
        this.etat = etat;
        this.client = client;
        this.employé = employé;
    }

    public Compte() {
    }

    public String getNemuro() {
        return nemuro;
    }

    public void setNemuro(String nemuro) {
        this.nemuro = nemuro;
    }

    public Double getSold() {
        return sold;
    }

    public void setSold(Double sold) {
        this.sold = sold;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public statut getEtat() {
        return etat;
    }

    public void setEtat(statut etat) {
        this.etat = etat;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employe getEmployé() {
        return employé;
    }

    public void setEmployé(Employe employé) {
        this.employé = employé;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return Objects.equals(nemuro, compte.nemuro) && Objects.equals(sold, compte.sold) && Objects.equals(dateCreation, compte.dateCreation) && etat == compte.etat && Objects.equals(client, compte.client) && Objects.equals(employé, compte.employé);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nemuro, sold, dateCreation, etat, client, employé);
    }

    @Override
    public String toString() {
        return "Compte{" +
                "nemuro=" + nemuro +
                ", sold=" + sold +
                ", dateCreation=" + dateCreation +
                ", etat=" + etat +
                ", client=" + client +
                ", employé=" + employé +
                '}';
    }
}
