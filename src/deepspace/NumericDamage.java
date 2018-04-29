/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;
import java.lang.Math.*;
import java.util.ArrayList;

/**
 *
 * @author victor
 */
public class NumericDamage extends Damage {
    private int nWeapons;
    
    NumericDamage(int nWeapons, int nShields){
        super(nShields);
        this.nWeapons = nWeapons;
    }
    
    public int getNWeapons(){
        return nWeapons;
    }
    
    @Override 
    public NumericDamage copy() {
        return new NumericDamage(nWeapons,getNShields());
    }
    
    @Override
    public NumericDamageToUI getUIversion() {
        return new NumericDamageToUI(this);
    }
    
    @Override
    public NumericDamage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
        return new NumericDamage(Math.min(nWeapons,w.size()), adjustShields(s));
    }
    
    
    @Override
    public boolean hasNoEffect(){
        return nWeapons == 0 && super.hasNoEffect();
    }
    
    @Override
    public void discardWeapon(Weapon w) {
        if(nWeapons != 0)
             nWeapons--;
    }

    
    @Override
    public String toString() {
        return "nWeapons: " + nWeapons + super.toString();
       
    }
}
