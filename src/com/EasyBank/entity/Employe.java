package com.EasyBank.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Employe extends Personne{
    private String matricul;
    private LocalDate dateRecrutement;
    private String email;
    private List<Mission> missions;

    public Employe(String nom, String prenom, LocalDate dateNaissance, String telephone, String matricul, LocalDate dateRecrutement, String email, List<Mission> missions) {
        super(nom, prenom, dateNaissance, telephone);
        this.matricul = matricul;
        this.dateRecrutement = dateRecrutement;
        this.email = email;
        this.missions = missions;
    }
    public Employe() {
    }

    public String getMatricul() {
        return matricul;
    }

    public void setMatricul(String matricul) {
        this.matricul = matricul;
    }

    public LocalDate getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(LocalDate dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employe employe = (Employe) o;
        return Objects.equals(matricul, employe.matricul) && Objects.equals(dateRecrutement, employe.dateRecrutement) && Objects.equals(email, employe.email) && Objects.equals(missions, employe.missions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), matricul, dateRecrutement, email, missions);
    }

    @Override
    public String toString() {
        return "Employe{" +
                "matricul='" + matricul + '\'' +
                ", dateRecrutement=" + dateRecrutement +
                ", email='" + email + '\'' +
                ", missions=" + missions +
                '}';
    }
}
