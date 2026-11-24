package com.ecommerce.model.stock;

public class allocationStock {
    private int ligneId, varianteId, depotId, quantite, expedie, libere;

    public int getLigneId() {
        return ligneId;
    }

    public void setLigneId(int ligneId) {
        this.ligneId = ligneId;
    }

    public int getVarianteId() {
        return varianteId;
    }

    public void setVarianteId(int varianteId) {
        this.varianteId = varianteId;
    }

    public int getDepotId() {
        return depotId;
    }

    public void setDepotId(int depotId) {
        this.depotId = depotId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getExpedie() {
        return expedie;
    }

    public void setExpedie(int expedie) {
        this.expedie = expedie;
    }

    public int getLibere() {
        return libere;
    }

    public void setLibere(int libere) {
        this.libere = libere;
    }
}
