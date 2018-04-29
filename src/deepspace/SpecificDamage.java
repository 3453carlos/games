/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author victor
 */
public class SpecificDamage extends Damage {
    private ArrayList<WeaponType> weapons;
    
    SpecificDamage(ArrayList<WeaponType> w, int nShields) {
        super(nShields);
        weapons = w;
    }
    
    public ArrayList<WeaponType> getWeapons(){
        return weapons;
    }
    
    @Override
    public SpecificDamage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){        
        ArrayList<WeaponType> aux = new ArrayList<WeaponType>();
        ArrayList<WeaponType> res = new ArrayList<WeaponType>();
          
        for (Weapon elemento:w)
            aux.add(elemento.getType());
               
        WeaponType wtypes [] = WeaponType.values();
        for(int i = 0; i < wtypes.length; i+=1) {
            int min = Math.min(Collections.frequency(aux, wtypes[i]),Collections.frequency(weapons, wtypes[i]));
            for(int j = 0; j < min; j+=1)
                res.add(wtypes[i]);
        } 
        
        return new SpecificDamage(res,adjustShields(s));        
    }
    
    @Override
    public void discardWeapon(Weapon w) {         
        weapons.remove(w.getType());
          
    }
    
    @Override 
    public SpecificDamage copy() {
        return new SpecificDamage(new ArrayList<WeaponType>(weapons),getNShields());
    }
         
    
    @Override
    public boolean hasNoEffect(){
        return  weapons.size() == 0 && super.hasNoEffect();
    }
    
    @Override
    public SpecificDamageToUI getUIversion() {
       return new SpecificDamageToUI(this);
    }
    
    @Override
    public String toString() {
        return "Weapons: " + weapons + super.toString();
    }
}
