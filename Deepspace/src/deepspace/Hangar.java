
package deepspace;
import java.util.ArrayList;

/**
 *
 * @author victor
 */
public class Hangar {
    private int maxElements;
    private ArrayList<ShieldBooster> shieldBoosters = new ArrayList();
    private ArrayList<Weapon> weapons = new ArrayList();
    
    Hangar(int capacity){
        maxElements=capacity;
    }
    
    Hangar(Hangar copy){
        maxElements = copy.maxElements;
        shieldBoosters = copy.shieldBoosters;
        weapons = copy.weapons;
    }

    public int getMaxElements() {
        return maxElements;
    }

    public ArrayList<ShieldBooster> getShieldBoosters() {
        return shieldBoosters;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }
    
    HangarToUI getUIversion(){
        return new HangarToUI(this);
    }
    
    private boolean spaceAvailable(){
        return shieldBoosters.size() + weapons.size() < maxElements;
    }
    
    public boolean addWeapon(Weapon w){
        if( spaceAvailable() ){
            weapons.add(w);
            return true;
        } else
            return false;              
    }
    
    public boolean addShieldBooster(ShieldBooster w){
        if( spaceAvailable() ){
            shieldBoosters.add(w);
            return true;
        } else
            return false;              
    }
    
   public Weapon removeWeapon(int w){
       if(w >= 0 && w < weapons.size())
         return weapons.remove(w);  
       
       return null;
    }
   
    public ShieldBooster removeShieldBooster(int s){
        if( s >= 0 && s < shieldBoosters.size())
            return shieldBoosters.remove(s);        
       return null;
    }
   
    
   
    
}
