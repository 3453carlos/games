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
public class SafeEnemyCardDeck extends CardDeck<EnemyStarShip> {
    public EnemyStarShip next(){
       return super.next();
    }
}
