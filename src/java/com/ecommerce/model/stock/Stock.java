package com.ecommerce.model.stock;

public class Stock {
    private int varianteId, depotId, physique, reserve;

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

    public int getPhysique() {
        return physique;
    }

    public void setPhysique(int physique) {
        this.physique = physique;
    }

    public int getReserve() {
        return reserve;
    }

    public void setReserve(int reserve) {
        this.reserve = reserve;
    }
}
