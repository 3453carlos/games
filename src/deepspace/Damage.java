package deepspace;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;


/**
 *
 * @author victor
 */
public class Damage {
    private int nShields, nWeapons;
    private ArrayList<WeaponType> weapons;

    Damage( int nWeapons, int nShields) {
        this.nShields = nShields;
        this.nWeapons = nWeapons;
    }

    Damage(ArrayList<WeaponType> weapons, int nShields) {
        this.nShields = nShields;
        this.weapons = weapons;
        this.nWeapons = -1;
    }
    
    Damage(Damage copy) {
        nShields = copy.nShields;
        nWeapons = copy.nWeapons;
        weapons = copy.weapons;        
    }
    
    DamageToUI getUIversion() {
        return new DamageToUI(this);
    }

    public int getNShields() {
        return nShields;
    }

    public int getNWeapons() {
        return nWeapons;
    }

    public ArrayList<WeaponType> getWeapons() {
        return weapons;
    }
      
    
    
    public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s) {
     //Primero miramos si es numérico o armas concretas el arma
        if (nWeapons == -1) {//Entonces es específico
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
        
        return new Damage(res,Math.min(nShields,s.size()));
        } else //Entonces es numérico
           return new Damage(Math.min(nWeapons,w.size()), Math.min(nShields,s.size()));      
      
      }
    
     public void discardWeapon(Weapon w) {
          if(nWeapons == -1) //Entonces es específico
              weapons.remove(w.getType());
           else //Es numérico
              if(nWeapons > 0)
                  nWeapons -= 1;  
         
       }

    @Override
    public String toString() {
        return "Damage{" + "nShields=" + nShields + ", nWeapons=" + nWeapons + ", weapons=" + weapons + '}';
    }
      
   public void discardShieldBooster() {
       if(nShields > 0)
           nShields -= 1;
   }
   
   public boolean hasNoEffect() {
       return nShields == 0 && (nWeapons == 0 || (nWeapons == -1 && weapons.isEmpty()));
   }
   
   private int arrayContainsType(ArrayList<Weapon> w,WeaponType t) {
       for(int i = 0; i < w.size(); i++) 
           if(w.get(i).getType() == t)
               return i;
       
        return -1;       
    }
      
    
}
