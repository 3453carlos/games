/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.lang.Math.*;

/**
 *
 * @author victor
 */
public class SpaceStation implements SpaceFighter{
    private static int MAXFUEL = 100; 
    private static float SHIELDLOSSPERUNITSHOT = 0.1f;
    
    private float ammoPower, fuelUnits, shieldPower;
    private int nMedals; 
    private String name;
    private Damage pendingDamage;
    private ArrayList<Weapon> weapons = new ArrayList();
    private ArrayList<ShieldBooster> shieldBoosters = new ArrayList();
    private Hangar hangar;
    
    
    SpaceStation(String n, SuppliesPackage s) {
        name = n;
        ammoPower = s.getAmmoPower();
        fuelUnits = s.getFuelUnits();
        shieldPower = s.getShieldPower();
        nMedals = 0;
    }
    
    SpaceStation(SpaceStation c){
        name = c.name;
        ammoPower = c.ammoPower;
        fuelUnits = c.fuelUnits;
        shieldPower = c.shieldPower;
        for(int i = 0; i < c.weapons.size(); i++)
            weapons.add(c.weapons.get(i));
        for(int i = 0; i < c.shieldBoosters.size(); i++)
            shieldBoosters.add(c.shieldBoosters.get(i));
        nMedals = c.nMedals;
        hangar = c.hangar;
        pendingDamage = c.pendingDamage;        
    }   
    
    private void assignFuelValue(float f) {
      if(f<=  MAXFUEL)
        fuelUnits = f;
    } 
    
    
    private void cleanPendingDamage(){
        if(pendingDamage.hasNoEffect())
             pendingDamage = null;
    }
    
    SpaceStationToUI getUIversion() {
        return new SpaceStationToUI(this);
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

    public int getNMedals() {
        return nMedals;
    }

    public String getName() {
        return name;
    }

    public Damage getPendingDamage() {
        return pendingDamage;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<ShieldBooster> getShieldBoosters() {
        return shieldBoosters;
    }

    public Hangar getHangar() {
        return hangar;
    }
       
    public void discardHangar() {
      hangar = null;             
    }
    
    public boolean receiveShieldBooster(ShieldBooster s) {
        if(hangar != null)
            return hangar.addShieldBooster(s);
               
        return false;
    }
    
    public boolean receiveWeapon(Weapon w) {
        if(hangar != null)
            return hangar.addWeapon(w);
               
        return false;
    }
    
    public void receiveHangar(Hangar h) {
        if(hangar == null)
            hangar = h;
    }
    
    public void receiveSupplies(SuppliesPackage s) {
        ammoPower += s.getAmmoPower();
        shieldPower += s.getShieldPower();
        if(fuelUnits + s.getFuelUnits() <= MAXFUEL)
            fuelUnits += s.getFuelUnits();
        else
             fuelUnits = MAXFUEL;
    }
    
    public void setPendingDamage(Damage d) {
        pendingDamage = d.adjust(weapons,shieldBoosters);
    }
    
    public void mountWeapon(int i) {
         if(hangar != null){
            Weapon w = hangar.removeWeapon(i);
            if(w!=null)
                weapons.add(w);
        }
}
    
    public void mountShieldBooster(int i) {
       if(hangar != null){
            ShieldBooster s = hangar.removeShieldBooster(i);
            if(s!=null)
                shieldBoosters.add(s);
        }
}
    
    public void discardWeaponInHangar(int i) {
        if(hangar != null)
           hangar.removeWeapon(i);
    }
    
    public void discardShieldBoosterInHangar(int i) {
        if(hangar != null)
           hangar.removeShieldBooster(i);
    }
    
    public float getSpeed() {
        return fuelUnits / MAXFUEL;        
    }
    
    public void move() {
        fuelUnits -= getSpeed() * fuelUnits;
    }
    
    public boolean validState() {
        return pendingDamage == null || pendingDamage.hasNoEffect();
    }
    
    public void cleanUpMountedItems() {
      //ESTO PUEDE QUE NO FUNCIONE ASÍ
      Predicate<Weapon> weaponPredicate;
        weaponPredicate = (w-> w.getUses() == 0);
      weapons.removeIf(weaponPredicate);
      
      Predicate<ShieldBooster> shieldPredicate;
        shieldPredicate = (s-> s.getUses() == 0);
      shieldBoosters.removeIf(shieldPredicate);      
    }
    
    public float fire() { 
        int size = weapons.size();
        float factor = 1;
        for (int i = 0; i < size; i++) {
            factor *= weapons.get(i).useIt();
        }        
        return factor;  
    }
    
    public float protection() { 
        int size = shieldBoosters.size();
        float factor = 1;
        for (int i = 0; i < size; i++) {
            factor *= shieldBoosters.get(i).useIt();
        }        
        return factor;
    }
    
    public ShotResult receiveShot(float shot) { 
        float myProtection = protection();
        if(myProtection >= shot) {
            shieldPower -= SHIELDLOSSPERUNITSHOT * shot;
            shieldPower = (float) Math.max(0.0,shieldPower); //Hay que hacer un cast, sino da error de conversion ya que max devuelve double
            return ShotResult.RESIST;
        } else {
            shieldPower = (float) 0.0;
            return ShotResult.DONOTRESIST;
        }   
    }
    public Transformation setLoot(Loot loot) { 
        CardDealer dealer = CardDealer.getInstance();
        int h = loot.getNHangars();
        if(h > 0) {
            Hangar hangar = dealer.nextHangar();
            receiveHangar(hangar);
        }
        
        int elements = loot.getNSupplies();
        for(int i = 0; i < elements; i++) {
            SuppliesPackage sup = dealer.nextSuppliesPackage();
            receiveSupplies(sup);
        }
        
        elements = loot.getNWeapons();
        for(int i = 0; i < elements; i++) {
            Weapon weap = dealer.nextWeapon();
            receiveWeapon(weap);
        }
        
        elements = loot.getNShields();
        for(int i = 0; i < elements; i++) {
            ShieldBooster sh = dealer.nextShieldBooster();
            receiveShieldBooster(sh);
        }
        
        int medals = getNMedals();
        nMedals += medals; 
        
        if(loot.getEfficient())
            return Transformation.GETEFFICIENT;
        else if(loot.spaceCity())
            return Transformation.SPACECITY;
        else
            return Transformation.NOTRANSFORM;
    }
    
    public void discardWeapon(int i) { 
        int size = weapons.size();
        if(i >= 0 && i < size) {
            Weapon w = weapons.remove(i);
            if(pendingDamage != null) {
                pendingDamage.discardWeapon(w);
                cleanPendingDamage();
            }
        }
    }    
    
    
    public void discardShieldBooster(int i) { 
        int size = shieldBoosters.size();
        if(i >= 0 && i < size) {
            ShieldBooster s = shieldBoosters.remove(i);
            if(pendingDamage != null) {
                pendingDamage.discardShieldBooster();
                cleanPendingDamage();                
            }
        }    
    }   
       
    
}
