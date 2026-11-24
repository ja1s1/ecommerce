package com.ecommerce.model.produit;

public class ImgProduit {
    private int id, produitId, position;
    private String url;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getProduitId() {
        return produitId;
    }
    public void setProduitId(int produitId) {
        this.produitId = produitId;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
