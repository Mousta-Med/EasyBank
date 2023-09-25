package com.EasyBank.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Affectation {
    private LocalDate dateAffectation;
    private LocalDate dateFinAffectation;
    private Mission mission;
    private Employe employe;

    public Affectation(LocalDate dateAffectation, LocalDate dateFinAffectation, Mission mission, Employe employe) {
        this.dateAffectation = dateAffectation;
        this.dateFinAffectation = dateFinAffectation;
        this.mission = mission;
        this.employe = employe;
    }

    public Affectation() {
    }

    public LocalDate getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(LocalDate dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public LocalDate getDateFinAffectation() {
        return dateFinAffectation;
    }

    public void setDateFinAffectation(LocalDate dateFinAffectation) {
        this.dateFinAffectation = dateFinAffectation;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    @Override
    public String
    toString() {
        return "Affectation{" +
                "dateAffectation=" + dateAffectation +
                ", dateFinAffectation=" + dateFinAffectation +
                ", mission=" + mission +
                ", employe=" + employe +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Affectation that = (Affectation) o;
        return Objects.equals(dateAffectation, that.dateAffectation) && Objects.equals(dateFinAffectation, that.dateFinAffectation) && Objects.equals(mission, that.mission) && Objects.equals(employe, that.employe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateAffectation, dateFinAffectation, mission, employe);
    }
}
