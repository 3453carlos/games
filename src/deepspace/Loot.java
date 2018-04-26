package deepspace;


import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author victor
 */
class Loot {
    private int nSupplies, nWeapons, nShields, nHangars, nMedals;
    private boolean getEfficient, spaceCity;
    Loot(int nSupplies, int nWeapons, int nShields, int nHangars, int nMedals, boolean ef, boolean city) {
        this.nSupplies = nSupplies;
        this.nWeapons = nWeapons;
        this.nShields = nShields;
        this.nHangars = nHangars;
        this.nMedals = nMedals;
        spaceCity = city;
        getEfficient = ef;
    }   

    Loot(int nSupplies, int nWeapons, int nShields, int nHangars, int nMedals) {
        this.nSupplies = nSupplies;
        this.nWeapons = nWeapons;
        this.nShields = nShields;
        this.nHangars = nHangars;
        this.nMedals = nMedals;
        spaceCity = false;
        getEfficient= false;
    } 
    public boolean getEfficient(){
        return getEfficient;
    }
    
    public boolean spaceCity(){
        return spaceCity;
    }
    public int getNSupplies() {
        return nSupplies;
    }

    public int getNWeapons() {
        return nWeapons;
    }

    public int getNShields() {
        return nShields;
    }

    public int getNHangars() {
        return nHangars;
    }

    public int getNMedals() {
        return nMedals;
    }
    
    LootToUI getUIversion() {
        return new LootToUI(this);
    }

    @Override
    public String toString() {
        return "Loot{" + "nSupplies=" + nSupplies + ", nWeapons=" + nWeapons + ", nShields=" + nShields + ", nHangars=" + nHangars + ", nMedals=" + nMedals + '}';
    }
    
    
    
    
    
}
