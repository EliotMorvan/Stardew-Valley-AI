/*
 * sprite d'une case de dirt
 */
package stardewvalleyautomaton.IHM.Sprites.Fond;

import stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite;
import stardewvalleyautomaton.IHM.Sprites.IHM_Sprite;

/**
 *
 * @author simonetma
 */
public class IHM_Sprite_Dirt extends IHM_Sprite {
    
    private static final int NOMBRE_MODIFIEUR = 24;                             //nombre de modifieurs différents
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public IHM_Sprite_Dirt() {
        super("TilesExt",NOMBRE_MODIFIEUR);
        this.gestionPositionSprites();
    }
    
    //INITIALISATION DES POSITIONS POSSIBLES DU SPRITES DANS LA SPRITESHEET ----
    private void gestionPositionSprites() {
        this.setPosition(0, 9, 1);
        this.setPosition(1, 8, 0);
        this.setPosition(2, 7, 5);
        this.setPosition(3, 8, 3);
        this.setPosition(4, 9, 0);
        this.setPosition(5, 9, 3);
        this.setPosition(6, 10, 0);
        this.setPosition(7, 10, 1);
        this.setPosition(8, 10, 3);
        this.setPosition(9, 8, 2);
        this.setPosition(10, 7, 2);
        this.setPosition(11, 10, 2);
        this.setPosition(12, 7, 3);
        this.setPosition(21, 8, 1);
        this.setPosition(22, 7, 4);
        this.setPosition(23, 8, 5);
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
        return Enum_IHM_Sprite.IHM_Dirt;
    }

}
