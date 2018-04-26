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
public class BetaPowerEfficientSpaceStation extends PowerEfficientSpaceStation{
    private final float EXTRAEFFICIENCY;
    private Dice dice;
    
    BetaPowerEfficientSpaceStation(SpaceStation station) {
        super(station);
        EXTRAEFFICIENCY = 1.2f;
    }
    
    public float fire() {
        if (dice.extraEfficiency()) {
           return EXTRAEFFICIENCY*super.fire();
        } else {
           return super.fire();
        }
    }
    
}
