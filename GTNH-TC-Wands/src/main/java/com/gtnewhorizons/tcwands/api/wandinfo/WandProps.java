package com.gtnewhorizons.tcwands.api.wandinfo;

public class WandProps {

    private int baseCost, capCost;

    public WandProps(int baseCost, int capCost) {
        this.baseCost = baseCost;
        this.capCost = capCost;
    }

    public int getCapCost() {
        return capCost;
    }

    public int getBaseCost() {
        return baseCost;
    }
}
