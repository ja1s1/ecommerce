package com.ecommerce.model.commande;

import java.sql.Date;

public class Expedition {
    private int id, commandeId, depotId;
    private String transporteur, numeroSuivi;
    private Date expedieLe, livreLe;
    
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
    public int getDepotId() {
        return depotId;
    }
    public void setDepotId(int depotId) {
        this.depotId = depotId;
    }
    public String getTransporteur() {
        return transporteur;
    }
    public void setTransporteur(String transporteur) {
        this.transporteur = transporteur;
    }
    public String getNumeroSuivi() {
        return numeroSuivi;
    }
    public void setNumeroSuivi(String numeroSuivi) {
        this.numeroSuivi = numeroSuivi;
    }
    public Date getExpedieLe() {
        return expedieLe;
    }
    public void setExpedieLe(Date expedieLe) {
        this.expedieLe = expedieLe;
    }
    public Date getLivreLe() {
        return livreLe;
    }
    public void setLivreLe(Date livreLe) {
        this.livreLe = livreLe;
    }
}
