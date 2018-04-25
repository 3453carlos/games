package deepspace;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author victor
 */
class SuppliesPackage {
    private float ammoPower, fuelUnits, shieldPower;

    SuppliesPackage(float ammoPower, float fuelUnits, float shieldPower) {
        this.ammoPower = ammoPower;
        this.fuelUnits = fuelUnits;
        this.shieldPower = shieldPower;
    }
    
    SuppliesPackage(SuppliesPackage s) {
        ammoPower = s.ammoPower;
        fuelUnits = s.fuelUnits;
        shieldPower = s.shieldPower;
    }

    public float getAmmoPower() {
        return ammoPower;
    }

    public float getFuelUnits() {
        return fuelUnits;
    }

    public float getShieldPower() {
        return shieldPower;
    }

    @Override
    public String toString() {
        return "SuppliesPackage{" + "ammoPower=" + ammoPower + ", fuelUnits=" + fuelUnits + ", shieldPower=" + shieldPower + '}';
    }
    
    
    
}
