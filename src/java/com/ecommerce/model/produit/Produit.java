package com.ecommerce.model.produit;

public class Produit {
    private int id;
    private String nom, slug, description;
    private double prixBaseTTC;
    private boolean actif;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrixBaseTTC() {
        return prixBaseTTC;
    }
    public void setPrixBaseTTC(double prixBaseTTC) {
        this.prixBaseTTC = prixBaseTTC;
    }
    public boolean isActif() {
        return actif;
    }
    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
