package com.EasyBank.entity;

import java.util.Objects;

public class Mission {
    private String code;
    private String nom;
    private String description;
    public Mission() {
    }
    public Mission(String code, String nom, String description) {
        this.code = code;
        this.nom = nom;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mission mission = (Mission) o;
        return Objects.equals(code, mission.code) && Objects.equals(nom, mission.nom) && Objects.equals(description, mission.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, nom, description);
    }

    @Override
    public String toString() {
        return "Mission{" +
                "code=" + code +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
