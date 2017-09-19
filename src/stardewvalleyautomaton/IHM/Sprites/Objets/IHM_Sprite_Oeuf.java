/*
 * sprite d'un oeuf
 */
package stardewvalleyautomaton.IHM.Sprites.Objets;

import stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite;
import stardewvalleyautomaton.IHM.Sprites.IHM_Sprite;

/**
 *
 * @author Matthieu
 */
public class IHM_Sprite_Oeuf extends IHM_Sprite {

    private static final int NOMBRE_MODIFIEUR = 1;                              //nombre de modifieurs différents
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public IHM_Sprite_Oeuf() {
        super("Objets", NOMBRE_MODIFIEUR);
        this.gestionPositionSprites();
    }
    
    //INITIALISATION DES POSITIONS POSSIBLES DU SPRITES DANS LA SPRITESHEET ----
    private void gestionPositionSprites() {
        this.setPosition(0, 7, 6);
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
        return Enum_IHM_Sprite.IHM_Oeuf;
    }    
}
