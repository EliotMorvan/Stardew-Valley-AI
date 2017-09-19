/*
 * sprite d'un arbre
 */
package stardewvalleyautomaton.IHM.Sprites.Objets;

import stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite;
import stardewvalleyautomaton.IHM.Sprites.IHM_Sprite;

/**
 *
 * @author Matthieu
 */
public class IHM_Sprite_Arbre extends IHM_Sprite {

    private static final int NOMBRE_MODIFIEUR = 14;                             //nombre de modifieurs différents
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public IHM_Sprite_Arbre() {
        super("TilesExt", NOMBRE_MODIFIEUR);
        this.gestionPositionSprites();
    }
    
    //INITIALISATION DES POSITIONS POSSIBLES DU SPRITES DANS LA SPRITESHEET ----
    private void gestionPositionSprites() {
        this.setPosition(0, 5, 1);
        this.setPosition(1, 4, 1);
        this.setPosition(2, 3, 0);
        this.setPosition(3, 3, 1);
        this.setPosition(4, 3, 2);
        this.setPosition(5, 2, 0);
        this.setPosition(6, 2, 1);
        this.setPosition(7, 2, 2);
        this.setPosition(8, 1, 0);
        this.setPosition(9, 1, 1);
        this.setPosition(10, 1, 2);
        this.setPosition(11, 0, 0);
        this.setPosition(12, 0, 1);
        this.setPosition(13, 0, 2);
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
        return Enum_IHM_Sprite.IHM_Arbre;
    }    
}
