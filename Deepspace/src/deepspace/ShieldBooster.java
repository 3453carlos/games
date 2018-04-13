package deepspace;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author victor
 */
class ShieldBooster {
    private String name;
    private float boost;
    private int uses;

    ShieldBooster(String name, float boost, int uses) {
        this.name = name;
        this.boost = boost;
        this.uses = uses;
    }
    
    ShieldBooster(ShieldBooster s) {
        name = s.name;
        boost = s.boost;
        uses = s.uses;
    }
    
    ShieldToUI getUIversion() {
        return new ShieldToUI(this);
    }

    public float getBoost() {
        return boost;
    }

    public int getUses() {
        return uses;
    }
    
    float useIt() {
        if(uses > 0) {
            uses--;
            return boost;
        } else
            return 1.0f;            
    }

    @Override
    public String toString() {
        return "ShieldBooster{" + "name=" + name + ", boost=" + boost + ", uses=" + uses + '}';
    }
    
    
    
    
}
