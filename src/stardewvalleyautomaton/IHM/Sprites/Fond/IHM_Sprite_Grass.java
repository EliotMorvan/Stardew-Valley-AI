/*
 * sprite d'une case de grass
 */
package stardewvalleyautomaton.IHM.Sprites.Fond;

import stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite;
import stardewvalleyautomaton.IHM.Sprites.IHM_Sprite;

/**
 *
 * @author simonetma
 */
public class IHM_Sprite_Grass extends IHM_Sprite {

    private static final int NOMBRE_MODIFIEUR = 1;                              //nombre de modifieurs différents

    //CONSTRUCTEUR -------------------------------------------------------------
    public IHM_Sprite_Grass() {
        super("TilesExt", NOMBRE_MODIFIEUR);
        this.gestionPositionSprites();
    }

    //INITIALISATION DES POSITIONS POSSIBLES DU SPRITES DANS LA SPRITESHEET ----
    private void gestionPositionSprites() {
        this.setPosition(0, 14, 1);
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
        return Enum_IHM_Sprite.IHM_Grass;
    }
}
