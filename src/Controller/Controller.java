/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import View.*;
import deepspace.*;
import java.util.ArrayList;
/**
 *
 * @author victor
 */

public class Controller {
    private GameUniverse model;
    private View view;
    
    public Controller(GameUniverse model, View view){
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }
    
   public void start(){       
//       NamesCapture namesCaptureDialog = new NamesCapture(this);
//       ArrayList<String> names = namesCaptureDialog.getNames();
       ArrayList<String> names = view.getNames();
       model.init(names);
       view.updateView();
       view.showView();        
   }
   
   public void finish(int i){
        if (view.confirmExitMessage())
             System.exit(i);
   }
   
   public SpaceStationToUI currentStation(){
      return model.getUIversion().getCurrentStation();
   }
   
   public EnemyToUI currentEnemy(){
      return model.getUIversion().getCurrentEnemy();
   }
    
   public GameState getState(){
       return model.getState();
   }
    
   public void mountCombatElements(ArrayList<Integer> v){
       int w = model.getUIversion().getCurrentStation().getHangar().getShieldBoosters().size();
       for(int i=v.size()-1; i>=0; i--){
           if(i < w )
              model.mountShieldBooster(i);
           else
              model.mountWeapon(i-w);
       }
       view.updateView();
   }    
   
   public void discardCombatElements(ArrayList<Integer> vh, ArrayList<Integer> vw, ArrayList<Integer> vs){       
      if(vh.size()!=0){ //El hangar, aunque lo descartemos, no está nulo, está vacío. Entonces el vh, el vector de posiciones en el hangar, llega vacío
       int w = model.getUIversion().getCurrentStation().getHangar().getShieldBoosters().size();
       for(int i=vh.size()-1; i>=0; i--){
           if(i < w )
              model.discardShieldBoosterInHangar(i);
           else
              model.discardWeaponInHangar(i-w);
       }
      }
       
       for(int i=vw.size()-1; i>=0; i--)
              model.discardWeapon(i);
       
       for(int i=vs.size()-1; i>=0; i--)
              model.discardShieldBooster(i);

       
       view.updateView();
       
   }
   
   public CombatResult combat(){
       CombatResult r = model.combat();
       view.updateView();
       return r;
   }
   public boolean haveAWinner(){
       return model.haveAWinner();
   } 
   
    public boolean nextTurn() {
        boolean result = model.nextTurn();
        view.updateView();
        return result;
    }
       
   public void discardHangar(){
       model.discardHangar();
       view.updateView();
   }
   
   public void resultadoCombate(CombatResult c){
       view.resultadoCombate(c);
   }
   
   public void pendingDamageMessage(){
       view.pendingDamageMessage();
   }
}
