package com.ecommerce.model.paiement;

public class Paiement {
    private int id, commandeId;
    private String moyen, prestataire, referenceExterne, cleIdempotence, statut;
    private double montant;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCommandeId() {
        return commandeId;
    }
    public void setCommandeId(int commandeId) {
        this.commandeId = commandeId;
    }
    public String getMoyen() {
        return moyen;
    }
    public void setMoyen(String moyen) {
        this.moyen = moyen;
    }
    public String getPrestataire() {
        return prestataire;
    }
    public void setPrestataire(String prestataire) {
        this.prestataire = prestataire;
    }
    public String getReferenceExterne() {
        return referenceExterne;
    }
    public void setReferenceExterne(String referenceExterne) {
        this.referenceExterne = referenceExterne;
    }
    public String getCleIdempotence() {
        return cleIdempotence;
    }
    public void setCleIdempotence(String cleIdempotence) {
        this.cleIdempotence = cleIdempotence;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
}
