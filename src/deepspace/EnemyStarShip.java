/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *
 * @author victor
 */
public class EnemyStarShip implements SpaceFighter{
    private float ammoPower, shieldPower;
    private String name;
    private Loot loot;
    private Damage damage;

    EnemyStarShip(String name, float ammoPower, float shieldPower,Loot loot, Damage damage) {
        this.ammoPower = ammoPower;
        this.shieldPower = shieldPower;
        this.name = name;
        this.loot = loot;
        this.damage = damage;
    }
    
    EnemyStarShip(EnemyStarShip e) {
        this.ammoPower = e.ammoPower;
        this.shieldPower = e.shieldPower;
        this.name = e.name;
        this.loot = e.loot;
        this.damage = e.damage.copy();
        
    }

    public float getAmmoPower() {
        return ammoPower;
    }

    public float getShieldPower() {
        return shieldPower;
    }

    public String getName() {
        return name;
    }

    public Loot getLoot() {
        return loot;
    }

    public Damage getDamage() {
        return damage;
    }
    
    EnemyToUI getUIversion() {
        return new EnemyToUI(this);
    }
    
    public float fire() {
       return ammoPower;
    }
    
    public float protection() {
        return shieldPower;
    }
    
    public ShotResult receiveShot(float shot) {
        if(shot >= shieldPower)
            return ShotResult.DONOTRESIST;
        else
            return ShotResult.RESIST;            
    }

    @Override
    public String toString() {
        return "EnemyStarShip{" + "ammoPower=" + ammoPower + ", shieldPower=" + shieldPower + ", name=" + name + ", loot=" + loot + ", damage=" + damage + '}';
    }
    
    
    
    
    
    
    
}
