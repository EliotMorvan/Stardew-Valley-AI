/*
 * sprite d'une case de lightgrass
 */
package stardewvalleyautomaton.IHM.Sprites.Fond;

import stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite;
import stardewvalleyautomaton.IHM.Sprites.IHM_Sprite;

/**
 *
 * @author simonetma
 */
public class IHM_Sprite_LightGrass extends IHM_Sprite {
    
    private static final int NOMBRE_MODIFIEUR = 13;                             //nombre de modifieurs différents
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public IHM_Sprite_LightGrass() {
        super("TilesExt",NOMBRE_MODIFIEUR);
        this.gestionPositionSprites();
    }
    
    //INITIALISATION DES POSITIONS POSSIBLES DU SPRITES DANS LA SPRITESHEET ----
    private void gestionPositionSprites() {
        this.setPosition(0, 11, 0);
        this.setPosition(1, 14, 3);
        this.setPosition(2, 15, 1);
        this.setPosition(3, 16, 3);
        this.setPosition(4, 14, 2);
        this.setPosition(5, 14, 0);
        this.setPosition(6, 15, 3);
        this.setPosition(7, 13, 1);
        this.setPosition(8, 13, 3);
        this.setPosition(9, 15, 2);
        this.setPosition(10, 15, 0);
        this.setPosition(11, 13, 2);
        this.setPosition(12, 13, 0);
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
        return Enum_IHM_Sprite.IHM_LightGrass;
    }
}
