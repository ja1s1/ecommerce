package com.ecommerce.model.paiement;

public class Remboursement {
    private int id, paiementId;
    private String cleIdempotence, motif, statut;
    private double montant;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPaiementId() {
        return paiementId;
    }
    public void setPaiementId(int paiementId) {
        this.paiementId = paiementId;
    }
    public String getCleIdempotence() {
        return cleIdempotence;
    }
    public void setCleIdempotence(String cleIdempotence) {
        this.cleIdempotence = cleIdempotence;
    }
    public String getMotif() {
        return motif;
    }
    public void setMotif(String motif) {
        this.motif = motif;
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
