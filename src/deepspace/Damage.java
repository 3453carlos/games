package deepspace;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;


/**
 *
 * @author victor
 */
public abstract class Damage {
    private int nShields;

    Damage(int nShields) {
        this.nShields = nShields;
    }

   
    abstract public Damage copy();
    
    abstract public DamageToUI getUIversion();

    public int getNShields() {
        return nShields;
    }      
    
    
    abstract public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s);
    
    abstract public void discardWeapon(Weapon w);
    
    public int adjustShields(ArrayList<ShieldBooster> s) {
        return Math.min(nShields,s.size());
    }
    
    
    @Override
    public String toString() {
        return "nShields: " + nShields;
    }
      
    public void discardShieldBooster() {
       if(nShields > 0)
           nShields -= 1;
    }
   
    public boolean hasNoEffect() {
        return nShields == 0;
    }
   
    /*
     abstract private int arrayContainsType(ArrayList<Weapon> w,WeaponType t) {
       for(int i = 0; i < w.size(); i++) 
           if(w.get(i).getType() == t)
               return i;
       
        return -1;       
    }
     */
      
    
}
