package com.EasyBank.entity;

import java.time.LocalDate;
import java.util.Objects;

public class CompteEpargne extends Compte{
    private double tauxInteret;

    public CompteEpargne(String nemuro, Double sold, LocalDate dateCreation, statut etat, Client client, Employe employé, double tauxInteret) {
        super(nemuro, sold, dateCreation, etat, client, employé);
        this.tauxInteret = tauxInteret;
    }

    public CompteEpargne() {
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CompteEpargne that = (CompteEpargne) o;
        return Double.compare(tauxInteret, that.tauxInteret) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tauxInteret);
    }

    @Override
    public String toString() {
        return "CompteEpargne{" +
                "tauxInteret=" + tauxInteret +
                '}';
    }
}
