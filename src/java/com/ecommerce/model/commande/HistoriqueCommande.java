package com.ecommerce.model.commande;

import java.sql.Date;

public class HistoriqueCommande {
    private int id, commandeId;
    private String statut;
    private Date enregistreLe;
    
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
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public Date getEnregistreLe() {
        return enregistreLe;
    }
    public void setEnregistreLe(Date enregistreLe) {
        this.enregistreLe = enregistreLe;
    }

}
