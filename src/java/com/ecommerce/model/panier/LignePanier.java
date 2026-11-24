package com.ecommerce.model.panier;

public class LignePanier {
    private int panierId, varianteId, quantite;

    public int getPanierId() {
        return panierId;
    }

    public void setPanierId(int panierId) {
        this.panierId = panierId;
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
}
