package com.ecommerce.model.produit;

public class ProduitCategorie {
    private int ProduitId, categorieId;

    public int getProduitId() {
        return ProduitId;
    }

    public void setProduitId(int produitId) {
        ProduitId = produitId;
    }

    public int getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }
}
