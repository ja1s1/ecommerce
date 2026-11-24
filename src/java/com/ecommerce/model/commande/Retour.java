package com.ecommerce.model.commande;

public class Retour {
    private int id, expeditionId, ligneId, quantite;
    private String motif, statut;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getExpeditionId() {
        return expeditionId;
    }
    public void setExpeditionId(int expeditionId) {
        this.expeditionId = expeditionId;
    }
    public int getLigneId() {
        return ligneId;
    }
    public void setLigneId(int ligneId) {
        this.ligneId = ligneId;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
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
}
