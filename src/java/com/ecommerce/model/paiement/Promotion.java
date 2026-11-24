package com.ecommerce.model.paiement;

import java.sql.Date;

public class Promotion {
    private int id;
    private String code, typeRemise;
    private double valeur;
    private Date debut, fin;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getTypeRemise() {
        return typeRemise;
    }
    public void setTypeRemise(String typeRemise) {
        this.typeRemise = typeRemise;
    }
    public double getValeur() {
        return valeur;
    }
    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
    public Date getDebut() {
        return debut;
    }
    public void setDebut(Date debut) {
        this.debut = debut;
    }
    public Date getFin() {
        return fin;
    }
    public void setFin(Date fin) {
        this.fin = fin;
    }
}
