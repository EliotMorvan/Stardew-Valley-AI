/*
 * sprite d'une poule
 */
package stardewvalleyautomaton.IHM.Sprites.Personnages;

import stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite;
import static stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite.IHM_Poule;

/**
 *
 * @author Matthieu
 */
public class IHM_Sprite_Poule extends IHM_Sprite_Personnage {
    
    private static final int NOMBRE_MODIFIEUR = 34;                             //nombre de modifieurs différents
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public IHM_Sprite_Poule() {
        super("Poule", NOMBRE_MODIFIEUR);
        this.gestionPositionSprites();
    }
    
    //INITIALISATION DES POSITIONS POSSIBLES DU SPRITES DANS LA SPRITESHEET ----
    private void gestionPositionSprites() {
        this.setPosition(0, 0, 0);
        this.setPosition(1, 0, 1);
        this.setPosition(2, 0, 2);
        this.setPosition(3, 0, 3);
        this.setPosition(10, 1, 0);
        this.setPosition(11, 1, 1);
        this.setPosition(12, 1, 2);
        this.setPosition(13, 1, 3);
        this.setPosition(20, 2, 0);
        this.setPosition(21, 2, 1);
        this.setPosition(22, 2, 2);
        this.setPosition(23, 2, 3);
        this.setPosition(30, 3, 0);
        this.setPosition(31, 3, 1);
        this.setPosition(32, 3, 2);
        this.setPosition(33, 3, 3);
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
        return IHM_Poule;
    }

    @Override
    public void setPositionAnimation(int position) {
        //un seul sprite pour la poule donc position=modifieur
        this.setModifieur(position);
    }
    
}
