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
public enum WeaponType {
    LASER(2.0f), MISSILE(3.0f), PLASMA(4.0f);  
    
    private final float power;
    
    WeaponType(float power) {
        this.power = power;
    }
    
    float getPower() {
        return power;
    }  

    @Override
    public String toString() {
        String res;
        switch (this) {
            case LASER:
                res = "Laser{" + "power=" + power + '}'; 
                break;
            case MISSILE:
                res = "Missile{" + "power=" + power + '}'; 
                break;
            case PLASMA:
                res = "Plasma{" + "power=" + power + '}';
                break;
            default:
                res = "Error";
                break;
        }
        return res;
    }   
    
}