package com.EasyBank.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Client extends Personne{
    private String code;
    private String adresse;

    public Client() {
    }

    public Client(String nom, String prenom, LocalDate dateNaissance, String telephone, String code, String adresse) {
        super(nom, prenom, dateNaissance, telephone);
        this.code = code;
        this.adresse = adresse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(code, client.code) && Objects.equals(adresse, client.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code, adresse);
    }

    @Override
    public String toString() {
        return "Client{" +
                "code=" + code +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
