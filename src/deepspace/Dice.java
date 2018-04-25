package deepspace;
import java.util.Random;
/**
 *
 * @author victor
 */
class Dice {
    private final float NHANGARSPROB ,NSHIELDSPROB, NWEAPONSPROB, FIRSTSHOTPROB;
    private Random generator;

    public Dice() {
        NHANGARSPROB=0.25f;
        NSHIELDSPROB=0.25f;
        NWEAPONSPROB=0.33f;
        FIRSTSHOTPROB=0.5f;
        generator = new Random();
    }
    
    int initWithHangars() {
       if(generator.nextFloat() < NHANGARSPROB)
           return 0;
       return 1;
    }
    
    int initWithNWeapons() {
        float aux = generator.nextFloat();
        if(aux < NWEAPONSPROB)
            return 1;
        else if (aux < NWEAPONSPROB*2)
            return 2;
        return 3;
    }
        
    int initWithNShields() {
       if(generator.nextFloat() < NSHIELDSPROB)
           return 0;
       return 1;
    }
    
    int whoStarts(int nPlayers) {
        return generator.nextInt(nPlayers);
    }
    
    GameCharacter firstShot() {
        if(generator.nextFloat() > FIRSTSHOTPROB)
           return GameCharacter.ENEMYSTARSHIP;
        else
            return GameCharacter.SPACESTATION;
    }
    
    boolean spaceStationMoves(float speed) {
        return generator.nextFloat() <= speed;
    }
    
    
    
    
    
}
