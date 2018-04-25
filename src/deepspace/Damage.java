package deepspace;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;


/**
 *
 * @author victor
 */
public abstract class Damage {
    protected int nShields;

    Damage(int nShields) {
        this.nShields = nShields;
    }

   
    public Object copy(Object c) {
        return c;
    }
    
    DamageToUI getUIversion() {
        return new DamageToUI(this);
    }

    public int getNShields() {
        return nShields;
    }

    abstract public int getNWeapons();

    abstract public ArrayList<WeaponType> getWeapons();
      
    
    
    abstract public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s);
    
    abstract public void discardWeapon(Weapon w);
    
    
    @Override
    abstract public String toString();
      
    public void discardShieldBooster() {
       if(nShields > 0)
           nShields -= 1;
    }
   
    abstract public boolean hasNoEffect();
   
    private int arrayContainsType(ArrayList<Weapon> w,WeaponType t) {
       for(int i = 0; i < w.size(); i++) 
           if(w.get(i).getType() == t)
               return i;
       
        return -1;       
    }
      
    
}
