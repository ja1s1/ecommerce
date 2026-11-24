package com.ecommerce.model.panier;

public class Panier {
    private int id, clientId;
    private String jetonHash, etat;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
    public String getJetonHash() {
        return jetonHash;
    }
    public void setJetonHash(String jetonHash) {
        this.jetonHash = jetonHash;
    }
    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
}
