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
public class GameUniverse {
    private static int WIN = 10;
    private int currentStationIndex = 0;
    private int turns = 0;
    private Dice dice = new Dice();
    private GameStateController gameState = new GameStateController();
    private SpaceStation currentStation;
    private ArrayList<SpaceStation> spaceStations = new ArrayList();
    private EnemyStarShip currentEnemy;
    private boolean haveSpaceCity = false;
    
    public boolean haveAWinner() {
        return currentStation.getNMedals() >= WIN;
    }
    
    public void discardHangar() {
       if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
        currentStation.discardHangar();
    }
    
    public void discardWeapon(int i) {
       if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
        currentStation.discardWeapon(i);
    }
    
    public void discardShieldBooster(int i) {
       if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
        currentStation.discardShieldBooster(i);
    }
    
    public void discardShieldBoosterInHangar(int i) {
       if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
        currentStation.discardShieldBoosterInHangar(i);
    }
    
    public void discardWeaponInHangar(int i) {
       if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
        currentStation.discardWeaponInHangar(i);
    }
    
    public void mountWeapon(int i) {
       if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
        currentStation.mountWeapon(i);
    }
    
    public void mountShieldBooster(int i) {
       if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
        currentStation.mountShieldBooster(i);
    }
    
    
    public  void init(ArrayList<String> names){ 
        GameState state = gameState.getState();
        
        if(state == GameState.CANNOTPLAY){
           // create, ya esta creado creo
           CardDealer dealer = CardDealer.getInstance();
           
           for( int i = 0; i < names.size(); i++ ){
               SuppliesPackage supplies = dealer.nextSuppliesPackage();
               SpaceStation station = new SpaceStation(names.get(i),supplies);
               int nh = dice.initWithHangars();
               int nw = dice.initWithNWeapons();
               int ns = dice.initWithNShields();
               Loot l = new Loot(0, nw, ns, nh, 0);
               station.setLoot(l);
               spaceStations.add(station);
           }
           
           currentStationIndex = dice.whoStarts(names.size());
           currentStation = spaceStations.get(currentStationIndex);
           currentEnemy = dealer.nextEnemy();
           
           gameState.next(turns,spaceStations.size());
        } 
    }

    public boolean nextTurn(){ 
        GameState state = gameState.getState();
        
        if(state == GameState.AFTERCOMBAT){
            boolean stationState = currentStation.validState();
            
            if(stationState){
                currentStationIndex = (currentStationIndex + 1) % spaceStations.size();
                turns++;
                currentStation = spaceStations.get(currentStationIndex);
                currentStation.cleanUpMountedItems();
                CardDealer dealer = CardDealer.getInstance();
                currentEnemy = dealer.nextEnemy();
                gameState.next(turns, spaceStations.size());
                return true;
            } else
                return false;
        } else
            return false;
    }
    
    public CombatResult combat(){ 
        GameState state = gameState.getState();
        if(state == GameState.BEFORECOMBAT || state == GameState.INIT){
            return combat(currentStation,currentEnemy);
        } else
            return CombatResult.NOCOMBAT;
    }
    
    
    public CombatResult combat(SpaceStation station,EnemyStarShip enemy){ 
        GameState state = gameState.getState();
        boolean enemyWins;
        float fire;
        ShotResult result;
        CombatResult combatResult;        
        
        GameCharacter ch = dice.firstShot();

        if ( ch == GameCharacter.ENEMYSTARSHIP ) {
            fire = enemy.fire();
            result = station.receiveShot(fire);

            if( result == ShotResult.RESIST){
                fire = station.fire();
                result = enemy.receiveShot(fire);
                enemyWins = (result == ShotResult.RESIST);
            } else
                 enemyWins = true;
        } else {
            fire = station.fire();
            result = enemy.receiveShot(fire);
            enemyWins = (result == ShotResult.RESIST);
        }

     if(enemyWins) {
         float s = station.getSpeed();
         boolean moves = dice.spaceStationMoves(s);

         if (!moves) {
             Damage damage = enemy.getDamage();
             station.setPendingDamage(damage);
             combatResult = CombatResult.ENEMYWINS;
         } else {
             station.move();
             combatResult = CombatResult.STATIONESCAPES;
         }            
     } else {
         Loot aLoot = enemy.getLoot();
         Transformation t = station.setLoot(aLoot);
         if(t == Transformation.GETEFFICIENT) {
             makeStationEfficient();
             System.out.println("Me meto en eficiente");
             combatResult = CombatResult.STATIONWINSANDCONVERTS;
         } else if (t == Transformation.SPACECITY) {
             createSpaceCity(); 
             System.out.println("Me meto en city");
             combatResult = CombatResult.STATIONWINSANDCONVERTS;
         } else {
         combatResult = CombatResult.STATIONWINS;
         System.out.println("Me meto en stationwins");
        }
     }            

     gameState.next(turns,spaceStations.size());
     return combatResult;      
    }
    
    
    public GameState getState() {
        return gameState.getState();
    }
    
    public GameUniverseToUI getUIversion() {
        return new GameUniverseToUI(currentStation, currentEnemy);
    }
    
    private void makeStationEfficient() {
        if(dice.extraEfficiency())
            currentStation = new PowerEfficientSpaceStation(currentStation);
        else
            currentStation = new BetaPowerEfficientSpaceStation(currentStation);
        spaceStations.set(currentStationIndex, currentStation);
    }
    
    private void createSpaceCity(){
        if(!haveSpaceCity){
            currentStation = new SpaceCity(currentStation, spaceStations);
            spaceStations.set(currentStationIndex, currentStation);
            haveSpaceCity = true;
        }
    }

    
    
}
