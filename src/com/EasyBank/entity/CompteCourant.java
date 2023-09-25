package com.EasyBank.entity;

import java.time.LocalDate;
import java.util.Objects;

public class CompteCourant extends Compte{
    private Double decouvert;

    public CompteCourant() {
    }
    public CompteCourant(Integer nemuro, Double sold, LocalDate dateCreation, statut etat, Client client, Employe employé, Double decouvert) {
        super(nemuro, sold, dateCreation, etat, client, employé);
        this.decouvert = decouvert;
    }

    public Double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(Double decouvert) {
        this.decouvert = decouvert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CompteCourant that = (CompteCourant) o;
        return Objects.equals(decouvert, that.decouvert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), decouvert);
    }

    @Override
    public String toString() {
        return "CompteCourant{" +
                "decouvert=" + decouvert +
                '}';
    }
}
