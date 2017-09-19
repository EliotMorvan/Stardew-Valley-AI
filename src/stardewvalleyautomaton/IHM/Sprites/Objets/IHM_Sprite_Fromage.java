/*
 * sprite d'une machine à fromage
 */
package stardewvalleyautomaton.IHM.Sprites.Objets;

import stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite;
import static stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite.IHM_Fromage;
import stardewvalleyautomaton.IHM.Sprites.IHM_Sprite;

/**
 *
 * @author Matthieu
 */
public class IHM_Sprite_Fromage extends IHM_Sprite {

    private static final int NOMBRE_MODIFIEUR = 2;                             //nombre de modifieurs différents
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public IHM_Sprite_Fromage() {
        super("Craftables", NOMBRE_MODIFIEUR);
        this.gestionPositionSprites();
    }
    
    //INITIALISATION DES POSITIONS POSSIBLES DU SPRITES DANS LA SPRITESHEET ----
    private void gestionPositionSprites() {
        this.setPosition(0, 5, 0);
        this.setPosition(1, 4, 0);
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
        return IHM_Fromage;
    }
    
}
