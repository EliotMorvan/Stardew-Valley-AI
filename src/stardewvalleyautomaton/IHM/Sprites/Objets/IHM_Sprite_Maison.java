/*
 * sprite d'une maison
 */
package stardewvalleyautomaton.IHM.Sprites.Objets;

import stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite;
import static stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite.IHM_Maison;
import stardewvalleyautomaton.IHM.Sprites.IHM_Sprite;

/**
 *
 * @author Matthieu
 */
public class IHM_Sprite_Maison extends IHM_Sprite {

    
    private static final int NOMBRE_MODIFIEUR = 77;                             //nombre de modifieurs différents
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public IHM_Sprite_Maison() {
        super("Maison",NOMBRE_MODIFIEUR);
        this.gestionPositionSprites();
    }
    
    //INITIALISATION DES POSITIONS POSSIBLES DU SPRITES DANS LA SPRITESHEET ----
    private void gestionPositionSprites() {
        this.setPosition(0,7,0);
        this.setPosition(1,7,1);
        this.setPosition(2,7,2);
        this.setPosition(3,7,3);
        this.setPosition(4,7,4);
        this.setPosition(5,7,5);
        this.setPosition(6,7,6);
        
        
        this.setPosition(10,6,0);
        this.setPosition(11,6,1);
        this.setPosition(12,6,2);
        this.setPosition(13,6,3);
        this.setPosition(14,6,4);
        this.setPosition(15,6,5);
        this.setPosition(16,6,6);
        
        
        this.setPosition(20,5,0);
        this.setPosition(21,5,1);
        this.setPosition(22,5,2);
        this.setPosition(23,5,3);
        this.setPosition(24,5,4);
        this.setPosition(25,5,5);
        this.setPosition(26,5,6);
       
        
        this.setPosition(30,4,0);
        this.setPosition(31,4,1);
        this.setPosition(32,4,2);
        this.setPosition(33,4,3);
        this.setPosition(34,4,4);
        this.setPosition(35,4,5);
        this.setPosition(36,4,6);
        
        
        this.setPosition(40,3,0);
        this.setPosition(41,3,1);
        this.setPosition(42,3,2);
        this.setPosition(43,3,3);
        this.setPosition(44,3,4);
        this.setPosition(45,3,5);
        this.setPosition(46,3,6);
       
        
        this.setPosition(50,2,0);
        this.setPosition(51,2,1);
        this.setPosition(52,2,2);
        this.setPosition(53,2,3);
        this.setPosition(54,2,4);
        this.setPosition(55,2,5);
        this.setPosition(56,2,6);
        
        
        this.setPosition(60,1,0);
        this.setPosition(61,1,1);
        this.setPosition(62,1,2);
        this.setPosition(63,1,3);
        this.setPosition(64,1,4);
        this.setPosition(65,1,5);
        this.setPosition(66,1,6);
       
        
        this.setPosition(70,0,0);
        this.setPosition(71,0,1);
        this.setPosition(72,0,2);
        this.setPosition(73,0,3);
        this.setPosition(74,0,4);
        this.setPosition(75,0,5);
        this.setPosition(76,0,6);
    }
    
    @Override
    public int WidthSprite() {
        return 16;
    }

    @Override
    public int HeightSprite() {
        return 16;
    }

    @Override
    public Enum_IHM_Sprite getType() {
        return IHM_Maison;
    }
    
}
