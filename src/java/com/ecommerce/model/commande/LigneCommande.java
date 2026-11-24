package com.ecommerce.model.commande;

public class LigneCommande {
    private int id, commandeId, varianteId, quantite;
    private double prixUnitaireTTC, remiseLigne;
    private String skuAchete, designation;
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
    public int getVarianteId() {
        return varianteId;
    }
    public void setVarianteId(int varianteId) {
        this.varianteId = varianteId;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public double getPrixUnitaireTTC() {
        return prixUnitaireTTC;
    }
    public void setPrixUnitaireTTC(double prixUnitaireTTC) {
        this.prixUnitaireTTC = prixUnitaireTTC;
    }
    public double getRemiseLigne() {
        return remiseLigne;
    }
    public void setRemiseLigne(double remiseLigne) {
        this.remiseLigne = remiseLigne;
    }
    public String getSkuAchete() {
        return skuAchete;
    }
    public void setSkuAchete(String skuAchete) {
        this.skuAchete = skuAchete;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
