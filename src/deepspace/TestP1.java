/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;
import java.util.ArrayList;


/**
 *
 * @author victor
 */
public class TestP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        
        //Loot
        Loot l1 = new Loot(1,2,3,4,5);
        System.out.println(l1.toString());
        
        //ShieldBooster
        ShieldBooster s1 = new ShieldBooster("hola",8,-5);
        System.out.println(s1.toString() + " useit: " + s1.useIt());
        
        
        //SuppliesPackage 
        SuppliesPackage p1 = new SuppliesPackage(1,2,3);
        System.out.println(p1.toString());
        SuppliesPackage p2 = new SuppliesPackage(p1);
        System.out.println(p2.toString());
        
        //Weapon
        Weapon w1 = new Weapon("arma1", WeaponType.LASER, 20);
        System.out.println(w1.toString());
        System.out.println(w1.useIt());
        
        //Dice
        Dice d1 = new Dice();
        
        int hangars = 0;
        int weapons[] = {0,0,0};
        int shields = 0;
        
        for(int i = 1; i <= 10000; i++) {
            hangars += d1.initWithHangars();
            shields += d1.initWithNShields();
            weapons[d1.initWithNWeapons()-1]++;                
        }
        
        System.out.println("Hangars: " + hangars + ", shields: " + shields +
                "Weapons(1,2,3): " + weapons[0] + " " + weapons[1] + " " + weapons[2]);    
        
        System.out.println("Empieza " + d1.whoStarts(5) + " firstshot: " + d1.firstShot()
                + " moves: " + d1.spaceStationMoves(10));  
            
    }
    
}
