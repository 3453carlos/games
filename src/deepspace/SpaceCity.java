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
public class SpaceCity extends SpaceStation {
    private ArrayList<SpaceStation> collaborators = new ArrayList();
    private SpaceStation base;
    
   SpaceCity(SpaceStation base, ArrayList<SpaceStation> rest){    
    super(base);
    collaborators = rest; 
    this.base = base;
   }
   
    public ArrayList<SpaceStation> getCollaborators(){
        return collaborators;    
    }

    public float fire(){
        float fire = base.fire();
        int n = collaborators.size();
        for(int i = 0; i < n; ++i)
            fire += collaborators.get(i).fire();
        return fire;       
    }
    
    public float protection(){
        float protect = base.protection();
        int n = collaborators.size();
        for(int i = 0; i < n; ++i)
            protect += collaborators.get(i).protection();
        return protect;
    }

    public Transformation setLoot(Loot loot){
        super.setLoot(loot);
        return Transformation.NOTRANSFORM;
    }   
    
}
