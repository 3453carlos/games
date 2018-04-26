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
public class PowerEfficientSpaceStation extends SpaceStation {
    private final float EFFICIENCYFACTOR;
    
    PowerEfficientSpaceStation (SpaceStation station) {
        super(station);
        EFFICIENCYFACTOR = 1.10f;
    } 
    
    @Override
    public float fire() {
        return super.fire()*EFFICIENCYFACTOR;
    }
    
    @Override
    public float protection() {
        return super.protection()*EFFICIENCYFACTOR;
    }
    
    @Override
    public Transformation setLoot(Loot loot){
        super.setLoot(loot);
        return Transformation.NOTRANSFORM;
    }
}
